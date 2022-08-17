package com.myhome.web.upload.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.upload.model.UploadFilesDTO;

@Repository
public class UploadFilesDAO {

	private static final Logger logger = LoggerFactory.getLogger(UploadFilesDTO.class);
	
	@Autowired
	private SqlSession session;
			
	public boolean insertData(UploadFilesDTO data) {
		logger.info("insertData(UploadFilesDTO={})", data);
		
		int result = session.insert("fileMapper.insertData", data);
		
		return result == 1 ? true : false;
	}

	public List<UploadFilesDTO> selectDatas(int bid) {
		List<UploadFilesDTO> datas =  session.selectList("fileMapper.selectDatas", bid);
		return datas;
	}

}
