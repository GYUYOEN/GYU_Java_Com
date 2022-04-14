package exam01;

public class MyException_C {
	
	public void exceptionThrows() throws Exception {
		// 현재 메서드를 실행한 위치로 발생된 에러를 던지는(throws) 메서드
		exceptionRun();
	}
	
	public void exceptionNonThrows() {
		// 현재 메서드에서 발생된 에러를 처리하는(try ... catch) 메서드
		try {
			exceptionRun();
		} catch(Exception e) { // exceptionRun() 메서드에서 던진 에러를 처리 -> 메인 오류x
			System.out.println("Exception 을 처리하였습니다.");
		}
	}
	
	// throws Exception : 발생된 에러를 호툴한 위치로 던짐
	private void exceptionRun() throws Exception {
		// 에러를 발생 시키기 위한 메서드
		System.out.println("에러 발생!");
//		throw new Exception(); // 강제로 에러 발생시킴
		throw new UserDefineException_C("사용자 정의 에러가 발생하였습니다.");
	}
}
