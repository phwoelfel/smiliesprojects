package nw_prog;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class Testclient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int j = 0;
			while(true){
				Socket cl = new Socket("localhost", 5000);
				BadThread bt = new BadThread(cl, j);
				bt.start();
				j++;
			}
		} catch (UnknownHostException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class BadThread extends Thread{
	public Socket client = null;
	public int nummer = 0;
	
	public BadThread(Socket cli, int num){
		client = cli;
		nummer = num;
	}
	public void run(){
		PrintWriter out=null;
		try {
			out = new PrintWriter(client.getOutputStream());
			int z=0;
			while(true){
				out.println("Thread" +nummer +": Hallo " +z);
				out.flush();
				z++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}