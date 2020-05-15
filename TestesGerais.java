package udp2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TestesGerais {
	
	public static void main(String[] args) throws IOException {
		 
			//Declaring String variable 
		Scanner sc = new Scanner(System.in);
		
			String s = sc.next();	//Converting String into int using Integer.parseInt()
			String r = s.substring(0,1);
			System.out.println("r: " + r);
			int i = Integer.parseInt(r);  
			System.out.println(s+100);//200100, because "200"+100, here + is a string concatenation operator  
			System.out.println(i+100);//300, because 200+100, here + is a binary plus operato
			System.out.println(r+100);
		
		//DataInputStream leitura = new DataInputStream(System.in);
	/*	 int a = 4;
		a = leitura.readByte();
		
		System.out.println(a);
		
		Scanner sc = new Scanner(System.in);
		
		int b = 250;
		
		byte[] data = new byte[5];
		
		data[4] = (byte) b;
		data[3] = (byte) (b & 0xFF);
		data[2] = (byte) (b << 8);
		data[1] = (byte) ((b << 16));
		data[0] = (byte) ((b << 24));
		
		int c = (byte) data[0] + data[1] + data[2] + data[3];
		
		c = leitura.readByte();
		System.out.println(c);
		
	//	System.out.println("Resposta: " + b + " "+ data[0] + data[1]
	//		+ data[2] +" "+ data[3] + "   " + c);
	//	System.out.println(data[4]); */
	}

}
