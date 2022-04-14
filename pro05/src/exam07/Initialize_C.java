package exam07;

public class Initialize_C {
	public int num1; // JVM 초기값
	public static String name1;
	
	public int num2 = 10; // 명시적 초기값
	public static String name2 = "java";
	
	public int num3 = 20;
	public static String name3 = "programming"; // 인스턴스 초기화 블럭 초기값
	
	{ 
		num3 = num3 + 10;
		// name3 = name2 + " " + name3; // 정적변수 넣어줘도 상관없음
	}
	
	static { // 정적변수만 넣어줘야함 // 인스턴스 초기화 블럭 초기값
		// num3 = num3 + 10; -> 오류뜸 : the non-static field num3
		name3 = name2 + " " + name3;
	}
}

