package exam07;

public class Constructor_C {
	public int num1;
	public int num2;
	public int num3;
	public int num4;
	
	// 초기화할 것이 없으면 생성자 메서드 없어도 됨
	// 기본 생성자 메서드
	
	public Constructor_C() {}
	
	// 매개변수가 있는 생성자
	// 매개변수가 있는 생성자가 없는 경우 JVM이 기본 생성자 자동 생성
	// 메개변수가 있는 생성자가 있는 경우 JVM이 자동 생성하지 않음 	
	// 이쪽 num1은 외부에서 들어오 변수
	// public Constructor_C(int num1) {
	//		// 전달 받음 매개변수 값을 이용하여 초기화
		
	// 		// this : 내부에 있는 변수. 자기자신
	// 		this.num1 = num1;
	// 		System.out.println("int");
	// }
	
	public Constructor_C(int num1) {
		this.num1 = num1;
	}
	
	public Constructor_C(int num1, int num2) {
		this(num1); // 위에 있는 생성자 호출, 반드시 첫 줄 선언
		this.num2 = num2;
	}
	
	public Constructor_C(int num1, int num2, int num3) {
		this(num1, num2); // 위에 있는 생성자 호출
		this.num2 = num2;	
	}
	
	public Constructor_C(int num1, int num2, int num3, int num4) {
		this(num1, num2, num3); // 위에 있는 생성자 호출
		this.num2 = num2;	
	}
	
	// 오버로딩
	// public Constructor_C(int num1, int num2) {
	//		this.num1 = num1;
	// }
	
	// 이렇게는 사용 안됨(같은 매개변수 개수, 같은 매개변수 타입) : new Constructor(10,20) 헷갈림
	// public Constructor_C(int num1, int num2) {}
	// public Constructor_C(int num1, int num2) {} 
	
	// 이렇게는 가능, 구분이 가능하면 됨
	// public Constructor_C(int num1, String num2) {}
	// public Constructor_C(String  num1, int num2) {}
	// public Constructor_C(int num3, int num4) {}
	
	
	// 일반 메서드
	// void(반환형) 위 생성자 메서드에는 반환형이 없음 : 반환할 것이 없으므로
	// public void method() {
	//	
	// }
	public Constructor_C(byte b) {
		System.out.println("byte");
	}
	public Constructor_C(char c) {
		System.out.println("char");
	}
	public Constructor_C(short s) {
		System.out.println("short");
	}
	public Constructor_C(long l) {
		System.out.println("long");
	}
}
