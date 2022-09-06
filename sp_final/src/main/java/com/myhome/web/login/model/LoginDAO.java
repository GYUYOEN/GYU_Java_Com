package com.myhome.web.login.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {
	
	@Autowired
	private SqlSession session;

	public boolean updateSignup(LoginDTO loginDto) {
		int result = session.insert("loginMapper.insertSignup", loginDto);
		System.out.println(result);
		
		return result == 1 ? true : false;
	}

	public LoginVO selectEmployee(LoginVO loginVo) {
		LoginVO result = session.selectOne("loginMapper.selectEmployee", loginVo);
		System.out.println(result);
		return result;
	}
	
}
