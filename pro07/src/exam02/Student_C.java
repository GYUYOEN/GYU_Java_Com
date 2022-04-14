package exam02;

public class Student_C extends Person_C {
	private int classLevel; // 학년
	private int classRoom;	// 반
	
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
	
	// ClassLevel과 ClassRoom도 출력하고 싶을 때
	@Override
	public String toString() {
		return super.toString() + " : " + getClassLevel() + " : " + getClassRoom();
	}
}

