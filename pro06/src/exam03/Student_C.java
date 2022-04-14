package exam03;

import java.util.Random;

public class Student_C {
	private String name;	// 이름
	private int age;		// 나이
	private int classLevel;	// 학년
	private int classRoom;	// 반
	private int classNum;	// 번호
	
	public Student_C(String name) {
		// this.name = name;
		// this.age = 14;
		// this.classLevel = 1;
		this(name, 14, 1);
	}
	
	public Student_C(String name, int age) { // 빠른년생
		// this.name = name;
		// this.age = age;
		// this.classLevel = 1;
		this(name, age, 1);
	}
	
	public Student_C(String name, int age, int classLevel) {
		this.name = name;
		this.age = age;
		this.classLevel = classLevel;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getClassLevel() {
		return this.classLevel;
	}
	
	public void nextYear() {
		this.age++;
		
		if(this.classLevel == 3) {
			this._graduate();
		} else {
			this.classLevel++;
			this.classRoom = this._assignClassRoom();
			// return 값이 있으므로 this._assignClassRoom()
		}
	}
	
	private int _assignClassRoom() { // return할 값이 있을 때
		Random rand = new Random();
		return rand.nextInt(9) + 1;
	}
	
	private void _graduate() {
		this.classLevel = -1;
		this.classRoom = -1;
		this.classNum = -1;
	}
	
	public String toString() {
		return String.format("%s(%d): %d 학년 %d 반", this.name, this.age, this.classLevel, this.classRoom);
		// return this.name + "(" + this.age + "): " + this.classLevel + " 학년 " + this.classRoom + " 반";
	}
}
