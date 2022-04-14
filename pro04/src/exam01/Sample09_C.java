package exam01;

import java.util.Arrays;

public class Sample09_C {

	public static void main(String[] args) {
		/*
		 * 동적 배열
		 *      - 기존의 배열은 크기가 한 번 정해지면 크가를 늘리거나 쥴일 수 없다.
		 *      - 원한 배열의 크기를 늘리거나 줄일 수 있도록 기존 배열을 조직 (실제로 늘리는 건 아님)
		 *      - 최초 배열의 크기보다 크거나 작은 배열을 새로 만들고 새로 생성된 배열에 값을 복사(깊은 복사)하는 형식으로 작업
		 */
		int[] arr1 = new int[3];
		arr1[0] = 10;	arr1[1] = 20;	arr1[2] = 30;
		// 1
		System.out.println(Arrays.toString(arr1));
		
		// 2
		int[] temp = new int[arr1.length + 1];
		for(int i = 0; i < arr1.length; i++) {
			temp[i] = arr1[i];
		}
		arr1 = temp; // 얕은 복사
		System.out.println(Arrays.toString(arr1));
		// 3
		temp = Arrays.copyOf(arr1, arr1.length + 1); // 뒷쪽에 값을 추가할떄
		arr1 = temp;
		System.out.println(Arrays.toString(arr1));
		// 4
		temp = new int[arr1.length + 1];
		System.arraycopy(arr1, 0, temp, 0, arr1.length);
		arr1 = temp;
		System.out.println(Arrays.toString(arr1));
		// 5
		temp = new int[arr1.length + 1];
		System.arraycopy(arr1, 0, temp, 1, arr1.length); // 앞쪽에 ㄱ밧을 추가하고 싶을때
		arr1 = temp;
		System.out.println(Arrays.toString(arr1));
	}

}
