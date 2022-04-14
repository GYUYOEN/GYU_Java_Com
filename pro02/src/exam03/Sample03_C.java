package exam03;

import java.util.Scanner;

public class Sample03_C {
	
	public static void main(String[] args) {
		// 사용자가 입력한 한 줄 데이터를 처리하기 위한 sc.nextline()
		
		Scanner sc = new Scanner(System.in);
		
		String sInput = sc.nextLine();
		
		System.out.println("입력 문자열 : " + sInput);
	}

}
