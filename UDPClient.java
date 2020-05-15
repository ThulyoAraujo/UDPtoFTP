package udp;

import java.io.*;
import java.net.*;

public class UDPClient {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader inFromUser =
				new BufferedReader(new InputStreamReader(System.in));
		
		DatagramSocket clientSocket = new DatagramSocket(); // Deixa pronto para fazer uma conexão
		
		InetAddress IPAddress =
				InetAddress.getByName("127.0.0.1"); //Endereco do destinatario
		
		byte[] sendData = new byte[1024]; //Vetor que vai receber a mensagem para ser enviada
		byte[] receiveData = new byte[1024];
		
		String sentence = inFromUser.readLine(); // Le a mensagem do usuario
		
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = 
				new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		
		DatagramPacket receivePacket = 
				new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String modifiedSentence = 
				new String(receivePacket.getData());
		
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();
		
	}

}
