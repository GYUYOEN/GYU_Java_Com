package exam02;

public class Sample03_C {

	public static void main(String[] args) {
		// 값을 감소 시키면서 반복
		for(int i = 99; i > 0; i--) {
			System.out.println(i);
		}
		
		// 문자를 1씩 증가시키면서 반복 문자에는 대응하는 문자코드(정수코드) 가 있기 때문에 가능
		for(char c = 'A'; c <= 'Z'; c++) {  // 문자에는 정수코드가 있음. ex) A = 65
			System.out.println(c);
		}
		
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		
		for(char c = 'a'; c <= 'z'; c++) {
			System.out.println(c);
		}
		
		for(char c = '가'; c <= '강'; c++) {
			System.out.println(c);
		}
		
		for(char c = 'ㄱ'; c <= 'ㅎ'; c++) {
			System.out.println(c);
		}
		
		for(char c = 'ㅏ'; c <= 'ㅣ'; c++) {
			System.out.println(c);
		}



	}
	
}

