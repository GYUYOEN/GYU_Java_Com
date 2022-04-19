package exam02.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class TCPClient_C {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			/*
			 * 1. 클라이언트용 소켓 생성
			 */
			byte[] addr = new byte[] {(byte)192, (byte)168, 25, 57}; 
			InetAddress serverIP = InetAddress.getByAddress(addr);
			int serverPort = 50000; // 서버 포트
			// 클라이언트 포트는 안쓰는 거 알아서 랜덥 생성
			
			Socket sock = new Socket(serverIP, serverPort); // 접속하고자 하는 서버 IP와 Port
			
			/*
			 * 통신용 입출력 스트림 생성
			 */
			
			// getInputStream(socket에서 만들어 줌) : 바이트기반 -> InputStreamReader : 문자변환보조 -> BufferedReader : 성능향상보조
			BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream())); // 입력
			// BufferedWriter : 버퍼에 담기 -> OutputStreamWriter : 문자를 -> sock.getOutputStream() : 바이트로
			BufferedWriter sockOut = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())); // 출력
			while(true) {
				// 서버로 부터 수신한 메시지가 있으면 반복 진행 -> 읽기 위해
				while(sockIn.ready()) {
					String line = sockIn.readLine(); // 줄단위로 읽음
					System.out.println(line);
				}
				
				// 서버에 메시지 전송
				System.out.print("Client : ");
				String msg = sc.nextLine();
				sockOut.write(msg);
				sockOut.newLine(); // 개행
				sockOut.flush();
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}