package exam02;

public class Main_C {

	public static void main(String[] args) {
		Person_C p = new Person_C();
		p.setName("이름");
		p.setAge(10);
		p.setGender('M');
		System.out.println(p);
		
		Student_C s = new Student_C();
		s.setName("홍길동");
		s.setAge(16);
		s.setGender('M');
		s.setClassLevel(1);
		s.setClassRoom(5);
		System.out.println(s);
		
		Teacher_C t = new Teacher_C();
		t.setName("박주성");
		t.setAge(30);
		t.setGender('M');
		t.setClassLevel(1);
		t.setClassRoom(5);
		t.setSubject("영어");
		System.out.println(t);
		
		Person_C p1 = new Person_C();
		Person_C p2 = new Person_C();
		
		p1.setName("김정수");		p1.setAge(25);		p1.setGender('M');
		p2.setName("김정수");		p2.setAge(25);		p2.setGender('W');
		
		System.out.println(p1.equals(p2));
	}

}