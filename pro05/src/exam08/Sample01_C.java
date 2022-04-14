package exam08;

import java.util.Arrays;
import java.util.Random;

public class Sample01_C {

	public static void main(String[] args) {
		MethodSample_C m = new MethodSample_C();
		
		m.method01();
		// System.out.println(m.method01()); x
		
		int r1 = m.method02(); // r1에는 0이 저장
		int[] r2 = m.method03();
		String r3 = m.method04();
		
		int[] arg1 = new int[] {1, 2, 3}; // 매개변수로 전달할 때 얕은복사가 이루어짐
		System.out.println(arg1);
		m.method05(arg1);
		System.out.println(Arrays.toString(arg1));
		
		Random rand = new Random();
		m.method06(rand);
		
		m.method07();
		m.method07(1, 2, 3, 4);
		m.method07(1, 2, 3, 4, 5, 6, 7, 8); // 인자 전달
	}

}
