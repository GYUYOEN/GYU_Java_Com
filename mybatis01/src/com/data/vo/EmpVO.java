package com.data.vo;
// 객체를 다룰 클래스
public class EmpVO {
	// 가급적이면 멤버변수명을 컬럼명과 동일하게 지어주기
	// 멤버변수명을 마음대로 지어버리면 에러 -> select에 별칭 부여 (EMPLOYEE_ID AS empId)
	
	// private int employee_id;
	// private String first_name;
	// private String last_name;
	
	private int empId;
	private String fName;
	private String lName;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	
}
