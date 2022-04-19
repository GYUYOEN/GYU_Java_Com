package exam07;

public class Sample01_C {

	public static void main(String[] args) {
		Initialize_C init = new Initialize_C();
		
		System.out.println("JVM 초기값 : " + init.num1 + "," + Initialize_C.name1);
		System.out.println("명시적 초기값 : " + init.num2 + "," + Initialize_C.name2);
		System.out.println("초기화 블럭 : " + init.num3 + "," + Initialize_C.name3);
		
		Constructor_C con1 = new Constructor_C(10);
		System.out.println(con1.num1);
		
		Constructor_C con2 = new Constructor_C(20);
		System.out.println(con2.num1);
		
		// 기본 생성자로 초기화 : 매개변수 생성자가 있는 경우 기본생성자 자동으로 생성 x, 만들어줘야함
		Constructor_C con3 = new Constructor_C(); // 기본생성자는 없고 매개변수가 있는 생성자만 있을 경우 오류 -> The constructor Constructor_C() is undefined
		System.out.println(con3.num1); // JVM 초기값으로 초기화
		
		char c = 65;
		Constructor_C con4 = new Constructor_C(c);
		
//		Constructor_C con4 = new Constructor_C((char)65);
		
//		byte b = 65;
//		Constructor_C con4 = new Constructor_C(b);
		// ...
		
		
		Constructor_C con5 = new Constructor_C(10);
		Constructor_C con6 = new Constructor_C(10, 20);
		Constructor_C con7 = new Constructor_C(10, 20, 30);
		Constructor_C con8 = new Constructor_C(10, 20, 30, 40);
	}

}

