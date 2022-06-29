package com.data.vo;

public class EmpComplexVO {
	private int empId;
	private String empName;
	private String empPhone;
	private double empComPct;
	private int empSalary;
	// Vo 객체에 멤버 중 하나가 객체타입 -> resultMap 사용
	private DeptVO dept;
	private JobVO job;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public double getEmpComPct() {
		return empComPct;
	}
	public void setEmpComPct(double empComPct) {
		this.empComPct = empComPct;
	}
	public int getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}
	public DeptVO getDept() {
		return dept;
	}
	public void setDept(DeptVO dept) {
		this.dept = dept;
	}
	public JobVO getJob() {
		return job;
	}
	public void setJob(JobVO job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "EmpComplexVO [empId=" + empId + ", empName=" + empName + ", empPhone=" + empPhone + ", empComPct="
				+ empComPct + ", empSalary=" + empSalary + ", dept=" + dept + ", job=" + job + "]";
	}
}
