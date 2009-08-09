package netchat;

import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import org.frig.server.ClientHandler;
import org.frig.server.SocketServer;

public class Server extends SocketServer {

	public Server(int port) throws GeneralSecurityException, IOException {
		super(port);
	}

	@Override
	protected ClientHandler createClientHandler(Socket client) {
		return new Clienthandler(client, this);
	}
	
	protected void broadcast(String msg){
		for(int i=0;i<clients.size();i++){
			((Clienthandler)clients.get(i)).sendMessage(msg);
		}
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Port");
		int port = scan.nextInt();
		try {
			Server serv = new Server(port);
			serv.start();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
