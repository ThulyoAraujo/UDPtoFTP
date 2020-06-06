package servidor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CabecalhoServidor implements Serializable {

	private boolean syn = false;
	private boolean ack = false;
	/*
	 * private boolean rst = false; private boolean fin = false;
	 * 
	 * private int seqNumber; private int ackNumber; private short window;
	 */

	public CabecalhoServidor(boolean syn, boolean ack) {
		this.syn = syn;
		this.ack = ack;
	}

	public boolean isSyn() {
		return syn;
	}

	public void setSyn(boolean syn) {
		this.syn = syn;
	}

	public boolean isAck() {
		return ack;
	}

	public void setAck(boolean ack) {
		this.ack = ack;
	}
	/*
	 * public boolean isRst() { return rst; }
	 * 
	 * public void setRst(boolean rst) { this.rst = rst; }
	 * 
	 * public boolean isFin() { return fin; }
	 * 
	 * public void setFin(boolean fin) { this.fin = fin; }
	 * 
	 * public int getSeqNumber() { return seqNumber; }
	 * 
	 * public void setSeqNumber(int seqNumber) { this.seqNumber = seqNumber; }
	 * 
	 * public int getAckNumber() { return ackNumber; }
	 * 
	 * public void setAckNumber(int ackNumber) { this.ackNumber = ackNumber; }
	 * 
	 * public short getWindow() { return window; }
	 * 
	 * public void setWindow(short window) { this.window = window; }
	 */

	byte[] paraStream() {
		byte[] stream = null;
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			ObjectOutput outS = new ObjectOutputStream(bos);
			outS.writeObject(this);
			outS.flush();
			stream = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}

	public static CabecalhoServidor paraPacote(byte[] stream) {
		CabecalhoServidor streamTotal = null;
		try (ByteArrayInputStream bais = new ByteArrayInputStream(stream);
				ObjectInputStream ois = new ObjectInputStream(bais)) {
			streamTotal = (CabecalhoServidor) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return streamTotal;
	}

}
