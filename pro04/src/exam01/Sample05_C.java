package exam01;

import java.util.Scanner;

public class Sample05_C {

	public static void main(String[] args) {
		/*
		 * 카이사르 암호 만들기
		 *      - 사용자 입력으로 영단어를 입력 받는다.
		 *      - 입력받은 영단어는 문자 배열로 생성한다.
		 *      - 생성된 문자 배열에 대해 암호화된 배열을 저장하기 위한 배열을 새로 만든다.
		 *      - 문자 쉬프트는 3 으로 하고 만약 'z' 문자에 대한 쉬프가  필요한 경우 'a'로 넘어가게 해야 한다.
		 *      
		 * 출력 형식
		 *      암호화 전 : apple
		 *      암호화 후 : dssoh
		 */
		Scanner sc = new Scanner(System.in);
		
		System.out.print("영단어 입력 : ");
		String sInput = sc.nextLine();
		
		// 문자열을 문자 배열로 변환
		// 암호화 전
		char[] origin = new char[sInput.length()]; // 여기서 sInput.length() 는 메서드 : 문자열
		for(int i = 0; i < origin.length; i++) {
			origin[i] = sInput.charAt(i);
		}
		// ->
		// .toCharArray 메서드를 사용하여 문자열을 문자 배열로 변환 
		// origin = sInput.toCharArray();
		
		// 암호화 후
		char[] crypto = new char[origin.length]; // sInput.length() -> origin.length로 변경 가능. 이것은 배열 길이 속성 : 배열
		for(int i = 0; i < crypto.length; i++) { 
			if(origin[i] + 3 > 'z') { // 나중에 문자열 길이를 다르게 저장하면 sInput.length() 보다는 crypto.length 사용하는 것이 더 맞음
				crypto[i] = (char)(origin[i] + 3 - 26); 
			} else {
				crypto[i] = (char)(origin[i] + 3); 
			}
		}
		
		String res1 = "";
		String res2 = "";
		// 문자 배열을 문자열로 만들어서 출력하기 위해 반복문을 사용.
				for(int i = 0; i < origin.length; i++) {
					res1 += origin[i];
					res2 += crypto[i];
				}
		
		// ->
		// new 연산으로 문자열 생성을 할 때 문자 배열을 넣는다.
		res1 = new String(origin);
		res2 = new String(crypto);
		// ->
		// String.valueOf(문자배열) 메서드를 사용한다.
		res1 = String.valueOf(origin);
		res2 = String.valueOf(crypto);
		
		System.out.println("암호화 전 : " + res1);
		System.out.println("암호화 후 : " + res2);
	}

}