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
		 * 		  바이트 기반 스트림 + 문자 변환 보조 스트림 사용 : 네트워크 통신 관련 작업할 때 사용
		 * 		- 성능 향상 보조 스트림(BufferedInputStream, BufferedOutputStream, BufferedReader, BufferedWriter) : 버퍼 역할
		 * 		  바이트 기반 스트림 + 성능 향상 보조 스트림(BufferInputStream, BufferOutputStream)
		 * 		  문자 기반 스트림 + 성능 향상 보조 스트림(BufferReader, BufferWriter)
		 * 		  = 바이트 기반 스트림 + 성능 향상 보조 스트임 + 문자 변환 보조 스트림
		 * 		- 기본 데이터 타입 보조 스트림(DataInputStream,DataOutputStream)
		 * 		  바이트 기반 스트림 + 기본 데이터 타입 보조 스트림 : 기본 자료형을 입출력해줌 -> 입출력할 때 안바꿔 줘도됨
		 * 		- 객체 보조 스트림(ObjectInputSream,ObjectOutputStream)
		 * 		  바이트 기반 스트림 + 객체 보조 스트림 : 객체 입출력에 사용
		 * 
		 * 버퍼 : 프로그램과 장치사이에 입출력할 때 사용하는 중간 완충 장치(임시 저장 해줌)
		 * 		 프로그램 동작이 느려지는 것을 막아줌	 
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
		// 사용자 home Directory 알려줌
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/문자보조스트림.테스트");
		
//		try(FileOutputStream osw = new FileOutputStream(f)) { // osw.close(); 를 작성 해야함 ->
		try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f))) {
//			OutputStreamWriter osw = new OutputStreamWriter(fis); // 기반 스트림을 가져다 쓰는 보조 스트림
			osw.write("바이트 기반 스트림 + 문자보고 스트림"); // 타입이 문자
//			osw.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		try(InputStreamReader isr = new InputStreamReader(new FileInputStream(f))) {
			char[] buff = new char[1024];
			StringBuilder sb = new StringBuilder();
			
			// 읽을 준비가 됬는가 파일안에 읽을 내용이 있는가
			while(isr.ready()) {
				int size = isr.read(buff);
				// buff에 있는 내용을 0 부터 size만큼 추가
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
			// 성능은 buffer만 추가한 거기 때문에 바이트 요구 -> getBytes() 써야됨
			bos.write("바이트 기반 스트림 + 성능 향상 보조 스트임".getBytes());
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))) {
			StringBuilder sb = new StringBuilder();
			while(true) {
				// 읽을수 있을 만큼 읽어라, bis.available() : 버퍼에 읽을 수 있는 데이터 양
				int size = bis.available();
				// 읽을 것이 없으면 0, 읽을 데이터가 없다가 아니라 버퍼에 채워져 있는 데이터가 없다는 말, 더이상 읽지 않겠다
				if(size == 0) {
					break;
				}
				byte[] bytes = new byte[size]; // 읽을 수 있는 size 만큼 byte를 만들고
				int eof = bis.read(bytes, 0, size); // -1 이 아닐때 까지 읽어라
				sb.append(new String(bytes)); // 읽을 수 있게 문자로 바꿔줌
			}
			System.out.println(sb.toString());
			
			// 파일 Input에도 있는거라 위와같이 bufferedInput에만 있는 기능 사용해봄
//			byte[] bytes = bis.readAllBytes(); // 모든 바이트를 읽어서 배열에 저장
//			String data = new String(bytes); // 우리가 읽을 수 있게 문자로 뱐환
//			System.out.println(data);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
	

	// 구지 이렇게 쓰지 말고 "문자 기반 스트림 + 성능 향상 보조 스트림" 사용, 같은 거임
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
	
	public void ex03() {
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/성능향상보조스트림.테스트");
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
			bw.write("문자 기반 스트림 + 성능 향상 보조 스트임");
			bw.newLine(); // 개행 시켜줌
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e ) {
			e.printStackTrace();
		}
		
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			while(br.ready()) {
				// readLine() : 한줄씩 읽음 -> 반환타입 문자
				System.out.println(br.readLine()); // 바꿀 필요 없이 바로 출력
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
			dos.writeBoolean(false); // byte로 써서 파일에 알 수 없는 문자가 써짐 : 파일을 읽을 필요 없을 때 사용
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
			ObjSample_C os = (ObjSample_C)obj; // 다운캐스팅
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
