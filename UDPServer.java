package udp2;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class UDPServer {

	public static void main(String[] args) throws IOException {

		DatagramSocket serverSocket = new DatagramSocket(9876); // "9876" porta

		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		while (true) {
			DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivedPacket);
			String sentence = " ";
			sentence = new String(receivedPacket.getData());
			InetAddress iPAddress = receivedPacket.getAddress();

			String syn = sentence.substring(0,1);
			int synNumber = Integer.parseInt(syn);
			
			if (synNumber == 1) {

				int port = receivedPacket.getPort();
				String capitalizedSentence = " ";
				capitalizedSentence = "acertou";
				sendData = capitalizedSentence.getBytes();
				DatagramPacket senPacket = new DatagramPacket(sendData, sendData.length, iPAddress, port);
				serverSocket.send(senPacket);

			} else {

				int port = receivedPacket.getPort();
				String capitalizedSentence = " ";
				capitalizedSentence = sentence.toUpperCase();
				sendData = capitalizedSentence.getBytes();
				DatagramPacket senPacket = new DatagramPacket(sendData, sendData.length, iPAddress, port);
				serverSocket.send(senPacket);
			}
			
for (int i = 0; i < 1024; i++) {
	receiveData[i] = 0;
	//sendData[i] = 0;
//	serverSocket.close();
	
}
		}

	}

}
