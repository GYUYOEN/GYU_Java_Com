package exam04;

public class Sample01_C {
	
	public static void main(String[] args) {
		// 논리 부정 연선자 -> !
		boolean b1 = true;
		boolean b2 = !b1;
		System.out.println("논리 부정 연산 결과 : " + b2);
		
		// 증감 연산자 -> ++, --
		//      - 증감 연산은 해당하는 변수의 값이 변화한다.
		int i1 = 1;
		int i2 = ++i1;
		int i3 = --i1;
		System.out.println("증감 연산 결과 : " + i2);
		System.out.println("증감 연산 결과 : " + i3);
		System.out.println("증감 연산 결과 : " + i1);
		
		/*
		 * 증감 연산의 전위/후위 연산
		 *   - ++, -- 연산 기호를 피연산자 앞에 붙이면 전위연산      
		 *   - ++, -- 연산 기호를 피연산자 뒤에 붙이면 후위연산
		 *   
		 * 전위 연산의 연산 순서
		 *       변수에 저장된 값을 증감 시켜 사용(할당)한 후 동일 변수에 값을 저장
		 * 
		 * 후위 연산의 연산 순서
		 *       변수에 저장된 값을 우선 사용(할당)한 후 증감된 값을 동일 변수에 저장
		 */
		int i4 = 1;
		int i5 = ++i4;
		
		int i6 = 1;
		int i7 = i6++;
		
		System.out.println("전위 연산 결과 : " + i5);
		System.out.println("후위 연산 결과 : " + i7);
		
		System.out.println("전위 후위에 사용한 변수의 값 : " + i4 +"," + i6);
	}
}
