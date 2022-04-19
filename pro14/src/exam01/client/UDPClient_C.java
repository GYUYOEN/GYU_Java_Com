package exam01.client;
// client와 server는 개별 PC라고 생각하고 봐야함 

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
import java.util.Scanner;

public class UDPClient_C {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			// localhost : pc 내부에서 사용하기 위한 주소 -> 127.0.0.1
//			InetAddress iNet = InetAddress.getByName("localhost");
//			System.out.println(iNet.getHostAdress()); // -> localhost 출력
			
			// 직접 지정해줌
			// 명령 프롭프트에 ipconfig 입력 -> IPv4 주소
			// 다른 PC 사이에 통신해야할 때 이 주소 사용
//			byte[] ipArr = new byte[] {(byte)192, (byte)168, 25, 57};
//			InetAddress iNet = InetAddress.getByAddress(ipArr);
			
			/*
			 * 1. 네트워크 통신을 위한 IP 주소 정보 설정
			 */
			
			// getLocalHost() : 별도로 주소를 지정하지 않아도 자동으로 알아서 지정해줌
			// InetAddress 객체로 만들어줌
			InetAddress iNet = InetAddress.getLocalHost();
			InetAddress serverINet = iNet;
			/*
			 * 2. UDP 통신용 소켓 생성
			 */
			DatagramSocket dSock = new DatagramSocket(50100, iNet); // 바인딩 시켜줌, 두개가 연결이 되어야함
			
			while(true) {
				/*
				 * 3. 사용자가 입력한 메시지를 데이터 그램 패킷으로 만들어서 전송, 메시지 보내기
				 */
				// 메아리
				System.out.print("Client : ");
				
				String msg = sc.nextLine();
				// 문자열에서 바이트로 바꿔줌
				// 51100 : 서버 포트
				DatagramPacket dPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverINet, 51100);
				dSock.send(dPack); // 만들어진 거 보내기
				
				/*
				 * 4. 서버로부터 데이터 수신확인 응답을 받기 위한 부분, 메시지 수신
				 * 3번 4번 순서 바뀌면 서버
				 */
				// UDP는 구지 안해도 됨
				byte[] buff = new byte[1024];
				DatagramPacket resPack = new DatagramPacket(buff, buff.length); // resPack : 누가 보냈는지에 대한 정보 있음
				dSock.receive(resPack);
				
				InetAddress clientIP = dPack.getAddress(); // 송신측 IP
				int clientPort = dPack.getPort(); // 송신측 Port
				
				String rev = new String(resPack.getData(), 0, resPack.getData().length);
				System.out.println("Server Message -> " + rev);
			}
		} catch (UnknownHostException e) {
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
