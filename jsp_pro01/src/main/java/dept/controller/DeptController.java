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

import common.util.Parameter;
import dept.model.DeptDTO;
import dept.service.DeptService;

// 사용자가 전달한 데이터를 받아서 service에 넘겨주거나 서비에서 전달받은 데이터를 view 에다가 연결
@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	private Parameter param = new Parameter();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getParameter("search") : /depts?search=
		String search = request.getParameter("search"); // 무조건 문자열로 받아서 저장 // jsp에서 사용자가 입력한 입력값 추출
//		System.out.println(search);
		int pageCount = param.defaultIntValue(request, "pgc", "10");
//		String pageCount = this.defaultValue(request, "pgc", "10");
// 		String pageCount = request.getParameter("pageCount") == null ? "10" : request.getParameter("pageCount");
		int  page = param.defaultIntValue(request, "page", "1");
//		String page = this.defaultValue(request, "page", "1");
// 		String page = request.getParameter("page") == null ? "1" : request.getParameter("page");
//		page = page.isEmpty() ? "1" : page;
//		page = page.matches("\\d+") ? page : "1";
//		pageCount = pageCount.isEmpty() ? "10" : pageCount;
//		pageCount = page.matches("\\d+") ? pageCount : "10";
		
		
		// 사용자가 이력한 값이 숫자인지 아닌지 판별
//		boolean isNumber = true;
//		for(int i = 0; i < search.length(); i++) {
//			char c = search.charAt(i);
//			if(!(c >= '0' && c <= '9')) {
//				isNumber = false;
//				break;
//			}
//		}
		
		// pageCount 라는 이름에 쿠기 있는지 없는지 확인
		boolean pageCountCookieExist = false;
		Cookie[] cookies = request.getCookies(); // request(클라이언트(브라우저) 콘솔창으로 확인) 에서 가져옴. 배열로 되어 있음
		for(Cookie c: cookies) { // 쿠키가 있으면 기존값 활용
			if(c.getName().equals("pageCount")) {
				pageCount = Integer.parseInt(c.getValue());
				pageCountCookieExist = true;
			}
		}
		
		// 쿠키가 없거나 pgc 파라미터가 있을때
		if(request.getParameter("pgc") != null || !pageCountCookieExist) { // 쿠키가 없으면 기본값 설정하여 쿠키설정
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		// 쿠키 생성 후 전달
		Cookie cookie = new Cookie("pageCount", String.valueOf(pageCount)); // ("쿠키이름", "쿠키값")
		response.addCookie(cookie);
		
		List<DeptDTO> deptDatas = null;
		if(search == null) { // search 가 없으면 전체 검색
			deptDatas = service.getPage(page, pageCount);
			request.setAttribute("pageList", service.getPageList(pageCount));
//			if(request.getParameter("page") == null) { // null 일 때
//				deptDatas = service.getPage(page);				
//			} else if(request.getParameter("page").isEmpty()) { // 빈 문자열일 때
//				deptDatas = service.getPage(page); // page = 1
//			} else { // 값이 있을 때
//				if(request.getParameter("page").matches("\\d+")) { // 페이지가 숫자면
//					page = Integer.parseInt(request.getParameter("page"));
//				}
//				deptDatas = service.getPage(page); // page = 1
//			}
//			// page 목록을 가져옴
		} else { // search 가 있으면 id 검색
			// 매개변수 타입에 따라
			
			// DeptDTO 로 보내기
//			DeptDTO dto = new DeptDTO();
//			dto.setDeptId(Integer.parseInt(search));
//			DeptDTO data = service.getId(dto);
			
			// int 로 변환해서 보내기
//			DeptDTO data = service.getId(Integer.parseInt(search));
			
			// 사용자가 이력한 값이 숫자인지 아닌지 판별
			// 정규표현식 이용하여 숫자 판별 -> 구글에 regexr 검색
			boolean isNumber = search.matches("\\d+"); // \d: 숫자 -> 이케이프 문자 -> \ 하나더 작성, + : 1자리 이상
			if(isNumber) { // 숫자인지 아닌지 판별
				// String 으로 보내기
				DeptDTO data = service.getId(search);
				if(data != null) {
					deptDatas = new ArrayList<DeptDTO>();
					deptDatas.add(data);
				}
			}
			// 숫자가 아닐 경우 전체 조회를 하고 싶을 때 사용, 이 로직을 안쓰면 아무것도 조회 안됨으로 보여짐
//			else {
//				deptDatas = service.getAll();
//			}
		}
		
		// 전체 목록 확인 -> List 활용 List<부서객체정보>
//		List<DeptDTO> deptDatas = service.getAll();
		// System.out.println(deptDatas);
		
		// controller에서 조회한 데이터를 view에 전달 
		// request 객체에 속성 설정 -> forward 할때 같이 전달됨
		request.setAttribute("deptDatas", deptDatas); // 설정한 값을 같이 request 할수 있음 
		// setAttribute() 사용하면 deptDatas 가 list -> Object 로 업캐스팅 됨
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
