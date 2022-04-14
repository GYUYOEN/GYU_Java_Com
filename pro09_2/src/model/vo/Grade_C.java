package model.vo;

import exception.GradeLevelException_C;
import exception.GradeScoreException_C;

// 과목에 대한 성적 정보를 가지는 클래스
public class Grade_C extends Subject_C {
	/*
	 * 점수(score)와 등급(level) 을 정의
	 * getter/setter 도 작성
	 */
	private double score;
	private char level;
	private char[] levelArr = new char[] {
			'F', 'F', 'F', 'F', 'E', 'E', 'D', 'C', 'B', 'A'
	};
	
	public Grade_C(String name) {
		super(name);
		this.level = 'F';
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) throws GradeScoreException_C {
		if(!(score >= 0 && score <= 100)) {
			throw new GradeScoreException_C("성적 점수의 범위는 0 ~ 100 사이의 값으로 설정하세요.");
		}
		this.score = score;
		this.level = levelArr[(int)(score / 10)];
	}
	
	public char getLevel() {
		return level;
	}
	
	public void setLevel(char level) {
		if(!(level >= 'A' && level <= 'F')) {
			throw new GradeLevelException_C("등급은 'A' ~ 'F' 까지만 설정할 수 있습니다.");
		}
		this.level = level;
	}

	@Override
	public String toString() {
		return "Grade [name=" + getName() + ", score=" + score + ", level=" + level + "]";
	}
	
}