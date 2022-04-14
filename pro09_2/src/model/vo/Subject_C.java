package model.vo;

//과목 정보를 가지는 클래스
public class Subject_C {
	/*
	 * 과목명(name) 과 getter/setter 만 정의
	 */
	private String name;
	
	public Subject_C(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}