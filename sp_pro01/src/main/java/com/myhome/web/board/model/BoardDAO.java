package com.myhome.web.board.model;

import java.util.*;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.common.util.Paging;

@Repository
public class BoardDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAO.class);
	
	@Autowired
	private SqlSession session;
	
	public List<BoardDTO> selectAll(){
		logger.info("selectAll()");
		List<BoardDTO> result = session.selectList("boardMapper.selectAll");
		return result;
	}
	
	public int getTotalRows() {
		logger.info("getTotalRows()");
		int result = session.selectOne("boardMapper.getTotalRows");
		return result;
	}
	
	// spring이 자동 dao.close()를 하는데
	// Cursor는 반복작업이 끝날 때 까지 open인 상태를 유지해야함 -> 세션이 유지 되어야함
	public void selectPage(Paging paging) {
		logger.info("selectPage(paging={})", paging);
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("boardMapper.selectPage", null, rb);
		paging.setPageDatas(cursor.iterator());
	}

	public BoardDTO selectData(int id) {
		BoardDTO result = session.selectOne("boardMapper.selectData", id);
		return result;
	}
	
	public boolean insertData(BoardDTO data) {
		logger.info("insertData(data={})", data);
		int result = 0;
		if(data.getId() == 0) {
			result = session.insert("boardMapper.insertDataAutoSeq", data);			
		} else {
			result = session.insert("boardMapper.insertData", data);
		}
		return result == 1 ? true : false;
	}
	
	public boolean updateData(BoardDTO data) {
		logger.info("updateData(data={})", data);
		int result = session.update("boardMapper.updateData", data);
		return result == 1 ? true : false;
	}
	
	public boolean deleteData(BoardDTO data) {
		logger.info("deleteData(data={})", data);
		int result = session.delete("boardMapper.deleteData", data.getId());
		return result == 1 ? true : false;
	}
	
	public boolean deleteStatisData(BoardDTO data) {
		logger.info("deleteStatisData(data={})", data);
		int result = session.delete("boardMapper.deleteStatisData", data.getId());
		return result >= 0 ? true : false;
	}
	
	public boolean updateLike(BoardDTO data) {
		int result = session.update("boardMapper.updateLike", data);
		return result == 1 ? true : false;
	}
	
	public BoardStatisDTO selectStatis(BoardStatisDTO data) {
		BoardStatisDTO result = session.selectOne("boardMapper.selectStatis", data);
		return result;
	}
	
	public boolean updateStatis(BoardStatisDTO data) {
		int result = session.update("boardMapper.updateStatis", data);
		return result == 1 ? true : false;
	}
	
	public boolean updateStatis(BoardStatisDTO data, String type) {
		if(type.equals("like")) {
			int result = session.update("boardMapper.updateLikeStatis", data);
			return result == 1 ? true : false;
		} else {
			return updateStatis(data);
		}
	}
	
	public boolean updateViewCnt(BoardDTO data) {
		int result = session.update("boardMapper.updateViewCnt", data);
		return result == 1 ? true : false;
	}
	
	public boolean insertStatis(BoardStatisDTO data) {
		int result = session.insert("boardMapper.insertStatis", data);
		return result == 1 ? true : false;
	}
	
	/*
	public int getNextSeq() {
		int result = session.selectOne("boardMapper.getNextSeq");
		return result;
	}
	
	public void selectPage(Paging paging, String search) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("boardMapper.selectPage", search, rb);
		paging.setPageDatas(cursor.iterator());
	}
	
	
	*/

}