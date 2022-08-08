package dept.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.Parameter;
import dept.model.DeptDTO;
import dept.service.DeptService;
import login.model.PermDTO;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	private Parameter param = new Parameter();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = (String)request.getAttribute("view");
		HttpSession session = request.getSession();
		
		String search = request.getParameter("search");
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = 0;
		
		boolean pageCountCookieExist = false;
		
		if(session.getAttribute("pageCount") != null) {
			pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
			pageCountCookieExist = true;
		}
		
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		
		session.setAttribute("pageCount", pageCount);
		request.setAttribute("page", page);
		
		/*
		session.removeAttribute("pageCount"); // 세션에 설정한 pageCount 속성 제거
		session.setMaxInactiveInterval(60*60*24);
		session.invalidate(); // 세션을 만료 시켜 새로운 세션을 만들 수 있게 한다.
		request.getSession(true);	// 유효한 세션이 없는 경우 새로 만들고 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
		request.getSession(false);  // 유효한 세션이 없는 경우 null 반환, 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
		*/
		
		
		/*
		boolean pageCountCookieExist = false;
		Cookie[] cookies = request.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("pageCount")) {
				pageCount = Integer.parseInt(c.getValue());
				pageCountCookieExist = true;
			}
		}
		
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		Cookie cookie = new Cookie("pageCount", String.valueOf(pageCount));
		// 시간은 초단위 설정
		// -1 로 설정하면 무한:session, 0 으로 설정하면 즉시 만료
		cookie.setMaxAge(60*60*24*30*12);
		cookie.setPath("/depts"); // 쿠키 사용 경로 설정(하위 경로 포함)
		response.addCookie(cookie);
		*/
		
		List<DeptDTO> deptDatas = null;
		if(search == null) {
			deptDatas = service.getPage(page, pageCount);
			request.setAttribute("pageList", service.getPageList(pageCount));
		} else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) {
				DeptDTO data = service.getId(search);
				if(data != null) {
					deptDatas = new ArrayList<DeptDTO>();
					deptDatas.add(data);
				}
			}
		}
		
		request.setAttribute("deptDatas", deptDatas);
		
		RequestDispatcher rd = request.getRequestDispatcher(view + "dept/index.jsp");
		rd.forward(request, response);
	}

}
