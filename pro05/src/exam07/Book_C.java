package exam07;

public class Book_C {
	public String name;			// 책이름
	public String writer;		// 작가
	public int rentDays;		// 대여기간
	public int codeNumber;		// 코드번호
	public int rentPrice;		// 대여금액
	public String publisher;	// 츨핀시
	public String category;		// 분류
	public String libraryName;	// 책보유도서관
	
	public Book_C() {}
	
	// 필요한 만큼만 생성
	public Book_C(String name) {
		this.name = name;
	}
	
	public Book_C(String name, int codeNumber) {
		this(name);
		this.codeNumber = codeNumber;
	}
	
	public Book_C(String name, int codeNumber, String writer) {
		this(name, codeNumber);
		this.writer = writer;
	}
	
	public Book_C(String name, int codeNumber, String writer, int rentDays) {
		this(name, codeNumber, writer);
		this.rentDays = rentDays;
	}
	
	public Book_C(String name, int codeNumber, String writer, int rentDays, int rentPrice) {
		this(name, codeNumber, writer, rentDays);
		this.rentPrice = rentPrice;
	}
	
	// 객체 생성 없이 사용
	// public void method1() {
	//	 	System.out.println("메서드 실행 됨");
	// }
	
	// public void method2() {
	//		System.out.println("메서드 실행 됨");
	// }
}
