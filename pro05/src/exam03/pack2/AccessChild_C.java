package exam03.pack2;

import exam03.pack1.AccessParent_C;

//AccessMain_C : 자식
//extends AccessParent_C : 부모
public class AccessChild_C extends AccessParent_C { // 상속
	public void method() {
		// 다른 클래스, 다른 패키지 하지만 후손임.
		System.out.println(p1); // public 가능
		System.out.println(p2); // protected 후손 클래스라서 가능  
		System.out.println(p3); // default 불가능
		System.out.println(p4); // private 불가능
	}

}
