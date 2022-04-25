package exam02;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
	
class Person {
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

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
	
}
	
public class Sample04_C {
	
	public static void main(String[] args) {
		Set<Person> pSet = new HashSet<Person>();
		
		pSet.add(new Person("홍길동", 23));
		pSet.add(new Person("김철수", 25));
		pSet.add(new Person("김철수", 25));
		
		// 객체안의 값까지 중복을 허용하지 않게 하고 싶을 경우 
		// -> 객체 안에 hashcode and equals 생성
		// 이름만 보고 중복 삭제하고 싶을 경우 hashcode and equals 생성할 때 이름만 체크하고 생성
		//  hashcode and equals 생성 안하면 이름, 나이 중복되도 출력됨
		
		System.out.println(pSet);
		
	}
}



