package exam01;

import java.util.Scanner;

public class Homework_C {

	public static void main(String[] args) {
		/*
		 * 환율 계산 프로그램 
         * 원달러 환율 값 입력 : 1235.00          
         * 
         * 달러를 원화로 계산하겠습니까? 아니면 원화를 달러로 계산하겠습니까? 
		 ​* (1:달러->원화, 2:원화->달러) 
		 ​* 
		 * 달러값 입력 : 100 
		 * 100 달러는 123500 원 입니다. 
	     * 
	     * 원화값 입력 : 100000 
	     * 100000 원은 약 80.97 달러 입니다.
		 */
		Scanner sc = new Scanner(System.in);
		 
	    double rate;
	    int menuNumber;
	    int dollar, won;
	    
	    System.out.print("원달러 값 입력 : ");
	    rate = sc.nextDouble();
	    
	    System.out.println("달러를 원화로 계산하시겠습니까? 아니면 원화를 달러로 계산하시겠습니까? : ");
	    System.out.print("1 : 달러 -> 원화, 2 : 원화 -> 달러 => ");
	    menuNumber = sc.nextInt();
	     
	    if(menuNumber == 1) {
	    	// 달러 -> 원화
	    	System.out.print("달러값 입력 : ");
		    dollar = sc.nextInt();
		    won = (int)(dollar * rate);
	    	System.out.printf("%d 달러는 %.0f 원 입니다", dollar, won);
	     } else if(menuNumber == 2) {
	    	// 원화 -> 달러
	    	System.out.print("원화값 입력 : ");
			won = sc.nextInt();
		    System.out.printf("%d 원은 %.2f 달러 입니다", won, won / rate);
	     }

	}

}
