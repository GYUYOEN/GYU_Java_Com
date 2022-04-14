package exam05;

public class Teacher_C extends Person_C {
	private int classLevel;
	private int classRoom;
	private String subject;
	
	public Teacher_C(String name, int age) {
		super(name, age);
	}
	
	
	public int getClassLevel() {
		return classLevel;
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
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
