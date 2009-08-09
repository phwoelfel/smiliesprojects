package nw_prog;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

public class Qotd_server {
	private static final int PORT = 5000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> quotes = new ArrayList<String>();
		BufferedReader in=null;
		try {
			//in = new BufferedReader(new FileReader("src/nw_prog/quotes.txt"));
			in = new BufferedReader(new InputStreamReader(nw_prog.Qotd_server.class.getResourceAsStream("quotes.txt"),"UTF-8"));
			//nw_prog.Qotd_server.class.getResourceAsStream("quotes.txt");
			String line;
			while((line=in.readLine())!=null){
				quotes.add(line);
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		Socket sock = null;
		ServerSocket serv = null;
		try {
			serv = new ServerSocket(PORT);
			System.out.println("Server started at port " +PORT);
			//serv.setSoTimeout(60000);
			int anz = 0;
			
			while(true){
				try{
					sock = serv.accept();
					//PrintStream out = new PrintStream(sock.getOutputStream());
					QotdClientHandler th = new QotdClientHandler(sock, quotes);
					th.start();
					QotdStopper st = new QotdStopper(sock, th);
					st.start();
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

class QotdClientHandler extends Thread{
	protected Socket cli = null;
	protected ArrayList<String> qots = null;
	
	public QotdClientHandler(Socket cl, ArrayList<String> arl){
		qots = arl;
		cli = cl;
	}
	
	public void run(){
		PrintWriter out = null;
		try {
			do{
				if(cli!=null){
					out = new PrintWriter(new OutputStreamWriter(cli.getOutputStream(),"UTF-8"));
					String myqot="";
					Random zuf = new Random();
					myqot = qots.get(zuf.nextInt(qots.size()));
					//int a = 0;
					String outstr = myqot;
					if(myqot.length()>80){
						outstr="";
						String[]qot = myqot.split(" ");
						int l = 0;
						
						for(int i=0;i<qot.length;i++){
							if( (l+qot[i].length()+1) > 80){
								outstr += System.getProperty("line.separator");
								l=0;
							}
							l+=qot[i].length()+1;
							outstr += qot[i] +" ";
							System.out.println("l: " +l);
							System.out.println("qot: " +outstr);
						}
					}
					
					out.println(outstr);
					out.flush();
					sleep(5000);
				}
			}
			while(true);
			
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		finally {
			if ( cli != null ){
				try { cli.close(); } catch ( IOException e ) { System.out.println(e); }
			}
			if ( out != null ){
				out.close();
			}
		}
	
	}
}


class QotdStopper extends Thread{
	protected Socket cli = null;
	protected Thread th = null;
	
	public QotdStopper(Socket cl, Thread t){
		th = t;
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
					if(eing.startsWith("exit")){
						th.interrupt();
					}
					
				}
			}
			while(!eing.startsWith("exit"));
			if(out!=null){
				out.close();
			}
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
/*
 * ArrayList<String> quotes = new ArrayList<String>();
		BufferedReader in=null;
		try {
			in = new BufferedReader(new FileReader("worte.txt"));
			String line;
			while((line=in.readLine())!=null){
				quotes.add(line);
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
 */
