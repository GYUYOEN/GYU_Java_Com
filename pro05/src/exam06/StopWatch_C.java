package exam06;

public class StopWatch_C {
	/*
	 * 사물을 추상화하여 클래스(객체)를 만드는 과정
	 * 
	 * 1. 사물이 가지는 속성과 시능을 분휴한다.
	 * 2. 분류된 속성과 기능 중 프로그램에 필요한 속성과 기능만 재분류한다.
	 *    예)
	 * 		쇼핑몰용 회원 정보 관리에 필요한 사람정보
	 * 		대학교 학생 정보 관리에 필요한 사람정보
	 * 		회사 직원 정보 관리에 필요한 사람정보 -> 이름, 성별, 나이, 주소, 직책, 연봉...
	 * 3. 재분류한 속성 및 기능만을 정의한 클래스 다이어그램을 만든다.
	 * 4. 클래스 다이어그램을 참고하여 클래스를 만든다.
	 */
	
	/*
	 * 스탑워치 프로그램 구현에 사용할 시간을 추상화하여 클래스 다이어그램 및 클래스로 만들어 본다.
	 * 		속성은 접근제한, 타입, 이름, 필요한 경우 초기값 까비 설장
	 * 		기능은 이름만 정의하도록 한다. 
	 * 
	 * 속성 : 시, 분, 초
	 * 기능 : 시작, 중지, 일시정지, 리셋
	 * +---------------------+                 
	 * |  exam05.StopWatch   | 클래스명           
	 * +---------------------+                 
	 * | - hour:int          | 속성 -> 멤버변수
	 * | - minute:int        |                      
	 * | - second:int        |                                      
	 * +---------------------+                     
	 * | + start():void      | private를 외부에서 간접접근 가능하도록 만듬
	 * | + stop():void       |
	 * | + pause():void      |
	 * | + reset():void      |
	 * +---------------------+
	 * 추가, 변경 가능
	 */
	private int hour; // 직접 접근이 인됨. 간접적으로 접군해야함
	private int minute;
	private int second;
	
	public void setHour(int h) {		// setter
		hour = h;
	};
	public int getHour() {		//getter
		return hour;
	}
	
	public void start() {} // 간접 접근하기 위한 것
	public void stop() {}
	public void pause() {}
	public void reset() {}
}