package emps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;

@WebServlet("/emps/detail")
public class EmpsDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/emps/detail.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id 파라메터 추출 후 변수 id 에 저장
		String id = request.getParameter("id");
		
		// 변수 id 에 저장된 값으로 직원 조회
		EmpService empService = new EmpService();
		EmpDTO data = empService.getId(id);
		EmpDetailDTO dataDetail = empService.getDetail(data.getEmpId());
		
		// 조회한 데이터를 JSP 에서 사용할 수 있게 request 객체의 속성으로 저장
		request.setAttribute("data", data);
		request.setAttribute("dataDetail", dataDetail);
		
		// 프로필 사진이 /static/img/emp/ 디렉터리에 직원ID 로 저장되어 있는 경우 프로필 사진의 경로를
		// request 객체의 속성으로 저장(단, 없으면 기본 사진이 사용되게 한다.)
		String imagePath = empService.getProfileImage(request, "/static/img/emp/", data);
		request.setAttribute("imagePath", imagePath);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
