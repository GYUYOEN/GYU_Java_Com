package ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get/contextpath")
public class GetContextPathController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();

		StringBuilder sb = new StringBuilder("{");
		sb.append("\"contextpath\": \"" + contextPath + "\"");
		sb.append("}");
		
		response.getWriter().print(sb.toString());
		response.getWriter().flush();
	}

}
