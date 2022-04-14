import java.util.Scanner;

import game.menu.SettingMenu_C;
import game.updown.Correct_C;
import game.updown.Fail_C;
import game.updown.GuessNum_C;
import game.updown.Result_C;

/*
 * <<<<<<< UP! & DOWN! >>>>>>>>
 * 
 * 1. Game Start!
 * 2. Penalty Setting
 * 3. Exit
 * 
 * :2
 * 
 * 2번을 입력 한 경우 벌칙을 설정할 수 있도록 한다.
 * 벌칙은 up_down.penalty 파일에 기록한다.
 * 
 * up & down 번호를 지정된 횟수안에 맞추지 못한 경우
 * Fail 클래스가 생성되는데 이때 설정된 벌칙 파일을 불러와 문자열 배열에 할당 후 
 * 임의의 벌칙이 출력되도록 한다.
 * 
 * 벌칙 설정은 추가 뿐만 어니라 삭제도 할 수 있으며, 수정도 가능하게 한다.
 */


public class Main_C {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * 업앤다운
		 *     1. 임의의 숫자가 하나 정해진다.
		 *     2. 정해진 임의의 숫자를 맞춘다.
		 *        2-1. 정해진 숫자보다 낮은 숫자를 부르면 -> UP!
		 *        2-2. 정해진 숫자보다 높은 숫자를 부르면 -> DOWN!
		 *     3. 정해진 숫자를 맞추면 게임 종료
		 *        단, 정해진 횟수 안에서 맞추어야 한다.
		 */
		
		/*
		 * GuessNum 클래스
		 *     - 생성하면 바로 임의의 숫자를 만든다.
		 *     - 생성할 때 맞출 수 있는 횟수를 제한한다.
		 *     - 임의의 정수값을 전달하면 UP, DOWN 을 반환한다.
		 *     - 임의의 정수값과 GuessNum 클래스에 생성된 정수값이 동일하면 Correct 를 반환한다.
		 *     - 임의의 정수값이 전달되면 횟수를 차감한다.
		 *     - 모든 횟수가 차감되여 0 이 되면 Fail 을 반환하게 한다.
		 */
		
		// UP 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// DOWN 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// Correct 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// Fail 클래스 : 클래스로만 생성하여 쓰도록 한다.
		Scanner sc = new Scanner(System.in);
		
		String mainMenu = "";
		mainMenu += "<<<<< UP! & DOWN! >>>>>\n";
		mainMenu += "┌─────────────────────┐\n";
		mainMenu += "│ 1. Game Start!      │\n";
		mainMenu += "│ 2. Penalty Setting! │\n";
		mainMenu += "│ 3. Exit             │\n";
		mainMenu += "└─────────────────────┘\n";
		mainMenu += ": ";
		
		while(true) {
			System.out.print(mainMenu);
			String input = sc.nextLine();
		
			switch(input.charAt(0)) {
				case '1':
					System.out.println("게임을 진행을 위한 정보 로딩중....");
					Thread.sleep(1500);
					break;
				case '2':
					System.out.println("벌칙을 설정하기 위한 메뉴로 이동중...");
					Thread.sleep(1500);
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					SettingMenu_C sm = new SettingMenu_C();
					sm.show();
					break;
				case '3':
					System.out.println("게임을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("잘못된 번호를 입력하였습니다. 다시 입력하세요.");
					System.out.println("Enter 키를 입력하면 다시 메뉴 화면이 나옵니다.");
					sc.nextLine();
			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
					
//		Scanner sc = new Scanner(System.in);
//		
//		while(true) {
//			GuessNum_C game = new GuessNum_C();
//			
//			while(game.remainCount()) {
//				System.out.print("임의의 숫자 입력 : ");
//				int num = sc.nextInt();
//				
//				Result_C res = game.guessing(num);
//				System.out.println(res);
//				
//				if(res instanceof Correct_C || res instanceof Fail_C) {
//					System.out.println("게임을 다시 시작합니다.");
//					break;
//				}
//			}
//		}
//	}
	}
}
