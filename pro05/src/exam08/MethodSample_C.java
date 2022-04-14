package exam08;

import java.util.Random;

public class MethodSample_C {
	
	public void method01() {
	System.out.println("반환 타입은 void 이고 매개변수가 없는 메서드");
	}
	
	public int method02() { // 오류 -> This method must return a result of type int
		System.out.println("반환 타입은 int(기본자료형) 이고 매개변수가 없는 메서드");
		return 0; // 함수를 호출한 위치로 return
	}
	
	public int[] method03() {
		int[] res = new int[5];
		System.out.println("반환 타입은 int[](배열) 이고 매개변수가 없는 메서드");
		return res;
		// return new int[5];
	}
	
	public String method04() {
		String res = new String();
		System.out.println("반환 타입은 String(클래스/객체) 이고 매개변수가 없는 메서드");
		return res;
		// return null;
	}
	
	public void method05(int[] arr) {
		System.out.println("반환 타입은 void 이고 매개변수가 배열인 메서드");
		System.out.println(arr); // 같은 참조값을 사용하고 있어서 같음.
		for(int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "] -> " + arr[i]);
		}
	}
	public void method06(Random r) { // 반환값이 2개 이상일 때는 배열 이용해보기
	// public int[] method06(Random r) {
		System.out.println("반환 타입은 void 이고 매개변수가 클래스(객체)인 메서드");
		r.nextInt(10); 
		// int x1 = r.nextInt(10); 
		// int x2 = r.nextInt(10);
		// return new  int[] {x1, x2}; 
	}
	
	public void method07(int ... nums) {
		System.out.println("반환 타입은 void 이고 매개변수가 가변인 메서드");
		System.out.println(nums);
		for(int i = 0; i < nums.length; i++) {
			System.out.println("arr[" + i + "] -> " + nums[i]);
		} // 배열로 생성
	}
}