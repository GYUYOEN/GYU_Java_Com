import java.util.Scanner;

import game.updown.Correct_C;
import game.updown.Fail_C;
import game.updown.GuessNum_C;
import game.updown.Result_C;

public class Main_C {

	public static void main(String[] args) {
		/*
		 * 업앤 다운
		 * 		1. 임의의 숫자가 하나 정해진다.
		 * 		2. 정해진 임의의 숫자를 맞춘다.
		 * 			2-1. 정해진 숫자보다 낮은 숫자를 부르면 ->	UP!
		 * 			2-2. 정해진 숫자보다 높은 숫자를 부르면 ->	DOWN!
		 * 		3. 정해진 숫자를 맞추면 게임 종료
		 * 			단, 정해진 횟수 안에서 맞추어야 한다.
		 */
		
		/* GuessNum 클래스
		 * 		- 생성하면 바로 임의의 숫자를 만든다.
		 * 		- 생성할 때 맞출 수 있는 횟수를 제한한다.
		 * 		- 임의의 정수값을 전달하면 UP, DOWN 을 반환한다.
		 * 		- 임의의 정수값과 GuessNum 클래스에 생성된 정수값이 동일하면 Correct를 반환한다.
		 * 		- 임의의 정수값이 전달되면 횟수를 차감한다.
		 * 		- 모든 횟수가 차감되어 0 이 되면 Fail 을 반환하게 한다.
		 */
		
		// UP 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// DOWN 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// Correct 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// Fail 클래스 : 클래스로만 생성하여 쓰도록 한다.
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			GuessNum_C game = new GuessNum_C();
			
			while(game.remainCount()) {
				System.out.print("임의의 숫자 입력 : ");
				int num = sc.nextInt();
				
				Result_C res = game.guessing(num);
				System.out.println(res);
				
				if(res instanceof Correct_C || res instanceof Fail_C) {
					System.out.println("게임을 다시 시작합니다.");
					break;
				}
			}
		}
	}

}
