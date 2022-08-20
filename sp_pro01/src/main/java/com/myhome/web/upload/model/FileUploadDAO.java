package com.myhome.web.upload.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAO {

//	private static final Logger logger = LoggerFactory.getLogger(FileUploadDTO.class);
	
	@Autowired
	private SqlSession session;
			
	public boolean insertData(FileUploadDTO data) {
		int res = session.insert("fileUploadMapper.insertData", data);
		
		return res == 1 ? true : false;
	}
	
	public List<FileUploadDTO> selectDatas(int bid) {
		List<FileUploadDTO> res = session.selectList("fileUploadMapper.selectDatas", bid);
		return res;
	}
	
}
