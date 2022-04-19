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
	
	public Database_C() {
		this.file = new File("C:/Users/GUE1/gbo_game.record");
	}
	
	public Database_C(File file) {
		this.file = file;
	}
	
	public int[] load() {
		int[] nArr = new int[3];
		
		if(file.exists()) {
			// Buffered 이용
//			try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//				String data = "";
//				while(br.ready()) {
//					data = br.readLine();
//				}
//				
//				StringTokenizer st = new StringTokenizer(data, "\n");
//				nArr = new int[st.countTokens()];
//				int i = 0;
//				while(st.hasMoreTokens()) {
//					String s = st.nextToken();
//					nArr[i++] = Integer.parseInt(s);
//				} 
//				
//			} catch (FileNotFoundException e) {
//				System.out.println("FileReader 클래스로 읽을 파일을 찾지 못했습니다.");
//				e.printStackTrace();
//			} catch (IOException e) {
//				System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
//				e.printStackTrace();
//			}
//		}
			
			try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
				int i = 0;
				while(dis.available() > 0) {
					nArr[i++] = dis.readInt();
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
	
	public void save(int[] record) {
		// Buffered 이용
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
//			for(int i = 0; i < record.length; i++) {
//				bw.write(Integer.valueOf(record[i]).toString() + " ");
//			}
//			bw.flush();
//		} catch (FileNotFoundException e) {
//			System.out.println("쓰기 작업을 위한 파일을 찾을 수 없습니다.");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("쓰기 작업 중 문제가 발생하였습니다.");
//			e.printStackTrace();
//		}
		
		// 
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