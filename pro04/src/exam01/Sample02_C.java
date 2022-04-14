package exam01;

public class Sample02_C {

	public static void main(String[] args) {
		/*
		 * 배열의 크기가 10인 배열을 선언하고 10 ~ 1 까지의 값으로
		 * 초기화 하도록 한다.
		 */
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		int init = 10;
		
		for(int i = 0; i < 10; i++) {
			arr1[i] = init--;
		}
		for(int i = 0; i < 10; i++) {
			System.out.print(arr1[i] + "\t");
		}
		System.out.print("\n");
		for(int i = 9; i >= 0; i--) {
			arr2[i] = 10 - i;
		}
		for(int i = 0; i < 10; i++) {
			System.out.print(arr2[i] + "\t");
		}
		System.out.print("\n");
		
		/*
		 * 배열의 크기가 10 인 배열을 선언하고 2 부터 시작하여 짝수값에 해당하는
		 * 값으로 초기화 하도록 한다.
		 */
		
		int[] arr3 = new int[10];
		init = 2;
		
		for(int i = 0; i <10; i++) {
			arr3[i] = init;
			init += 2;
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.print(arr3[i] + "\t");
		}
		System.out.print("\n");
		
		/*
		 * 배열의 크기가 5인 실수 배열을 선언하고 1 부터 0.5 씩 증가된 값으로
		 * 초기화 하도록 한다.
		 */
		double[] arr4 = new double[5];
		double init2 = 1.0;
		
		for(int i = 0; i < 5; i++) {
			arr4[i] = init2;
			init2 += 0.5;
		}
		
		for(int i = 0; i < 5; i++) {
			System.out.print(arr4[i] + "\t");
		}
		System.out.print("\n");
		
		/*
		 * 베열의 크기가 5 인 문자 배열을 선언하고 'A' ~ 'E' 까지의 문자값으로
		 * 초기화 하도록 한다.
		 */
		char[] arr5 = new char[5];
		
		for(int i = 0; i < 5; i++) {
			arr5[i] = (char)('A' + i);
		}
		for(int i = 0; i < 5; i++) {
			System.out.print(arr5[i] + "\t");
		}
			System.out.print("\n");
		}

}
