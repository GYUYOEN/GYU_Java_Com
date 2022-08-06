package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import emps.model.EmpDTO;

@WebServlet("/ajax/test")
public class AjaxTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		
		PrintWriter out = response.getWriter();
		
		/* 기본 ajax 사용법
		// data 에 받음
		out.print("{");
		out.print("		\"msg\": \"Success\","); // 속성 : 값 형태 (객체형태)
		out.print("		\"kor\": \"한글테스트\"");
		out.print("}"); // 이 형식(json형식)에 맞춰 보내주면 success에서 data양식 받고 받아온 데이터를 이용하여 출력
		*/
		
		/* jackson을 이용한 ajax 사용법 (첫번째 방법)
		// Json 형식을 만들기 위한 준비과정
		JsonFactory factory = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = factory.createGenerator(sw); // 잭슨에 있는 기능 중 다음을 사용하면 직접 문자열로 만들 필요없음
				
		jg.useDefaultPrettyPrinter(); // 보기좋은 형태로 출력하기 위해 사용
		jg.writeStartObject(); // 객체를 쓰기 위한 준비 "{" 시작
			
		// 한객체를 만들어줌
		jg.writeFieldName("msg");
		jg.writeString("Hello");
				
		jg.writeFieldName("kor");
		jg.writeString("안녕하세요"); // FieldName 에 사용될 문자(데이터값)
				
		jg.writeFieldName("x");
		jg.writeNumber(100);
				
		jg.writeEndObject(); // "}" 끝
		jg.close();
				
		System.out.println(sw.toString());
		out.print(sw.toString()); // 위에서 만든 json 문자열 출력
		out.flush();
		*/
		
		
		// 두번째 방법
		ObjectMapper om = new ObjectMapper();
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		for(int i = 0; i < 5; i++) {
			EmpDTO data = new EmpDTO();
			data.setEmpId(100 + i);
			data.setEmpName("JSON");
			data.setEmail("JSON@emp.com");
			data.setDeptId(100);
			data.setDeptName("제이선부");
			data.setJobId("code");
			data.setJobName("JSON CODE");
			datas.add(data);
		}
		// 따로 출력없이 바로출력가능(PrintWriter으로 바로 출력하게 함)
		om.writeValue(out, datas); // out : PrintWriter out = response.getWriter(); 으로 바로 출력되게 만듬
	
		response.setContentType("application/json; charset=utf-8"); // 데이터 타입이 json 이므로 작성, 마지막에 작성
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
