package exam03.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			InetAddress ipv4 = InetAddress.getLocalHost();
			int port = 51000;
			
			DatagramSocket udpSocket = new DatagramSocket(port, ipv4);
			
			String input = sc.nextLine();
			byte[] serverAddr = parserIPvd(input);
			int ServerPort = 50000;
			
			DatagramPacket packet;
			while(true) {
				System.out.print("경로 : ");
				String path = sc.nextLine();
				
				File f = new File(path);
				
				if(f.exists()) {
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					int len = packet.getlength();
					while(bis.available() > 0) {
						byte[] data = bis.readNBytes(bis.available());
						packet = new
					}
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			
		}

	}

}
