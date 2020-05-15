package udp2;

import java.io.*;
import java.net.*;

public class UDPClient {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader inFromUser =
				new BufferedReader(new InputStreamReader(System.in));
		
		DatagramSocket clientSocket = new DatagramSocket(); // Deixa pronto para fazer uma conex�o
		
		InetAddress IPAddress =
				InetAddress.getByName("127.0.0.1"); //Endereco do destinatario
		
		
		
		byte[] sendData = new byte[1024]; //Vetor que vai receber a mensagem para ser enviada
		byte[] receiveData = new byte[1024];
		
		
		Cabecalho cabecalho = new Cabecalho();
		
		cabecalho.setSequenceNumber((byte) 9);
		
		//String seqNumber = cabecalho.getSequenceNumber();
		sendData[0] = cabecalho.getSequenceNumber();
		
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
		
	//	for (int i = 0; i < receiveData.length; i++) {
	//		System.out.println(receiveData[i]);
	//	}
	//	
	}

}
