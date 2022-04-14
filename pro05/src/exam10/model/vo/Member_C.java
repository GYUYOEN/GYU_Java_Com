package exam10.model.vo;

public class Member_C {
	private String memberid;
	private String memberPwd;
	private String memberName;
	private int age;
	private char gender;
	private String phone;
	private String email;
	
	// 기본 생성자는 생략해서 자동으로 생성되게 만듬
	// public Member_C() {}
	
	public void changeName(String name) {
		memberName = name;
	}
	
	public void printName() {
		System.out.print(memberName);
	}
	
}