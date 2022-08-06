package dept.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts/add")
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService service = new DeptService();

	// 양식이 제공되고 doPost에 전송
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/dept/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	// 전송받은 데이터를 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		DeptDTO data = service.addDept(deptId, deptName, mngId, locId);
		
		String view = "/WEB-INF/jsp/dept/add.jsp";
		if(data != null) {
			if(data.getDeptId() != -1 && data.getMngId() != -1 && data.getLocId() != -1) { // data가 중복이 됨
				// 저장성공
//				response.sendRedirect("/jpr/depts"); // 전체 조회
				response.sendRedirect(request.getContextPath() + "/depts?search=" + data.getDeptId()); // 추가된 값만 조회
			} else {
				Map<String, String> error = new HashMap<String, String>();
				if(data.getDeptId() == -1) {
					error.put("deptId", "동일한 부서 ID가 존재합니다.");
				}
				if(data.getMngId() == -1) {
					error.put("mngId", "관리자ID 정보가 존재하지 않습니다.");
				}
				if(data.getLocId() == -1) {
					error.put("locId", "지약ID 정보가 존재하지 않습니다.");
				}
				request.setAttribute("error", error);
				request.getRequestDispatcher(view).forward(request, response);
			}
		} else {
			// 저장실패 후 기존 페이지를 유지하면서 사용자가 입력했던 데이터도 최대한 보존
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

}
