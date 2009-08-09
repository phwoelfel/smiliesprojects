import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;

import org.frig.server.ClientHandler;
import org.frig.server.SocketServer;


public class Netzwerk_test {

	public static void main(String[] args) {
			try {
				SocketServer server=new SocketServer(5000){
					protected ClientHandler createClientHandler(Socket client){
						return new ClientHandler(client,this){

							@Override
							public void service() {
								try {
								java.io.PrintWriter os = null;
								java.io.BufferedReader is = null;
								os = new java.io.PrintWriter(client.getOutputStream(), true);
								is = new java.io.BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
								
								String msg=is.readLine();
								System.out.println("Hey a client sent us "+msg);
								os.write("Thank you for you message!! "+msg);
								
								} catch (IOException e) {
									e.printStackTrace();
								}
								
							}
							
						};
					}
				};
				server.start();
				
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
