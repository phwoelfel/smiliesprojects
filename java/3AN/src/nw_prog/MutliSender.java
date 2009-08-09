package nw_prog;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class MutliSender {

	
	public static void main(String[] args) {
		int PORT = 5000;
		String IP_ADR = "230.1.33.7";
		String eing = "";
		
		Scanner sc = new Scanner(System.in);
		MulticastSocket socke = null;
		InetAddress IP = null;
		
		try {
			IP = InetAddress.getByName(IP_ADR);
			socke = new MulticastSocket(PORT);
			
			do{
				eing = sc.nextLine();
				DatagramPacket pack = new DatagramPacket(eing.getBytes(), eing.length(), IP, PORT);
				socke.send(pack);
			}while(!eing.equals("exit"));
			
		} catch (UnknownHostException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		
	}

}
