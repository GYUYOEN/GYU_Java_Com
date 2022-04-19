package game.updown;

import java.util.Random;
import java.util.Scanner;

public class GuessNum_C {
	private Random rand = new Random();
	private int guess;
	private int guessLimit;
	
	Scanner sc = new Scanner(System.in);
	
	public GuessNum_C() {
		this.guess = rand.nextInt(100)+1;
		this.guessLimit = 10;
	}
	
	public GuessNum_C(int rangeMax, int limit) {
		this.guess = rand.nextInt(rangeMax)+1;
		this.guessLimit = limit;
	}
	
	public GuessNum_C(int rangeMin, int rangeMax, int limit) {
		this.guess = rand.nextInt(rangeMax-rangeMin) + rangeMax;
		this.guessLimit = limit;
	}
	
	public Result_C guessing(int number) {
		Result_C res = new Fail_C();
		this.guessLimit--;
		
		if(remainCount()) {
			if(number > this.guess) {
				res = new Down_C();
			} else if(number < this.guess) {
				res = new Up_C();
			} else if(number == this.guess) {
				res = new Correct_C();
			}
		} else if(this.guessLimit == 0) {
				if(number == this.guess) {
					res = new Correct_C();
			}
		}
		return res;
	}

	public boolean remainCount() {
		return this.guessLimit > 0 ? true : false;
	}
	
}
