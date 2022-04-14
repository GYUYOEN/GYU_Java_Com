package exam03.pack2;

import exam03.pack1.AccessMain_C;

public class AccessTest2_C { // 외부 페키지(전체)
	
	public static void main(String[] args) {
		// 다른 클래스, 다른 패키지에서 AccessMain 클래스의 멤버 변수에 접근
	AccessMain_C m1 = new AccessMain_C();
	System.out.println(m1.v1); // public 가능
	System.out.println(m1.v2); // protected 다른 패키지 붕가능 
	System.out.println(m1.v3); // default 다른 패키지 불가능
	System.out.println(m1.v4); // private 불가능
	}
	
}
