package exam03;

public class Sample02_C {

	public static void main(String[] args) {
		/*
		 * 지역 변수 사용할 때 주의사항
		 *      지역변수를 사용할 때에는 반드시 초기화가 되어 있어야 한다.
		 */
		int i1; // 선언만 한 상태
		i1 = 1;
		
		System.out.println(i1);
		
		/*
		 * 상수 : 변하지 않는 값을 의미한다.
		 * 자바 프로그램에서는 한 번 초기화를 한 후에는 변경되지 않는 것을 의미
		 */
		final int number;
		number = 2;
		
		/*
		 * 변수명 명명 규칙
		 *      1. 한글 사용 가능하나 가급적 사용하지 않는 것이 좋음.
		 *      2. 영문자는 대소문자 구분하여 사용해야 함.
		 *      3. 띄어쓰기 금지, 대산 _(언더바) 사용
		 *      4. 숫자는 영문자 뒤에 작성
		 *      5. 예액어를 사용하면 안됨.
		 *      6. 자바에서는 $ 특수문자도 사용할 수 있다.
		 *      
		 *      옵션
		 *      - 변수나 메서드명은 소문자로만 작성하되 두개 이상의 단어 조합을 사용하는 경우 
		 *        두번째 이후 단어의 첫번째 문자는 대문자로 만들어야 한다. -> 카멜케이스 camelcase
		 *      - 클래스명은 모든단어의 첫번째문자는 대문자로 만들어야한다.
		 *      - 상수명은 대문자로만 작성한다.
		 *      
		 *      권고 사항
		 *          상수명을 작성할 때 반드사 대문자로만 작성할 것.
		 */
		
		String str1 = "기차" + 123 +45 + "출발";
		String str2 = 123 + 45 + "기차" + "출발";
		String str3 = "기차" + "출발" + 123 + 45;
		String str4 =  "기차" + "출발" + (123 + 45);
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);

	}

}
