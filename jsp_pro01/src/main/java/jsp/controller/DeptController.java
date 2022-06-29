package jsp.controller;

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

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search"); // 무조건 문자열로 받아서 저장
//		System.out.println(search);
		
//		boolean isNumber = true;
//		for(int i = 0; i < search.length(); i++) {
//			char c = search.charAt(i);
//			if(!(c >= '0' && c <= '9')) {
//				isNumber = false;
//				break;
//			}
//		}
		
		List<DeptDTO> deptDatas = null;
		if(search == null) {
			deptDatas = service.getAll();
		} else {
			// 매개변수 타입에 따라
			
			// DeptDTO 로 보내기
//			DeptDTO dto = new DeptDTO();
//			dto.setDeptId(Integer.parseInt(search));
			
			// int 로 변환해서 보내기
//			DeptDTO data = service.getId(Integer.parseInt(search));
			
			// 정규표현식 -> 구글에 regexr 검색
			boolean isNumber = search.matches("\\d+"); // \d: 숫자 -> 이케이프 문자 -> \ 하나더 작성, + : 
			if(isNumber) { // 숫자인지 아닌지 판별
				// String 으로 보내기
				DeptDTO data = service.getId(search);
				if(data != null) {
					deptDatas = new ArrayList<DeptDTO>();
					deptDatas.add(data);
				}
			}
		}
		
//		List<DeptDTO> deptDatas = service.getAll();
		// System.out.println(deptDatas);
		
		request.setAttribute("deptDatas", deptDatas); // 설정한 값을 같이 request 할수 있음 
		// deptDatas : list -> Object 로 업캐스팅
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
