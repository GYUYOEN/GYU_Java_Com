package exam10.run;

import exam10.model.vo.Member_C;

public class Run1_C {

	public static void main(String[] args) {
		Member_C m = new Member_C();
		
		m.changeName("홍길동");
		m.printName();
	}

}