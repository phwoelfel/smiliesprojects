package netchat;

import java.io.IOException;
import java.net.Socket;

import org.frig.server.ClientHandler;
import org.frig.server.SocketServer;

public class Clienthandler extends ClientHandler {
	
	protected java.io.PrintWriter os = null;
	protected java.io.BufferedReader is = null;
	
	
	public Clienthandler(Socket client, SocketServer sup) {
		super(client, sup);
		try {
			os = new java.io.PrintWriter(client.getOutputStream());
			is = new java.io.BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void service() {
		try {
			
			String userInput = "";
			boolean go = true;
			while(go){
				userInput = is.readLine();
				if(userInput==null){
					go=false;
					break;
				}
				System.out.println("Message received: "+userInput);
				if(userInput.equals("exit")){
					go=false;
					break;
				}
				else{
					((Server)sup).broadcast(userInput);
				}
			}
		
		is.close();
		os.close();
		is=null;
		os=null;
		client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg){
		if(os!=null){
			os.write(msg+"\n");
			os.flush();
		}
	}
	
	public String toString(){
		return "";
	}
	
}
