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

@WebServlet("/image/upload")
@MultipartConfig
public class CKEditorImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		// SmartEditor도 많이 씀
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData"); // loginData를 가져옴

		Part part = request.getPart("upload");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(!part.getSubmittedFileName().isEmpty()) { // 실제 업로드된 이미지가 있는지 확인
			String realPath = request.getServletContext().getRealPath("/static/img/board/"); // 실제 경로에서 찾기
			part.write(realPath + part.getSubmittedFileName()); // 저장 경로 - 파일이 실제로 저장될 경로. part.getSubmittedFileName() : 이미지 이름이 저절로 나옴

			// CKEditor 응답, 요청 형식에 맞춰줘야함
			sb.append(String.format("\"%s\": %d, ", "uploaded", 1));
			sb.append(String.format("\"%s\": \"%s\", ", "fileName", part.getSubmittedFileName())); // 같은 사진을 업로드해도 알아서 이름을 자동으로 바꿔주기 때문에 중복을 피해줌
			sb.append(String.format("\"%s\": \"%s\"  ", "url", "/static/img/board/" + part.getSubmittedFileName()));
		}
		sb.append("}");
		
		out.append(sb.toString());
		out.flush();
	}

}
