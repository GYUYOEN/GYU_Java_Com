package game.record;

public class Record_C {
	
	private int[] score = new int[3];
	
	public void addWin() {
		// +1 승을 한다.
		score[0]++;
	}
	
	public void addLose() {
		// +1 패를 한다.
		score[1]++;
	}
	
	public void addDraw() {
		// +1 무를 한다.
		score[2]++;
	}
	
	public int getWin() {
		// 승 정보를 반환한다.
		return score[0];
	}
	
	public int getLose() {
		// 패 정보를 반환한다.
		return score[1];
	}
	
	public int getDraw() {
		// 무 정보를 반환한다.
		return score[2];
	}
	
	public double getWinRate() {
		// 승률 정보를 반환한다.
		return score[0] / getRecordCount() * 100;
	}
	
	public int[] getScore() {
		// 승,패,무 정보를 배열로 반환한다.
		return score.clone();
	}
	
	public void setScore(int[] score) {
		this.score = score;
	}
	
	public int getRecordCount() {
		// 전체 승,패,무 횟수를 반환한다.
		return score[0] + score[1] + score[2];
	}
}