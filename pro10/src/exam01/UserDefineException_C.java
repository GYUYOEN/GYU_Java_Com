package exam01;

// 클래스 자체가 예외를 구분할 수 있는 클래스 이름으로 사용 가능 
// 라이브러리 만들 때 : 개발자한테 오류를 알려주기 위해서 사용
public class UserDefineException_C extends Exception {
	public UserDefineException_C() {}
	public UserDefineException_C(String message) {
		super(message);
	}
}
