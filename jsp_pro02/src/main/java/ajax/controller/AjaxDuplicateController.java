package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.service.EmpService;

@WebServlet("/ajax/duplicate")
public class AjaxDuplicateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService empService = new EmpService();
	private DeptService deptService = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		
		String errCode = "\"code\": \"%s\"";
		String errMsg = "\"message\": \"%s\"";
		if(name.equals("deptId") && !value.isEmpty()) {
			DeptDTO data = deptService.getId(value);
			if(data != null) {
				errCode = String.format(errCode, "error");
				errMsg = String.format(errMsg, "부서 ID가 중복되었습니다.");
			} else {
				errCode = String.format(errCode, "success");
				errMsg = String.format(errMsg, "사용 가능한 부서ID 입니다.");
			}
		} else if(name.equals("empId") && !value.isEmpty()) {
			EmpDTO data = empService.getId(value);
			if(data != null) {
				errCode = String.format(errCode, "error");
				errMsg = String.format(errMsg, "직원 ID가 중복되었습니다.");
			} else {
				errCode = String.format(errCode, "success");
				errMsg = String.format(errMsg, "사용 가능한 직원 ID 입니다.");
			}
		}
		PrintWriter out = response.getWriter();
		out.println("{");
		out.println(errCode + ",");
		out.println(errMsg);
		out.println("}");
		out.flush();
	}

}
