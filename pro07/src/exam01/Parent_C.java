package exam01;

public class Parent_C {
	// public int number = 1;
	private int number = 1; // 자식에 직접적인 접근 불가능 
	
	// 상속관계에서는 super를 별도로 지정하지 않으면 기본생성자 작성
	// 그렇지 않으면 자식에 super()를 지정해줌
	public Parent_C() {}
	
	// 매개변수 생성자가 생성되면 기본생성자 안만들어짐
	public Parent_C(int number) {
		this.number = number;
	}
	
	// 간접적인 접근이 가능하도록 함
	public void setNumber(int number) {
		System.out.println("부모의 setNumber 메서드 동작 시작");
		this.number = number;
		System.out.println("부모의 setNumber 메서드 동작 끝");
	}
	
	public int getNumber() {
		return this.number;
	}
}
