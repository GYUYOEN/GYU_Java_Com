package dept.controller;

import java.io.IOException;
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

@WebServlet("/depts/mod")
public class DeptModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService service = new DeptService();
	
//	@Override // 권한을 확인하고 권한에 따라 doGET과 doPost 진행
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		PermDTO perm = ((Map<String, PermDTO>)session.getAttribute("permData")).get("departments");
//		perm.ispAdd();
//		perm.ispDelete();
//		perm.ispRead();
//		perm.ispUpdate();
//		
//		if(perm.ispRead()) {
//			super.service(req, resp); // req.getMethod가 뭐냐에 따라 doGET or doPOST 로 이동
//		} else {
//			// resp.sendError(403);
//			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
//		}
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DeptDTO data = service.getId(id);
		
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/dept/mod.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
//		service.modifyDept(deptId, deptName, mngId, locId);
		
		// AddController 에서는 이작업을 service 함
		DeptDTO data = new DeptDTO();
		data.setDeptId(Integer.parseInt(deptId));
		data.setDeptName(deptName);
		data.setMngId(Integer.parseInt(mngId));
		data.setLocId(Integer.parseInt(locId));
		
		int result = service.modifyDept(data);
		
		String view = "/WEB-INF/jsp/dept/mod.jsp";
		request.setAttribute("data", data);
		
		// 이렇게 switch, case 문을 쓰면 error 메세지 한번에 출력 안됨
		switch(result) {
			case 1:
				response.sendRedirect(request.getContextPath() + "/depts?search=" + data.getDeptId());
				return;
			case 0:
				request.setAttribute("errorCode", "error");
				request.setAttribute("errorMsg", "수정 작업 중 알 수 없는 문제가 발생하였습니다.");
				break;
			case -1:
				request.setAttribute("errorCode", "mngId");
				request.setAttribute("errorMsg", "관리자가 존재하지 않습니다.");
				break;
			case -2:
				request.setAttribute("errorCode", "locId");
				request.setAttribute("errorMsg", "해당 지역이 존재하지 않습니다.");
				break;
		}
		request.getRequestDispatcher(view).forward(request, response);
	}

}
