package exam02;

import java.util.Scanner;
import java.util.Random;

public class Sample12_C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		String name, player, sCom = "";
		int computer;
		int result = 0;   // 이기면 1, 지면 -1, 비기면 0
		int wCnt = 0, dCnt = 0, lCnt = 0; // 전승무패를 새기위해 사용
		
		System.out.print("당신의 이름을 입력해주세요 : ");
		name = sc.nextLine();
		
		for(;;) {
			System.out.print("가위바위보 : ");
			player = sc.nextLine();
			computer = random.nextInt(3);
			
			if(computer == 0) {
				sCom = "가위";
			} else if(computer == 1) {
				sCom = "바위";
			} else if(computer == 2) {
				sCom = "보";
			}
	
			if(player.equals("exit")) {
				System.out.printf("%d전 %d승 %d무 %d패", wCnt + dCnt + lCnt, wCnt, dCnt, lCnt);
				break; // 종료
			} else if(player.equals("가위")) {
				if(computer == 0) {
					result = 0;   dCnt++;
				} else if(computer == 1) {
					result = -1;  lCnt++;
				} else if(computer == 2) {
					result = 1;   wCnt++;
				}
			} else if(player.equals("바위")) {
				if(computer == 0) {
					result = 1;   wCnt++;
				} else if(computer == 1) {
					result = 0;   dCnt++;
				} else if(computer == 2) {
					result = -1;  lCnt++;
				}
			} else if(player.equals("보")) {
				if(computer == 0) {
					result = -1;  lCnt++;
				} else if(computer == 1) {
					result = 1;   wCnt++;
				} else if(computer == 2) {
					result = 0;   dCnt++;
				}
			} else {
				System.out.println("잘못 입력하였습니다.");
				continue;
			}
			System.out.printf("컴퓨터 : %s\n%s : %s\n", sCom, name, player);
			switch(result) {
				case -1:
					System.out.println("졌습니다.ㅠㅠ");
					break;
				case 0 :
					System.out.println("비겼습니다.");
					break;
				case 1 :
					System.out.println("이겼습니다!!");			
			}
		}
	}
}