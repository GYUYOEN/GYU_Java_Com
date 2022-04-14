package model.vo;

import java.util.Random;
import exception.PasswordUnvalidException_C;
import exception.PasswordUnvalidException_C;

public abstract class Account_C {
	
	private String name;
	private String password;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
//		boolean numExisted = false;
//		boolean lowerExisted = false;
//		boolean upperExisted = false;
//		for(int i = 0; i < password.length(); i++) {
//			if(password.charAt(i) >= '0' && password.charAt(i) <= '9') {
//				numExisted = true;
//			}
//			
//			if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
//				lowerExisted = true;
//			}
//			
//			if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
//				upperExisted = true;
//			}
//		}
//		
//		if(!(numExisted && (lowerExisted || upperExisted))) {
//			throw new PasswordUnvalidException_C("패스워드에는 숫자/영문자 조합으로 만들어야 합니다.");
//		}
		
		// 정규표현식 사용
		// {4, 12} : 글자수 4 ~ 12
		// [^0-9] : 0-9 벙위를 제외(부정 x), 
		// ^[0-9] : 이 범위 안에서 시작, [0-9]{4, 12}$ : 이 범위안에서(숫자로 끝) -> 전체에서 시작과 끝(라인x -> 라인은 flag 사용)
		// 구글에 regex 쳐서 정규 표현식 활용
		// -> [abcdef] = [a-f], [a-f]{2} : a~f 중 두자 연속인것
		// [0-9]{3}-[0-9]{4}-[0-9]{4} : 한번에 찾기 가능
		// [0-9]{2,3} : 2자리나 3자리 숫자
		if(!(password.matches("[0-9A-Za-z]{4,12}") // 참이야 하고
				&& !password.matches("[0-9]{4,12}") // 거짓이어야 하고
				&& !password.matches("[a-zA-Z]{4,12}"))) { // 거짓이어야 하고
			throw new PasswordUnvalidException_C("패스워드에는 숫자/영문자 조합으로 만들어야 합니다.");
		}
		
		this.password = password;
	}
	
	// 패스워드 변경
	public boolean changePassword(String curPass, String changePass) {
		if(curPass.equals(this.getPassword())) {
			this.setPassword(changePass);
			return true;
		}
		return false;
	}
	
	// 패스워드를 초기화한다.(영문자 6자를 임의로 생성하여 초기화한다.)
	// 학생용 계정은 STD_ 접두사가 붙어서 생성되게 한다.
	// 선생님용 계정은 TCH_ 접두사가 붙어서 생성되게 한다.
	public String resetPassword() {
		Random r = new Random();
		String newPass = "";
		
		for(int i = 0; i < 6; i++) {
			if(r.nextBoolean()) {
				// 대문자
				newPass += (char)(r.nextInt(26) + 65);
			} else {
				// 소문자
				newPass += (char)(r.nextInt(26) + 97);
			}
		}
		setPassword(newPass);
		return newPass;
	}
	
}
