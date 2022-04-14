package model.vo;

import java.util.Random;

//학생 정보를 가지는 클래스
public class Student_C extends Account_C {
	private Grade_C[] grades;
	
	public Student_C(String name) {
		setName(name);
		setPassword("a1111");
	}

	public Grade_C[] getGrades() {
		return grades;
	}

	public void setGrades(Grade_C[] grades) {
		this.grades = grades;
	}

	@Override
	public String resetPassword() {
		String prefix = "STD_";
		String newPass = super.resetPassword();
		setPassword(prefix + newPass);
		return newPass;
	}
	
}