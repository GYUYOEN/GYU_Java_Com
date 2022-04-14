package game.updown;

import java.util.Random;

public class Fail_C extends Result_C {
	
	private Random rand = new Random();
	private String[] penaltys = new String[] {
			"소주 1잔 마시기"
	};
	
	@Override
	public String toString() {
		return penaltys[rand.nextInt(penaltys.length)];
	}
}
