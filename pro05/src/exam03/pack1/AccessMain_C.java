package exam03.pack1;

//AccessMain_C : 자식
//extends AccessParent_C : 부모
public class AccessMain_C extends AccessParent_C { // 상속
	public int v1;
	protected int v2;
	int v3;
	private int v4;
	
	public void method1() { // 해당 클래스 내부 안에 다가능
		// 해당 클래스 내부에 있는 멤버 변수에 접근
		System.out.println(v1); // public 가능
		System.out.println(v2); // protected 가능
		System.out.println(v3); // default 가능
		System.out.println(v4); // private 가능
	}
	
	public void method2() {
		// 다른 클래스, 같은 패키지 그리고 후손
		System.out.println(p1); // public 가능
		System.out.println(p2); // protected 가능
		System.out.println(p3); // default 후손이지만 같은 패키지라서 가능
		System.out.println(p4); // private 불가능
	}
}
