package exam02;

public class Sample03_C {
	public static void main(String[] args) {
		/*
		 * format 활용
		 */
		
		// 정렬(왼쪽 또는 오른쪽)
		System.out.printf("%5s | %5s\n", "name", "age");
		System.out.printf("%5s | %5d\n", "Hong", 32);
		System.out.printf("%5s | %5d\n", "Kim", 27);
		System.out.printf("%5s | %5d\n", "Park", 29);
		
		System.out.println("--------------------");
		
		System.out.printf("%-5s | %-5s\n", "name", "age");
		System.out.printf("%-5s | %-5d\n", "Hong", 32);
		System.out.printf("%-5s | %-5d\n", "Kim", 27);
	    System.out.printf("%-5s | %-5d\n", "Park", 29);
	    
        System.out.println("--------------------");
		
		System.out.printf("%-5s | %-5s\n", "name", "age");
		System.out.printf("%-5s | %-5.1f\n", "Hong", 32.0);
		System.out.printf("%-5s | %-5.1f\n", "Kim", 27.1);
	    System.out.printf("%-5s | %-5.1f\n", "Park", 29.2);
	    
	    System.out.println("--------------------");
	    
	    //한글을 사용할 때 문제점
	    System.out.printf("%6s | %5s\n", "이름", "나이"); // 한글은 2자리 차지 
		System.out.printf("%6s | %5d\n", "홍길동", 32);
		System.out.printf("%6s | %5d\n", "김건", 27);
		System.out.printf("%6s | %5d\n", "박지우", 29);
	}

}
