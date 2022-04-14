package exam07;

public class Sample02_C {

	public static void main(String[] args) {
		Book_C book1 = new Book_C();
		Book_C book2 = new Book_C("Java Programming");
		Book_C book3 = new Book_C("Java Programming", 123456789);
		Book_C book4 = new Book_C("Java Programming", 123456789, "홍길동");
		Book_C book5 = new Book_C("Java Programming", 123456789, "홍길동", 5);
		
		// 객체 생성 없이 사용
		// Book_C.method2();
	}

}
