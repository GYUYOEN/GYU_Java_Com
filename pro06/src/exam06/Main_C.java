package exam06;

import java.util.Arrays;

public class Main_C {

	public static void main(String[] args) {
		// 오버로딩
		GradeList_C gList1 = new GradeList_C();
		GradeList_C gList2 = new GradeList_C(5);
		GradeList_C gList3 = new GradeList_C("국어", "영어", "수학", "과학");
		for(int i = 0; i < gList3.length(); i++) {
			// System.out.println(gList3.get(i));
			System.out.println(gList3.getName(i) + ":" + gList3.getScore(i));
			
		}
		
		System.out.println("--------------------------");
		
		Grade_C[] gArr = new Grade_C[2]; // gArr : 참조값이 있는 공간
		gArr[0] = new Grade_C("과학");
		gArr[1] = new Grade_C("영어");
		
		GradeList_C gList4 = new GradeList_C(gArr); // 참조값 전달
		gArr[0].setName("국어"); // 같은 곳을 참조 하고 있음 -> 얕은 복사
		gList4.get(1).setName("수학");
		gList4.add("역사");
		gList4.add("영어", 89.4);
		gList4.add(new Grade_C("과학", 78.8));
		
		
		for(int i = 0; i < gList4.length(); i++) {
			System.out.println(gList4.getName(i) + ":" + gList4.getScore(i));
			
		}
		
		GradeList_C gList5 = new GradeList_C();
		gList5.add("국어", 67.8);
		gList5.add("영어", 78.4);
		gList5.add("수학", 82.8);
		gList5.add("과학", 83.6);
		gList5.add("사회", 93.2);
		
		System.out.println(Arrays.toString(gList5.getTop(3)));
		System.out.println(Arrays.toString(gList5.getBottom(3)));
		
		
	}

}
