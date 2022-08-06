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
	private String view = ""; // 해당 controller에 경로는 거의 고정이므로 별도의 멤버변수 만들어서 저장
	private EmpService service = new EmpService();
	private Parameter param = new Parameter();
	
	// servlet은 별도의 명시를 하지 않아도 init() 을 거침(초기화 작업 -> 서버가 꺼지지 않는 이상 계속 유지)
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.view = "/WEB-INF/jsp/emp/index.jsp";
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = param.defaultSessionValue(request, "pageCount", "10"); // session을 이용해 초기값 설정
		
//		List<EmpDTO> datas = service.getEmpAll();
		List<EmpDTO> datas = service.getEmpPage(session, page, pageCount);
		List<Integer> pageList = service.getPageList(session, pageCount);
		
		request.setAttribute("datas", datas);
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}


}
