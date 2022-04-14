package model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Teacher_C extends Account_C {
	private Date loginDate;	// 로그인 시간
	
	public Teacher_C(String name) {
		setName(name);
		setPassword("a1111");
	}
	
	public Teacher_C(String name, String password) {
		setName(name);
		setPassword(password);
	}

	public Date getLoginDate() {
		return loginDate;
	}
	
	public String getLoginDateFormat() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return sFormat.format(loginDate);
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Override
	public String resetPassword() {
		String prefix = "TCH_";
		String newPass = super.resetPassword();
		setPassword(prefix + newPass);
		return newPass;
	}
	
}
