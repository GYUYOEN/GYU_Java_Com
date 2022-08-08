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
import com.fasterxml.jackson.databind.json.JsonMapper;

import emps.model.EmpDTO;

@WebServlet("/ajax/test")
public class AjaxTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		
		/*
		PrintWriter out = response.getWriter();
		JsonFactory factory = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = factory.createGenerator(sw);
		jg.useDefaultPrettyPrinter();
		jg.writeStartObject();
		
		jg.writeFieldName("msg");
		jg.writeString("Hello");
		
		jg.writeFieldName("kor");
		jg.writeString("안녕하세요");
		
		jg.writeFieldName("x");
		jg.writeNumber(100);
		
		jg.writeEndObject();
		jg.close();
		
		System.out.println(sw.toString());
		out.print(sw.toString());
		out.flush();
		*/
		
		PrintWriter out = response.getWriter();
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
		om.writeValue(out, datas);
		
		
		response.setContentType("application/json; charset=utf-8");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
