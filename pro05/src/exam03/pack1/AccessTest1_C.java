package exam03.pack1;

public class AccessTest1_C { 

	public static void main(String[] arg) {
		// 다른 클래스, 같은 패키지에서 AccessMain 클래스의 멤버변수에 접근
		AccessMain_C m1 = new AccessMain_C();
		System.out.println(m1.v1); // public 가능
		System.out.println(m1.v2); // protected 가능 
		System.out.println(m1.v3); // default 가능
		System.out.println(m1.v4); // v4 is not visible -> private 불가능
	}
}

