package emps.model;

import java.sql.Date;

public class EmpDetailDTO {
	private int empId;
	private String phone;
	private Date hireDate;
	private int salary;
	private double commission;
	private int mngId;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public void setEmpId(String  empId) {
		this.empId = Integer.parseInt(empId);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public void setHireDate(String hireDate) {
		if(hireDate != null) {
			if(hireDate.isEmpty()) {
				this.hireDate = new Date(new java.util.Date().getTime()); // 현재날짜 사용
			} else {
				this.hireDate = Date.valueOf(hireDate);
			}
		} else {
			this.hireDate = new Date(new java.util.Date().getTime()); // 현재날짜 사용
		}
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setSalary(String salary) {
		if(salary == null) salary = "0";
		this.salary = Integer.parseInt(salary);
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public void setCommission(String commission) {
		if(commission == null) commission = "0";
		this.commission = Double.parseDouble(commission);
	}
	public int getMngId() {
		return mngId;
	}
	public void setMngId(int mngId) {
		this.mngId = mngId;
	}
	
	@Override
	public String toString() {
		return "EmpDetailDTO [empId=" + empId + ", phone=" + phone + ", hireDate=" + hireDate + ", salary=" + salary
				+ ", commission=" + commission + ", mngId=" + mngId + "]";
	}
}
