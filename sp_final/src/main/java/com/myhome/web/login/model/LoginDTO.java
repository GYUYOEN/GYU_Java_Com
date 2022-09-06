package com.myhome.web.login.model;

public class LoginDTO {
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
}
