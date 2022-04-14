package exam03;

public class Subject_C {
	// 과목 정보를 가지는 객체
	private String name; 
	// 과목에는 Grade 를 포함시킨다.
	private Grade_C grade;
	
	public Subject_C(String name) {
		this.name = name;
	}
	
	public Subject_C(String name, double point) {
		this.name = name;
		this.grade = new Grade_C(point);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Grade_C getGrade() {
		return grade;
	}

	public void setGrade(Grade_C grade) {
		this.grade = grade;
	}
	
}