package exam02.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer_C {
	public static void main(String[] args) {
		try {
			/*
			 * 1. 서버용 소켓 생성
			 */
			ServerSocket sSock = new ServerSocket();
			
			/*
			 * 2. 생성한 소켓에 서버IP 주소, 서버 Port 번호 바인딩(연결)
			 */
			byte[] addr = new byte[] {(byte)192, (byte)168, 25, 57}; 
			InetAddress serverIP = InetAddress.getByAddress(addr);
			int serverPort = 50000;
			
			InetSocketAddress serverIpPort = new InetSocketAddress(serverIP, serverPort); // IP 랑 Port 결합시켜줌
			sSock.bind(serverIpPort);
			
			/*
			 * 3. 클라이언트 연결 요청을 대기 후 요청이 오면 accept 해서 클라이언트 소켓 생성
			 */
			// 연결 요청 
			Socket cSock = sSock.accept(); // cSock : 클라이언트 소켓
			
			InetAddress clientIP = cSock.getInetAddress(); // 클라이언트 주소 정보
			int clientPort = cSock.getPort(); // 클라이언트 포트 정보, 랜덤 생성
			int connectionPort = cSock.getLocalPort(); // 서버(자기자신) 포트 정보, 랜덤 생성
			
			//                  나                  상대
			System.out.printf("%s:%d <----------> %s:%d\n", serverIP.getHostAddress(), connectionPort, 
					clientIP.getHostAddress(), clientPort);
			
			/*
			 * 4. 통신용 압출력 스트림 생성
			 */
			BufferedReader sockIn = new BufferedReader(new InputStreamReader(cSock.getInputStream())); // 입력
			BufferedWriter sockOut = new BufferedWriter(new OutputStreamWriter(cSock.getOutputStream())); // 출력
			
			/*
			 * 5. 지속적인 통신을 위한 반복문
			 */
			boolean disconnect = false;
			while(true) {
				// 클라이언트로 부터 수신한 메시지가 있으면 반복 진행
				while(sockIn.ready()) {
					String line = sockIn.readLine();
					if(line.contains("exit")) {
						disconnect = true;
						break;
					}
					System.out.println(line);
				}
				if(disconnect) {
					break;
				}
			}
			
			/*
			 * 6. 통신 종료 후에는 모든 자원 반납
			 */
			sockIn.close();
			sockOut.close();
			cSock.close();
			sSock.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
