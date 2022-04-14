package exam09;

public class Circle_C {
	private double radius;
	private double area;
	private double round;
	
	public final static double PI = 3.14;
	
	public Circle_C() {}
	
	public Circle_C(double radius) {
		setRadius(radius);
	}
	
	// setter : 설정해줌
	public void setRadius(double radius) {
		this.radius = radius;
		_area();
		_round();
	}
	
	// getter : 가져옴
	public double getRadius() {
		return radius;
	}
	
	// getter
	public double getArea() {
		return area;
	}
	
	// getter
	public double getRound() {
		return round;
	}
	
	private void _area() {
		this.area = radius * radius * PI;
	}
	
	// 원 둘레 -> 2πr
	private void _round() {
		this.round = 2 * PI * radius;
	}

}
