package exam06;

import java.util.Random;

public class Main_C {

	public static void main(String[] args) {
		/*
		 * 다형성 / 업캐스팅 / 다운케스팅 활용
		 * 		- 일반 고객 5명을 생성한다.(이름, 나이, 성별 초기화 하지 않아도 됨)
		 * 		- 일반 고객 5명을 배열에 넣어 랜덤한 고객, 랜덤한 가격으로 물품을 구입하게 만든다.
		 * 		- 물품 구입액이 1,000,000 원을 초과하면 프리미엄 고객으로 전환시켜서 물품 구입에 할인율을
		 * 		  적용할 수 있도록 만든다.
		 * 		- 고객이 물품을 구입하는 작업을 반복문을 통해 번복하게 만드며, 100번 반복하게 만든다.
		 * 
		 * 일반 고객 -> 프리미업 고객 전환
		 * 		NormalCustomer n1 = new Normalcustomer("홍길동"); // 일반 고객 정보를
		 * 		PremiumCustomer p1 = new PremiumCustomer(); // 새로운 프리미엄 객체를 생성하여
		 * 		p1.setName(n1.getName()); // 고개 정보만 프리미엄 객체에 저장한다.
		 */
		
		// 부모를 통해 자식메서드 실행
		Customer_C[] cArr = new Customer_C[3];
		cArr[0] = new NormalCustomer_C();
		cArr[0] = new PremiumCustomer_C();
		cArr[0] = new EmployeeCustomer_C();
		
		for(int i = 0; i < cArr.length; i++) {
			// [i].buy("xxxxx", 3000000);
			// cArr[i].refund();
			System.out.println(cArr[i].getClass());
			cArr[i] = cArr[i].renewal();
			System.out.println(cArr[i].getClass()); // 인스턴스타입이 무엇인지 확인 가능
		}
//		Random rand = new Random();
//		Customer_C[] cArr = new Customer_C[5];
//		
//		for(int i = 0; i < cArr.length; i++) {
//			cArr[i]  = new NormalCustomer_C();
//		}
//		
//		int unit = 10000;
//		for(int i = 0; i < 100; i++) {
//			int idx = rand.nextInt(cArr.length);
//			int price = (rand.nextInt(190000) + unit) / unit * unit;
//			
//			// 다운 캐스팅 : instanceof 연산자
//			if(cArr[idx] instanceof NormalCustomer_C) {
//				NormalCustomer_C c = (NormalCustomer_C) cArr[idx];
//				c.buy("xxxxxx", price);
//				
//				if(price > 1000000) {
//					// 배열없이 반복
//					PremiumCustomer_C p = new PremiumCustomer_C();
//					p.setName(c.getName());
//					p.setAge(c.getAge());
//					p.setGender(c.getGender());
//					p.setPriceTotal(price);
//					// 배열에 집어 넣어 주기위해서
//					cArr[idx] = p;
//					System.out.println("축하합니다. 구입액이 1,000,000 원을 초과하여 프리미엄 등급으로 상승하였습니다.");
//					System.out.println("앞으로 물품 구입 누적액에 따른 할인율이 적용됩니다.");
//				}
//				
//			} else {
//				PremiumCustomer_C p = (PremiumCustomer_C) cArr[idx];
//				p.buy("xxxxxx", price);
//			}
			
		
		
	}

}
