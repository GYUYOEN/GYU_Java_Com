package com.myhome.web.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.comment.model.CommentDAO;
import com.myhome.web.comment.model.CommentDTO;
import com.myhome.web.common.util.Paging;


@Service
public class CommentService {

	@Autowired
	private CommentDAO dao;
	
	public boolean add(CommentDTO data) {
		boolean result = dao.insertData(data);
		return result;
	}

	public List<CommentDTO> getDatas(int bid) {
		List<CommentDTO> datas = dao.selectDatas(bid);
		return datas;
	}

	public CommentDTO getData(int id) {
		CommentDTO data = dao.selectData(id);
		return data;
	}

	public boolean remove(CommentDTO data) {
		boolean result = dao.deleteData(data);
		return result;
	}

	public boolean modify(CommentDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}

	@Transactional
	public Paging getPage(int page, int limit, int bid) {
		int totalRows = dao.getTotalRows(bid);
		
		Paging paging = new Paging(page, limit, totalRows);
		dao.selectPage(paging, bid);
		return paging;
	}

}
