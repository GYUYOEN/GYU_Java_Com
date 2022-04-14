package exam02;

//import exam01_C.*; -> *을 쓸 경우 이 패키지 안에 있는 거 모두 사용가능
//import java.util.*;
import exam01.PersonPub_C;
import exam01.PersonDef; // -> PersonDef is not visible

public class Sample01_C {

	public static void main(String[] args) {
		PersonPub_C p1 = new PersonPub_C();
		
		// import 를 안쓸 경우
		// exam01_C.PersonPub p1 = new exam01_C.new PersonPub(); 페키지 명을 명시해야함
		
		PersonDef p2 = new PersonDef(); // public을 안써서 오류뜸
		// 왠만하면 public을 써야함

	}

}