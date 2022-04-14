package exam05;

public class Student_C extends Person_C {
	private int classLevel;
	private int classRoom;
	
	public int getClassLevel() {
		return classLevel;
	}
	
	public Student_C(String name, int age) {
		super(name, age);
	}
	
	public void setClassLevel(int classLevel) {
		this.classLevel = classLevel;
	}
	
	public int getClassRoom() {
		return classRoom;
	}
	
	public void setClassRoom(int classRoom) {
		this.classRoom = classRoom;
	}
	
	
}
