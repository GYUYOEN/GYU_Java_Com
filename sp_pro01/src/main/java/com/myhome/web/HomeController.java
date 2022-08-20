package com.myhome.web;

import java.util.List;

//import java.text.DateFormat;
//import java.util.Date;
//import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;

// spring 은 아래(@Controller) annotation을 명시해 줘야 bean 객체 등록 가능
// DI (Dependency Injection) : 의존성 주입 -> 외부에서 객체 생성하여 주입
// IOC (Inversion Of Control) : 제어 역전 -> 객체의 호출을 외부에서 결정
@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class); // HomeController.class = com.myhome.web.HomeController

	// "/" : JSP에서 @WebServlet()의 ()안에 것과 동일
	// method = RequestMethid.GET = doGET(), doPOST()
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale); // 로그인 기능
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate ); // servlet -> jsp (RequestDispatcher, forword, request.setAttribute())
//		
//		return "home"; // view와 같음
//	}
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(Model model) {
//		logger.info("Welcome index!");
		
		List<DeptDTO> deptDatas = deptService.getAll();
		
		model.addAttribute("deptDatas", deptDatas);
		
		return "index";
	}
}
