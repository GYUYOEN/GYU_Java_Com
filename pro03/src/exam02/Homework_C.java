package exam02;

import java.util.Scanner;

public class Homework_C {

	public static void main(String[] args) {
		
		/* 
		 * 반복문을 사용하여 1 ~ 10 까지의 누적 합을 구하시오
		 * 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = 55
		 */
		int tot = 0;
		for(int i = 1; i <= 10; i++) {
			tot += i; // tot = tot + i;
		}
		System.out.println("1 ~ 10 까지의 누적합 -> " + tot);
		
		
		
		/*
		 * 사용자가 입력한 정수값에 해당하는 구구단을 출력하시오.
		 */
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수값을 입력 : ");
		int num = sc.nextInt();
		
		System.out.printf("%d 단을 입력 하였습니다.\n", num);
		
		for(int i = 1; i < 10; i++) {
			System.out.printf("%d X %d = %d\n", num, i, num * i);
		}
		
		
		
		/*
		 * 구구단을 출력할 때 한 줄에 3개의 결과가 출력되도록 하시오.
		 */
		int cnt = 0;  // flag
		for(int i = 1; i < 10; i++) {
			System.out.printf("%d X %d = %d\t", num, i, num * i); // /t로 바꿔즘
			cnt++;
			if(cnt == 3) {
				System.out.println("\n");
				cnt = 0;
			}
		}
	}
}