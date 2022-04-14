package exam10.model.vo;

public class Circle_C {
	private final double PI = 3.14;
	private int radius = 1;
	
	// 생략
	// public Circle_C() {}
	
	public void incrementRadius() {
		// 원 반지름의 크기를 +1 시키는 함수
		radius++;
	}
	
	public void getAreaOfCircle() {
		// 원 넓이를 출력하는 함수
		System.out.println(PI * radius * radius);
	}
	
	public void getSizeOfCircle() {
		// 원 둘레를 출력하는 함수
		System.out.println(2 * PI * radius);
	}
}
