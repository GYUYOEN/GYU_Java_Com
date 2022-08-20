package com.myhome.web.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.upload.controller.UploadFilesDAO;
import com.myhome.web.upload.model.UploadFilesDTO;

@Service
public class UploadFilesService {

	
	@Autowired
	private UploadFilesDAO dao;
	
	public boolean insertData(UploadFilesDTO data, MultipartFile file, String realPath, int id) {
		data.setBId(id);
		data.setFileSize(file.getSize());
		data.setFileName(file.getOriginalFilename());
		data.setLocation(realPath + "/file/board");
		data.setUrl(realPath);
		boolean result = dao.insertData(data);
		
		return result;
	}

	public List<UploadFilesDTO> selectDatas(int bid) {
		List<UploadFilesDTO> datas = dao.selectDatas(bid);
		return datas;
	}
	

}
