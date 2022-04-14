package exam01;

import java.util.Scanner; 

public class Sample02_C {

	public static void main(String[] args) {
		/*
		 * 사용자 입력을 통해 정수 값을 입력받아 다음의 문제를 푸시오.
		 *  1.사용자 입력값의 범위를 설정하여 입력값의 범위를 초과하는 경우 주의 메시지를 출력한다.
		 *     입력값의 범위 : 1 ~ 99
		 *     입력값의 범위를 벗어난 경우 "1 ~ 99 사이의 값을 입력하시오." 라는 메시지를 출력
		 *     올바은 입력값의 범위를 입력한 경우 "장수값이 정상적으로 입력되었습니다" 라는 메시지를 출력
		 *     
		 *  2. 0 ~ 100 사이의 정수 값을 입력 받아 다음 조건에 해당하는 경우 적절한 메시지를 출력하도록 한다.
		 *     입력된 정수 값이 0 ~ 39 사이의 값인 경우 "과락입니다." 메시지를 출력
		 *     입력된 정수 값이 40 ~ 59 사이의 값인 경우 "E 등급 입니다." 메시지를 출력
		 *     입력된 정수 값이 60 ~ 69 사이의 값인 경우 "D 등급 입니다." 메시지를 출력
		 *     입력된 정수 값이 70 ~ 79 사이의 값인 경우 "C 등급 입니다." 메시지를 출력
		 *     입력된 정수 값이 80 ~ 89 사이의 값인 경우 "B 등급 입니다." 메시지를 출력
		 *     입력된 정수 값이 90 ~ 100 사이의 값인 경우 "A 등급 입니다." 메시지를 출력 
		 */
		Scanner sc = new Scanner(System.in);
		
		String result;
		int num1, num2;
		
		result = ""; // 딱히 쓸게 없으므로 ""에 아무것도 넣지 않고 초기화
		
		System.out.print("정수값을 입력하세요 : ");
		num1 = sc.nextInt();
		
		if(num1 >= 1 && num1 <= 99) {
			System.out.println("정수값이 정상적으로 입력되었습니다.");
		} else {
			System.out.println("1 ~ 99 사이의 값을 입력하사오.");
		}
		
		System.out.print("점수를 입력하세요(0 ~ 100) : ");
		num2 = sc.nextInt();
		
		if(num2 <= 39) {
			result = "과락입니다.";
		} else if(num2 <= 59) {
			result = "E 등급입니다.";
		} else if(num2 <= 69) {
			result = "D 등급입니다.";
		} else if(num2 <= 79) {
			result = "C 등급입니다.";
		} else if(num2 <= 89) {
			result = "B 등급입니다.";
		} else if(num2 <= 100) {
			result = "A 등급입니다.";
		}
		
		System.out.println(result);
		
		/*
		 * or 초기화 값 쓰지 말고
		 * 
		 * if(num2 <= 39) {
			result = "과락입니다.";
		} else if(num2 <= 59) {
			result = "E 등급입니다.";
		} else if(num2 <= 69) {
			result = "D 등급입니다.";
		} else if(num2 <= 79) {
			result = "C 등급입니다.";
		} else if(num2 <= 89) {
			result = "B 등급입니다.";
		} else {        // 여기를 바꿔줌
			result = "A 등급입니다.";
		}
		
		 */
	}

}