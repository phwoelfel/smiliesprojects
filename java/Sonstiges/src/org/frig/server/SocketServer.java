/*
 * Created on Apr 16, 2007
 * Author: Paul Woelfel
 * Email: frig@frig.at
 */
package org.frig.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Vector;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public abstract class SocketServer extends Thread {

	/**
	 * @param args
	 */
	protected int port=22;
	protected boolean ssl=false;
	protected ServerSocket serversocket;
	protected boolean cleanexit=false;
	protected Vector clients;
	
	/**
	 * This is just a dummy main function, you have to use your own main
	 * @param args
	 */
	public static void main(String[] args) {
		try{
            System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        
	}
	
	public SocketServer(int port) throws GeneralSecurityException, IOException{
		this(port,false);
	}
	
	public SocketServer(int port,boolean ssl) throws GeneralSecurityException, IOException{
		super();
		this.port=port;
		this.ssl=ssl;
		clients=new Vector();
		if(this.ssl){
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(new FileInputStream(System.getProperty("javax.net.ssl.keyStore")),System.getProperty("javax.net.ssl.keyStorePassword").toCharArray());
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);
			SSLContext ctx = SSLContext.getInstance("SSLv3");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance( KeyManagerFactory.getDefaultAlgorithm() );
			kmf.init( ks, System.getProperty("javax.net.ssl.keyStorePassword").toCharArray());
			ctx = SSLContext.getInstance( "SSLv3" );
			ctx.init( kmf.getKeyManagers(), tmf.getTrustManagers(), SecureRandom.getInstance("SHA1PRNG") );
			SSLServerSocketFactory ssf = ctx.getServerSocketFactory();
			
			serversocket=(SSLServerSocket) ssf.createServerSocket(port);
			((SSLServerSocket)serversocket).setNeedClientAuth(false);
	    }else{
	    	serversocket=new ServerSocket(this.port);
	    }
		System.out.println("Server listening on Port "+this.port+" with"+(ssl?"":"out")+" ssl");
    
	}

	public void run() {
		try{
			while(!cleanexit){
				ClientHandler ch=this.createClientHandler(serversocket.accept());
				clients.add(ch);
				ch.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected abstract ClientHandler createClientHandler(Socket client) ;
	
	public void removeClient(ClientHandler c){
		clients.remove(c);
		System.out.println("Client disconnected");
	}
	
	public void cleanexit(){
		cleanexit=true;
	}
	
	public void finalize(){
		try{
			for(int i=0;i<clients.size();i++){
				try{
					((ClientHandler)clients.get(i)).interrupt();
					((ClientHandler)clients.get(i)).finalize();
				}catch(Exception e){}
			}
			serversocket.close();
			clients.clear();
		}catch(Exception e){}
	}

	public int getPort() {
		return port;
	}

	public ServerSocket getServersocket() {
		return serversocket;
	}

	public boolean isSsl() {
		return ssl;
	}


	public Vector getClients() {
		return clients;
	}

}
