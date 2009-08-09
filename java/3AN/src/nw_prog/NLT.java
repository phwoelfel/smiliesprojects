package nw_prog;

import java.io.IOException;
import java.net.*;

import javax.swing.JTextArea;


//NetworkListenThread
class NLT extends Thread {
	MulticastSocket socke = null;
	InetAddress IP = null;
	JTextArea empf_ta = null;
	
	public NLT(MulticastSocket s, InetAddress adr, JTextArea em){
		socke = s;
		IP = adr;
		empf_ta = em;
		try {
			socke.joinGroup(IP);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			String empf = "";
			do{
				byte[] buf = new byte[1024];
				DatagramPacket pack = new DatagramPacket(buf, buf.length);
				socke.receive(pack);
				empf = new String(pack.getData(), 0, pack.getLength());
				//System.out.println(empf);
				empf_ta.append(empf +"\n");
			}while(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
