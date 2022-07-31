package login.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;
import job.model.JobDTO;
import job.service.JobService;

@WebServlet("/myinfo")
@MultipartConfig
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/login/myinfo.jsp";
	
	private EmpService empService = new EmpService();
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
			
			EmpDetailDTO empDetail = empService.getDetail(empData.getEmpId());
			List<DeptDTO> deptDatas = deptService.getAll();
			List<JobDTO> jobDatas = jobService.getAll();
			
			request.setAttribute("empDetail", empDetail);
			request.setAttribute("deptDatas", deptDatas);
			request.setAttribute("jobDatas", jobDatas);
			
			// 로그인을 한 사원의 이미지 /static/img/emp/사원ID.png 가 있는지 확인 후
			// 없으면 default.png 를 사용하는 것으로 하고 있으면 사원ID.png를 사용하는 것으로 만든다.
			
			// 메타 파일에 들어가서 찾아야 함(서버에서 요청하는 경로는 전부 이곳에 배포 -> 기본적으로 생각하는 workspace 가 아님)
//			String realPath = request.getServletContext().getRealPath("/static/img/emp/"); // 실제 경로에서 찾기
//			
//			File file = new File(realPath + empData.getEmpId() + ".png"); // 파일 입출력
//			// 메타 파일에 들어가서 찾아야 함(서버에서 요청하는 경로는 전부 이곳에 배포 -> 기본적으로 생각하는 workspace 가 아님)
//			if(file.exists()) {
//				request.setAttribute("imagePath", "/static/img/emp/" + empData.getEmpId() + ".png"); // url로 찾기
//			} else {
//				request.setAttribute("imagePath",  "/static/img/emp/default.png");
//			}
			// 전부 service에서 작업하도록 해줌
			String imagePath= empService.getProfileImage(request, "/static/img/emp/", empData);
			request.setAttribute("imagePath", imagePath);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		String email = request.getParameter("email"); // EmpDTO 에 저장된 정보
		String phone = request.getParameter("phone"); // EmpDetailDTO 에 저장된 정보
		
		// 필터에 적용해서 더이상 필요 없음
		// session.getAttribute("loginData") : session 에서 loginData 가져옴 -> 로그인 되서 loginData가 있다는 가정 하에
//		if(session.getAttribute("loginData") == null) {
//			response.sendRedirect(request.getContextPath() + "/login");
//			return;
//		}
		
		int empId = ((EmpDTO)session.getAttribute("loginData")).getEmpId();
		EmpDTO updateEmpData = new EmpDTO();
		updateEmpData.setEmpId(empId);
		updateEmpData.setEmail(email);
		
		EmpDetailDTO updateEmpDetailData = new EmpDetailDTO();
		updateEmpDetailData.setEmpId(empId);
		updateEmpDetailData.setPhone(phone);
		
		boolean result = empService.setEmployee(updateEmpData, updateEmpDetailData);
	
		if(result) {
			// 수정 작업 성공
			// 파일 업로드 과정 
			Part part = request.getPart("uploadImage"); // multipart/form-data로 전송하는 데이터를 받아내기 위한 객체
			
//			String imgFileName = part.getSubmittedFileName(); // 이미지 파일명
//			long imgSize = part.getSize(); // 이미지 크기(byte 단위)
			
			// 업로드가 안되면 파일이름이 없음
			if(!part.getSubmittedFileName().isEmpty()) { // 실제 업로드된 이미지가 있는지 확인(이미지는 안바꾸고 정보만 바꾸는 경우가 있을 수 있으므로)
				String realPath = request.getServletContext().getRealPath("/static/img/emp/"); // 실제 경로에서 찾기
				part.write(realPath + empData.getEmpId() + ".png"); // 저장 경로 - 파일이 실제로 저장될 경로
			}
			
			// 
			response.sendRedirect(request.getContextPath() + "/logout"); // 로그아웃이 되었다는 정보를 가지는 페이지
			session.invalidate(); // 세션 초기화
		} else {
			doGet(request, response); // 로직이 별차이가 없으므로 doGet() 호출
		}
		
	}

}
