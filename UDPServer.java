package udp;

import java.io.*;
import java.net.*;

public class UDPServer {
	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket serverSocket = new DatagramSocket(9876); //"9876" porta
		
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		while (true) {
			DatagramPacket receivedPacket  =
					new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivedPacket);
			String sentence = new String(receivedPacket.getData());
			InetAddress iPAddress = receivedPacket.getAddress();
			
			int port = receivedPacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			DatagramPacket senPacket = new DatagramPacket(sendData, sendData.length, iPAddress, port);
			serverSocket.send(senPacket);
		}
		
	}

}
