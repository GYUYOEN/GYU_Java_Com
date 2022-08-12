package com.myhome.web.emp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.emp.model.EmpDAO;
import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.emp.model.EmpDetailDTO;

@Service
public class EmpService {
	
	@Autowired
	private EmpDAO dao;
	
	public boolean add(EmpDTO empData, EmpDetailDTO empDetailData) {
		boolean res1 = dao.insertEmployee(empData);
		boolean res2 = dao.updateEmployeeDetail(empDetailData);
		
		if(res1 && res2) {
			return true;
		} else {
			return false;
		}
	}
	
	public EmpDTO getId(String empId) {
		EmpDTO data = dao.selectId(Integer.parseInt(empId));
		return data;
	}

	public List<EmpDTO> getEmpAll() {
		List<EmpDTO> datas = dao.selectAll();
		return datas;
	}

	public List<EmpDTO> getEmpPage(HttpSession session, int page, int count) {
		EmpDTO loginUser = (EmpDTO)session.getAttribute("loginData");
		
		List<EmpDTO> datas = dao.selectPage(loginUser, (page - 1) * count, count);
		return datas;
	}

	public List<Integer> getPageList(HttpSession session, int pageCount) {
		EmpDTO loginUser = (EmpDTO)session.getAttribute("loginData");
		
		List<Integer> pageList = new ArrayList<Integer>();
		
		int total = dao.totalRow(loginUser);
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {
			pageList.add(num + 1);
		}
		return pageList;
	}

	public EmpDetailDTO getDetail(int empId) {
		EmpDetailDTO data = dao.selectDetail(empId);
		return data;
	}

	public boolean setEmployee(EmpDTO updateEmpData, EmpDetailDTO updateEmpDetailData) {
		String email = updateEmpData.getEmail();
		if(email.contains("@emp.com")) {
			email = email.replace("@emp.com", "");
			updateEmpData.setEmail(email);
		}
		
		boolean res1 = dao.updateEmployee(updateEmpData);
		boolean res2 = dao.updateEmployeeDetail(updateEmpDetailData);
		
		if(res1 && res2) {
			return true;
		}
		return false;
	}
	
	public String getProfileImage(HttpServletRequest request, String imagePath, EmpDTO data) {
		String realPath = request.getServletContext().getRealPath(imagePath);
		File file = new File(realPath + data.getEmpId() + ".png");
		
		if(file.exists()) {
			return imagePath + data.getEmpId() + ".png";
		} else {
			return imagePath + "default.png";
		}
	}
	
	public String getProfileImage(HttpServletRequest request, String imagePath, int id) {
		EmpDTO data = new EmpDTO();
		data.setEmpId(id);
		return getProfileImage(request, imagePath, data);
	}
	
	public String setProfileImage(HttpServletRequest request, String param, String imagePath, EmpDTO data) throws IOException, ServletException {
		Part part = request.getPart(param);
		
		if(!part.getSubmittedFileName().isEmpty()) {
			String realPath = request.getServletContext().getRealPath(imagePath);
			part.write(realPath + data.getEmpId() + ".png");
			return imagePath + data.getEmpId() + ".png";
		} else {
			return imagePath + "default.png";
		}
	}
	
	public String setProfileImage(HttpServletRequest request, String param, String imagePath, int id) throws IOException, ServletException {
		EmpDTO data = new EmpDTO();
		data.setEmpId(id);
		return setProfileImage(request, param, imagePath, data);
	}

	public boolean removeId(String id) {
		boolean result = dao.deleteId(Integer.parseInt(id));

		return result;
	}

}
