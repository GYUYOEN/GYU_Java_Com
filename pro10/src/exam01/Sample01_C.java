package exam01;

public class Sample01_C {

	public static void main(String[] args) {
		MyException_C m = new MyException_C();
		
//		String s = null;
//		s.length();
		
		System.out.println("에러 발생 전.");
//  	m.exceptionThrows(); // 오류 발생 -> "에러 발생 후 처리 완료!" 실행 x -> 강제 종료
		// -> 이렇게 처리하면 해결
		try {
			m.exceptionThrows();
		} catch(Exception e) {
			// 빨간글씨 나오는게 싫으면 이거만 출력 ->
//			System.out.println("Sample01_C.main 12줄 에러 발생!!");
			// e.printStackTrace() : 딱히 처리할 건 없고 어떤 에러가 발생햇는지 프린트하고 싶을 때 사용(에러 경로 추적)
			// -> 강제종료x, "에러 발생 후 처리 완료!" 출력
			e.printStackTrace();
		}
		
//		m.exceptionNonThrows();
		System.out.println("에러 발생 후 처리 완료!");

	}

}
