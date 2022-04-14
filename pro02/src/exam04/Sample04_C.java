package exam04;

import java.util.Scanner;

public class Sample04_C {
	
	public static void main(String[] args) {
		/* 섭씨 화씨 변환
		 *      사용자 입력으로 섭씨 값을 받는다. (정수 값만 입력받는다.)
		 *      입력받은 섭씨를 화씨로 변환하여 출력 (실수 결과로 출력한다.)
		 *      
		 * 섭씨 -> 화씨 변환 식
		 *      (섭씨 *(9 /5)) + 32 = 화씨
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int n1;
		double n2;
		
		System.out.print("°C -> °F 로 변환 합니다.\n온도 입력(°C) : ");
		n1 = sc.nextInt(); // 실수 값을 넣고 싶으면 double n1 = sc.nextDouble();
		
		n2 = (n1 * (9.0/ 5.0)) + 32;
		
		System.out.printf("화씨 변환 값 : %.2f°F", n2);
		
		/* 주의사항
		 * System.out.println(1 / 0); -> 분모가 0 이면 에러뜸
		 */

	}
}
