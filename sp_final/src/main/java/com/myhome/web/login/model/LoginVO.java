package com.myhome.web.login.model;

public class LoginVO {
	private String empId;
	private String empPw;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpPw() {
		return empPw;
	}
	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}
	
	@Override
	public String toString() {
		return "LoginVO [empId=" + empId + ", empPw=" + empPw + "]";
	}
}
