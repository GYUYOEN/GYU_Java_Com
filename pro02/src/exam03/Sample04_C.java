package exam03;

import java.util.Scanner;

public class Sample04_C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1, num2;
		
		System.out.print("첫 번쩨 정수 값 입력 : ");
		num1 = sc.nextInt();
		
		System.out.print("첫 번쩨 정수 값 입력 : ");
		num2 = sc.nextInt();
		
		int result = num1 + num2;
		
		System.out.printf("결과 : %d", result);
	}

}
