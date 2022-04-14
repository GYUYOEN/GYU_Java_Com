package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class Sample02_C {
	
	Scanner sc = new Scanner(System.in);
	
	public void ex01() {
		/*
		 * 사용자 입력으로 정수 값 입력을 받을 때 한번의 입력으로 1개 이상의 정수값을 
		 * 받을 수 있도록 한다. (nextLine() 메서드를 사용해야함)
		 * 
		 * 예)
		 * 		정수값 입력 : 10 20 30 40 50
		 * 
		 * 		다음의 값을 입력하였습니다.
		 * 		10, 20, 30, 40, 50
		 */
			System.out.print("정수값 입력 : ");
			String input = sc.nextLine();
			String[] sArr = input.split(" ");
			
			System.out.println("다음의 값을 입력하세요.");
			System.out.println(String.join(", ", sArr));
			
		}
	
	public void ex02() {
		/*
		 * 사용자 입력으로 전화번호를 입력 받을 때 xxx-xxxx-xxxx 형식으로 입력하지 않은 경우
		 * 다시 입력하도록 한다.
		 */
		String input = "";
		while(input.matches("\\d(3)-\\d(4)-\\d(4)")); {
			System.out.print("전화번호 입력 : ");
			input = sc.nextLine();
		}
		System.out.println("전화번호 입력을 확인하였습니다.");
	}
	
	public void ex03() {
		/*
		 * 사용자 입력으로 전화번호를 입력 받고 출력할 때 전화번호 마지막 4자리 숫자는
		 * 다음의 문자로 마스킹 처리하여 출력되도록 한다. (마스킹 문자 -> *)
		 */
		System.out.print("전화번호 입력 : ");
		String input = sc.nextLine();
		
//		String masking = input.replace(input.substring(input.length() - 4, input.length()), "****");
//		String masking = input.substring(0, input.length() - 4) + "****";
		String[] sArr = input.split("-");
		sArr[2] = "****";
		String masking = String.join("-", sArr);
		System.out.println(masking);
	}
	
	public void ex04() {
		/*
		 * 사용자 입력으로 이메일 주소 형식을 입력받을 떄 xxxxx@gmail.com 또는 xxxxx@naver.com,
		 * xxxxx@nate.com 의 주소형식만 받을 수 있도록 한다.(xxxxx 는 5글자 제한 아님)
		 */
		String[] emailDomain = new String[] {
				"gmail.com", "naver.com", "nate.com"
		};
		
		while(true) {
			System.out.print("이메일 주소 입력 : ");
			String input = sc.nextLine();
			
			// @ 가 포함된 이메일 주소 형식인지 확인
			String[] email = input.split("@");
			if(email.length == 2) {
				boolean isValiad = false;
				// 지정된 도메인 주소가 맞는지 확인
				for(int i = 0; i < email.length; i++) {
					if(email[i].equals(emailDomain[i])) {
						isValiad = true;
						break;
					}
				}
				if(isValiad) {
					System.out.println("이메일 주소를 확인하였습니다.");
					break;
				} else {
					System.out.println("허용된 이메일 주소 도메인이 아닙니다.");
				}
			} else {
				System.out.println("이메일 주소 형식이 아닙니다. 다시 입력하새요.");
			}
		}
	}
	
	public static void main(String[] args) {
		Sample02_C sample = new Sample02_C();
//		sample.ex01();
//		sample.ex02();
//		sample.ex03();
		sample.ex04();
	}
}
