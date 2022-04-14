package exception;

// 패스워드에 대해 영문자/숫자 조합으로 설정하게 만들기 위한 unchecked Exception 생성 및 적용

public class PasswordUnvalidException_C extends UnvalidException_C {

	public PasswordUnvalidException_C() {
		super();
	}

	public PasswordUnvalidException_C(String message) {
		super(message);
	}

}

