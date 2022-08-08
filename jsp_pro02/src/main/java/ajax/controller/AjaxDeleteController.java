package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emps.service.EmpService;

@WebServlet("/ajax/delete")
public class AjaxDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		
		boolean res = false;
		switch(type) {
			case "emp":
				EmpService empService = new EmpService();
				res = empService.removeId(id);
				break;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		PrintWriter out = response.getWriter();
		if(res) {
			sb.append(String.format("\"%s\": \"%s\",", "type", "success"));
			sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 성공"));
			sb.append(String.format("\"%s\": \"%s\" ", "message", "요청한 직원의 정보가 삭제되었습니다."));
		} else {
			sb.append(String.format("\"%s\": \"%s\",", "type", "fail"));
			sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 오류"));
			sb.append(String.format("\"%s\": \"%s\" ", "message", "요청한 직원의 정보를 삭제하는중 문제가 발생하였습니다."));
		}
		sb.append("}");
		out.print(sb.toString());
		out.flush();
	}

}
