package nw_prog;

import java.io.*;
import java.net.*;

public class MultiEchoServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket sock = null;
		ServerSocket serv = null;
		try {
			serv = new ServerSocket(5000);
			//serv.setSoTimeout(60000);
			int anz = 0;
			
			while(true){
				try{
					sock = serv.accept();
					//PrintStream out = new PrintStream(sock.getOutputStream());
					MachwasmitnClient th = new MachwasmitnClient(sock);
					th.start();
					System.out.println("Verbunden mit " +sock.getInetAddress().getHostName() +"(" +sock.getInetAddress().getHostAddress() +")");
					anz++;
					System.out.println("Anzahl der eingegangenen Verbindungen: " +anz);
				}
				catch(IOException ioe){
					System.out.println("Error while Connecting!");
				}
			}	
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		finally {
			if ( serv != null ){
				try { serv.close(); } catch ( IOException e ) { System.out.println(e); }
			}
		}
	}

}

class MachwasmitnClient extends Thread{
	public Socket cli = null;
	
	public MachwasmitnClient(Socket cl){
		cli = cl;
	}
	
	public void run(){
		String eing = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			do{
				if(cli!=null){
					in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
					eing = in.readLine();
					if(eing.startsWith("exit")){break;}
					out = new PrintWriter(cli.getOutputStream());
					out.println("Echo: " +eing);
					out.flush();
					System.out.println("Echo(" +cli.getInetAddress().getHostName() +"): " +eing);
				}
			}
			while(!eing.startsWith("exit"));
			out.close();
			in.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		finally {
			if ( cli != null ){
				try { cli.close(); } catch ( IOException e ) { System.out.println(e); }
			}
		}
	
	}
}
