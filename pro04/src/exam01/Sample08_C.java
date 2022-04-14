package exam01;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sample08_C {

	public static void main(String[] args) {
		/*
		 * 배열의 크기가 5 인 정수 배열울 생성하고 해당 배열에 1 ~ 19 까지의 정수 값을
		 * 임의로 생성하여 초기화 하는 작업을 수행하도록한다.
		 * 단, 배열에 초기화된 값은 중북돠어서는 안된다.
		 */
		Random rand = new Random();
		int[] arr1 = new int[5];
		boolean duplicate;
		
		
		for(int i = 0; i < arr1.length;) {
			duplicate = false;
			int num = rand.nextInt(19) + 1;
			for(int j = 0; j < i; j++) {
				if(arr1[j] == num) {
					duplicate = true;
					break;
				}
			}
			if(!duplicate) {
				arr1[i] = num;
				i++;
			}
			System.out.println(Arrays.toString(arr1));
		}
		
		/* 
		 * for(int i = 0; i < arr1.length; i++) {
		   System.out.print(arr1[i] + "\t");
		   }
		 */
		System.out.print("\n");
		
		System.out.println("-------------------------");
		
		/*
		 * 사용자로 부터 임의의 정수 값을 입력받고 입력받은 모든 정수의 합과 평균울 구하는
		 * 코드를 작성한다.
		 * 사용자로 부터 임의의 정수 값을 입력 받기 전에 총 몇개의 정수값을 입력 받을지 확인 후
		 * 작업을 한다.
		 * 
		 * 예제
		 * 	    총 입력 횟수 : 3
		 *      1 번째 정수값 입력 : 7
		 *      2 번째 정수값 입력 : 12
		 *      3 번째 정수값 입력 : 24
		 *      
		 *      총 합 : 43
		 *      평 균 : 14.3
		 */
		
		/*
		 * 나
		 * Scanner sc = new Scanner(System.in);
			int[] arr2 = new int[3];
			int n = 1;
			for(int i = 0; i < 3; i++) {
				System.out.printf("&d 번째 입력 : \n", n);
				int sInput = sc.nextInt();
				arr2[i] = sInput;
				n++;

			}
			System.out.printf("총합 : %d\n", arr2[0] + arr2[1] + arr2[2]);
			System.out.printf("평균 : %.1f", (double)(arr2[0] + arr2[1] + arr2[2]) / 3.0);
			}
		*/
		
	    // 강사님
		Scanner sc = new Scanner(System.in);
		
		System.out.print("총 입력 횟수 : ");
		int count = sc.nextInt();
		
		int[] arr3 = new int[count];
		int tot = 0;
		double avg = 0.0;
		
		for(int i = 0; i < arr3.length; i++) {
			System.out.printf("%d 번째 입력 : ", i + 1);
			arr3[i] = sc.nextInt();
			tot += arr3[i];
		}
		
		avg = (double)tot/ arr3.length;
		
		System.out.println("총합 : " + tot);
		System.out.println("평균 : " + avg);
	}
}