package exam01;

import java.util.Arrays;

public class Run_C {

	public static void main(String[] args) {
		// 흐름 확인하기
		Grade_C g = new Grade_C("국어");
		g.setScore(73.4);
		System.out.println(g.getName());
		System.out.println(g.getScore());
		System.out.println(g.getRank());
		
		Grade_C g1 = new Grade_C("수학", 85.7);
		System.out.println(g1.getName());
		System.out.println(g1.getScore());
		System.out.println(g1.getRank());
		
		Lotto_C lot = new Lotto_C();
		lot.generate();
		System.out.println(Arrays.toString(lot.getNumbers()));
		
		lot.generate(1);
		System.out.println(Arrays.toString(lot.getNumbers()));
		
		lot.generate(10, 20);
		System.out.println(Arrays.toString(lot.getNumbers()));
	}

}
