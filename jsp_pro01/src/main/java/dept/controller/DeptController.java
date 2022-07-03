package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

// 사용자가 전달한 데이터를 받아서 service에 넘겨주거나 서비에서 전달받은 데이터를 view 에다가 연결
@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search"); // 무조건 문자열로 받아서 저장 // jsp에서 사용자가 입력한 입력값 추출
//		System.out.println(search);
		
		// 사용자가 이력한 값이 숫자인지 아닌지 판별
//		boolean isNumber = true;
//		for(int i = 0; i < search.length(); i++) {
//			char c = search.charAt(i);
//			if(!(c >= '0' && c <= '9')) {
//				isNumber = false;
//				break;
//			}
//		}
		
		List<DeptDTO> deptDatas = null;
		if(search == null) { // search 가 없으면 전체 검색
			int page = 1;
			if(request.getParameter("page") == null) {
				deptDatas = service.getPage(page);				
			} else if(request.getParameter("page").isEmpty()) {
				deptDatas = service.getPage(page);
			} else {
				if(request.getParameter("page").matches("\\d+")) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				deptDatas = service.getPage(page);
			}
			// page 목록을 가져옴
			request.setAttribute("pageList", service.getPageList());
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
