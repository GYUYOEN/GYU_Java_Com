package com.myhome.web.upload.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.upload.model.UploadFilesDTO;
import com.myhome.web.upload.service.UploadFilesService;

@Controller
@RequestMapping(value="/upload")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private UploadFilesService fileService = new UploadFilesService();
	
	@PostMapping(value="/image", produces="application/json; charset=utf-8")
	@ResponseBody
	public String image(HttpServletRequest request
			, @RequestParam("upload") MultipartFile[] files // MultipartFile[] = getPart : 파일 업로드 할 떄 사용 -> 배열
			, @RequestParam("type") String type ) throws Exception {
		logger.info("image(files={}, type={}", files, type);
		
		JSONObject json = new JSONObject();
		
		String realPath = request.getServletContext().getRealPath("/resources");
		for(MultipartFile file: files) {
//			System.out.println(realPath);
//			System.out.println("isEmpty() : " + file.isEmpty());
//			System.out.println("getName() : " + file.getName()); // 파라메터 이름
//			System.out.println("getOriginalFilename() : " + file.getOriginalFilename()); // 업로드한 파일명
//			System.out.println("getSize() : " + file.getSize() / 1000); // 그냥하면 byte 단위, 1000으로 나누면 Kbyte
		
			json.put("uploaded", 1);
			json.put("fileName", file.getOriginalFilename());
			json.put("url", request.getContextPath() +  "/static/img/board/" + file.getOriginalFilename()); // /spring/static/img/board/...
			file.transferTo(new File(realPath + "/img/board/" + file.getOriginalFilename())); // 저장할 위치
		}
		return json.toJSONString();
	}
	
	// 저장 조회 삭제 -> 이미지 말고 다른 파일들도 들어갈수 있도록 -> type="file"을 사용
	// 파일 저장
	/*
	@PostMapping(value="/file", produces="application/json; charset=utf-8")
	@ResponseBody
	public String file(HttpServletRequest request
			, @RequestParam("upload") MultipartFile[] files
			, @RequestParam("type") String type) {
		
		JSONObject json = new JSONObject();
		
		String realPath = request.getServletContext().getRealPath("/resources");
		for(MultipartFile file: files) {
			UploadFilesDTO data = new UploadFilesDTO();
			data.setFileSize(file.getSize());
			data.setFileName(file.getOriginalFilename());
			data.setLocation(realPath + "/file/board");
			data.setUrl(realPath);
			
			json.put("uploaded", 1);
			json.put("fileName", file.getOriginalFilename());
			json.put("url", request.getContextPath() + "/static/file/board/" + file.getOriginalFilename());
			file.transferTo(new File(realPath + "/file/board" + file.getOriginalFilename()));
			fileService.insertFile();
		}
		
	}
	*/
}
