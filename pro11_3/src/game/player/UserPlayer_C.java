package game.player;

import java.util.Random;

import game.card.Bawi_C;
import game.card.Bo_C;
import game.card.Gawi_C;
import game.card.Hand_C;
import game.record.Record_C;

public class UserPlayer_C implements Player_C {
	
	Random rd = new Random();
	private String name;
	private Hand_C hand;
	private Record_C record = new Record_C();
	
	public UserPlayer_C(String name) {
		this.name = name;
	}
	
	public void setCardHand(String name) {
		if(name.equals("가위")) {
			hand = new Gawi_C();
		} else if(name.equals("바위")) {
			hand = new Bawi_C();
		} else if(name.equals("보")) {
			hand = new Bo_C();
		} else {
			this.randomCardHand();
		}
	}
	
	@Override
	public void randomCardHand() {
		int rand = rd.nextInt(3);
		switch(rand) {
			case 0:
				hand = new Gawi_C(); break;
			case 1:
				hand = new Bawi_C(); break;
			case 2:
				hand = new Bo_C(); break;
		}
	}
	
	@Override
	public String versus(Hand_C h) {
		String result = "";
		switch(hand.compare(h)) {
			case -1:
				record.addLose();
				result = "패 "; break;
			case 0:
				record.addDraw();
				result = "무 "; break;
			case 1:
				record.addWin();
				result = "승 "; break;
		}
		return result;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Hand_C getHand() {
		return this.hand;
	}
	
	public String getRecord() {
		String result = "";
		result = String.format("%d 승 %d 패 %d 무",
				record.getWin(), record.getLose(), record.getDraw());
		return result;
	}

	public void setRecordArray(int[] record) {
		this.record.setScore(record);
	}
	
	public int[] getRecordArray() {
		return record.getScore();
	}
}
