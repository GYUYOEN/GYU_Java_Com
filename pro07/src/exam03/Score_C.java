package exam03;

import java.util.Objects;

public class Score_C {
	// 점수만 가지는 객체
	private double point;
	
	public Score_C(double point) {
		this.point = point;
	}
	
	// 점수는 기본 0 ~ 100 사이의 값만 설정할 수 있게 한다.
	public void setPoint(double point) {
		if(point >= 0 && point <= 100) {
			this.point = point;
		}
	}
	
	public double getPoint() {
		return this.point;
	}

	@Override
	public int hashCode() {
		return Objects.hash(point);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score_C other = (Score_C) obj;
		return Double.doubleToLongBits(point) == Double.doubleToLongBits(other.point);
	}

	@Override
	public String toString() {
		return "Score_C [point=" + point + "]";
	}
	
}
