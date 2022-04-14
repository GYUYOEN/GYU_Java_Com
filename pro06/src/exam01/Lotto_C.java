package exam01;

import java.util.Random;

public class Lotto_C {
	private int rangeMin = 1;
	private int rangeMax = 45;
	private int count = 6;
	private int[] numbers;
	private Random rand = new Random();
	
	public Lotto_C() {
		this.numbers = new int[count];
	}
	
	public Lotto_C(int min, int max, int count) {
		this.rangeMin = min;
		this.rangeMin = max;
		this.count = count;
		this.numbers = new int[count];
		// this() 사용 x -> 첫줄에 써야하는 데 첫줄에 쓸수 없어서
	}
	
	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}
	
	public int getRangeMin() {
		return rangeMin;
	}
	
	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}
	
	public int getRangeMax() {
		return rangeMax;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public int[] getNumbers() {
		return numbers;
	}
	
	public void generate(int ... nums) { // nums는 배열로 처리됨
		int cnt = nums.length;
		
		for(int i = 0; i < cnt; i++) {
			numbers[i] = nums[i];
		}
		
 		// for(int i = cnt; i < count; i++) {
		//		numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
			
		for(int i = cnt; i < count;) {
			int num = this.rand.nextInt(rangeMax) + rangeMin;
			if(!_isDuplicate(num)) {
				numbers[i] = num;
				i++;
			}
		}
	}
	
	/*
	 * 가변인자 사용하면 없어도됨. 매개변수의 수만 변하고 코드는 거의 동일 
	 * 로직과 흐름이 거의 동일 -> 가변인자 사용
	public void generate(int n1) {
		numbers[0] = n1;
		for(int i = 1; i < count; i++) {
			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
		}
	}
	
	public void generate(int n1, int n2) {
		numbers[0] = n1;	numbers[1] = n2;
		for(int i = 2; i < count; i++) {
			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
		}
	}
	
	public void generate(int n1, int n2, int n3) {
		numbers[0] = n1;	numbers[1] = n2;	
		numbers[2] = n3;
		for(int i = 3; i < count; i++) {
			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
		}
	}
	
	public void generate(int n1, int n2, int n3, int n4) {
		numbers[0] = n1;	numbers[1] = n2;	
		numbers[2] = n3;	numbers[3] = n4;
		for(int i = 4; i < count; i++) {
			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
		}
	}
	
	public void generate(int n1, int n2, int n3, int n4, int n5) {
		numbers[0] = n1;	numbers[1] = n2;	
		numbers[2] = n3;	numbers[3] = n4;	
		numbers[4] = n5;
		for(int i = 5; i < count; i++) {
			numbers[i] = this.rand.nextInt(rangeMax) + rangeMin;
		
	 */
	
	private boolean _isDuplicate(int number) {
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == number) {
				return true;
			}
		}
		return false;
	}
}
