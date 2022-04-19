package exam01.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServer_C {
	
	public static void main(String[] args) {
		try {
			/*
			 * 1. 네트워크 통신을 위한 IP 주소 정보 설정
			 */
			// 서버는 가급적이면 주소 직접 설정해주는 방법으로 사용 : 서버는 여러개의 주소를 가질 수 있기 때문
			byte[] ipArr = new byte[] {(byte)192, (byte)168, 25, 57};
			InetAddress iNet = InetAddress.getByAddress(ipArr);
			
			DatagramSocket dSock = new DatagramSocket(51100, iNet);
			
			// 계속해서 통신하기 때문에 반복문 사용
			while(true) {
				/*
				 * 3. 클라이언트가 보낸 데이터 그램 패킷 메시지를 수신대기함
				 */
				byte[] buff = new byte[1024];
				DatagramPacket dPack = new DatagramPacket(buff, buff.length);
//				System.out.print("수신 대기...");
				dSock.receive(dPack); // 받을 준비
				
				SimpleDateFormat sFormat = new SimpleDateFormat("[yyyy.MM.dd a hh:mm:ss.sss]");
				String time = sFormat.format(new Date());
				
				InetAddress clientIP = dPack.getAddress();
				int clientPort = dPack.getPort(); // 50100
				System.out.printf("%s - %s:%d 에서 접속했습니다.\n", time, clientIP.toString(), clientPort);
				
				// 문서에 시간데이터를 남기기 위해, 접속 로그를 만들어줌
				try (BufferedWriter bw = new BufferedWriter(
						new FileWriter(
						new File(System.getProperty("user.home") + "/connection.log"), true))) {
					bw.write(time + "\n");
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				/*
				 * 4. 수신한 메시지를 문자열로 반환
				 */
				String rev = new String(dPack.getData(), 0, dPack.getData().length);
				System.out.println("Client 메시지를 수신하였습니다.");
				System.out.println("Client Message -> " + rev);
				
				/*
				 * 5. 응답을 위한 데이터그램 생성 후 전송
				 */
				// UDP는 구지 안해도 됨
				String msg = "수신함.";
				clientIP = dPack.getAddress(); // serverINet, dPack : 클라이언트가 보낸 패킷, 누가 보냈는지
				clientPort = dPack.getPort(); // 51100, 어떠한 포트로 보냈는지
				DatagramPacket respPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, clientIP, clientPort); // 송신한 측에 보내라
				dSock.send(respPack);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
