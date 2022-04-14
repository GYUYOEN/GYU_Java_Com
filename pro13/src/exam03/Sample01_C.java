package exam03;

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
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class Sample01_C {
	public static void main(String[] args) {
		/*
		 * 보조 스트림
		 * 		- 입출력애 사용하는 기반 스트림을 보조하는 역할을 수행한다.
		 * 		- 기반 스트림의 기능 높이거나 추가 기능을 부여하기 위해 사용
		 * 		- 보조 스트림은 반드시 기반 스트림을 사용해야 쓸 수 있다.(단ㄷ으로 사용 불가)
		 * 		- 문자 변환 보조 스트림(InputStreamReader, InputStreamWriter)
		 * 		  바이트 기반 스트림 + 문자 변환 보조 스트님 사용
		 * 		- 성능 향상 보조 스트림(BufferedInputStream, BufferedOutputStream, BufferedReader, BufferedWriter)
		 * 		  바이트 기반 스트림 + 성능 향상 보조 스트림(BufferInputStream, BufferOutputStream)
		 * 		  문자 기반 스트림 + 성능 향상 보조 스트림(BufferReader, BufferWriter)
		 * 		  = 바이트 기반 스트림 + 성능 향상 보조 스트임 + 문자 변환 보조 스트림
		 * 		- 기본 데이터 타입 보조 스트림(DataInputStream,DataOutputStream)
		 * 		  바이트 기반 스트림 + 기본 데이터 타입 보조 스트림
		 * 		- 객체 보조 스트림(ObjectInputSream,ObjectOutputStream)
		 * 		  바이트 기반 스트림 + 객체 보조 스트림
		 */
		Sample01_C smp = new Sample01_C();
		
//		smp.ex01();
//		smp.ex02();
//		smp.ex03();
//		smp.ex04();
		smp.ex05();
	}
	
	public void ex01() {
		// 문자 변환 보조 스트림
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/문자보조스트림.테스트");
		
//		try(FileOutputStream osw = new FileOutputStream(f)) {
		try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f))) {
//			OutputStreamWriter osw = new OutputStreamWriter(fis);
			osw.write("바이트 기반 스트림 + 문자보고 스트임");
//			osw.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		try(InputStreamReader isr = new InputStreamReader(new FileInputStream(f))) {
			// 읽을 준비가 됬는가 파일이 있는가
			char[] buff = new char[1024];
			StringBuilder sb = new StringBuilder();
			while(isr.ready()) {
				int size = isr.read(buff);
				sb.append(buff, 0, size);
			}
			System.out.println(sb.toString());
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
	}
	
	public void ex02() {
		// 성능 향상 보조 스트림
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/성능향상보조스트림.테스트");
		
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f))) {
			bos.write("바이트 기반 스트림 + 성능 향상 보조 스트임".getBytes());
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))) {
			StringBuilder sb = new StringBuilder();
			while(true) {
				// 읽을수 있을 만큼 읽어라
				int size = bis.available();
				// 읽을 것이 없으면 0, 읽을 데이터가 없다가 아니라
				if(size == 0) {
					break;
				}
				byte[] bytes = new byte[size];
				int eof = bis.read(bytes, 0, size);
				sb.append(new String(bytes));
			}
			System.out.println(sb.toString());
//			byte[] bytes = bis.readAllBytes();
//			String data = new String(bytes);
//			System.out.println(data);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
	

	
	try(OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(f)))) {
		osw.write("바이트 기반 스트림 + 성능 향상 보조 스트임 + 문자 변환 보조 스트림");
	} catch(FileNotFoundException e) {
		e.printStackTrace();
	} catch(IOException e ) {
		e.printStackTrace();
	}
	
	
	try(InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new FileInputStream(f)))) {
		char[] buff = new char[1024];
		StringBuilder sb = new StringBuilder();
		while(isr.ready()) {
			// 읽을수 있을 만큼 읽어라
			int size = isr.read(buff);
			sb.append(buff, 0, size);
			}
		System.out.println(sb.toString());
//		byte[] bytes = bis.readAllBytes();
//		String data = new String(bytes);
//		System.out.println(data);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
	}
	
	public void ex03() {
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/성능향상보조스트림.테스트");
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
			bw.write("바이트 기반 스트림 + 성능 향상 보조 스트임");
			bw.newLine(); // 개행 시켜줌
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			while(br.ready()) {
				// readLine() : 한줄씩 읽음
				System.out.println(br.readLine());
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
	}
	
	public void ex04() {
		// 기본 데이터 타입 보조
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/기본데이터타입보조스트림.테스트");
		
		try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))) {
			dos.writeBoolean(false);
			dos.writeInt(100);
			dos.writeDouble(12.34);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		try(DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
			boolean b1 = dis.readBoolean();
			int i1 = dis.readInt();
			double d1 = dis.readDouble();
			System.out.println(b1);
			System.out.println(i1);
			System.out.println(d1);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
	}
	
	public void ex05() {
		// 객체 보조 : 네트워크 통수신
		// 주의 사항 : 객체를 바이트 데이터로 변환 했을 떄의 객체 구조가 바이트 데이터를 객체로 변환할 때의
		//			 객체의 구조와 동일해야 한다.(객체 구조가 바뀌면 변환에 실패한다.)		
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/gbo_game.record");
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
			ObjSample_C os = new ObjSample_C(123, 12.34, false, "객체를 파일르 저장");
			oos.writeObject(os);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			Object obj = ois.readObject();
			ObjSample_C os = (ObjSample_C)obj;
			System.out.println(obj);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
