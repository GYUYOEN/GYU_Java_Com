package com.myhome.web.boar.service;

import java.sql.SQLDataException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.board.controller.BoardController;
import com.myhome.web.board.model.BoardDAO;
import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.model.BoardStatisDTO;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;


@Service
public class BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	private BoardDAO dao;
	
	public List<BoardDTO> getAll() {
		List<BoardDTO> datas = dao.selectAll();
		return datas;
	}

	// @Transactional 선언한 메서드는 메서드가 끝날때까지는 세션을 유지 시켜줌 -> 끝나면 close 시켜줌
	// @Transactional 은 따로 xml에 설정을 해주어야 사용 가능
	@Transactional
	public Paging getPage(int page, int limit) {
		logger.info("getPage(page={}, limit={})", page, limit); 
		int totalRows = dao.getTotalRows();
		
		Paging paging = new Paging(page, limit, totalRows);
		dao.selectPage(paging);
		
		return paging;
	}
	
	public BoardDTO getData(int id) {
		logger.info("getData(id={})", id);
		BoardDTO data = dao.selectData(id);
		
		return data;
	}
	
	public int add(EmpDTO empDto, BoardVO data) {
		logger.info("add(EmpDto={}, data={})", empDto, data);
		BoardDTO boardDto = new BoardDTO();
		boardDto.setTitle(data.getTitle());
		boardDto.setContent(data.getContent());
		boardDto.setEmpId(empDto.getEmpId());
		
		boolean result = dao.insertData(boardDto);
		
		if(result) {
			return boardDto.getId();
			// spring 에서는 commit, rollback 안해도 됌
		}
		return 0;
	}
	
	public boolean modify(BoardDTO data) {
		logger.info("modify(data={})", data);
		boolean result = dao.updateData(data);
		return result;
	}
	
	@Transactional // 아래 과정이 한 개의 트랜섹션 -> deleteStatisData() 지우고 deleteData() 지움
	public boolean remove(BoardDTO data) {
		logger.info("remove(data={})", data);
		dao.deleteStatisData(data); // 통계 정보 (추천, 조회수) : 제약조건 때문에 먼저 지움
		boolean result = dao.deleteData(data);
		
		return result;
	}
	
	// SQLDataException 익셉션이 일어나면 rollback이 일어나도록 함
	// RuntimeException 말고 다른 익셉션들은 자동으로 rollback이 안일어남
	// SQLDataException은 던져줘야 함
	@Transactional(rollbackFor = SQLDataException.class)
	public void incLike(EmpDTO empDto, BoardDTO data) throws SQLDataException {		
		// 1. EMP_BOARDS_STATISTICS 테이블에서 추천 했던 기록을 찾는다.
		// 2. 찾은 기록에서 ISLIKE 컬럼의 값에 따라 다음의 작업을 진행한다.
		//     2-1. 찾은 기록에서 ISLIKE 컬럼의 값이 N 이면 Y로 변경 후
		//          EMP_BOARDS 에서 추천수 + 1 을 한다.
		//     2-2. 찾은 기록에서 ISLIKE 컬럼의 값이 Y 이면 N으로 변경 후
		//          EMP_BOARDS 에서 추천수 - 1 을 한다.
		
		logger.info("incLike(empDto={}, data={})", empDto, data);
		
		boolean result = false;
		BoardStatisDTO statisData = new BoardStatisDTO();
		statisData.setbId(data.getId());
		statisData.setEmpId(empDto.getEmpId());
		
		statisData = dao.selectStatis(statisData);
		
		if(statisData.isLike()) {
			// 추천을 했음 -> 추천수 - 1 / 추천안함(false)
			statisData.setLike(false);
			data.setLike(data.getLike() - 1);
		} else {
			// 추천을 안 했음 -> 추천수 + 1 / 추천함(true)
			statisData.setLike(true);
			data.setLike(data.getLike() + 1);
		}
		
		result = dao.updateStatis(statisData, "like"); // 통계 업데이트
		if(!result) {
			throw new SQLDataException("BoardService.incLike - 추천 통계 업데이트 중 문제가 발생");
		}
		result = dao.updateLike(data); // 게시글 업데이트
		if(!result) {
			throw new SQLDataException("BoardService.incLike - 추천 수 업데이트 중 문제가 발생");
		}
	}
	
	@Transactional
	public void incViewCnt(EmpDTO empDto, BoardDTO data) {
		logger.info("incViewCnt(empDto={}, data={})", empDto, data);
		
		boolean result = false;
		BoardStatisDTO statisData = new BoardStatisDTO();
		statisData.setbId(data.getId());
		statisData.setEmpId(empDto.getEmpId());
		
		statisData = dao.selectStatis(statisData); // 통계확인
		
		if(statisData == null) { // 통계가 없으면
			result = dao.updateViewCnt(data);
			if(!result) {
				throw new RuntimeException("BoardService.incViewCnt - 조회수 업데이트 직업 중 오류 발생");
			}
			statisData = new BoardStatisDTO();
			statisData.setbId(data.getId());
			statisData.setEmpId(empDto.getEmpId());
			
			result = dao.insertStatis(statisData); // 통계 추가
			if(!result) {
				throw new RuntimeException("BoardService.incViewCnt - 통계 정보 추가 직업 중 오류 발생");
			}
		} else { // 통계가 있으면
			java.util.Date now = new java.util.Date();
			long timeDiff = now.getTime() - statisData.getLatestView().getTime();
			if(timeDiff / (1000 * 60 * 60 * 24) >= 7) {
				result = dao.updateViewCnt(data);
				if(!result) {
					throw new RuntimeException("BoardService.incViewCnt - 조회수 업데이트 직업 중 오류 발생");
				}
				result = dao.updateStatis(statisData); // 통계 수정
				if(!result) {
					throw new RuntimeException("BoardService.incViewCnt - 통계 정보 업데이트 직업 중 오류 발생");
				}
			}
		}
		
		if(result) {
			data.setViewCnt(data.getViewCnt() + 1);
		}
		
		// Transaction 어노테이션에 있는 곳에 RuntimeException이 있으면 rollback을 자동으로 해줌
//		throw new RuntimeException("RuntimeException을 발생 시키면 롤백");
	}
	
	
	/*

	

	public Paging getPage(String page, String limit, String search) {
		int totalRows = dao.getTotalRows(search);
		
		Paging paging = new Paging(Integer.parseInt(page), Integer.parseInt(limit), totalRows);
		dao.selectPage(paging, search);
		
		return paging;
	}

	*/
}