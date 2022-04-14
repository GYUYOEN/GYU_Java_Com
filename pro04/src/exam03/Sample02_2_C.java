package exam03;

import java.util.Arrays;
import java.util.Random;

public class Sample02_2_C {

	public static void main(String[] args) {
		// 1.  배열의 크기가 10 인 정수 배열을 2개 생성한 후 각 배열에 10 ~ 99 사이의 난수값으로 초기화 시키고 출력한다.
		Random rand = new Random();
				
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
				
		for(int i = 0; i < arr1.length; i++) {
			arr1[i] = rand.nextInt(89) + 10;
			arr2[i] = rand.nextInt(89) + 10;
		}
		System.out.println("1. 결과 확인 ");
		System.out.println("\t" + Arrays.toString(arr1));
		System.out.println("\t" + Arrays.toString(arr2));
				
		// 2. 1번 문제에서 생성한 첫번째 배열과 두번째 배열의 합을 구하여 새로운 새번째 배열을 만들고 출력한다.
		int[] arr3 = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i] + arr2[i];
		}
		System.out.println("2. 결과 확인 ");
		System.out.println("\t" + Arrays.toString(arr3));
				
		// 3. 2번 문제까지 진행하여 만들어진 3개의 배열을 이용하여 짝수값만 저장되어 있는 배열과 홀수삾만 저장되어 있는 배열을 만들고 짝수배열과 홀수배열을 출력한다.
		// 크기를 먼저 구함
		// 동적 배열 사용 : count 안해도 됨
		int[] arr4 = new int[0];
		int[] arr5 = new int[0];
		int idx1 = 0, idx2 = 0;
		for(int i = 0; i < arr1.length; i++) {
			int[] temp;
			if(arr1[i] % 2 == 0) {
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
				
				arr4[idx1] = arr1[i];
				idx1++;
			} else {
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
						
				arr5[idx2] = arr1[i];
				idx2++;
			}
					
			if(arr2[i] % 2 == 0) {
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
						
				arr4[idx1] = arr2[i];
				idx1++;
			} else {
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
						
				arr5[idx2]= arr2[i];
				idx2++;
			}
					
			if(arr3[i] % 2 == 0) {
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
						
				arr4[idx1] = arr3[i];
				idx1++;
			} else {
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
						
				arr5[idx2]= arr3[i];
				idx2++;
			}
		}
				
		System.out.println("3. 결과 확인 ");
		System.out.println("\t" + Arrays.toString(arr4));
		System.out.println("\t" + Arrays.toString(arr5));
		
		// 4. 짝수/홀수 배열에 있는 값들 중 중복된 값이 있는 경우 하나의 값만 남겨 새로운 배열로 만들고 출력한다.
		// 짝수
		int[] arr6 = new int[1];
		int[] arr7 = new int[1];
		// (1) 첫번째 값은 중복없으니 바로 저장
		arr6[0] = arr4[0];
		for(int i = 1; i < arr4.length; i++) {
			// (2) 배열 중 하나를 끄집어내서 다른 곳에 저장
			int temp = arr4[i];
			boolean dup = false;
			for(int j = 0; j < arr6.length; j++) {
				// (3)다른곳에 저장한 하나의 배열과 전에 비교했던 배열 중복검사
				if(arr6[j] == temp) { 
					dup = true;
					break;
				} 
			}
			// (4) 중복 없으면 배열크기 늘리고 저장
			if(!dup) { // 중복이 되지 않을 때
			arr6 = Arrays.copyOf(arr6, arr6.length + 1);
			arr6[arr6.length -1] = temp;
			}
		}
		
		// 홀수
		arr7[0] = arr5[0];
		for(int i = 1; i < arr5.length; i++) {
			int temp = arr5[i];
			boolean dup = false;
			for(int j = 0; j < arr7.length; j++) {
				if(arr7[j] == temp) { 
					dup = true;
					break;
				} 
			}
			if(!dup) { // 중복이 되지 않을 때
			arr7 = Arrays.copyOf(arr7, arr7.length + 1);
			arr7[arr7.length -1] = temp;
			}
		}
		System.out.println("4. 결과 확인 ");
		System.out.println("\t" + Arrays.toString(arr6));
		System.out.println("\t" + Arrays.toString(arr7));


	}

}

