package exam04;

public class Main_C {

	public static void main(String[] args) {
		NormalCustomer_C c1 = new NormalCustomer_C();
		PremiumCustomer_C p1 = new PremiumCustomer_C();
		
		c1.buy("루이비통", 1000000);
		for(int i = 0; i < 12; i++) {
			p1.buy("루이비통", 1000000);			
		}
	}

}
