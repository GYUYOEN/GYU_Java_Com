package exam01;

import java.util.Random;

public class Sample06_C {

	public static void main(String[] args) {
		/*
		 * 가위, 바위, 보 게임에서 컴퓨터가 생성한 정수를 가위, 바위, 보 문자열로
		 * if 문을 사용하여 치환했던 작업을 배열로 활용함
		 */
		Random rand = new Random();
		String[] convert = {"가위", "바위", "보"};
		int res = rand.nextInt(3);
		
		System.out.println("컴퓨터 : " + convert[res]);
	}
}
