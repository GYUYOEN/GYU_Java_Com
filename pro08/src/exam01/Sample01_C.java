package exam01;

import java.util.Arrays;

public class Sample01_C {

	public static void main(String[] args) {
		// 문자열 생성
			String s1 = "문자열";
			String s2 = new String("문자열");
			
			char[] cArr = new char[] {'문', '자', '열'};
			String s3 = new String(cArr);
				
			byte[] bArr = new byte[] {65, 66, 67, 68};
			String s4 = new String(bArr);
				
			System.out.println(s1 + "|" + s2 + "|" + s3 + "|" + s4);
			
			// 문자열 관련 메서드
			boolean equal = s1.equals(s2);
			System.out.println("동일한 문자열 값 비교 : " + equal);
			
			for(int i = 0; i < s1.length(); i++) {
				char c1 = s1.charAt(i);
				System.out.println("문자 추출 : " + c1);
			}
			
			s1 = "String(문자열) 관련 메서드";
			boolean contain = s1.contains("문자");
			System.out.println("\"문자열\" 에서 \"문자\" 포함 유무 : " + contain);
			
			int index = s1.indexOf("문자");
			System.out.println("\"문자열\" 에서 \"문자\"가 포함되어 있으면 그 위치가 어디냐? : " + index);
		
			s1 = s1.replace("메서드", "method");
			System.out.println(s1);
			
			// 문자열 분류
			String[] sArr = s1.split(" ");
			System.out.println(Arrays.toString(sArr));
			
			s1 = "010-1234-5678";
			sArr = s1.split("-");
			System.out.println(Arrays.toString(sArr));
			
			s1 = String.join("/", sArr);
			System.out.println(s1);
			
			// 전화번호를 입력받아서 출력할 때 전화번호의 뒷자리 4자리는 **** 로 마스킹하시오.
			
			s1 = "  앞/뒤로 공백이 포함된 문자열  ";
			s1 = s1.trim();
			System.out.println(s1);
			
			s1 = s1.substring(2);
			System.out.println(s1);
				
			s1 = s1.substring(3, 6);
			System.out.println(s1);
			
			s1 = "영문자 upper/lower";
			s1 = s1.toUpperCase();
			System.out.println(s1);
			
			s1 = s1.toLowerCase();
			System.out.println(s1);
			
			cArr = s1.toCharArray();	// 문자열을 문자 배열로 반환
			System.out.println(cArr);
			
			s1 = "010-1234-5678";
			boolean isPhone = s1.matches("\\d(3)-\\d(4)-\\d(4)");	
			// 정규 표현식 -> 전화번호, 이메일, 패스워드, 아이디...
			System.out.println(isPhone);
			
			s1 = String.format("%s-%s-%s", "010", "1234", "4567");
			System.out.println(s1);
	}

}
