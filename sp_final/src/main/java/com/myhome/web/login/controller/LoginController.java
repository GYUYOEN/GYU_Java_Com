package com.myhome.web.login.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myhome.web.login.model.EmpDTO;
import com.myhome.web.login.service.LoginService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService service;
	
	@GetMapping(value="/login")
	public String login(Model model) {
		return "login/login";
	}
	
//	@PostMapping(value="/login")
//	public String login(Model model, HttpSession session,
//			String empId, String empPw) {
////		loginVo.setEmpId(empId);
////		loginVo.setEmpPw(empPw);
//		
//		boolean result = service.login(empId, empPw);
//		
//		if(result) {
//			return "home";
//		}
//		return "login/login";
//	}
	
	@GetMapping(value="/signup") 
	public String signup(Model model) {
		return "login/signup";
	}
	
	@PostMapping(value="/signup")
	public String signup(Model model, EmpDTO empDto,
			String empId, String empNm, String empPw, String empCheckPw, String empEmail, String empAssistEmail) {
		empDto.setEmpId(empId);
		empDto.setEmpNm(empNm);
		empDto.setEmpPw(empPw);
		empDto.setEmpCheckPw(empCheckPw);
		empDto.setEmpEmail(empEmail);
		empDto.setEmpAssistEmail(empAssistEmail);
		
		service.signup(empDto);
		
		return "login/signup";
	}
	
	@GetMapping(value="/admin")
	public String admin(Model model) {
		return "admin";
	}
	
	@GetMapping(value="/login/fail") 
	public String loginFail(Model model) {
		return "/login/login_fail";
	}
}
