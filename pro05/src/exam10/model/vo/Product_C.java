package exam10.model.vo;

public class Product_C {
	private String pName = "키보드";
	private int price = 250000;
	private String brand = "X오X드";
	
	// 기본생성자 생략
	// public Product_C() {}
	
	public void information() {
		System.out.println(pName);
		System.out.println(price);
		System.out.println(brand);
	}

}