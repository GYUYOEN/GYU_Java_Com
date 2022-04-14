package exam02;

import java.util.Scanner;

public class Sample10_C {

	public static void main(String[] args) {
		/*
		 * 같은 문자열인지 비교할 때
		 *      문자열변수명.equals(문자열값)
		 */
		Scanner sc = new Scanner(System.in);
		String s = "문자열";
		
		System.out.print("문자열 입력 : ");
		String sInput = sc.next();
		
		System.out.printf("%s / %s\n", s, sInput);
		
		if(s.equals(sInput)) {
			System.out.println("equals 로 비교했을 때 동일한 문자열로 판별");
		} else {
			System.out.println("equals 로 비교했을 때 동일하지 않은 문자열로 판별");
		}
		
		if(s == sInput) {
			System.out.println("== 로 비교했을 때 동일한 문자열로 판별");
		} else {
			System.out.println("== 로 비교했을 때 동일하지 않은 문자열로 판별");
		}
	}

}