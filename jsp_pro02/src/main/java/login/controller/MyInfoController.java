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
	
	private EmpService empService = new EmpService();
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = (String)request.getAttribute("view");
		HttpSession session = request.getSession();
		
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		EmpDetailDTO empDetail = empService.getDetail(empData.getEmpId());
		List<DeptDTO> deptDatas = deptService.getAll();
		List<JobDTO> jobDatas = jobService.getAll();
		
		request.setAttribute("empDetail", empDetail);
		request.setAttribute("deptDatas", deptDatas);
		request.setAttribute("jobDatas", jobDatas);
		
		// 로그인을 한 사원의 이미지 /static/img/emp/사원ID.png 가 있는지 확인 후
		// 없으면 default.png 를 사용하는 것으로 하고 있으면 사원ID.png 를 사용하는 것으로 만든다.
		String imagePath = empService.getProfileImage(request, "/static/img/emp/", empData);
		request.setAttribute("imagePath", imagePath);
		
		RequestDispatcher rd = request.getRequestDispatcher(view + "login/myinfo.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		String email = request.getParameter("email"); // EmpDTO
		String phone = request.getParameter("phone"); // EmpDetailDTO
		
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
