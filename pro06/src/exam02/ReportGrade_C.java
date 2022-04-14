package exam02;

import java.util.Arrays;

public class ReportGrade_C {
	private String name;
	private String[] subjects;
	private double[] grades;
	
	public ReportGrade_C(String name) {
		this.name = name;
	}
	
	public ReportGrade_C(String name, String[] subjects) {
		this.name = name;
		this.subjects = subjects;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String[] getSubjects() {
		return subjects;
	}
	
	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}
	
	public double[] getGrades() {
		return grades;
	}
	
	public void setGrades(double[] grades) {
		this.grades = grades;
	}
	
	public void setGrades(String subject, double grade) {
		/*
		 * 과목 배열의 인덱스와 정수 배열의 인덱스는 서로 연관되어 있다.
		 * 과목 배열의 0번 인덱스의 값이 "국어" 이면 국어 과목에 대한 점수는
		 * 점수 배열의 0번 인덱스에 있다.
		 * 
		 * 과목 배열 : {"국어", "영어", "수학", "사회", "체육"}
		 * subjects = "수학";
		 */
		
		int idx = -1;
		for(int i = 0; i < this.subjects.length; i++) {
			if(this.subjects[i].equals(subject)) {
				idx = i;
				break;
			}
		}
		this.grades[idx] = grade;
	}
	
	public double getGrade(String subject) {
		int idx = -1;
		for(int i = 0; i < this.subjects.length; i++) {
			if(this.subjects[i].equals(subject)) {
				idx = i;
				break;
			}
		}

		return this.grades[idx];
	}
	
	// 동적배열을 사용하여 새로운 과목과 점수를 추가
	public void addSubject(String subject) {
		// 점수는 0 점으로 추가
		this.addSubject(subject, 0);
	}
	
	public void addSubject(String subject, double grade) {
		// 점수는 grade 값으로 추가
		this.subjects = Arrays.copyOf(this.subjects, this.subjects.length + 1);
		this.grades = Arrays.copyOf(this.grades, this.grades.length + 1);
		
		this.subjects[this.subjects.length - 1] = subject;
		this.grades[this.grades.length - 1] = grade;
	}
	
}