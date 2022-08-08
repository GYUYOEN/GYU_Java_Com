package login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;
import login.service.LoginService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/index.jsp";
	
	private DeptService deptService = new DeptService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		if(session.getAttribute("loginData") == null) {
			List<DeptDTO> deptList = deptService.getAll();
			request.setAttribute("deptList", deptList);
			rd = request.getRequestDispatcher(view);
		} else {
			rd = request.getRequestDispatcher("/WEB-INF/jsp/index2.jsp");
		}
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		String deptId = request.getParameter("deptId");
		String empName = request.getParameter("empName");
		
		String deptRe = request.getParameter("deptRe");
		
		LoginService service = new LoginService();
		boolean result = service.login(request.getSession(), empId, deptId, empName);
		
		if(result) {
			// 로그인 성공
			if(deptRe != null) {
				Cookie cookie = new Cookie("deptRe", String.valueOf(deptId));
				cookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("deptRe", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			// 로그인 실패
			List<DeptDTO> deptList = deptService.getAll();
			request.setAttribute("deptList", deptList);
			request.setAttribute("error", "로그인 정보를 다시 확인하세요!");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			rd.forward(request, response);
		}
		
	}

}
