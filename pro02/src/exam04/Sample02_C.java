package exam04;

public class Sample02_C {

	public static void main(String[] args) {
		/* 
		 * 산술 연산자
		 * +, -, *, /(나누기), %(나머지)
		 */
		int num1,num2;
		
		num1 = 10; num2 = 3;
		
		System.out.printf("%d * %d = %d\n", num1, num2, num1 + num2);
		System.out.printf("%d - %d = %d\n", num1, num2, num1 - num2);
		System.out.printf("%d * %d = %d\n", num1, num2, num1 * num2);
		System.out.printf("%d / %d = %f\n", num1, num2, (double)num1 / num2); // 소수점을 출력하고 싶을 때는 잠시 형번환 시켜줌
		System.out.printf("%d / %d = %f\n", num1, num2, num1 / (double)num2); // 앞 뒤 상관없음
		System.out.printf("%d %% %d = %d\n", num1, num2, num1 % num2); // % 중복 -> 이스케이프 활용
		
		/*
		 * 비교 연산자
		 * ==(같다), !=(다르다), <(왼쪽 피연산자 값이 작다), >(왼쪽 피연산자 값이 크다)
		 * <=(왼쪽 피연산자 값이 작거나 같다), >=(왼쪽 피연산자 값이 크거나 같다)
		 * 바교 연산의 결과는 true 또는 false의 논리 값이다.
		 */
		num2 = 10;
		System.out.printf("%d == %d -> %b\n", num1, num2, num1 == num2);
		System.out.printf("%d != %d -> %b\n", num1, num2, num1 != num2);
		System.out.printf("%d < %d -> %b\n", num1, num2, num1 < num2);
		System.out.printf("%d > %d -> %b\n", num1, num2, num1 > num2);
		System.out.printf("%d <= %d -> %b\n", num1, num2, num1 <= num2);
		System.out.printf("%d >= %d -> %b\n", num1, num2, num1 >= num2);
		
		/* 
		 * 논리 연산자
		 * &&(and 연산), ||(or 연산)
		 * true, false 논리 값을 가지고 연산을 수행해야 한다.
		 * 반복문에 많이 사용
		 */
		System.out.printf("%b && %b -> %b\n", true, true, true && true); // 둘 다 참일 때에만 참, 하나 라도 거짓이면 거짓
		System.out.printf("%b &&  %b -> %b\n", true, false, true && false);
		System.out.printf("%b && %b -> %b\n", false, true, false && true);
		System.out.printf("%b && %b -> %b\n", false, false, false && false);
		
		System.out.printf("%b || %b -> %b\n", true, true, true || true); // 둘 다 거짓일 때에만 거짓, 하나 라도 참이면 참
		System.out.printf("%b || %b -> %b\n", true, false, true || false);
		System.out.printf("%b || %b -> %b\n", false, true, false || true);
		System.out.printf("%b || %b -> %b\n", false, false, false || false); 
		
		
		
	}
}
