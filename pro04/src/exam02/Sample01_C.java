package exam02;

import java.util.Arrays;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * 2차 배열
		 *      - 배열 안에 배열을 만들어서 사용하는 형태
		 *      - 표(테이블) 형식의 데이터를 사용할 때 많이 사용함.
		 */
		int[][] arr1 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		
		// arr1[0][0] = 1; arr1[0][1] = 2; arr1[0][2] =3;
		// arr1[1][0] = 1; arr1[1][1] = 2; arr1[1][2] =3;
		// arr1[2][0] = 1; arr1[2][1] = 2; arr1[2][2] =3;
		
		// Arrays.toString 를 몰랐을 때
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				System.out.print(arr1[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
		System.out.print("----------------------------------\n");
		
		// Arrays.toString 을 사용하여 출력
		for(int i = 0; i < arr1.length; i++) {
			System.out.println(Arrays.toString(arr1[i]));
		}
	}

}
