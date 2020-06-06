package cliente;

import java.io.*;
import java.net.*;

public class Cliente {

	static byte[] sendData = new byte[1024];
	static byte[] receiveData = new byte[1024];
	static String sentence;
	static DatagramSocket clientSocket;
	static InetAddress IPAddress;
	static int meuSeqNumber;
	static DatagramPacket sendPacket;

	public static void main(String[] args) throws Exception {

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		clientSocket = new DatagramSocket();
		IPAddress = InetAddress.getByName("127.0.0.1");

		sentence = "Este e um teste";

		System.out.println("Gerando conexão");
		gerarConexao();

		enviarDados();
		System.out.println("Dados enviados");
		String modifiedSentence = receberDados();
		System.out.println("Dados Recebidos");

		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();

	}

	private static void gerarConexao() throws Exception {

		CabecalhoCliente pacotesDeCabecalho = new CabecalhoCliente(true, false);
		
		clientSocket.setSoTimeout(10);
		boolean synAckRecebido = false;
		while (!synAckRecebido) {
			enviarPacotes(pacotesDeCabecalho);
			try {
				CabecalhoCliente recebido = receberPacotes();
				if (recebido.isAck() && recebido.isSyn()) {
					synAckRecebido = true;
					System.out.println("Syn Ack recebido");
				}
			} catch (SocketTimeoutException e) {

			}
		}
		
		boolean ackRecebido = false;
		pacotesDeCabecalho = new CabecalhoCliente(false, true);
		while(!ackRecebido) {
			enviarPacotes(pacotesDeCabecalho);
			try {
				receberPacotes();
			} catch (SocketTimeoutException e) {
				break;
			}
		}

	}

	private static String receberDados() throws IOException {
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		return new String(receivePacket.getData());
	}

	private static CabecalhoCliente receberPacotes() throws Exception {
		receiveData = new byte[1024];
		DatagramPacket recvDp = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(recvDp);
		return CabecalhoCliente.paraPacote(recvDp.getData());
	}

	private static void enviarDados() throws IOException {
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
	}

	private static void enviarPacotes(CabecalhoCliente pacoteDeCabecalho) throws Exception {
		sendData = pacoteDeCabecalho.paraStream();
		DatagramPacket sdPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sdPacket);
	}

}
