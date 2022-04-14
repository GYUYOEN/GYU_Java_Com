package exam02;

public class Sample02_C {
	
	public static void main(String[] args) throws InterruptedException {
		/*
		 * 이스케이프 문자
		 *      - 문자열에 작성하는 문자들 중 일부 문자에 대해 다른 기능을 부여하기 위해 사용
		 *      - \n, \r, \t, \', \", \\ 등의 이스케이프 문자가 있음
		 *      
		 */
		System.out.println("\"진행 프로그레스 바\" 와 같은 출력 형태가 필요한 때");
		
		System.out.print("이클립스\r자바\n");
		
		System.out.print("다운로드 중...[  0%]\r");
		Thread.sleep(1000);  // 1초 동안 일시정지
		System.out.print("다운로드 중...[ 10%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 20%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 30%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 40%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 50%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 60%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 70%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 80%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[ 90%]\r");
		Thread.sleep(1000);
		System.out.print("다운로드 중...[100%]\r");
	}

}
