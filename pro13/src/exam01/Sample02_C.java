package exam01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Sample02_C {

	public static void main(String[] args) {
		/*
		 * FileInputStream 클래스
		 *     - 바이트 기반 스트림으로 바이트 단위로 파일을 읽는다.
		 *     - 미디어, 이미지, 텍스트 파일 등 모든 종류의 파일 읽기 가능
		 */
		// 미디어, 이미지 파일은 이런식으로 처리x, 텍스트 파일만 가능
		File f = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/read_test.txt");
		StringBuilder sb = new StringBuilder();
		// close를 쓰면 객체를 try, catch 밖에 만들어야함
		// 외부에서 선언
		FileInputStream fr = null;
		try {
			// 내부에서 생성
			fr = new FileInputStream(f);
			/* 영문을 읽을 때에는 아래의 방식으로도 충분.
			// while 문 쓰는 이유 : 한자한자 읽기 위해
			while(true) {
				// read() : int 형임, 얼마나 읽었냐, -1 나오면 읽을 값이 없다는 뜻
				// i 에는 읽은 바이트 값이 저장
				int i = fr.read(); // read() : 얼마나 읽었냐
				if(i == -1) {
					break;
				}
//				System.out.println((char)i); // 바로 출력
				sb.append((char)i); // StringBuilder 사용해서 출력
			}
			System.out.print(sb.toString());
			*/
			
			// 다른 나라글을 읽기 위해서 쓰는 방식
			// 버퍼 크기만큼 읽는다.
			byte[] buffer = new byte[4]; // read를 통해 4 바이트씩 읽어서 buffer에 저장 
			byte[] readBytes = new byte[0]; // 읽고 난 후 사용할 배열, 모아둘 배열
			
			while(true) {
				// 바이트 배열이 제공되고 제공된 바이트 배열만큼 읽음, 바이트 배열을 모아서 문자열로 만듬
				// 마지막에 2바이트만 남겨지면(읽으면) 2바이트만 i 값에 저장됨 -> 읽은만큼만 처리
				int i = fr.read(buffer); 
				
				if(i == -1) {
					break;
				}
				// 읽기 전 길이 측정
				int endIndex = readBytes.length;
				// 읽은 buffer들을 모두 readChars에 저장(깊은복사)
				readBytes = Arrays.copyOf(readBytes, readBytes.length + i); // 배열의 크기를 늘리기만 함
				System.arraycopy(buffer, 0, readBytes, endIndex, i); // buffer에 저장된 값을 옮겨줌
				// endIndex : 0, 4, 8, 12 ... 길이만큼 늘어나게 만들어줘야함 -> buffer를 이어서 넣어주기 위해서
			}
			
			sb.append(new String(readBytes));
			System.out.print(sb.toString());
		} catch (FileNotFoundException e) {
			System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
			e.printStackTrace();
		} finally {
			try {
				fr.close(); // 연 파일을 닫아주기 위해 -> 메모리 절략
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
