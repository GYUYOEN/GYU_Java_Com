package com.join.vo;

import java.sql.Date;

/*
 * 회원가입을 위한 사용자 정보를 담고 있는 객체로 활용
 * 멤버 변수에 대한 getter/setter 필요
 * VO(Value Object) : 값만 저장이 되는 object
 */
public class JoinVo {
	private String userid;		// 아이디				 20 byte (영문자)
	private String userpw;		// 패스워드			 20 byte
	private String username;	// 사용자의 실제 이름	 20 byte
	private char gender;		// M : 남자, F : 여자	 1 byte
	private int age;			// 나이
	private Date createDate;	// 회원가입일
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUserpw() {
		return userpw;
	}
	
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	// 문자열을 받으면면 정수로 바꿔줌
	public void setGender(String gender) {
		this.gender = gender.charAt(0);
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	// 문자열로 받음면 정수로 바꿔줌
	public void setAge(String age) {
		this.age = Integer.parseInt(age);
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
