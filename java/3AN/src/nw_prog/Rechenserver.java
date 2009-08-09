package nw_prog;

import java.io.*;
import java.net.*;

public class Rechenserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket sock = null;
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(5000);
			do{
				sock = server.accept();
				RS_clhandler clh = new RS_clhandler(sock);
				clh.start();
				System.out.println("Verbunden mit " +sock.getInetAddress().getHostName() +"(" +sock.getInetAddress().getHostAddress() +")");
			}while(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class RS_clhandler extends Thread{
	protected Socket cl = null;
	
	public RS_clhandler(Socket c){
		cl = c;
	}
	
	public void run(){
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			out = new PrintWriter(cl.getOutputStream());
			do{
				out.write("Bitte eine Rechnung eingeben (z.B.: 1+1, erlaubt: +, -, *, /)!\n\r");
				out.flush();
				String rechn = in.readLine();
				
				int erg = 0;
				String[]rechnar = rechn.split("\\+");
				if(rechnar.length>1){ //Plus rechnen
					if(!rechnar[0].equals("")){
						erg = Integer.parseInt(rechnar[0]);
					}
					for(int i=1;i<rechnar.length;i++){
						if(!rechnar[i].equals("")){
							erg += Integer.parseInt(rechnar[i]);
						}
					}
				}
				else{
					rechnar = rechn.split("-");
					if(rechnar.length>1){ //Minus rechnen
						if(!rechnar[0].equals("")){
							erg = Integer.parseInt(rechnar[0]);
						}
						for(int i=1;i<rechnar.length;i++){
							if(!rechnar[i].equals("")){
								erg -= Integer.parseInt(rechnar[i]);
							}
						}
					}
					else{
						rechnar = rechn.split("\\*");
						if(rechnar.length>1){ //Mal rechnen
							if(!rechnar[0].equals("")){
								erg = Integer.parseInt(rechnar[0]);
							}
							for(int i=1;i<rechnar.length;i++){
								if(!rechnar[i].equals("")){
									erg *= Integer.parseInt(rechnar[i]);
								}
							}
						}
						else{
							rechnar = rechn.split("/");
							if(rechnar.length>1){ //Dividiert rechnen
								if(!rechnar[0].equals("")){
									erg = Integer.parseInt(rechnar[0]);
								}
								for(int i=1;i<rechnar.length;i++){
									if(Integer.parseInt(rechnar[i])==0){
										throw new myEx("Division durch 0");
									}
									if(!rechnar[i].equals("")){
										erg /= Integer.parseInt(rechnar[i]);
									}
								}
							}
							else{
								//out.write("Ungueltige Rechnung");
								throw new myEx("Ungueltige Rechnung");
							}
						}
					}
				}
				
				out.write(rechn +"=" +erg +"\n\r");
				out.flush();
			}while(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (myEx e) {
			out.write(e.getMessage() +"\n\r");
			out.flush();
		}
		finally {
			if ( cl != null ){
				try { cl.close(); } catch ( IOException e ) { System.out.println(e); }
			}
			if( out != null){
				out.close();
			}
			if( in != null){
				try { in.close(); } catch (IOException e) {	System.out.println(e);}
			}
		}
	}
}

class myEx extends Exception{
	public myEx(String e){
		super("Fehler: " +e);
	}
}