package exam01;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * Java - 객체지향언어
		 *      - 현실 세계에 존재하는 객체들 간의 관계(상호작용)를 프로그램 안에서도
		 *        동일하게 사용할 수 있도록 만들어 두었기 때문에 이를 객체지향언어라 한다.
		 *      - 자바에서는 이러한 객체가 Class 라고하는 단위로 정의가 되고 이 Class 를 통해
		 *        생성이 되면 메모리에 저장이 된다.
		 *      - Class 를 이용하여 객체를 만들 때에는 new 연산자를 사용하게 된다.
		 *      - 객체에는 변수, 배열, 객체, 메서드 등이 포함되어 사용된다.
		 *      - 객체를 만들기 위해서 Class 를 사용하는데 이 클래스는 객체의 설계도로 셍각하면 된다. -> 블루프린트
		 *      - class 로 부터 생성된 객체는 인스턴스(instance)라는 이름으로도 불리운다. 인스터스는 객체에 포함되어 있음
		 */
		
		PersonPub_C p1 = new PersonPub_C(); // p : 인스턴스(객체). new 연산자로 생성
		PersonDef p2 = new PersonDef();
		
		System.out.println("실행 완료!");
	}

}
