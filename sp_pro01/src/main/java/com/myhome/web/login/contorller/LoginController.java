package com.myhome.web.login.contorller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.web.HomeController;
import com.myhome.web.login.vo.LoginVO;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/*
	@RequestMapping(value="/login", method=RequestMethod.POST)
	// @RequestParam("empId") : jsp와 일치하지 않을 떄 ""에 jsp에 들어간 걸로 맞춰줌
	public String login(Model model
			, @RequestParam("id") int empId
			, @RequestParam("deptId") int deptId
			, @RequestParam("name") String empName) {
//	public String login(Model model, int empId, int deptId, String empName) { // 알아서 형변환 헤줌
//	public String login(Model model, HttpServletRequest res) {
		
//		logger.info("empId: {}", res.getParameter("empId"));
//		logger.info("deptId: {}", res.getParameter("deptId"));
//		logger.info("empName: {}", res.getParameter("empName"));
		
		logger.info("empId: {}", empId);
		logger.info("deptId: {}", deptId);
		logger.info("empName: {}", empName);
		
		return "";
	}
	*/
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, LoginVO loginVo) { // 알아서 LoginVO에 넣어줌. parameter 명과 일치해야함
		
		logger.info("empId: {}", loginVo.getEmpId());
		logger.info("deptId: {}", loginVo.getDeptId());
		logger.info("empName: {}", loginVo.getEmpName());
		
		return "";
	}
}
