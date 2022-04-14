package exception;

// Exception : checked exception -> 오류뜸 -> try, catch or throw 해야함
// 처리하기 어렵고 주의하면서 실행헤라
// RuntimeException : unchecked exception -> 오류안뜸, 실행할때 오류뜸
// 개발자가 고칠 수 있는 범위
public class GradeException_C extends RuntimeException {

	public GradeException_C() {
		super();
	}

	public GradeException_C(String message) {
		super(message);
	}
	
}
