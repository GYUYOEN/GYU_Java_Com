package com.data.vo;

import java.sql.Date;
import java.util.List;

public class EmpSelectVO {
	private int salary;
	private Date startDate;
	private Date endDate;
	private int deptId;
	private List<Integer> deptIdList;
//	private String strStartDate;
//	private String strEndDate;
	
//	public String getStrStartDate() {
//		return strStartDate;
//	}
//	public void setStrStartDate(String strStartDate) {
//		this.strStartDate = strStartDate;
//	}
//	public String getStrEndDate() {
//		return strEndDate;
//	}
//	public void setStrEndDate(String strEndDate) {
//		this.strEndDate = strEndDate;
//	}
	
	public List<Integer> getDeptIdList() {
		return deptIdList;
	}
	public void setDeptIdList(List<Integer> deptIdList) {
		this.deptIdList = deptIdList;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	
}
