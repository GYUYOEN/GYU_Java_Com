package exam05;

public class Main_C {

	public static void main(String[] args) {
		/*
		 * 다형성
		 * 		- 객체지향 프로그래밍의 3대 특징 중 하나
		 * 		- 하나의 객체가 여러 형태를 가진다는 의미
		 * 		- 상속을 사용하여 부모 타입으로 부터 파생된 여러 타입의 자식 객체를 
		 * 		  부모 클래스로 다룰 수 있도록 하는 기능
		 */
		
		/*
		 * 업 캐스팅 (형변환할 때 사용했었음)
		 * 		- 상위 객체로 변환하는 것
		 * 		- 자식 클래스가 부모 클래스로 변화하는 것 
		 * 		  -> 부모 타입의 참조변수에 자식 타입의 참조변수를 저장할 수 있다.
		 * 		- 자동으로 변환이 이루어지기 때문에 별도의 캐스팅 연산자를 사용할 필요가 없다.
		 * 		  (부모는 하나의 타입밖에 존재하지 않으므로. 자식의 경우 여러개 존재)
		 */
		
		Person_C s1= new Student_C("홍길동", 16);
		Person_C t1 = new Teacher_C("김철원", 28);
		
		// 한반에 담임선생님과 학생들이 있는 것을 나타냄
		Person_C[] p1 = new Person_C[3];
		p1[0] = new Teacher_C("김철원", 28);
		p1[1] = new Student_C("홍길동", 16);
		p1[2] = new Student_C("박장석", 16);
		
		// student와 teacher에 있는 classLevel과 classRoom을 출력하고 싶을 때
		// : 다운 캐스팅 사용
		for(int i = 0; i < p1.length; i++) {
			System.out.println("이름 : " + p1[i].getName());
			System.out.println("나이 : " + p1[i].getAge());
			System.out.println("============================");
			
		}
		
		/*
		 * 다운 캐스팅
		 * 		- 하위 객체로 변환하는 것
		 * 		- 업 캐스팅으로 부모 타입의 참조변수에 저장한 자식 객체의 참조변수를 
		 * 		  다시 원래의 타입으로 변환하기 위해 사용
		 */

		Person_C s2 = new Student_C("홍길동", 16);
		Student_C s3 = (Student_C) s2;
		s3.setClassLevel(1);
		s3.setClassRoom(2);
		
		Person_C t2 = new Teacher_C("김철원", 28);
		Teacher_C t3 = (Teacher_C) t2;
		t3.setClassLevel(1);
		t3.setClassRoom(2);
		t3.setSubject("국어");
	
		Person_C[] p2 = new Person_C[2];
		p2[0] = s3;		p2[1] = t3;
	
		for(int i = 0; i < p2.length; i++) {
			int level, room;
			String subject = "";
		
			if(p2[i] instanceof Student_C) {
				level = ((Student_C) p2[i]).getClassLevel();
				room = ((Student_C) p2[i]).getClassRoom();
				System.out.printf("%d 학년 %d 반 학생\n", level, room);
			} else if(p2[i] instanceof Teacher_C) {
				level = ((Teacher_C) p2[i]).getClassLevel();
				room = ((Teacher_C) p2[i]).getClassRoom();
				subject = ((Teacher_C) p2[i]).getSubject();
				System.out.printf("%d 학년 %d 반 %s 담당 담임선생님\n", level, room, subject);
			}
		}
	}
}
