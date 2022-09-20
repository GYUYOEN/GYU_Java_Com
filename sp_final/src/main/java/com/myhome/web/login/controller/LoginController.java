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
import com.myhome.web.login.model.LoginVO;
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
	
	@PostMapping(value="/login")
	public String login(Model model, HttpSession session, LoginVO loginVo,
			String empId, String empPw) {
		
		
//		loginVo = service.loadUserByUsername(empId);
//		System.out.println(loginVo.getUsername());
//		System.out.println(loginVo.getPassword());
//		if(loginVo.getUsername().equals(empId) && loginVo.getPassword().matches(empPw)) {
//			return "home";
//		}
		return "login/login";
	}
	
	@GetMapping(value="/signup") 
	public String signup(Model model) {
		return "login/signup";
	}
	
	@PostMapping(value="/signup")
	public String signup(Model model, EmpDTO empDto,
			String empId, String empNm, String empPw, String empCheckPw, String empEmail, String empAssistEmail) {
		
		System.out.println(empId);
		System.out.println(empNm);
		System.out.println(empPw);
		System.out.println(empCheckPw);
		System.out.println(empEmail);
		System.out.println(empAssistEmail);
		
		empDto.setEmpId(empId);
		empDto.setEmpNm(empNm);
		empDto.setEmpPw(empPw);
		empDto.setEmpCheckPw(empCheckPw);
		empDto.setEmpEmail(empEmail);
		empDto.setEmpAssistEmail(empAssistEmail);
		
		boolean result = service.signup(empDto);
		
		if(result) {
			return "redirect:/login";
		}
		return "login/signup";
	}
	
	@GetMapping(value="/admin")
	public String admin(Model model) {
		return "admin";
	}
	
	@GetMapping(value="/login/fail") 
	public String loginFail(Model model) {
		return "login/login_fail";
	}
}
