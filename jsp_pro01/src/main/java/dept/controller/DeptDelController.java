package dept.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts/del")
public class DeptDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	// 조회 작업
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DeptDTO data = service.getId(id);
		request.setAttribute("data", data);
		
		// 데이터가 있는지 없는지 controller에서 판단 -> 서로 다른 페이지로 이동
		String view ="/WEB-INF/jsp/dept/del.jsp"; // 삭제할건지, 안할건지에 대한 확인 페이지
		if(data == null) {
			view ="/WEB-INF/jsp/dept/del_no.jsp";
		}
		
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	// 삭제 작업
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		int result = service.deleteDept(deptId);
		
		String view ="/WEB-INF/jsp/dept/del_erorr.jsp";
		
		switch(result) {
			case 1:
//				response.sendRedirect("/jsp01/depts");
				response.sendRedirect(request.getContextPath() + "/depts");
				return; // 밑에 더 수행할 코드가 있으면 break;, return : 메서드를 끝냄
			case 0:
				request.setAttribute("error", true);
				request.setAttribute("erorrMsg", "삭제 처리 중 문제발생");
				break;
			case -1:
				request.setAttribute("error", true);
				request.setAttribute("erorrMsg", "삭제할 데이터가 존재하지 않습니다.");
				break;
		}
		request.getRequestDispatcher(view).forward(request, response);
	}

}
