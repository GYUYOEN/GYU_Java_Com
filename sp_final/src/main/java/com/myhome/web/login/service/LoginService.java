package com.myhome.web.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.login.model.LoginDAO;
import com.myhome.web.login.model.LoginDTO;
import com.myhome.web.login.model.LoginVO;

@Service
public class LoginService {

	@Autowired
	private LoginDAO dao;

	public boolean signup(LoginDTO loginDto) {
		boolean result = dao.updateSignup(loginDto);
		return result;
	}

	public boolean login(LoginVO loginVo, String empId, String empPw) {		
		
		loginVo = dao.selectEmployee(loginVo);
		
		if(loginVo == null) {
			return false;			
		} else {
			return true;
		}
	}
	
}
