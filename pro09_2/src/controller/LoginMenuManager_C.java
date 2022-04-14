package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.vo.Student_C;
import model.vo.Teacher_C;
import model.vo.Teacher_C;

public class LoginMenuManager_C {
	private Scanner sc = new Scanner(System.in);
	private TeacherDatabaseManager_C tDB = new TeacherDatabaseManager_C();
	private DatabaseManager_C sDB = new DatabaseManager_C();
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	
	public void main() {
		StringBuilder menu = new StringBuilder();
		menu.append("1. 교사용 로그인\n");
		menu.append("2. 학생용 로그인\n");
		menu.append("3. 패스워드 초기화\n");
		menu.append("4. 종료\n");
		menu.append(">>> ");
		
		while(true) {
			int input = 0;
			while(true) {
				System.out.print(menu);
				
				if(sc.hasNextInt()) {
					input = sc.nextInt(); sc.nextLine();
					break;
				}
				sc.nextLine();
			}
			
			switch(input) {
				case 1: teacherLogin(); break;
				case 2: studentLogin(); break;
				case 3: resetPassword(); break;
				case 4:
					System.out.println("프로그램을 종료 합니다.");
					System.exit(0);
			}
		}
	}
	
	private void resetPassword() {
		System.out.print("1. 교사용 계정\n");
		System.out.print("2. 학생용 계정\n");
		System.out.print(">>> ");
		String type = sc.nextLine();
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		switch(type.charAt(0)) {
			case '1':
				if(tDB.isExisted(name)) {
					teacherResetPassword(name);
				}
				break;
			case '2':
				if(sDB.isExisted(name)) {
					studentResetPassword(name);
				}
		}
	}
	
	private void teacherResetPassword(String name) {
		Teacher_C t = tDB.getTeacher(name);
		String password = t.resetPassword();
		System.out.println(password + " 로  초기화 되었습니다. 초기화 패스워드로 로그인 후 다시 패스워드를 변경하세요.");
	}
	
	private void studentResetPassword(String name) {
		Student_C s = sDB.getStudent(name);
		String password = s.resetPassword();
		System.out.println(password + " 로  초기화 되었습니다. 초기화 패스워드로 로그인 후 다시 패스워드를 변경하세요.");
	}
	
	private void teacherLogin() {
		System.out.print("교사명 : ");
		String username = sc.nextLine();
		
		Teacher_C loginAccount = null;
		
		// 비밀번호 3회 입력 제한.
		for(int i = 0; i < 3; i++) {
			System.out.print("비밀번호 : ");
			String password = sc.nextLine();
			loginAccount = tDB.login(username, password);
			if(loginAccount != null) {
				break;
			}
		}
		
		if(loginAccount == null) {
			System.out.println("로그인에 실패하였습니다. 다시 시도하세요.");
		} else {
			if(loginAccount.getLoginDate() == null) {
				System.out.println("환영합니다. 첫 로그인 입니다.");
			} else {
				Date now = new Date();
				System.out.println("최근 접속 시간은 " + loginAccount.getLoginDateFormat() + " 입니다.");
				System.out.println("현재 로그인 시간은 " + sFormat.format(now) + " 입니다.");
				loginAccount.setLoginDate(now);
			}
			MenuManager_C tMenu = new MenuManager_C(loginAccount);
			tMenu.main();
		}
	}
	
	private void studentLogin() {
		System.out.print("학생명 : ");
		String username = sc.nextLine();
		
		Student_C loginAccount = null;
		
		// 비밀번호 3회 입력 제한.
		for(int i = 0; i < 3; i++) {
			System.out.print("비밀번호 : ");
			String password = sc.nextLine();
			loginAccount = sDB.login(username, password);
			if(loginAccount != null) {
				break;
			}
		}
		
		if(loginAccount == null) {
			System.out.println("로그인에 실패하였습니다. 다시 시도하세요.");
		} else {
			Date now = new Date();
			System.out.println("현재 접속 시간은 " + sFormat.format(now) + " 입니다.");

			MenuManager_C sMenu = new MenuManager_C(loginAccount);
			sMenu.main();
		}
	}
	
}
