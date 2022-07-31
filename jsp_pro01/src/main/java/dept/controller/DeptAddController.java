package dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;
import login.model.PermDTO;

@WebServlet("/depts/add") // form에 action에 해당
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService service = new DeptService();

	
	// 사용자가 추가 버튼과 같은 기능을 클릭 했을 때 동작 할 메서드
	// HTTP Request 가 GET 요청인 경우 처리하는 메서드
	// 주로 사용자가 입력 양식을 요청하는 경우 사용됨
	// 조회할 때 (양식 재공)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String view = "/WEB-INF/jsp/dept/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	// 사용자가 입력 양식에 입력한 데이터를 처리하기 위한 메서드
	// HTTP Request 가 POST 요청인 경우 처리하는 메서드
	// 주오 사용자가 입력한 양식 데이터를 처리하는 경우 사용됨
	// 추가, 수정, 삭제 할때 (데이터 처리)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/dept/add.jsp";
		
		// jsp -> controller (메시지 안의 입력 정보를 추출) : getParameter()
		// controller -> jsp : setAttribute()
		String deptId = request.getParameter("deptId"); // input 태그의 name 속성값 작성
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		// service에서 객체로 변환 (modController와 다른 방법)
		DeptDTO data = service.addDept(deptId, deptName, mngId, locId); // 전달받은 데이터를 그대로 service 에 전달
		request.setAttribute("data", data);
		
		if(data != null) {
			if(data.getDeptId() != -1 && data.getMngId() != -1 && data.getLocId() != -1) {
				// 저장 성공 후 리다이렉트를 사용하여 페이지를 이동하게 함
				response.sendRedirect(request.getContextPath() + "/depts?search=" + data.getDeptId());
				return;
			} else {
				Map<String, String> error = new HashMap<String, String>();
				
				if(data.getDeptId() == -1) {
					error.put("deptId", "동일한 부서 ID가 존재합니다.");
				}
				if(data.getMngId() == -1) {
					error.put("mngId", "관리자ID 정보가 존재하지 않습니다.");
				}
				if(data.getLocId() == -1) {
					error.put("locId", "지역ID 정보가 존재하지 않습니다.");
				}
				request.setAttribute("error", error);
				request.getRequestDispatcher(view).forward(request, response);
			}
//			if(data.getDeptId() == -1) { // 아이디가 중복이됨
//				request.setAttribute("error", data);
//				request.setAttribute("errorMsg", "부서코드 중복!");
//				request.getRequestDispatcher(view).forward(request, response);
//			} else if(data.getMngId() == -1) {
//				request.setAttribute("error", data);
//				request.setAttribute("errorMsg", "해당 관리자 ID 없음");
//				request.getRequestDispatcher(view).forward(request, response);
//			} else if(data.getLocId() == -1) {
//				request.setAttribute("error", data);
//				request.setAttribute("errorMsg", "해당 지역 Id 없음");
//				request.getRequestDispatcher(view).forward(request, response);
//			} else {
//				// 저장 성공 후 리다이렉트를 사용하여 페이지를 이동하게 함
////				response.sendRedirect("/jsp01/depts"); // 추가된 전체 목록 조회
////				response.sendRedirect("/jsp01/depts?search=" + data.getDeptId()); // 추가된 목록만 조회
//				// "/"사용하면 절대경로, request.getContextPath() 사용하면 상대경로(알아서 servlet context 정보를 알아서 가져옴)
//				response.sendRedirect(request.getContextPath() + "/depts?search=" + data.getDeptId()); // 브라우저(클라이언트)에 재요청
//				// context root 설정을 "/"로 쓰면 url 주소 localhost/depts?search=19.. <- 이런식으로만 작성 가능(단, 하나의 프로젝트에 대해서만 가능(여러개의 프로젝트에 대해서는 불가능))
//			}
		} else {
			// 저장 실패 기존 페이지를 유지하면서 사용자가 입력했던 데이터도 최대한 보존 -> redirect 사용하면 보존 할 수 없음
			// 회원가입에 id 중복체크와 비슷
			request.getRequestDispatcher(view).forward(request, response); // 여기서 클라이언트쪽으로 request를 보냈음으로 클라이언트쪽에서도 request.getParameter() 사용가능
		}
		
		/*
		 * 사용자로부터 전달 받은 데이터를 service 에 전달하고
		 * service는 전달받은 데이터를 dao에 전달하고
		 * service에서는 필요한 경우 전달 받은 데이터에 대한 비지니스 로직을 수행 후 dao 에 전달 할 수 있다.
		 * dao 에서는 전달 받은 데이터를 처리하기에 적절한 SQL 구문을 찾아서 처리할 수 있게 한다. 
		 */
	}

}
