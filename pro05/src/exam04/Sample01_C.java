package exam04;

public class Sample01_C {

	public static void main(String[] args) {
		StaticTest_C st1 = new StaticTest_C();
		StaticTest_C st2 = new StaticTest_C();
		StaticTest_C st3 = new StaticTest_C();
		
		// static 예약어를 사용한 변수는 클래스 명으로 사용하는 것을 권장한다. : 어차피 공유하고 있으니까 다 똑같이 써줌
		// st1.share = 20; // 얕은복사 : 같은 참조주소를 보고있음 -> 값이 다 같게 나옴, 초기값을 지정해 주면 공유하는 변수들 다 바꿔줌
		StaticTest_C.share = 20;
		// System.out.println(st1.share + "|" + st2.share + "|" + st3.share);
		System.out.println(StaticTest_C.share + "|" + StaticTest_C.share + "|" + StaticTest_C.share);
		
		// st2.share = 30;
		StaticTest_C.share = 30;
		// System.out.println(st1.share + "|" + st2.share + "|" + st3.share);
		System.out.println(StaticTest_C.share + "|" + StaticTest_C.share + "|" + StaticTest_C.share);
		
		// st3.share = 40;
		StaticTest_C.share = 40; // 변수의 얕은 복사처럼 다 같은 static을 보고있음. 그래서 값이 같아짐
		// System.out.println(st1.share + "|" + st2.share + "|" + st3.share);
		System.out.println(StaticTest_C.share + "|" + StaticTest_C.share + "|" + StaticTest_C.share);
		
		FinalTest_C ft1 = new FinalTest_C();
		
		System.out.println(ft1.CONSTANT);
		// ft1.CONSTANT = 20; // -> The final field FinalTest_C.CONSTANT cannot be assigned
		
		FinalStaticTest_C fst1 = new FinalStaticTest_C();
		FinalStaticTest_C fst2 = new FinalStaticTest_C();
		
		System.out.println(fst1.CONSTSHARE + "|" + fst2.CONSTSHARE); // 구지 이렇게 할필요 없음 ->
		System.out.println(FinalStaticTest_C.CONSTSHARE);
		FinalStaticTest_C.CONSTSHARE = 30; // -> 오류 : Final이라서
		
	}

}
