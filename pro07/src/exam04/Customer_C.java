package exam04;

import java.util.Objects;

/*
 * 고객
 * 		일반 고객
 * 			이름, 나이, 성별
 * 		프리미엄 고객
 * 			이름, 나이, 성별, 할인률, 누적구입액
 * 
 * 프리미엄 고객의 경우 누적구입액에 따라 할인률을 자동적으로 부여하는 기능이 있다.
 * 		- 누적구입액이 1,000,000 이상의 경우 할인률은 2%
 *		- 누적구입액이 3,000,000 이상의 경우 할인룰은 5%
 *		- 누적구입액이 10,000,000 이상의 경우 할인률은 10%
 */

public class Customer_C {
	private String name;
	private int age;
	private char gender;
	
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
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void buy(String productName, int price) {
		System.out.printf("%s 상품을 %,d 원에 구입하셨습니다.\n", productName, price);
	}
	
	@Override
	public String toString() {
		return "Customer_C [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, gender, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer_C other = (Customer_C) obj;
		return age == other.age && gender == other.gender && Objects.equals(name, other.name);
	}
	
	
}
