package util.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Parameter;

@WebServlet("/set/theme")
public class ThemeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theme = Parameter.getDefaultOrValue(request, "name", "default");
		
		Cookie cookie = new Cookie("theme", theme);
		cookie.setMaxAge(60 * 60 * 24 * 5);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"next\": \"" + request.getAttribute("next") + "\"");
		sb.append("}");
		
		response.getWriter().print(sb.toString());
		response.getWriter().flush();
	}
	
}
