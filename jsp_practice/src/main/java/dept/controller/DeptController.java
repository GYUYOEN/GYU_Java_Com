package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService service = new DeptService();
	private Parameter param = new Parameter();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // 세션 정보 가져오기
		
		String search = request.getParameter("search");
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = 0;
		
		
		boolean pageCountSessionExist = false;
		// session.getAttribute("pageCount") : 세션에 저장된 정보 가져오기
		if(session.getAttribute("pageCount") != null) { // 저장된 세션 정보가 있으면 저장
			pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
			pageCountSessionExist = true;
		}
		
		if(request.getParameter("pgc") != null || !pageCountSessionExist) { // 파라미터를 이용해 변경 시켜줄 때 변경된 값 적용
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		
		session.setAttribute("pageCount", pageCount); // 세션설정
		request.setAttribute("page", page);
//		request.setAttribute("pageCount", pageCount); // 세션에 설정되어 있으므로 session 정보를 이용하는 방식으로
//		session.removeAttribute("pageCount"); // 세션에 설정한 pageCount 속성 제거
//		session.setMaxInactiveInterval(60*60*24);
//		session.invalidate(); // 세션을 만료 시켜 새로운 세션을 만들 수 있게 한다.
//		request.getSession(true); // 유효한 세션이 없는 경우 새로 만들고 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
//		request.getSession(false); // 유요한 세션이 없는 경우 null 반환, 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
		
		
		/* 쿠키를 이용
		// 쿠키 있는지 확인
		boolean pageCountCookieExist = false;
		Cookie[] cookies = request.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("pageCount")) {
				pageCount = Integer.parseInt(c.getValue());
				pageCountCookieExist = true;
			}
		}
		
		// 쿠기가 없거나 pgc 파라미터가 있을 때(파라미터를 이용해 변경 시켜줄 때 변경된 값 적용)
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pgc", "10"); // pgc가 null이면 기본값 10
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		// 쿠키 생성 후 전달
		Cookie cookie = new Cookie("pageCount", String.valueOf(pageCount)); // (쿠키이름, 쿠키값)
		cookie.setMaxAge(60*6*24*30*12); // 쿠키 만료일 지정(초단위(1년))
		cookie.setPath("/"); // 해당 페이지에서만 쿠키를 사용할 수 있도록 설정(하위 요소 포함(cookie.setPath("/depts"); 이면 /depts/add도 설정됨))
		response.addCookie(cookie);			
		*/
		
		List<DeptDTO> deptDatas = null;
		if(search == null) { // search 가 null 이면 전체 조회
			deptDatas = service.getPage(page, pageCount);
			request.setAttribute("pageList", service.getPageList(pageCount));
		} else { // null이 아니면 그 id로 검색
			boolean isNumber = search.matches("\\d+");
			if(isNumber) {
				DeptDTO data = service.getId(search); // 검색한 id에 관한 하나의 행정보를 가져오기 위해

				if(data != null) {
					deptDatas = new ArrayList<>();
					deptDatas.add(data);
				}
			}
		}
		request.setAttribute("deptDatas", deptDatas);

		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
