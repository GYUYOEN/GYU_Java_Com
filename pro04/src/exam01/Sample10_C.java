package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class Sample10_C {

	public static void main(String[] args) {
		/*
		 * 사용자로 부터 임의의 정수 값을 입력받고 입력받은 모든 정수의 합과 평균울 구하는
		 * 코드를 작성한다. (동적 배열 활용)
		 * 
		 * -1 입력이 들어오면 종료라고 판별한다.
		 * 
		 * 예제
		 * 	    총 입력 횟수 : 3
		 *      1 번째 정수값 입력 : 7
		 *      2 번째 정수값 입력 : 12
		 *      3 번째 정수값 입력 : 24
		 *      4 번째 벙수값 입력 : -1
		 *      
		 *      총 합 : 43
		 *      평 균 : 14.3
		 */
		Scanner sc = new Scanner(System.in);
		int cnt = 1, tot = 0;
		double avg;
		int[] arr1 = new int[0];
		while(true) {
			System.out.printf("%d 번째 정수값 입력 : ", cnt);
			
			int num;
			if(sc.hasNextInt()) { // 정수인지 아닌지를 판별
				num = sc. nextInt(); 
				sc.nextLine(); 
				// 정수와 문자열을 함꼐 입력받을때 nextInt에 개행 이 남아서 nextLine이 개행을 인식해버림으로 한번 더 써준다.
			} else {
				if(sc.nextLine().equals("exit")) {
					break;
				} else {
					System.out.println("정수값 또는 exit 룰 입력하세요.");
					continue;
				}
			}
			// int num = sc.nextInt();
			// if(num == -1) break;
			
			int[] temp = Arrays.copyOf(arr1, arr1.length + 1);
			arr1 = temp; // arr1으로 최종값을 출력할 것이므로 얕은 복사 해줌
			
			arr1[cnt - 1] = num;
			cnt += 1;
			
			System.out.println(Arrays.toString(arr1));
		}
		
		for(int i = 0; i < arr1.length; i++) {
			tot += arr1[i];
		}
		avg = (double)tot / arr1.length;
		
		System.out.println("총 합 : " + tot);
		System.out.println("평 균 : " + avg);
	}
}
