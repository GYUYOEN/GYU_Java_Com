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
public class MyinfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/login/myinfo.jsp";
	
	private DeptService deptService = new DeptService();
	private EmpService empService = new EmpService();
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
			
			// 지금 상황에서만 사용하는 것이므로 꼭 알아야하는 것은 아님(데이터베이스에 저장된 것이 아님)
			// 로그인을 한 사원의 미미지가 /static/img/emp/사원ID.png 가 있는지 확인 후
			// 없으면 default.png 를 사용하는 것으로 하고 있으면 사원ID.png 를 사용하는 것으로 만든다.
			
			// server 상에 실제 경로 지정해 주어야 함 -> c드라이브가 있음
			String realPath = request.getServletContext().getRealPath("/static/img/emp/"); 
			
			// 파일 입출력
			File file = new File(realPath + empData.getEmpId() + ".png");
			
			if(file.exists()) { // 파일이 존재할 경우
				request.setAttribute("imagePath", "/static/img/emp/" + empData.getEmpId() + ".png"); // url 경로
			} else { // 파일이 존재하지 않을 경우
				// /static/img/emp/default.png 은 실제 경로가 아님
				request.setAttribute("imagePath", "/static/img/emp/default.png");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
//		String imgFileName = part.getSubmittedFileName(); // part.getSubmittedFileName() : 이미지 파일명
//		long imgSize = part.getSize(); // part.getSize() : 이미지 사이즈(byte 단위)
		
		String email = request.getParameter("email"); // EmpDTO
		String phone = request.getParameter("phone");
		
//		if(session.getAttribute("loginData") == null) {
//			response.sendRedirect(request.getContextPath() + "/login");
//			return;
//		}
		
		int empId = empData.getEmpId();
		EmpDTO updateEmpData = new EmpDTO();
		updateEmpData.setEmpId(empId);
		updateEmpData.setEmail(email);
		
		EmpDetailDTO updateEmpDetailData = new EmpDetailDTO();
		updateEmpDetailData.setEmpId(empId);
		updateEmpDetailData.setPhone(phone);
		
		boolean result = empService.setEmployee(updateEmpData, updateEmpDetailData);
		
		if(result) {
			// 수정 작업 성공
			Part part = request.getPart("uploadImage");
			
			if(!part.getSubmittedFileName().isEmpty()) {
				String realPath = request.getServletContext().getRealPath("/static/img/emp/");
				part.write(realPath + empData.getEmpId() + ".png");
			}
			
			response.sendRedirect(request.getContextPath() + "/logout");
			session.invalidate();
		} else {
			doGet(request, response);
		}
	}

}
