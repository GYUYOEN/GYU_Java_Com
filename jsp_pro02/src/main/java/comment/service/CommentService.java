package comment.service;

import java.util.List;

import comment.model.CommentDAO;
import comment.model.CommentDTO;
import common.util.Paging;

public class CommentService {

	public boolean add(CommentDTO data) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.insertData(data);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}

	public List<CommentDTO> getDatas(int bid) {
		CommentDAO dao = new CommentDAO();
		List<CommentDTO> datas = dao.selectDatas(bid);
		dao.close();
		return datas;
	}

	public CommentDTO getData(int id) {
		CommentDAO dao = new CommentDAO();
		CommentDTO data = dao.selectData(id);
		dao.close();
		return data;
	}

	public boolean remove(CommentDTO data) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.deleteData(data);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}

	public boolean modify(CommentDTO data) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.updateData(data);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}

	public Paging getPage(String page, String limit, int bid) {
		CommentDAO dao = new CommentDAO();
		
		int totalRows = dao.getTotalRows(bid);
		
		Paging paging = new Paging(Integer.parseInt(page), Integer.parseInt(limit), totalRows);
		dao.selectPage(paging, bid);
		dao.close();
		return paging;
	}

}
