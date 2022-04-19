package game.card;

// 가위, 바위, 보 게임을 위한 기본 추상 클래스
public abstract class Hand_C { 
	
	// 가위, 바위, 보 비교를 위한 추상메서드
	public abstract int compare(Hand_C otherHand);
	
	// 가위, 바위, 보 치트용 메서드
	// 상대방의 패를 보고 이길 수 있는 패를 반환한다.
	public abstract Hand_C cheat(Hand_C otherHand);
}
