package game.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Database_C {

	private File file;
	
	// 정해진 파일을 미리 지정
	public Database_C() {
		this.file = new File("C:/Users/user1/gbo_game.record");
	}
	
	// 파일을 바꾸고 싶은 경우
	public Database_C(File file) {
		this.file = file;
	}
	
	// 불러오기
	public int[] load() {
		int[] nArr = new int[3];
		
		if(file.exists()) {
			StringBuilder sb = new StringBuilder();
			try(FileReader fr = new FileReader(file)) {
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
			
				// 골백을 기준으로 문자열 분리
				StringTokenizer st = new StringTokenizer(sb.toString(), " ");
				nArr = new int[st.countTokens()];
				int i = 0;
				while(st.hasMoreTokens()) {
					String s = st.nextToken();
					nArr[i++] = Integer.parseInt(s);
				}
			
			} catch (FileNotFoundException e) {
				System.out.println("FileReader 클래스로 읽을 파일을 찾지 못했습니다.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
				e.printStackTrace();
			}
		}
		return nArr;
	}



	
	// 저장
	public void save(int[] record) {
		try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			
			for(int i = 0; i < record.length; i++) {
				dos.writeInt(record[i]);
			}
			
			dos.flush();
		} catch (FileNotFoundException e) {
			System.out.println("쓰기 작업을 위한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("쓰기 작업 중 문제가 발생하였습니다.");
			e.printStackTrace();
		}
	}
}

