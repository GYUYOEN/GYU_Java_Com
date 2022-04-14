package exam01;

//부모에 기본생성자가 없는 상태면 오류가 뜸
public class Child_C extends Parent_C {
	
	// 생성자 호출
	public Child_C() {
		super(1);
	}
	
	public void printNumber() {
		// number += 1;
		// System.out.println(number);
		
		setNumber(2);
		System.out.println(getNumber());
	}
	
	// 똑같은 메서드가 부모와 자식에 모두 있음 : overriding(오버라이딩)
	// 기존 기능 수정/변경 하고 싶을 때 사용
	// 부모에 덧붙여서 자식에서 수행하고 싶을때 super 사용
	// 안해도 상관 없지만 오버라이딩이 가능한 메서드인지 확인시키는 용도로 쓰인다.
	// 자식도 실행되고 부모도 실행될 수 있게 해줌
	// 오버라이딩 없으면 부모만 실행 되고 super를 안쓰면 자식만 실행됨
	@Override
	public void setNumber(int number) {
		System.out.println("자식의 setNumber 메서드 동작 시작");
		super.setNumber(number);
		System.out.println("자식의 setNumber 메서드 동작 끝");
	}
	
	/*
	@Override 쓰면 오류뜸 : 오버라이딩 가능 메서드가 아님
	public void setName(String name) {
		
	}
	 */
}
