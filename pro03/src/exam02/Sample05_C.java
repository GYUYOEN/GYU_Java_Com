package exam02;

import java.util.Scanner;

public class Sample05_C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu;
		boolean existed = false; // for 문을 종료하기 위한 flag
		
		System.out.println("다음의 메뉴 번호 중 하나를 입력하시오.");
		for(int i = 0; i < 3; i++) {
			System.out.println("1. 조회");
			System.out.println("2. 추가");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("9. 종료");
			System.out.println(": ");
			menu = sc.nextInt();
			switch(menu) {
				case 1:
					System.out.println("조회 메뉴를 선택했습니다.");
					existed = true; // 정상적으로 입력했으니까 종료하라는 flag
					break;  // 인접한 switch에민 빌동. for 문에는 발동x. 중첩 발동x for 문 종료x
				case 2:
					System.out.println("추가 메뉴를 선택했습니다.");
					existed = true; // 다음에 활용하기 위해 임시저장한 flag. for 문을 빠져나가기 위해 사용
					break;
				case 3:
					System.out.println("수정 메뉴를 선택했습니다.");
					existed = true;
					break;
				case 4:
					System.out.println("삭제 메뉴를 선택했습니다.");
					existed = true;
					break;
				case 9:
					System.out.println("프로그램을 종료합니다.");
					existed = true;
				default:
					System.out.println("잘못된 메뉴 번호 입니다. 다시 입력하세요.");
			}
			if(existed) break; // 코드 라인 줄이기 위해 중괄호 셍략 (주의 : 1 줄만 가능)
			// if 문은 break 발동x -> for 문에 발동
			// for 문을 종료하기 위해 씀
		}
	}
}