package comment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.model.CommentDTO;
import comment.service.CommentService;
import emps.model.EmpDTO;

@WebServlet("/comment/add")
public class CommentAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentService service = new CommentService();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String bid = request.getParameter("bid"); // 어떤 게시글에 대한 댓글인지 구분 가능하게 하기 위해
		String content = request.getParameter("content"); // 입력한 댓글 가져오기
		
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		CommentDTO commentData = new CommentDTO();
		commentData.setbId(Integer.parseInt(bid));
		commentData.setEmpId(empData.getEmpId());
		commentData.setContent(content);
		
		boolean result = service.add(commentData);
		
		if(result) {
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + commentData.getbId());
		} else {
			request.getSession().setAttribute("error", "댓글 작성에 실패하였습니다.");
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + commentData.getId());
		}
	}

}
