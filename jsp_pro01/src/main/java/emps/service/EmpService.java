package emps.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import emps.model.EmpDAO;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;

public class EmpService {
	
	public boolean add(EmpDTO empData, EmpDetailDTO empDetailData) {
		EmpDAO dao = new EmpDAO();
		boolean res1 = dao.insertEmployee(empData);
		boolean res2 = dao.updateEmployeeDetail(empDetailData);
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		} else {
			dao.rollback();
			dao.close();
			return false;
		}
	}
	
	public static EmpDTO getId(String empId) {
		EmpDAO dao = new EmpDAO();
		EmpDTO data = dao.selectId(Integer.parseInt(empId));
		dao.close();
		return data;
	}
	
	public List<EmpDTO> getEmpAll() {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectAll();
		dao.close();
		return datas;
	}

	public List<EmpDTO> getEmpPage(HttpSession session, int page, int count) {
		EmpDAO dao = new EmpDAO();
		EmpDTO loginUser = (EmpDTO)session.getAttribute("loginData");
		
		List<EmpDTO> datas = dao.selectPage(loginUser, (page - 1) * count, count);
		dao.close();
		return datas;
	}

	public List<Integer> getPageList(HttpSession session, int pageCount) {
		EmpDAO dao = new EmpDAO();
		EmpDTO loginUser = (EmpDTO)session.getAttribute("loginData");
		List<Integer> pageList = new ArrayList<Integer>();
		
		int total = dao.totalRow(loginUser); // 전체 행수를 알아냄
		for(int num = 0; num <= (total - 1)/ pageCount; num++) {
			pageList.add(num + 1); // 1부터 시작
		}
		
		dao.close();
		return pageList;
	}

	public EmpDetailDTO getDetail(int empId) {
		EmpDAO dao = new EmpDAO();
		EmpDetailDTO data = dao.selectDetail(empId);
		dao.close();
		return data;
	}

	public boolean setEmployee(EmpDTO updateEmpData, EmpDetailDTO updateEmpDetailData) {
		EmpDAO dao = new EmpDAO();
		
		String email = updateEmpData.getEmail();
		if(email.contains("@emp.com")) {
			email = email.replace("@emp.com", "");
			updateEmpData.setEmail(email);
		}
		
		boolean res1 = dao.updateEmployee(updateEmpData);
		boolean res2 = dao.updateEmployeeDetail(updateEmpDetailData);
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}
	
	public String getProfileImage(HttpServletRequest request, String imagePath, EmpDTO data) {
		String realPath = request.getServletContext().getRealPath(imagePath); // 실제 경로에서 찾기
		File file = new File(realPath + data.getEmpId() + ".png"); // 파일 입출력
		
		// 메타 파일에 들어가서 찾아야 함(서버에서 요청하는 경로는 전부 이곳에 배포 -> 기본적으로 생각하는 workspace 가 아님)
		if(file.exists()) {
			return imagePath + data.getEmpId() + ".png"; // url로 찾기
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
		Part part = request.getPart(param); // multipart/form-data로 전송하는 데이터를 받아내기 위한 객체

		if(!part.getSubmittedFileName().isEmpty()) { // 실제 업로드된 이미지가 있는지 확인(이미지는 안바꾸고 정보만 바꾸는 경우가 있을 수 있으므로)
			String realPath = request.getServletContext().getRealPath(imagePath); // 실제 경로에서 찾기
			part.write(realPath + data.getEmpId() + ".png"); // 저장 경로 - 파일이 실제로 저장될 경로
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
		EmpDAO dao = new EmpDAO();
		boolean result = dao.deleteId(Integer.parseInt(id));
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		
		dao.close();
		return result;
	}
}
