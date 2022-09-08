package com.myhome.web.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.EmpMapper;
import com.myhome.web.login.model.EmpDTO;

@Service("loginService")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class LoginService implements UserDetailsService{

	private final EmpMapper empMapper = null;

	@Transactional
	public void signup(EmpDTO empDto) {
		System.out.println(empDto);
		empMapper.saveUser(empDto);
	}

//	public boolean login(LoginVO loginVo, String empId, String empPw) {		
//		
//		loginVo = dao.selectEmployee(loginVo);
//		
//		if(loginVo == null) {
//			return false;			
//		} else {
//			return true;
//		}
//	}

	@Override
	public EmpDTO loadUserByUsername(String empId) throws UsernameNotFoundException {
		
		EmpDTO empDto = empMapper.getUserAccount(empId);
		
		if(empDto == null) {
			throw new UsernameNotFoundException("User not authorized");		
		}
		return empDto;
	}
	
}
