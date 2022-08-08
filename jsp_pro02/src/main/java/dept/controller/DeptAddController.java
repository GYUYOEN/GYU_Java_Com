package dept.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;
import login.model.PermDTO;

@WebServlet("/depts/add")
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = (String)request.getAttribute("view");
		RequestDispatcher rd = request.getRequestDispatcher(view + "dept/add.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = (String)request.getAttribute("view");
		
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		DeptDTO data = service.addDept(deptId, deptName, mngId, locId);
		request.setAttribute("data", data);
		
		RequestDispatcher rd = null;
		
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
				rd = request.getRequestDispatcher(view + "dept/add.jsp");
			}
		} else {
			// 저장 실패 후 기존 페이지를 유지하면서 사용자가 입력했던 데이터도 최대한 보존
			rd = request.getRequestDispatcher(view + "dept/add.jsp");
		}
		rd.forward(request, response);
		
	}

}
