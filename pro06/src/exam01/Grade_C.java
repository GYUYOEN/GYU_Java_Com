package exam01;

public class Grade_C { // 생성시 마다 만들어진 각 인스턴스에 다음 매개변수가 다 생성됨 -> 메모리 효율 떨어짐
	private String name;
	private double score;
	private char rank;
	private final static char[] RANK = new char[] { // static 사용해 메모리 효율 높여줌
			'F', 'F', 'F', 'F', 'E', 'E', 'D', 'C', 'B', 'A', 'A'
	};
	
	// 매개변수가 있는 생성자 : 매개변수를 초기화 시킴(외부에서 값을 전달)
	// Source -> Generate Constructor using Fields...
	public Grade_C(String name) {
		this.name = name; // this가 없을 경우 우선순위 : 가까운 것(매개변수)
	}
	
	public Grade_C(String name, double score) {
		this.name = name; // 재사용성 높이고 코드량 줄이기 위해 사용(반드시 x)
		// this.score = score;
		// this._setRank();
		// setScore 메서드에 중복되어 있으므로
		this.setScore(score); // 재사용하고 코드량 줄어듬(if 문 안써도됨)
	}
	
	// Source -> Generate Getters and Setters...
	public String getName() { 
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		if(score <= 100 && score >= 0) {
			this.score = score;
		} else {
			this.score = 0;
		}
		this._setRank(); // setRank 호출
	}
	
	public char getRank() {
		return rank;
	}
	
	private void _setRank() {
		/* 점수가 설정되면 여기에 등급을 분류하기 위한 코드를 작성 // setter가 필요 없는 이유
		 * A : 100 이하 ~ 90 이상, B :  90 미만 ~ 80 이상. C : 80 미만 ~ 70이상
		 * D : 70 미만 60 이상, E : 60 미만 ~ 40 이상, F : 40 미만
		 */
		// 매개변수가 멤버로 설정이 되면 멤버로 쓰기. 헷갈릴 수 있으니 멤버변수(this)로 작성
		
		// 배열
		/*
	 	char[] rankDate = new char[] { 
				'F', 'F', 'F', 'F', 'E', 'E', 'D', 'C', 'B', 'A', 'A'
		};
		 */
		this.rank = Grade_C.RANK[(int)(this.score / 10)]; // static이 붙어았잇으므로 Grade_C.
		
		// switch
		/* 
		switch((int)(this.score / 10)) { // score은 더블임으로 int로 변환
			case 10:	case 9:
				this.rank = 'A';	break;
			case 8:
				this.rank = 'B';	break;
			case 7:
				this.rank = 'C';	break;
			case 6:
				this.rank = 'D';	break;
			case 5:		case 4:
				this.rank = 'E';	break;
			case 3:		case 2:		case 1:		case 0:
				this.rank = 'F';
		}
		 */
		
		// if
 		/*
 		if(this.score <= 100 && this.score >= 90) {
			this.rank = 'A';
		} else if(this.score < 90 && this.score >= 80) {
			this.rank = 'B';
		} else if(this.score < 80 && this.score >= 70) {
			this.rank = 'C';
		} else if(this.score < 70 && this.score >= 60) {
			this.rank = 'D';
		} else if(this.score < 60 && this.score >= 40) {
			this.rank = 'E';
		} else if(this.score < 40) {
			this.rank = 'F';
		}
 		 */
	}
	
	
}
