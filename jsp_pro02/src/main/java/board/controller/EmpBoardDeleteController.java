package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import emps.model.EmpDTO;

@WebServlet("/board/delete")
public class EmpBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(data.getEmpId() == empData.getEmpId()) {
			boolean result = service.remove(data);
			
			if(result) {
				sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 완료"));
				sb.append(String.format("\"%s\": \"%s\" ", "message", "요청한 게시글을 삭제하였습니다."));
			} else {
				sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 오류"));
				sb.append(String.format("\"%s\": \"%s\" ", "message", "요청한 게시글을 삭제하지 못하였습니다."));
			}
		} else {
			sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 오류"));
			sb.append(String.format("\"%s\": \"%s\" ", "message", "요청한 게시글을 삭제할 권한이 없습니다."));
		}
		sb.append("}");
		
		response.getWriter().append(sb.toString());
		response.getWriter().flush();
	}

}
