package board.service;

import javax.servlet.http.HttpSession;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;
import board.model.EmpBoardStatisDTO;
import common.util.Paging;
import emps.model.EmpDTO;

public class EmpBoardService {

	public EmpBoardService() {
		
	}
	
	public int add(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		// 직접 시퀀스를 생성하여 DTO에 설정하는 방법
//		int seq = dao.getNextSeq();
//		data.setId(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			dao.commit();
			dao.close();
			return data.getId();
		} else {
			dao.rollback();
			dao.close();
			return 0;
		}
	}

	public EmpBoardDTO getData(int id) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		EmpBoardDTO data = dao.selectData(id);
		
		dao.close();
		return data;
	}

	public void incViewCnt(HttpSession session, EmpBoardDTO data) {
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		EmpBoardDAO dao = new EmpBoardDAO();
		
		boolean result = false;
		EmpBoardStatisDTO statisData = new EmpBoardStatisDTO();
		statisData.setbId(data.getId());
		statisData.setEmpId(empData.getEmpId());
		
		statisData = dao.selectStatis(statisData);
		
		if(statisData == null) {
			result = dao.updateViewCnt(data);
			statisData = new EmpBoardStatisDTO();
			statisData.setbId(data.getId());
			statisData.setEmpId(empData.getEmpId());
			
			dao.insertStatis(statisData);
		} else {
			java.util.Date now = new java.util.Date();
			long timeDiff = now.getTime() - statisData.getLatestView().getTime();
			if(timeDiff / (1000*60*60*24) >= 7) {
				result = dao.updateViewCnt(data);
				dao.updateStatis(statisData);
			}
		}
		
		if(result) {
			data.setViewCnt(data.getViewCnt() + 1);
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		
	}

	public void incLike(HttpSession session, EmpBoardDTO data) {
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		EmpBoardDAO dao = new EmpBoardDAO();
		
		// 1. EMP_BOARDS_STATISTICS 테이블에서 추천 했던 기록을 찾는다.
		// 2. 찾은 기록에서 ISLIKE 컬럼의 값에 따라 다음의 작업을 진행한다.
		//     2-1. 찾은 기록에서 ISLIKE 컬럼의 값이 N 이면 Y로 변경 후
		//          EMP_BOARDS 에서 추천수 + 1 을 한다.
		//     2-2. 찾은 기록에서 ISLIKE 컬럼의 값이 Y 이면 N으로 변경 후
		//          EMP_BOARDS 에서 추천수 - 1 을 한다.
		
		boolean result = false;
		EmpBoardStatisDTO statisData = new EmpBoardStatisDTO();
		statisData.setbId(data.getId());
		statisData.setEmpId(empData.getEmpId());
		
		statisData = dao.selectStatis(statisData);
		
		if(statisData.isLike()) {
			statisData.setLike(false);
			data.setLike(data.getLike() - 1);
		} else {
			statisData.setLike(true);
			data.setLike(data.getLike() + 1);
		}
		
		dao.updateStatis(statisData, "like");
		result = dao.updateLike(data);
		
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
	}

	public Paging getPage(String page, String limit) { // page : page 번호, limit : 한 페이지에 보여줄 최대 데이터 수
		EmpBoardDAO dao = new EmpBoardDAO();
		
		int totalRows = dao.getTotalRows(); // 전체 행수 -> 총 페이지 수 구하기 위함
		
		Paging paging = new Paging(Integer.parseInt(page), Integer.parseInt(limit), totalRows);
		dao.selectPage(paging);
		dao.close();
		
		return paging;
	}

	public Paging getPage(String page, String limit, String search) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		int totalRows = dao.getTotalRows(search);
		
		Paging paging = new Paging(Integer.parseInt(page), Integer.parseInt(limit), totalRows);
		dao.selectPage(paging, search);
		dao.close();
		
		return paging;
	}

	public boolean remove(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		dao.deleteStatisData(data);
		boolean result = dao.deleteData(data);
		
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}

	public boolean modify(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		boolean result = dao.updateData(data);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}
}
