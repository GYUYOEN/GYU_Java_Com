package exam03;

import java.util.Objects;

public class Grade_C extends Score_C {
	// 점수와 등급 정보를 가지는 객체
	private char level;
	
	public Grade_C(double point) {
		super(point);
		// setPoint(point);
		this._setLevel();
	}
	
	private void _setLevel() {
		char[] rank = new char[] {
				'F', 'F', 'F', 'F', 'E', 'E', 'D', 'C', 'B', 'A', 'A'
		};
		this.level = rank[(int)(this.getPoint() / 10)];
	}
	
	@Override
	public void setPoint(double point) {
		super.setPoint(point);
		this._setLevel();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(level);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grade_C other = (Grade_C) obj;
		return level == other.level;
	}

	@Override
	public String toString() {
		return "Grade_C [getPoint()=" + getPoint() + ", level=" + level + "]";
	}
	
	
}
