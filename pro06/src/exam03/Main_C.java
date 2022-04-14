package exam03;

public class Main_C {

	public static void main(String[] args) {
		Student_C std = new Student_C("홍길동", 13);
		
		std.nextYear(); // 초기값 설정을 위해 매개변수 생성자 필요
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getClassLevel());
		System.out.println(std);
	}

}
