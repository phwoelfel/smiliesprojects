package nw_prog;

import java.io.*;
import java.net.*;

public class Hallo_Welt_Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket sock = null;
		ServerSocket serv = null;
		try {
			serv = new ServerSocket(5000);
			serv.setSoTimeout(60000);
			int anz = 0;
			while(true){
				sock = serv.accept();
				//PrintStream out = new PrintStream(sock.getOutputStream());
				PrintWriter out = new PrintWriter(sock.getOutputStream());
				out.println("Hallo Welt");
				out.close();
				anz++;
				System.out.println("Nachricht gesendet!\nAnzahl der eingegangenen Verbindungen: " +anz);
				if ( sock != null ){
					try { sock.close(); } catch ( IOException e ) { System.out.println(e); }
				}
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
		finally {
			if ( serv != null ){
				try { serv.close(); } catch ( IOException e ) { System.out.println(e); }
			}
		}
	}

}
