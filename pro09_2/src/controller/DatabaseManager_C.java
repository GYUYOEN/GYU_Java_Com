package controller;
import java.util.Arrays;

import model.vo.Grade_C;
import model.vo.Student_C;

public class DatabaseManager_C implements ImplDatabaseManager_C {
	// 학생 정보를 추가, 수정, 삭제할 수 있는 기능이 정의되어 있는 매니저 클래스
	
	private Student_C[] datas;
	
	// 초기화 블록
	{
		datas = new Student_C[5];
		datas[0] = new Student_C("홍길동");	datas[1] = new Student_C("김도원");
		datas[2] = new Student_C("박수아");	datas[3] = new Student_C("최종희");
		datas[4] = new Student_C("이정우");
		
		for(int i = 0; i < datas.length; i++) {
			datas[i].setGrades(new Grade_C[] {
					new Grade_C("국어"), new Grade_C("영어"), new Grade_C("수학"), new Grade_C("과학")
			});
		}
	}
	
	@Override
	public Grade_C[] search(String name) {
		int idx = _findIndex(name);
		if(idx == -1) {
			return null;
		}
		return datas[idx].getGrades();
	}

	@Override
	public boolean add(String name) {
		if(_isExisted(name)) {
			return false;
		}
		
		datas = Arrays.copyOf(datas, datas.length + 1);
		datas[datas.length - 1] = new Student_C(name);
		datas[datas.length - 1].setGrades(_initGrade());
		
		return true;
	}

	private Grade_C[] _initGrade() {
		Grade_C[] gArr = new Grade_C[] {
				new Grade_C("국어"), new Grade_C("영어"), new Grade_C("수학"), new Grade_C("과학")
		};
		return gArr;
	}

	@Override
	public Student_C modify(String name, String subject, int score) {
		int idx = _findIndex(name);
		Grade_C[] grades = datas[idx].getGrades();
		for(int i = 0; i < grades.length; i++) {
			if(subject.equals(grades[i].getName())) {
				if(score >= 0 && score <= 100) {
					grades[i].setScore(score);
				} else {
					grades[i].setScore(0);
				}
				return datas[idx];
			}
		}
		return null;
	}

	@Override
	public boolean remove(String name) {
		int idx = _findIndex(name);
		
		if(idx == -1) {
			return false;
		}
		
		Student_C[] temp = new Student_C[datas.length - 1];
		System.arraycopy(datas, 0, temp, 0, idx);
		System.arraycopy(datas, idx + 1, temp, idx, datas.length - (idx + 1));
		datas = temp;
		
		return true;
	}
	
	public Student_C getStudent(String name) {
		return datas[_findIndex(name)];
	}
	
	public boolean isExisted(String name) {
		return _isExisted(name);
	}
	
	private boolean _isExisted(String name) {
		return _findIndex(name) != -1 ? true : false;
	}
	
	private int _findIndex(String name) {
		int idx = -1;
		
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName())) {
				idx = i;
				return idx;
			}
		}
		
		return idx;
	}
	
	public Student_C login(String name, String password) {
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName()) && password.equals(datas[i].getPassword())) {
				return datas[i];
			}
		}
		return null;
	}
}
