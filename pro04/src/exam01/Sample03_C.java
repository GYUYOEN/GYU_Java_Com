package exam01;

public class Sample03_C {

	public static void main(String[] args) {
		// 문자열 배열 사용 방법 1
		String[] arr1 = {"1", "2", "3", "4"};
		
		for(int i = 0; i < 4; i++) {
			System.out.print(arr1[i] + "\t");
		}
		System.out.print("\n");
		System.out.println(arr1[0].length());
		
		// 문자열 배열 사용 방법 2
		// 문자열 배열에 값을 초기화하지 않으면 기본값 null 로 채워진다.
		String[] arr2 = new String[5]; 		
		for(int i = 0; i < 5; i++) {
			System.out.print(arr2[i] + "\t");
		}
		System.out.print("\n");
		// null 데이터가 있는 경우 문자열 메서드 사용에 오류가 발생한다. -> Exception in thread "main" java.lang.NullPointerException
		// System.out.print(arr2[0].length());
		// null : 예약어. 저장된 데이터가 없다는 뜻
		
		// 정수 배열에 값을 초기화하지 않으면 기본값 0 으로 채워진다.
		int[] arr3 = new int[5];
		for(int i = 0; i < 5; i++) {
			System.out.print(arr3[i] + "\t");
		}
		System.out.print("\n");
		
		double[] arr4 = new double[5]; 
		for(int i = 0; i < 5; i++) {
			System.out.print(arr4[i] + "\t");
		}
		System.out.print("\n");
		
		boolean[] arr5 = new boolean[5];
		for(int i = 0; i < 5; i++) {
			System.out.print(arr5[i] + "\t");
		}
		System.out.print("\n");
		
		char[] arr6 = new char[5];
		for(int i = 0; i < 5; i++) {
			System.out.print((int)arr6[i] + "\t");
		}
		System.out.print("\n");
	}

}
