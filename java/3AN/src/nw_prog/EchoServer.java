package nw_prog;

import java.io.*;
import java.net.*;

public class EchoServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket sock = null;
		ServerSocket serv = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			serv = new ServerSocket(5000);
			//serv.setSoTimeout(60000);
			int anz = 0;
			String eing = "";
			
			while(true){
				sock = serv.accept();
				//PrintStream out = new PrintStream(sock.getOutputStream());
				try{
					do{
						if(sock!=null){
							in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
							eing = in.readLine();
							if(eing.startsWith("exit")){break;}
							out = new PrintWriter(sock.getOutputStream(), true);
							out.println("Echo: " +eing);
							//out.flush();
						}
					}
					while(!eing.startsWith("exit"));
					out.close();
					in.close();
					if ( sock != null ){
						try { sock.close(); } catch ( IOException e ) { System.out.println(e); }
					}
					anz++;
					System.out.println("Anzahl der eingegangenen Verbindungen: " +anz);
			
				} catch (IOException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				finally {
					if ( in != null ){
						try { in.close(); } catch ( IOException e ) { }
					}
					if ( out != null ){
						out.close();
					}
					if ( sock != null ){
						try { sock.close(); } catch ( IOException e ) { }
					}
					
				}
			
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		finally{
			if ( serv != null ){
				try { serv.close(); } catch ( IOException e ) { }
			}
		}
	}
}
