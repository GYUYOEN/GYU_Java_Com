package controller;

import java.util.Date;

import model.vo.Teacher_C;

public class TeacherDatabaseManager_C {
	private Teacher_C[] datas;
	
	// 테스트용 초기 데이터 나중에는 삭제할 것임.
	{
		datas = new Teacher_C[2];
		datas[0] = new Teacher_C("고명환", "a1234");
		datas[0].setLoginDate(new Date());
		datas[1] = new Teacher_C("김수덕", "a1111");
	}
	
	public Teacher_C login(String name, String password) {
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName()) && password.equals(datas[i].getPassword())) {
				return datas[i];
			}
		}
		return null;
	}
	
	public Teacher_C getTeacher(String name) {
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
}