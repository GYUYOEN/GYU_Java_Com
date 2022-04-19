package exam01;

import java.util.Arrays;

public class Sample07_C {

	public static void main(String[] args) {
		/*
		 * 반복문을 사용한 깊은 복사 작업을 자바에서 제공하는 기능으로 바꾸어서 작업
		 */
		int[] arr1 = new int[] {1, 2, 3, 4, 5};
		
		// 1. 반복문을 사용한 깊은 복사
		int[] arr2 = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[i];
		}
		
		// 2. 자바의 기본 기능을 사용한 깊은 복사
		int[] arr3 = new int[arr1.length];
		
		// 2-1. arraycopy 사용
		// arr1[0] 부타 arr1.length 길이까지 arr3[0] 부터 순차적으로 넣는다.
		// arr1[1]에 있는것을 arr3[0]으로 넣고 싶을 때 사용 
		// System.arraycopy(arr1, 0, arr3, 0, arr1.length); // < arr1, 0 > = < int i = 0 , arr1[i] >
		System.arraycopy(arr1, 0, arr3, 2, arr1.length - 2); // -2 : 크기가 한정되어 있으므로 그 크기를 맞춰주기 위해 사용
		
		// arr1[0] = 10;
		// System.out.printf("arr1[0] -> %d, arr3[0] -> %d\n", arr1[0], arr3[0]);
		for(int i = 0; i < arr1.length; i++) {
			System.out.printf("arr1[%d] -> %d, arr3[%d] -> %d\n", i, arr1[i], i, arr3[i]);
		}
		
		// 2-2. Arrays 객체를 사용한 깊은 복사
		int[] arr4 = Arrays.copyOf(arr1, arr1.length + 1); // 길이를 늘리거나 줄일 때
		// -1 하면 arr4.length -> 4
		
		arr1[0] = 20;
		System.out.printf("arr1[0] -> %d, arr4[0] -> %d\n", arr1[0], arr4[0]);
		System.out.printf("arr4.length -> %d\n", arr4.length);
		
		// 2-3. .clone() 을 사용한 깊은 복사
		int[] arr5 = arr1.clone(); // 길이가 동일할 떄
		
		arr1[0] = 30;
		System.out.printf("arr1[0] -> %d, arr5[0] -> %d\n", arr1[0], arr5[0]);
	}
}
