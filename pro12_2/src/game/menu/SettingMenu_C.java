package game.menu;

import java.util.Scanner;

import game.db.PenaltyDatabase_C;

public class SettingMenu_C {
	private PenaltyDatabase_C pDB = new PenaltyDatabase_C();
	private Scanner sc = new Scanner(System.in);
	
	public SettingMenu_C() {
		this.pDB = new PenaltyDatabase_C();
		this.sc = new Scanner(System.in);
	}
	
	public void show() throws InterruptedException {
		String mainMenu = "";
		mainMenu += "<<<<< UP! & DOWN! >>>>>\n";
		mainMenu += "┌─────────────────────┐\n";
		mainMenu += "│ 1. Penalty Add      │\n";
		mainMenu += "│ 2. Penalty Modify   │\n";
		mainMenu += "│ 3. Penalty Remove   │\n";
		mainMenu += "│ 4. Back             │\n";
		mainMenu += "└─────────────────────┘\n";
		mainMenu += ": ";
		
		while(true) {
			System.out.print(mainMenu);
			String input = sc.nextLine();
			
			switch(input.charAt(0)) {
				case '1':
					addMenu();
					break;
				case '2':
					modifyMenu();
					break;
				case '3':
					removeMenu();
					break;
				case '4':
					System.out.println("이전 메뉴로 돌아 갑니다.");
					Thread.sleep(500);
					return;
				default:
			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}

	}
	private void addMenu() {
		String menu = "";
		menu += "추가할 벌칙을 작성하세요. 작성을 중단하려면 exit를 입력해주세요.\n";
		menu += ": ";
		
		while(true) {
			System.out.print(menu);
			String input = sc.nextLine();
			
			if(input.equals("exit")) {
				System.out.println("추가 작업을 종료하고 이전 메뉴로 돌아 갑니다.");
				return;
			}
			
			pDB.add(input);
			System.out.println("입력한 벌칙 저장이 완료되었습니다.");
		}
		
	}

	// 파일에 있는 것을 배열로 생성 -> 배열의 데이터를 수정 -> 배열의 데이터를 덮어씌움
	private void modifyMenu() {
		// 길이를 반환해준 이유 : 목록에 없는 번호를 입력했을 경우 오류메시지 출력하게 하기 위해
		int numberMax = _penaltyListUp();
		
		System.out.println("위 목록중에서 수정할 벌칙을 선택하세요.");
		System.out.print(": ");
		
		int number;
		while(true) {
			if(sc.hasNextInt()) {
				number = sc.nextInt(); sc.nextLine();
				if(number > 0 && number <= numberMax) {
					break;
				}
				System.out.printf("입력 번호는 1 ~ %d 까지 입니다.\n", numberMax);
			} else {
				System.out.println("다시 입력하세요.");
				sc.nextLine();
			}
			System.out.print(": ");
		}
		System.out.println("벌칙을 입력하세요.");
		System.out.print(": ");
		String penalty = sc.nextLine();
		
		pDB.modify(number - 1, penalty);
		System.out.println("입력한 벌칙으로 수정이 완료되었습니다.");
	}
	
	private void removeMenu() {
		int numberMax = _penaltyListUp();
		
		System.out.println("위 목록중에서 수정할 벌칙을 선택하세요.");
		System.out.print(": ");
		
		int number;
		while(true) {
			if(sc.hasNextInt()) {
				number = sc.nextInt(); sc.nextLine();
				if(number > 0 && number <= numberMax) {
					break;
				}
				System.out.printf("입력 번호는 1 ~ %d 까지 입니다.\n", numberMax);
			} else {
				System.out.println("다시 입력하세요.");
				sc.nextLine();
			}
			System.out.print(": ");
		}
		
//		pDB.remove(number - 1, penalty);
		pDB.remove(number - 1);
		System.out.println("선택한 벌칙이 삭제되었습니다.");
		
	}
	
	// 입력된 벌칙을 보여주기 위한 작업
	private int _penaltyListUp() {
		String[] penaltys = pDB.getList();
		StringBuilder sb = new StringBuilder();
		
		// 불러온걸 출력해줌
		for(int i = 0; i < penaltys.length; i++) {
			sb.append(String.format("%d. %s\n", i + 1, penaltys[i]));
		}
		
		// 출력도 하고 리턴도 해줌
		System.out.print(sb.toString());
		return penaltys.length;
	}
}


