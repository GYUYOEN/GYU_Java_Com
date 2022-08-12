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

@WebServlet("/ajax/duplicate")
public class AjaxDuplicateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService deptService = new DeptService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		
		String errCode = "\"code\": \"%s\"";
		String errMsg = "\"message\": \"%s\"";
		if(name.equals("deptId")) {
			DeptDTO data = deptService.getId(value);
			if(data != null) { // 중복 에러
				errCode = String.format(errCode, "error");
				errMsg = String.format(errMsg, "부서 ID가 중복되었습니다.");
			} else {
				errCode = String.format(errCode, "success");
				errMsg = String.format(errMsg, "사용 가능한 부서ID 입니다.");
			}
			PrintWriter out = response.getWriter();
			out.println("{");
			out.println(errCode + ",");
			out.println(errMsg);
			out.println("}");
		}
	}

}
