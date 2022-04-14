package controller;

import model.vo.Grade_C;
import model.vo.Student_C;

public interface ImplDatabaseManager_C {
	public Grade_C[] search(String name);
	public boolean add(String name);
	public Student_C modify(String name, String subject, int score);
	public boolean remove(String name);
}

