package emps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

@WebServlet("/emps/add")
public class EmpsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private String view = "/WEB-INF/jsp/";
	
	private EmpService empService = new EmpService();
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService();
	private String view = "/WEB-INF/jsp/emps/add.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<DeptDTO> deptDatas = deptService.getAll();
		List<JobDTO> jobDatas = jobService.getAll();
		
		request.setAttribute("deptDatas", deptDatas);
		request.setAttribute("jobDatas", jobDatas);
		
		String realPath = request.getServletContext().getRealPath("/static/img/emp/");
		request.setAttribute("imagePath", "/static/img/emp/default.png");
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String jobId = request.getParameter("jobId");
		String deptId = request.getParameter("deptId");
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hireDate");
		String phone = request.getParameter("phone");
		String salary = request.getParameter("salary");
		String commission = request.getParameter("commission");
		
		System.out.println(empId);
		System.out.println(empName);
		System.out.println(jobId);
		System.out.println(deptId);
		System.out.println(email);
		System.out.println(hireDate);
		System.out.println(phone);
		System.out.println(salary);
		System.out.println(commission);
		
		EmpDTO empData = new EmpDTO();
		empData.setEmpId(empId);
		empData.setEmpName(empName);
		empData.setJobId(jobId);
		empData.setDeptId(deptId);
		empData.setEmail(email);
		
		EmpDetailDTO empDetailData = new EmpDetailDTO();
		empDetailData.setEmpId(empId);
		empDetailData.setHireDate(hireDate);
		empDetailData.setPhone(phone);
		empDetailData.setSalary(salary);
		empDetailData.setCommission(commission);
		
		boolean result = empService.add(empData, empDetailData);
		
		if(result) {
			String imagePath = empService.setProfileImage(request, "uploadImage", "/static/img/emp/", empData);
			response.sendRedirect(request.getContextPath() + "/emps/detail?id=" + empData.getEmpId());
		} else {
			doGet(request, response);
		}
	}

}
