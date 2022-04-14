package exam02;

import java.util.Scanner;

public class Sample04_C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num;
		
		// 원하는 범위의 값이 입력될 때까지 반복. 단, 입력 횟수를 제한
		
		// 제한된 입력 횟수를 넘긴 경우 -> 입력 횟수를 초과하였습니다.
		for(int i = 0; i < 3; i++) {
			System.out.print("1 ~ 5 사이의 정수값 입력 : ");
			num = sc.nextInt();
			if(num >= 1 && num <= 5) {
				break;
			} 
			if(i == 2) {
				System.out.print("입력 횟수를 초과하였습니다.");
			}
		}
	}
}
