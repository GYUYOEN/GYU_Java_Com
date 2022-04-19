package exam05;

import java.util.Random;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * +---------------------+                     +--------------------+
		 * |    exam05.Circle    | 클래스명              |  exam05.Rectangle  |
		 * +---------------------+                     +--------------------+
		 * | + PI:double = 3.14  | 속성 -> 멤버변수       | + width:double     |
		 * | + radius:double     |                     | + height:double    |
		 * | + diamiter:double   |                     | + area:double      |
		 * | + area:double       |                     +--------------------+
		 * +---------------------+                     |                    |
		 * |                     | 기능 -> 멤버메서드      +--------------------+
		 * +---------------------+
		 * 
		 * 1. 위에 작성한 클래스 다이어그램을 보고 클래스를 만든다.
		 * 2. 동일 패키지에 Sample01 클래스를 만들고 Sample01 클래스에는 메인 메서드를
		 *    정의하여 1번에서 생성한 클래스를 객체(인스턴스)로 생성한다.
		 * 3. Circle 클래스로 2개의 인스턴스를 만들고 각각 반지름(radius) 1.5, 3.5 로
		 *    만들고 지름(diamiter)은 반지름을 참고하여 설정하고 원의 넓이(area)는 PI
		 *    값과 반지름 또는 지름을 참고하여 계산 후 저장하시오.
		 * 4. Rectangle 클래스로 2개의 인스턴스를 만들고 3번에 생성한 Circle 클래스의
		 *    인스턴스들이 가지고 있는 지름의 크기와 동일한 너비(width)를 가지는 인스
		 *    턴스로 설정한다. 높이의 경우 Random 을 이용하여 3 ~ 9 사이의 임의값으로
		 *    설정 후 넓이(area)를 구하고 설정한다.
		 */
		
		 Circle_C c1 = new Circle_C();
		 Circle_C c2 = new Circle_C();
		 
		 c1.radius = 1.5;	c2.radius = 3.5;
		 
		 c1.diamiter = c1.radius * 2;
		 c2.diamiter = c2.radius * 2;
		 
		 c1.area = Circle_C.PI * c1.radius * c1.radius;
		 c2.area = Circle_C.PI * c2.radius * c2.radius;
		 
		 System.out.printf("c1 인스턴스 정보 : 반지름(%.2f), 지름(%.2f), 넓이(%.2f)\n", c1.radius, c1.diamiter ,c1.area);
		 System.out.printf("c2 인스턴스 정보 : 반지름(%.2f), 지름(%.2f), 넓이(%.2f)\n", c2.radius, c2.diamiter ,c2.area);
		 
		 Rectangle_C r1 = new Rectangle_C();
		 Rectangle_C r2 = new Rectangle_C();
		 
		 Random rand = new Random();
		 
		 r1.width = c1.diamiter;
		 r2.width = c2.diamiter;
		 
		 r1.height = rand.nextInt(7) + 3;
		 r2.height = rand.nextInt(7) + 3;
		 
		 r1.area = r1.width * r1.height;
		 r2.area = r2.width * r2.height;
		 
		 System.out.printf("r1 인스턴스 정보 : 너비(%.2f), 높이(%.2f), 넓이(%.2f)\n", r1.width, r1.height ,r1.area);
		 System.out.printf("r2 인스턴스 정보 : 너비(%.2f), 높이(%.2f), 넓이(%.2f)\n", r2.width, r2.height ,r2.area);

				 
	}

}
