package board.service;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;
import board.model.EmpBoardStatisDTO;

public class EmpBoardService {
	
	public EmpBoardService() {
		
	}

	public int add(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		// 직접 시퀀스를 생성하여 DTO 에 설정하는 방법
//		int seq = dao.getNextSeq();
//		data.setId(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			dao.commit();
			dao.close();
			return data.getId();
		}
		dao.rollback();
		dao.close();
		return 0;
	}

	public EmpBoardDTO getData(int id) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		EmpBoardDTO data = dao.selectData(id);
		
		dao.close();
		return data;
	}

	public void incViewCnt(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		boolean result = false;
		EmpBoardStatisDTO statisData = dao.selectStatis(data);
		if(statisData == null) {
			result = dao.updateViewCnt(data);
			dao.insertStatis(data);
		} else {
			java.util.Date now = new java.util.Date();
			long timeDiff = now.getTime() - statisData.getLatestView().getTime();
			if(timeDiff / (1000 * 60 * 60 * 24) >= 7) {
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

	public void incLike(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		boolean result = dao.updateLike(data);
		if(result) {
			data.setLike(data.getLike() + 1);
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
	}

}
