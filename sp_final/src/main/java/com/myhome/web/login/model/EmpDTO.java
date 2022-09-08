package com.myhome.web.login.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmpDTO implements UserDetails {
	private String empId;
	private String empNm;
	private String empPw;
	private String empCheckPw;
	private String empEmail;
	private String empAssistEmail;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public String getEmpPw() {
		return empPw;
	}
	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}
	public String getEmpCheckPw() {
		return empCheckPw;
	}
	public void setEmpCheckPw(String empCheckPw) {
		this.empCheckPw = empCheckPw;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpAssistEmail() {
		return empAssistEmail;
	}
	public void setEmpAssistEmail(String empAssistEmail) {
		this.empAssistEmail = empAssistEmail;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [empId=" + empId + ", empNm=" + empNm + ", empPw=" + empPw + ", empCheckPw=" + empCheckPw
				+ ", empEmail=" + empEmail + ", empAssistEmail=" + empAssistEmail + "]";
	}
	
	// 계정이 갖고 있는 권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}
	
	// 계정의 패스워드를 리턴
	@Override
	public String getPassword() {
		return empPw;
	}
	
	// 계정의 이름을 리턴
	@Override
	public String getUsername() {
		return empId;
	}
	
	// 계정이 만료되지 않았는지를 리턴(true : 만료되지 않음)
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	
	// 계정이 잠겨있지 않았는지를 리턴(true : 잠겨있지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	
	// 계정의 패스워드가 만료되지 않았는지를 리턴(true : 패스워드가 만료되지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// 계정이 갖고 있는 권한 목록을 리턴
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
