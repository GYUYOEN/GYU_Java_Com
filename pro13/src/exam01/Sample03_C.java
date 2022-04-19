package exam01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sample03_C {

	public static void main(String[] args) {
		/*
		 * FileOutputStream
		 *     - 바이트 단위로 파일에 데이터를 쓰기위해 사용
		 *     - 미디어, 이미지, 텍스트 파일 등 모든 종류의 파일 쓰기 가능
		 */
		// 파일 쓰기할 때는 파일이 없으면 새로 만들어줌
		File f = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/read_test.txt");
		
		// 원래는 이전에 작업했던 거에 덮어씌어 지는데 이어서 작성하고 싶으면 true를 씀
		try (FileOutputStream fos = new FileOutputStream(f, true)) {
			fos.write(65); // -> ACII코드로 인식해 파일에 A가 써짐, 타입이 바이트 타입
			
			byte[] bArr = new byte[] {66, 67, 68, 69};
			fos.write(bArr);
			
			// "\nFileOutputStream\n".getBytes() : FileOutputStream 문자열을 바이트로 변환헤서 쓰기
			fos.write("\nFileOutputStream\n".getBytes());
			
			fos.write("한글로도 쓰기\n".getBytes());
			
			// buffer에 남아있는거 없이 다 내보내라
			fos.flush();
		} catch (FileNotFoundException e) {
			System.out.println("쓰기 작업을 위한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("쓰기 작업 중 문제가 발생하였습니다.");
			e.printStackTrace();
		}
	}

}
