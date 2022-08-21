package com.myhome.web.upload.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.upload.model.FileUploadDTO;
import com.myhome.web.upload.model.FileUploadDAO;

@Service
public class FileUploadService {

	@Autowired
	private FileUploadDAO dao;
	
	public List<FileUploadDTO> getDatas(int bid) {
		List<FileUploadDTO> datas = dao.selectDatas(bid);
		return datas;
	}
	
	private boolean _upload(MultipartFile file, FileUploadDTO data) throws Exception {
		File directory = new File(data.getLocation());
		if(!directory.exists()) { // 파일이 있는 지 확인("/resources/upload/board" 경로 확인)
			directory.mkdirs(); // 위 경로애 해당하는 파일/폴더가 없으면 만들어줌
		}
		
		if(file.getSize() / (1024 * 1024 * 5) > 1) {
			return false;
		}
		
		UUID uuid = UUID.nameUUIDFromBytes(file.getBytes()); // 중복을 해결하기 위한 작업
		
		data.setFileName(file.getOriginalFilename());
		data.setUuidName(uuid.toString());
		data.setFileSize(file.getSize());
		
		boolean res = dao.insertData(data); // DB 에 저장
		
		if(res) {
			// File.separatorChar : /, \ 알아서 구분 
			file.transferTo(new File(data.getLocation() + File.separatorChar + data.getUuidName())); // 실제 하드디스크에 저장
		
		}
		return res;
	}
	
	public int upload(MultipartFile file, FileUploadDTO data) throws Exception {
		boolean res = this._upload(file, data);
		return res ? 1 : 0;
	}
	
	public int upload(MultipartFile[] files, FileUploadDTO data) throws Exception {
		int count = 0;
		for(MultipartFile file : files) {
			boolean res = this._upload(file, data);
			count = res ? count + 1 : count;
		}
		return count;
	}
}
