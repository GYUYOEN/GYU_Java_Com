package exam01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

// pList안에 있는 요소들을 sorting 하기 위해서는 Comparable 인터페이스 등록 
// 비교가능한 Person 객체
// 기본정렬
class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	// 기존의 것을 바꾸고 싶을때 사용
	// 내림차순
	@Override
	public int compareTo(Person o) {
		if(this.getName().compareTo(o.getName()) > 0) {
			return 1;
		} else if(this.getName().compareTo(o.getName()) < 0) {
			return -1;
		} else {
			if(this.getAge() > o.getAge()) {
				return -1;
			} else if(this.getAge() < o.getAge()) {
				return 1;
			}
		}
		return 0;
		}
	}


public class Sample01_C {
	
	public static void main(String[] args) {
		/*
		 * List 계열 컬렉션 - ArrayList
		 */
		// E(제너럴 차입) : 리스트 컬렉션에 저장할 참조타입을 저장해줌
//		List<Integer> aList = new ArrayList<Integer>();
//		List<Integer> aList = new Vector<Integer>();
		List<Integer> aList = new LinkedList<Integer>();
		
		// 자동적으로 정수타입으로 저장됨
		aList.add(100);
		aList.add(200);
		aList.add(300);
		System.out.println(aList);
		
		
		aList.add(2, 400);
		System.out.println(aList);
		
		List<Integer> bList = new ArrayList<Integer>();
		bList.add(500); bList.add(600); bList.add(700);
		
		aList.addAll(bList);
		System.out.println(aList);
		
		// 기존의 객체를 빼고 새로운 객체를 추가
		Integer i1 = aList.set(2, 350); 
		System.out.println(i1 + " / " + aList);
		aList.set(3, 450);
		System.out.println(i1 + " / " + aList);
		
		// 주어진 객체가 저장되어 있는지 확인
		// 객체 타입으로 변경해줘야 함 : Object를 요구하기 때문
		// equals랑 유사
		boolean result1 = aList.contains(Integer.valueOf(350));
		System.out.println(result1);
		
		// 몇번 인덱스에 있는지 확인
		int result2 = aList.indexOf(Integer.valueOf(350));
		System.out.println(result2); // 없으면 -1이 나옴
		
		// 주어진 인덱스에 저장된 객체 리턴
		System.out.println(aList.get(0));
		System.out.println(aList.get(1));
		System.out.println(aList.get(2));
		
		// size 만큼의 데이터가 저장되어 있다.
		System.out.println(aList.size());
		
		for(int i = 0; i < aList.size(); i++) {
			System.out.println(aList.get(i));
		}
		
		// 컬렉션이 비어있는지 조사
		result1 = aList.isEmpty();
		System.out.println(result1);
		
		// 저장된 모든 객체를 삭제
		bList.clear();
		result1 = bList.isEmpty();
		System.out.println(result1);
		
		// 리턴 타입은 List<Integer>에서 <>에 있는 것에 맞춰서 리턴함
		// 삭제한 데이터를 i1에 리턴함(반환, 저장)
		// 임시 삭재 -> 삭제한 데이터 i1에 저장됨
		i1 = aList.remove(0);
		System.out.println(i1 + " / " + aList);
		
		System.out.println("<<<<< Iterator 사용 >>>>>");
		Iterator<Integer> iter = aList.iterator();
		// hasNext() : 반복할 객체가 있는지 확인
		while(iter.hasNext()) {
			Integer i2 = iter.next(); // next() : 반복할 객체가 있으면 꺼내옴
			System.out.println(i2);
		}
		
		// Iterator라는 메서드가 있으면 for each문 쓸수 있음 -> 다른 컬랙션들도 마찬가지
		System.out.println("<<<<< for each 문 >>>>>");
		// aList에 있는 요소들이 모두 소진 될때까지 i3에 aList 객체의 Integer를 하나씩 담음
		for(Integer i3: aList) {
			System.out.println(i3);
		}
		
		// 0번 인덱스를 마지막 위치로 마지막 인덱스를 0으로
		System.out.println("리버스 전 : " + aList);
		Collections.reverse(aList);
		System.out.println("리버스 후 : " + aList);
		
		// 오름 차순으로 정렬 : 크기순
		System.out.println("정렬 전 : " + aList);
		Collections.sort(aList);
		System.out.println("정렬 후 : " + aList);
		
		// 내림차순으로 정렬하기 위해서는 오름차순 후 리버스
		
		// 내림차순 위 방법이 마음에 안들 때, 바로 내림차순을 하고 싶을 때
		// 객체를 정렬할 때 많이 사용 : 사람 => 이름순, 나이순(이름 같으면), 성별순(나이 같으면) 순서대로 나열하기
		Collections.sort(aList, new Comparator<Integer>() {
			// 0번(i1), 1번(i2) -> 1번, 2번... 이렇게 순차적으로 비교하여 위치를 바꿔줌 
			// 반환값을 바꾸면 오름차순이 됨
			@Override
			public int compare(Integer i1, Integer i2) {
//				return i1 > i2 ? -1 : (i1 == i2 ? 0 : 1);
				if(i1 > i2) {
					return -1; // -1 일때 내림차순 정렬, 반환값을 1과 바꾸면 오름차순
				} else if(i1 == i2) {
					return 0;
				}
				return 1;
			}
			
		});
		System.out.println("내립차순 정렬 후 : " + aList);
		
		List<Person> pList = new ArrayList<Person>();
		pList.add(new Person("홍길동", 23));
		pList.add(new Person("김철수", 25));
		pList.add(new Person("김철수", 21));
		
		Person p1 = pList.get(0);
		Person p2 = pList.get(1);
		Person p3 = pList.get(2);
		
		// compareTo : 문자열의 비교
		// 홍길동의 유니코드값 - 김철수의 유니코드값
		System.out.println(p1.getName().compareTo(p2.getName()));
		// 김철수의 유니코드값 - 홍길동의 유니코드값 : 음수, 홍길동이 더 뒤에 있고 김철수는 홍길동보다 더 앞에 잇음
		// 같은 값이면 0
		System.out.println(p2.getName().compareTo(p1.getName()));
		System.out.println(p2.getName().compareTo(p3.getName()));
		
		Collections.sort(pList, new Comparator<Person>() {
			
			// 오름차순
			// 기본정렬을 바꾸고 싶을 때 사용
			@Override
			public int compare(Person p1, Person p2) {
				if(p1.getName().compareTo(p2.getName()) > 0) {
					return 1;
				} else if(p1.getName().compareTo(p2.getName()) < 0) {
					return -1;
				} else {
					if(p1.getAge() > p2.getAge()) {
						return 1;
					} else if(p1.getAge() < p2.getAge()) {
						return -1;
					}
				}
				return 0;
			}
		});
		
		for(Person p : pList) {
			System.out.println(p.getName() + " | " + p.getAge());
		}
		
		System.out.println("=========================");
		
		// Person 객체에 implements Comparable<Person> 안하면 Collections.sort에 에러뜸
		// sort : Comparable 한것만 인식
		Collections.sort(pList);
		for(Person p : pList) {
			System.out.println(p.getName() + " | " + p.getAge());
		}
	}
}
