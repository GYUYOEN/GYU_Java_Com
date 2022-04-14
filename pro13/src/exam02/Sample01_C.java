package exam02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * 프로그램에서 사용한 데이터를 파일로 쓰거나 읽기
		 */
		
		// 정수 배열에 저장된 1 ~ 100 까지의 임의의 정수를 파일로 저장
		Random rand = new Random();
		int[] iArr = new int[6];
		for(int i = 0; i < iArr.length; i++) {
			iArr[i] = rand.nextInt(100) + 1;
		}
		System.out.println(Arrays.toString(iArr));
		
		// FileWriter 사용.
		File f = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/read_test.txt");
		try(FileWriter fw = new FileWriter(f)) {
			for(int i = 0; i < iArr.length; i++) {
			fw.write(Integer.valueOf(iArr[i]).toString() + " ");
			}
			fw.flush();
		} catch (FileNotFoundException e) {
			System.out.println("쓰기 작업을 위한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("쓰기 작업 중 문제가 발생하였습니다.");
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		try(FileReader fr = new FileReader(f)) {
			char[] buffer = new char[4];
			char[] readChars = new char[0];
			
			while(true) {
				int i = fr.read(buffer);
				
				if(i == -1) {
					break;
				}
				
				int endIndex = readChars.length;
				readChars = Arrays.copyOf(readChars, readChars.length + i);
				System.arraycopy(buffer, 0, readChars, endIndex, i);
			}
			
			sb.append(new String(readChars));
			StringTokenizer st = new StringTokenizer(sb.toString(), " ");
			int[] nArr = new int[st.countTokens()];
			int i = 0;
			while(st.hasMoreTokens()) {
				String s = st.nextToken();
				nArr[i++] = Integer.parseInt(s);
			}
			System.out.println(Arrays.toString(nArr));
		} catch (FileNotFoundException e) {
			System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
			e.printStackTrace();
		}
	}

}
