package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import emps.model.EmpDTO;

@WebServlet("/ajax/imageUpload")
@MultipartConfig
public class AjaxImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData"); // loginData를 가져옴

		
		Part part = request.getPart("uploadImage");
		response.setContentType("application/json; charset=utf-8");
		
//		System.out.println(part.getSubmittedFileName()); // 업로드된 파일이 있는지 확인

		if(!part.getSubmittedFileName().isEmpty()) { // 실제 업로드된 이미지가 있는지 확인
			String realPath = request.getServletContext().getRealPath("/static/img/emp/"); // 실제 경로에서 찾기
			part.write(realPath + empData.getEmpId() + ".png"); // 저장 경로 - 파일이 실제로 저장될 경로
			// ajax를 이용한 전송 -> 클라이언트에 보냄
			// ajax는 따로 저장 버튼을 누르지 않아도 폴더에 저장이됨
			out.println("{");
			out.println("	\"msg\": \"저장 성공\",");
			out.println("	\"loc\": \"/static/img/emp/" + empData.getEmpId() + ".png\"");
			out.println("}");
		} else {
			out.println("{");
			out.println("	\"msg\": \"저장 실패\",");
			out.println("	\"loc\": \"/static/img/emp/default.png\"");
			out.println("}");
		}
		out.flush();
	}

}
