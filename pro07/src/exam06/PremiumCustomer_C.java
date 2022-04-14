package exam06;

public class PremiumCustomer_C extends Customer_C {
	private double discount;
	private double priceTotal;
	
	public double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}

	@Override
	public void buy(String productName, int price) {
		priceTotal += price;
		double p = _calcDiscount(price);
		System.out.printf("%s 상품을 %.2f 할인율 적용하여 %,.1f 원에 구입하였습니다.\n", productName, discount, p);
	}
	
	@Override
	public Customer_C renewal() {
		// 누적사용액이 500만원 미만이면 일반고객
		// 누적사용액이 500만원 이상이면 프리미엄 고객
		Customer_C c = this;	// 업캐스팅
		if(priceTotal < 5000000) {
			c = new NormalCustomer_C();		// 업캐스팅
			c.setName(getName());
			c.setAge(getAge());
			c.setGender(getGender());
			System.out.println("누적사용액이 기준에 미달하여 일반 고객으로 강등 조치되었습니다.");
		}
		return c;
	}
	
	private double _calcDiscount(int price) {
		if(priceTotal >= 10000000) {
			discount = 0.1;
			return price * 0.9;
		} else if(priceTotal >= 5000000) {
			discount = 0.05;
			return price * 0.95;
		} else if(priceTotal >= 3000000) {
			discount = 0.02;
			return price * 0.98;
		} else {
			return price;
		}
		
	}

}
