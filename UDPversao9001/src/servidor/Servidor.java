package servidor;

import java.io.*;
import java.net.*;

public class Servidor {

	static byte[] receiveData = new byte[1024];
	static byte[] sendData = new byte[1024];
	static int port;
	static DatagramPacket receivedPacket;
	static String sentence;
	static InetAddress iPAddress;
	static DatagramSocket serverSocket;

	public static void main(String[] args) throws Exception {

		serverSocket = new DatagramSocket(9876);

		while (true) {

			verificarConexao();
			receberDados();
			enviarDados();

		}

	}

	private static void verificarConexao() throws Exception {
		
		
		serverSocket.setSoTimeout(10);
		boolean synRecebido = false;
		while (!synRecebido) {
			try {
				CabecalhoServidor recebido = receberPacote();
				if (recebido.isSyn()) {
					synRecebido = true;
				}
			} catch (SocketTimeoutException e) {
				System.out.println("TimeOUT");
			}
		}

		boolean ackRecebido = false;
		CabecalhoServidor pacoteDeCabecalho = new CabecalhoServidor(true, true);
		while (!ackRecebido) {
			enviarPacote(pacoteDeCabecalho);

			try {
				CabecalhoServidor recebido = receberPacote();
				if (recebido.isAck()) {
					ackRecebido = true;
				}
			} catch (SocketTimeoutException e) {
				System.out.println("TimeOUT2");
			}
		}
	}

	private static void enviarDados() throws IOException {
		// DatagramSocket serverSocket = new DatagramSocket(9876);
		port = receivedPacket.getPort();
		String capitalizedSentence = sentence.toUpperCase();
		sendData = capitalizedSentence.getBytes();
		DatagramPacket senPacket = new DatagramPacket(sendData, sendData.length, iPAddress, port);
		serverSocket.send(senPacket);

	}

	private static void enviarPacote(CabecalhoServidor pacoteDeCabecalho) throws Exception {
		sendData = pacoteDeCabecalho.paraStream();
		DatagramPacket dp = new DatagramPacket(sendData, sendData.length, iPAddress, 9876);
		serverSocket.send(dp);
	}

	private static void receberDados() throws IOException {
		receivedPacket = new DatagramPacket(receiveData, receiveData.length);
		serverSocket.receive(receivedPacket);
		sentence = new String(receivedPacket.getData());
		iPAddress = receivedPacket.getAddress();

	}

	private static CabecalhoServidor receberPacote() throws Exception {
		// receiveData = new byte[2048];
		receivedPacket = new DatagramPacket(receiveData, receiveData.length);
		// port = recvDp.getPort();
		serverSocket.receive(receivedPacket);
		iPAddress = receivedPacket.getAddress();
		return CabecalhoServidor.paraPacote(receivedPacket.getData());
	}

}
