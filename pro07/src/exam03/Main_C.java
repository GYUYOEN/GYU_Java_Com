package exam03;

public class Main_C {

	public static void main(String[] args) {
		Subject_C s1 = new Subject_C("국어");
		Subject_C s2 = new Subject_C("영어", 78.3);
		
		System.out.println(s1.getName());
		System.out.println(s2.getName());
		
		System.out.println(s1.getGrade());
		System.out.println(s2.getGrade());
		System.out.println(s2.getGrade().getPoint());
	}

}
