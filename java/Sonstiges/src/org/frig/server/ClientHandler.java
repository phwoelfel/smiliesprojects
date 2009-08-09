/*
 * Created on Apr 16, 2007
 * Author: Paul Woelfel
 * Email: frig@frig.at
 */
package org.frig.server;

import java.net.Socket;

public abstract class ClientHandler extends Thread {

	protected Socket client;
	protected SocketServer sup;
	
	public ClientHandler(Socket client, SocketServer sup){
		this.client=client;
		this.sup=sup;
		System.out.println("New Client connected");
	}
	
	public void run() {
		this.service();
		try {
			sup.removeClient(this);
		} catch (Exception e) {}	
	}
	
	public abstract void service();
	
	
	
	public void finalize(){
		try {
			this.client.close();
		} catch (Exception e) {}
		
	}

}
