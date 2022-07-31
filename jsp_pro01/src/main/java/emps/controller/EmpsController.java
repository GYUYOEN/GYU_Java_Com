package emps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.Parameter;
import emps.model.EmpDTO;
import emps.service.EmpService;


@WebServlet("/emps")
public class EmpsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view="";
	private EmpService service = new EmpService();
	private Parameter param = new Parameter();
	
	// servlet 은 별도로 명시하지 않아도 init을 거침(초기화) -> 서버가 꺼졋다 켜지지 않는 이상 계속 동작
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.view = "/WEB-INF/jsp/emps/index.jsp"; // 경로 고정
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = param.defaultSessionIntValue(request, "pageCount", "10");
		
//		List<EmpDTO> datas = service.getEmpAll();
		List<EmpDTO> datas = service.getEmpPage(session, page, pageCount); // session 안의 사용자 정보를 전달하기 위해 session 사용
		List<Integer> pageList = service.getPageList(session, pageCount); // 페이지 번호 반환

		request.setAttribute("datas", datas);
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
