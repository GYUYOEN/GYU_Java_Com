package exam01;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Sample01_C {

	public static void main(String[] args) {
		/*
		 * 입출력(IO, Input/Output)
		 *     - 컴퓨터 내부/외부 장치와의 데이터를 주고받는 것
		 *     - 프로그램을 기준으로 내부/외부 장치에서 데이터가 현재 동작하는 프로그램으로 들어오면 입력
		 *       외부 장치로 데이터를 보내면 출력
		 *       
		 * 스트림(Stream)
		 *     - 입력스 트림, 출력스트림으로 나누어서 불린다.
		 *     - 두 장치간에 데이터를 지속적으로 입/출력하기 위해 생성된 연결 통로
		 *     - 단방향 통신을 지원하기 때문에 입력/출력을 동시에 할 수 없다.
		 *       동시에 하기 위해서 입력스트림, 출력스트림을 개별적으로 만든다.
		 *     - 스트림을 통해 데이터를 주고받을 때 문자기반스트림과 바이트기반스트림으로
		 *       분리된다.
		 *     - 문자기반스트림의 경우 일반 텍스트 문서를 전송하기 위해 사용한다.
		 *       (메모장 o, 한글파일 x, 보통 txt 확장자를 사용)
		 *     - 바이트기반스트림의 경우 일반 텍스트 문서를 제외한 모든 파일을 전송하기 위해 사용한다.
		 *       (영상, 음향, 이미지, 실행파일 등)
		 *       
		 *       연결프로그램을 메모장으로 열었을 때 읽을 수 있으면 문자기반스트림
		 *       읽을 수 없으면 바이트기반스트림
		 *       
		 *       내 프로그램 -> 콘솔창(System.out(출력))
		 *       내 프로그램 <- 콘솔창(System.in(입력))
		 */
		
		/*
		 * File 클래스(new File(확인하고자 하는 파일의 경로의 매개변수))
		 *     - 파일의 크기, 이름, 정보 등을 알아내기 위한 클래스로 사용
		 *     - 파일 생성, 삭제 및 폴더 생성, 삭제 기능을 제공
		 */
		File f = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/eclipse.ini");
		System.out.printf("파일 크기 : %d Byte\n", f.length());
		System.out.printf("파일 크기 : %.2f KByte\n", f.length() / 1024.0);
		
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
		String modifiedDate = sFormat.format(new Date(f.lastModified()));
		System.out.printf("수정 날짜 : %s\n", modifiedDate);
		
		System.out.printf("실행 파일 : %s\n", f.canExecute());
		System.out.printf("읽기 가능 : %s\n", f.canRead());
		System.out.printf("쓰기 가능 : %s\n", f.canWrite());
		
		System.out.printf("파일 : %s\n", f.isFile()); // 파일이냐
		System.out.printf("폴더 : %s\n", f.isDirectory()); // 폴더냐
		System.out.printf("숨김 : %s\n", f.isHidden()); // 숨김으로 되어있냐
		
		System.out.printf("파일/폴더명 : %s\n", f.getName());
		System.out.printf("상위 폴더명 : %s\n", f.getParent());
		System.out.printf("전체 경로명 : %s\n", f.getPath());
		
		System.out.printf("실제 파일/폴더 존재 유무 : %s\n", f.exists());
		
		File f2 = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/my_file");
		try {
			f2.createNewFile(); // my_file 이라는 새로운 파일을 만들어줌
		} catch (IOException e) {
			System.out.println("f2.createNewFile() 로 파일 생성중 에러 발생!");
			e.printStackTrace();
		}
		
		File f3 = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/my_folder");
		f3.mkdir(); // my_folder 라는 새로운 폴더를 생성해줌
		
		File f4 = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/my_file.txt");
		f4.delete(); // 파일 지우기
		
		File f5 = new File("C:/Users/GUE1/eclipse/jee-2021-12/eclipse/");
		String[] fileList1 = f5.list(); // 파일 목록 문자열 배열로 출력, 파일명만 출력
		System.out.println(Arrays.toString(fileList1));
		
		File[] fileList2 = f5.listFiles(); // 파일 객체로 출력, 전체적으로 출력
		System.out.println(Arrays.toString(fileList2));
		
		for(int i = 0; i < fileList2.length; i++) {
			String name = fileList2[i].getName();
			if(name.startsWith("my_")) { // startsWith("my_") : "my_"로 시작하는 파일
				fileList2[i].delete(); // 삭제
			}
		}
	}

}
