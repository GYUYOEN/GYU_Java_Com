package exam05;

public class Main_C {
	
	public static void main(String[] args) {
		Person_C p1 = new Person_C("홍길동");
		System.out.println(p1.getName());
		
		Person_C p2 = new Person_C("박지수");
		System.out.println(p2.getName());
		
		Person_C p3 = new Person_C("김정우");
		System.out.println(p3.getName());
		
		System.out.println("--------------------------");
		
		// 배열을 이용한 객체 사용법 -> 객체배열
		Person_C[] pArr = new Person_C[4];
		pArr[0] = p1;
		pArr[1] = p2;
		pArr[2] = p3;
		pArr[3] = new Person_C("최가은");
		
		for(int i = 0; i < pArr.length; i++) {
			System.out.println("pArr[" + i + "] -> " + pArr[i].getName()); 
		}
		
		System.out.println("--------------------------");
		
		PersonList_C pList = new PersonList_C(pArr);
		
		for(int i = 0; i < pList.length(); i++) {
			Person_C data = pList.get(i);
			System.out.println(data.getName());
		}
		
		System.out.println("--------------------------");
		// 추가
		pList.add("이장수");
		pList.add("이세영");
		pList.add("최종현");
		
		for(int i = 0; i < pList.length(); i++) {
			Person_C data = pList.get(i);
			System.out.println(data.getName());
		}
		
		System.out.println("--------------------------");
		// 수정
		pList.update("홍길동", "홍길명");
		pList.update(1, "박채은");
		
		for(int i = 0; i < pList.length(); i++) {
			Person_C data = pList.get(i);
			System.out.println(data.getName());
		}
		
		System.out.println("--------------------------");
		// 삭제
		pList.remove("김정우");
		pList.remove(2);
		
		for(int i = 0; i < pList.length(); i++) {
			Person_C data = pList.get(i);
			System.out.println(data.getName());
		}
	}
	
}
