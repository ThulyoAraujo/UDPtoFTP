package udp2;

public class Cabecalho {
	
	//O cabecalho tera 14 bytes reservados
	
	byte sequenceNumber; //4 bytes - 0-3
	int AckNumber; //4 bytes - 4-7
	int ack; //1 byte - 8
	int rst; //1 byte - 9
	int syn; //1 byte - 10
	int fin; //1 byte - 11
	int window; //2 bytes - 12-13
	
	public byte getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(byte sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public int getAckNumber() {
		return AckNumber;
	}
	public void setAckNumber(int ackNumber) {
		AckNumber = ackNumber;
	}
	public int getAck() {
		return ack;
	}
	public void setAck(int ack) {
		this.ack = ack;
	}
	public int getRst() {
		return rst;
	}
	public void setRst(int rst) {
		this.rst = rst;
	}
	public int getSyn() {
		return syn;
	}
	public void setSyn(int syn) {
		this.syn = syn;
	}
	public int getFin() {
		return fin;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
	public int getWindow() {
		return window;
	}
	public void setWindow(int window) {
		this.window = window;
	}
	
	

}
