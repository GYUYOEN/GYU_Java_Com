package exam02;

import java.util.Arrays;

public class Sample02_C {

	public static void main(String[] args) {
		/*
		 * 2차 배열의 깊은 복사
		 */
		int[] arr1 = new int[] {1, 2, 3};
		int[] arr2 = new int[arr1.length];
		
		for(int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[i];
		}
		
		arr1[0] = 10;
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		
		System.out.println("----------------------");
		
		int[][] arr3 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] arr4 = new int[arr3.length][arr3[0].length];
		
		// 2차 배열의 깊은 복사 (반복문)
		for(int i = 0; i < arr3.length; i++) {
			for(int j = 0; j < arr3[i].length; j++) {
				arr4[i][j] = arr3[i][j];
			}
		}
		
		// 2차 배열의 깊은 복사(System.arraycopy)
		int[][] arr5 = new int[arr3.length][];
		for(int i = 0; i < arr3.length; i++) {
			int[] temp = new int[arr3[i].length];
			System.arraycopy(arr3[i], 0, temp, 0, arr3[i].length);
			arr5[i] = temp;
		}
		
		arr3[0][0] = 10;
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(Arrays.toString(arr3[i])+ "\t");
		}		
		System.out.print("\n");
		
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(Arrays.toString(arr4[i])+ "\t");
		}
		System.out.print("\n");
		
		arr5[0][0] = 50;
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(Arrays.toString(arr5[i])+ "\t");
		}
	} 
}