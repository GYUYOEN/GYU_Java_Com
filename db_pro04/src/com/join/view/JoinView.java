package com.join.view;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.join.controller.JoinController;
import com.join.menu.Menu;
import com.join.vo.JoinVo;


/*
 * CLI 화면에 회원가입, 탈퇴, 정보 수정 등과 같은
 * 정보를 보여주기 위한 객체로 활용
 * 사용저 입력, 출력
 */
public class JoinView {
	private JoinController jc = new JoinController();
	private Scanner sc = new Scanner(System.in);
	private Menu menu = new Menu();
	
	public void show() {
		// 회원 가입 및 로그인 요청에 맞추어 적절한 메서드를 호출한다.
		while(true) {
			System.out.print(menu.getMain());
			System.out.print(">>>");
			String input = sc.nextLine();
		
			switch(input) {
				case "1" :
					this.joinMenu(); break;
				case "2" :
					this.loginMenu(); break;
				case "3" :
					System.exit(0);
				default :
					System.out.println("잘못작성하였습니다. 다시 입력하세요.");
			}
		}
	}
	
	public void joinMenu() {
		JoinVo data = new JoinVo();
		
		// 회원 가입을 위한 화면과 기능 제공
		System.out.print("       아이디 : ");
		data.setUserid(sc.nextLine());
		System.out.print("      패스워드 : ");
		data.setUserpw(sc.nextLine());
		System.out.print("     본인 이름 : ");
		data.setUsername(sc.nextLine());
		System.out.print("   성별(남/여) : ");
		data.setGender(sc.nextLine());
		System.out.print("나이(15세 이상) : ");
		data.setAge(sc.nextLine());
		
		// data에 사용자가 입력한 모든 정보가 등록됨
		boolean result = jc.join(data);
		
		if(result) {
			System.out.println("회원가입을 축하합니다.");
		} else {
			System.out.println("회원가입을 할 수 없습니다.(중복된 아이디가 있습니다.)");
		}
	}
	
	public void loginMenu() {
		// 로그인을 위한 화면과 기능 제공
		System.out.print("아이디 : ");
		String userid = sc.nextLine();
		System.out.print("패스워드 : ");
		String userpw = sc.nextLine();
		
		
		// JoinVo : 보통 로그인하면 사용자 정보가 필요하므로 로그인 성공하면 JoinVo로 나타내줌
		JoinVo account = jc.login(userid, userpw);
		
		if(account != null) {
			System.out.printf("%s 님이 로그인을 하였습니다.\n", account.getUserid());
			afterLoginMenu(account);
		} else {
			System.out.println("로그인에 실패하였습니다.");
		}
	}
	
	public void afterLoginMenu(JoinVo account) {
		while(true) {
			System.out.print(menu.getAfterLogin(account.getUserid()));
			System.out.print(">>> ");
			String input = sc.nextLine();
			
			switch(input) {
				case "1" :
					System.out.println(account.getUserid());
					System.out.println(account.getUsername());
					System.out.println(account.getGender());
					System.out.println(account.getAge());
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
					
					// 중복이 되므로
					// 날짜에 해당하는 밀리세컨즈 값이 나옴
					java.util.Date createDate = new java.util.Date(account.getCreateDate().getTime());
					
					String sDate = dateFormat.format(account.getCreateDate().getTime());
					
					System.out.println(sDate);
					
					java.util.Date now = new java.util.Date();
					
					// 주의 : java.sql.Date 는 날짜만 다루는 객체. 시간까지 다루기 위해서는 java.sql.timestamp 를 사용해야함
					java.sql.Date sqlDate = new java.sql.Date(now.getTime());
					// 문자열 -> sqlDate 타입으로 변환
					sqlDate = java.sql.Date.valueOf("2022-05-13"); // 형식 꼭 맞춰주기
					
					java.sql.Timestamp sqlDateTime = java.sql.Timestamp.valueOf("2022-05-13 10:19:30.12345");
					System.out.println(sqlDateTime);
					
					break;
				case "2" :
					// 아이디는 수정 못하게 할 것임.
					System.out.println("아무것도 입력을 하지 않으면 이전 값을 유지 합니다.");
					System.out.println("변경 할 패스워드를 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? account.getUserpw() : input;
					account.setUserpw(input);

					System.out.println("변경 할 이름을 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? account.getUsername() : input;
					account.setUsername(input);
					
					System.out.println("변경 할 성별(남/여)을 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? Character.toString(account.getGender()) : input;
					account.setGender(input);
					
					System.out.println("변경 할 나이를 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? Integer.toString(account.getAge()) : input;
					account.setAge(input);
					
					boolean result = jc.update(account); 
					
					if(result) {
						System.out.println("수정이 완료되었습니다.");
					} else {
						System.out.println("수정에 실패하였습니다.");
					}
					
					break;
				case "3" :
					if(jc.remove(account)) {
						System.out.println("탈퇴 처리가 완료 되었습니다.");
						return;
					} else {
						System.out.println("탈퇴 처리를 수행할 수 없습니다.");
					}
					break;
				case "4" :
					System.out.print("로그아웃 중 입니다.");
					account = null;
					System.out.println("로그아웃 작업이 완료되었습니다.");
					return;
				default :
					System.out.println("잘못된 메뉴 번호 입니다. 다시 입력하세요.");
			}
		}
	}
//
//	private void loginManager() {
//		JoinController jc = new JoinController();
//		System.out.println("┌-------------------┐");		
//		System.out.println("│	1. 정보 수정		│");		
//		System.out.println("│	2. 회원 탈퇴		│");
//		System.out.println("│	3. 로그 아웃		│");
//		System.out.println("└-------------------┘");
//		System.out.println(">>>");
//		
//		while(true) {
//			String input = sc.nextLine();
//		
//			switch(input) {
//				case "1" :
//					jc.update(); break;
//				case "2" :
//					jc.remove(); break;
//				case "3" :
//					this.show(); break;
//				default :
//					System.out.println("입력값이 잘못되었습니다. 다시 입력하세요.");
//			}
//		}
	
}
