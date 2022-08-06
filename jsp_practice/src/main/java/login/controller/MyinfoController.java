package login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;
import job.model.JobDTO;
import job.service.JobService;

@WebServlet("/myinfo")
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
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email"); // EmpDTO
		String phone = request.getParameter("phone"); // EmpDetailDTO
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		int empId = ((EmpDTO)session.getAttribute("loginData")).getEmpId();
		
		EmpDTO updateEmpData = new EmpDTO();
		updateEmpData.setEmpId(empId);
		updateEmpData.setEmail(email);
		
		EmpDetailDTO updateEmpDetailData = new EmpDetailDTO();
		updateEmpDetailData.setEmpId(empId);
		updateEmpDetailData.setPhone(phone);
		
		boolean result = empService.setEmployee(updateEmpData, updateEmpDetailData);
		
		if(result) {
			// 수정 완료
			response.sendRedirect(request.getContextPath() + "/logout");
			session.invalidate();
		} else {
			// 수정 실패
			doGet(request, response);
		}
	}

}
