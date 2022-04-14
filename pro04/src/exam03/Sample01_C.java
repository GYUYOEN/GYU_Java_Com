package exam03;

import java.util.Arrays;
import java.util.Scanner;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * 사용자가 입력한 영문자열에서 입력된 문자의수를 구하는 코드를 작성
		 *      1. 영문자를 제외한 다른 문자는 무시
		 *      2. 영문자의 대/소문자를 구분하지 않고 문자의 수를 구한다.
		 *      
		 * 예제 
		 *      영문자 입력 : hello java programming
		 *      
		 *      입력한 영문자 분포
		 */
	 	Scanner sc = new Scanner(System.in);
		
	 	// 사용자 입력을 통해 문자열을 받아야 함
	 	System.out.print("영문자 입력 : ");
	  	String data = sc.nextLine();
		
		// 알파벳 별 수를 세기위한 배열, 26개의 공간이 있는 배열
	  	int[] counting = new int[26];
	  	
	  	// 반복문을 사용하여 사용자가 입력한 문자열의 문자를 추출 - > charAt(위치값)
	  	for(int i = 0; i < data.length(); i++) {
	  		int idx = 0;
	  		char c = data.charAt(i);
	  		
	  		// 대소문자를 구분하지 않기 위한 방법
	  		if(c >= 65 && c <= 90) {
	  			idx = c - 65;
	  			counting[idx] += 1;
	  		} else if(c >= 97 && c <= 122) {
	  			idx = c - 97;
	  			counting[idx] += 1;
	  		}
	  	}
	  	System.out.println(Arrays.toString(counting));
	}
}
