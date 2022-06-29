package jsp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp_request")
public class JspRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
		
		
		String view = "/WEB-INF/jsp/jsp_request.jsp";
		
		// servlet에서 Request 메서드 사용하여 확인 해보기
		System.out.println("param_name : " + request.getParameter("param_name"));
		
//		System.out.println("param_chk : " + Arrays.asList(request.getParameterValues("param_chk")));
		if(request.getParameterValues("param_chk") != null) {
			System.out.println("param_chk : " + Arrays.asList(request.getParameterValues("param_chk")));
		}
		
		Iterator<String> iter = request.getParameterNames().asIterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 일일이 작성해주기 귀찮음으로 web.xml 에서 filter 태그를 이용해서 filter servlet을 만드는 작업해줌
//		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("username")); // 한글이 잘 출력되는지 확인-> 안되면 setCharacterEncoding 사용하여 제대로 출력되게 함
//		response.getWriter().append("한글"); 
		
		String view = "/WEB-INF/jsp/jsp_request.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
