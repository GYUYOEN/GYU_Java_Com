package exam08;

public abstract class Shape_C {
	private double width;
	private double height;
	
	// 추상 메서드 : 추상 메서드가 있으면 추상 클래스도 있어야함
	public abstract double getArea();
	
	// 추상 메서드
	public abstract double getRound();
	
	// 메서드
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	
}