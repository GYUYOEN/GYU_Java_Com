package controller;

import java.util.Date;
import java.util.Scanner;

import model.vo.Account_C;
import model.vo.Grade_C;
import model.vo.Student_C;
import model.vo.Teacher_C;

public class MenuManager_C {
	// 학생 성적 관리의 메뉴를 관리하기 위한 매니저 클래스

	private Scanner sc = new Scanner(System.in);
	private DatabaseManager_C db = new DatabaseManager_C();
	private Account_C loginAccount;
	
	public MenuManager_C(Account_C loginAccount) {
		this.loginAccount = loginAccount;
	}
	
	public void main() {
		if(loginAccount instanceof Teacher_C) {
			teacherMenu();
		} else {
			studentMenu();
		}
	}
	
	public void teacherMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append("1. 성적 조회\n");		// searchMenu()
		menu.append("2. 학생 정보 추가\n");	// studentAddMenu()
		menu.append("3. 성적 정보 수정\n");	// subjectModifyMenu()
		menu.append("4. 학생 정보 삭제\n");	// studentDeleteMenu()
		menu.append("5. 패스워드 변경\n");
		menu.append("9. 로그아웃\n");	// System.exit(0);
		menu.append(">>> ");
		while(true) {
			System.out.print(menu);
			
			String input = sc.nextLine();
			
			_clear();
			if(input.equals("1")) {
				searchMenu();
			} else if(input.equals("2")) {
				studentAddMenu();
			} else if(input.equals("3")) {
				subjectModifyMenu();
			} else if(input.equals("4")) {
				studentDeleteMenu();
			} else if(input.equals("5")) {
				changePasswordMenu();
			} else if(input.equals("9")) {
				logout();
				return;
			}
			_clear();
		}
	}
	
	public void changePasswordMenu() {
		System.out.print("현재 패스워드 : ");
		String curPass = sc.nextLine();
		
		System.out.print("변경 패스워드 : ");
		String changePass = sc.nextLine();
		
		boolean result = loginAccount.changePassword(curPass, changePass);
		if(result) {
			System.out.println("패스워드가 변경되었습니다.");
		} else {
			System.out.println("패스워들르 변경하지 못하였습니다. 현재 패스워드를 다시 확인하세요.");
		}
	}
	
	public void studentMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append("1. 성적 조회\n");
		menu.append("2. 패스워드 변경\n");
		menu.append("9. 로그아웃\n");
		menu.append(">>> ");
		while(true) {
			System.out.print(menu);
			
			String input = sc.nextLine();
			
			if(input.equals("1")) {
				String result = _printGrades(loginAccount.getName(), ((Student_C)loginAccount).getGrades());
				System.out.println(result);
			} else if(input.equals("2")) {
				changePasswordMenu();
			} else if(input.equals("9")) {
				logout();
				return;
			}
		}
	}
	
	private void logout() {
		if(loginAccount instanceof Teacher_C) {
			((Teacher_C)loginAccount).setLoginDate(new Date());
		}
		System.out.println(loginAccount.getName() + "님이 로그아웃 하였습니다.");
	}

	private void _clear() {
		for(int i = 0; i < 20; i++) {
			System.out.print("\n");
		}
	}

	public void searchMenu() {
		/*
		 * --------------------
		 * 이름 : 홍길동
		 * --------------------
		 * 국어    영어    수학    과학
		 *  xx점    xx점    xx점    xx점
		 * x등급   x등급   x등급   x등급
		 * --------------------
		 * 평균 : xx.xx 점
		 */
		String name = "";
		Grade_C[] datas = null;
		
		while(true) {
			System.out.print("학생 이름 입력 : ");
			name = sc.nextLine();
			datas = db.search(name);
			
			if(datas == null) {
				System.out.println("해당 학생은 존재하지 않습니다. 다시 입력하세요.");
			} else {
				break;				
			}
		}
		
		String result = _printGrades(name, datas);
		System.out.println(result);
		System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
	}
	
	
	public void studentAddMenu() {
		String name = "";
		while(true) {
			System.out.print("학생 이름 입력 : ");
			name = sc.nextLine();
			
			if(db.add(name)) {
				System.out.printf("%s 학생의 정보가 추가되었습니다.\n", name);
				System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
				break;
			} else {
				System.out.println("해당 학생은 이미 존재하는 정보 입니다.");
			}
		}
	}
	
	public void subjectModifyMenu() {
		String name = "";
		while(true) {
			Grade_C[] datas = null;
			
			while(true) {
				System.out.print("학생 이름 입력 : ");
				name = sc.nextLine();
				datas = db.search(name);
				
				if(datas == null) {
					System.out.println("해당 학생은 존재하지 않습니다. 다시 입력하세요.");
				} else {
					break;				
				}
			}
			
			String result = _printGrades(name, datas);
			System.out.println(result);
			System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
			
			System.out.println("출력 된 과목 순으로 점수를 입력하세요.(공백을 구분자로 사용하여 입력)");
			System.out.println("예) 78 89 99 ...");
			System.out.print(": ");
			
			String[] scoreArr = sc.nextLine().split(" ");
			int[] score = new int[0];
			
			if(scoreArr.length == datas.length) {
				score = new int[scoreArr.length];
				for(int i = 0; i < scoreArr.length; i++) {
					score[i] = Integer.parseInt(scoreArr[i]);
				}
				
				Student_C std;
				for(int i = 0; i < datas.length; i++) {
					std = db.modify(name, datas[i].getName(), score[i]);
					System.out.printf("%s 학생의 %s 과목 점수가 수정 되었습니다.\n", std.getName(), datas[i].getName());
				}
				System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
				break;
			} else {
				System.out.println("점수 입력 수량이 잘못되었습니다. " + datas.length + "개 과목 수만큼 입력하세요.");
			}
		}
	}
	
	public void studentDeleteMenu() {
		String name = "";
		while(true) {
			System.out.print("학생 이름 입력 : ");
			name = sc.nextLine();
			
			if(db.remove(name)) {
				System.out.println("학생 정보를 삭제하였습니다.");
				System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
				break;
			} else {
				System.out.println("해당 학생은 존재하지 않습니다. 다시 입력하세요.");
			}
		}
	}
	
	private String _printGrades(String name, Grade_C[] datas) {
		String hLine = "--------------------\n";
		StringBuilder sb = new StringBuilder();
		String[] scores = new String[datas.length];
		String[] levels = new String[datas.length];
		String[] subjects = new String[datas.length];
		double avg = 0;
		
		for(int i = 0; i < datas.length; i++) {
			scores[i] = Double.valueOf(datas[i].getScore()).toString();
			levels[i] = Character.valueOf(datas[i].getLevel()).toString();
			subjects[i] = datas[i].getName();
			avg += datas[i].getScore();
		}
		avg /= datas.length;
		
		sb.append(hLine);
		sb.append(String.format("이름 : %s\n", name));
		sb.append(hLine);
		sb.append(String.join("\t", subjects) + "\n");
		sb.append(String.join("점\t", scores) + "점\n");
		sb.append(String.join("등급\t", levels) + "등급\n");
		sb.append(hLine);
		sb.append(String.format("평균 : %.2f 점\n", avg));
		sb.append(hLine);
		
		return sb.toString();
	}
}

