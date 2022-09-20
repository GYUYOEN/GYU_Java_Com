package com.myhome.web.login.service;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myhome.web.login.model.EmpDTO;
import com.myhome.web.login.model.LoginVO;
import com.myhome.web.mapper.EmpMapper;

@Service("loginService")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class LoginService implements UserDetailsService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	public boolean signup(EmpDTO empDto) {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		empDto.setEmpPw(pwEncoder.encode(empDto.getEmpPw()));
		empDto.setEmpCheckPw(pwEncoder.encode(empDto.getEmpCheckPw()));
		int result = mapper.insertEmployee(empDto);
		
		return result == 1 ? true : false;
	}

	@Override
	public LoginVO loadUserByUsername(String empId) throws UsernameNotFoundException {	
		// user로 시작하면 사용자 권한을 부여
		// admin 으로 시작하면 관리자 권한을 부여
//		String sampleRole= "";
//		if(empId.startsWith("user")) {
//			sampleRole = "ROLE_USER";
//		} else if(empId.startsWith("admin")) {
//			sampleRole = "ROLE_USER";
//		}
		LoginVO loginVo = new LoginVO();
		
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		EmpDTO empDto = new EmpDTO();
		empDto.setEmpId(empId);
		
		empDto = mapper.selectEmployee(empDto);
		
		if(empDto == null) {
			return null;
		}
		loginVo.setUsername(empDto.getEmpId());
		loginVo.setPassword(empDto.getEmpPw());
		return new LoginVO(empDto);
	}
	
}
