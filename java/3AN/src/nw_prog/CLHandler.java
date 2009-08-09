package nw_prog;

import java.io.*;
import java.net.*;

class CLHandler extends Thread{
	protected Socket cli = null;
	protected ChatServ serv = null;
	private PrintWriter out = null;
	public String name = "";
	
	public CLHandler(Socket cl, ChatServ cs){
		cli = cl;
		serv = cs;
		try {
			out = new PrintWriter(cli.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		String eing = "";
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
			
			do{
				writeMsg("Bitte Nickname eingeben: ");
				eing = in.readLine();
				if(eing==null){
					eing = "NoUsername";
					break;
				}
			}while(!serv.checkName(eing));
			name = eing;
			
			serv.logMsg(name +" ist dem Chat beigetreten!");
			serv.sendMsg(name +" ist dem Chat beigetreten!", this);
			
			writeMsg("Willkommen auf dem Server\n");
			out.write(name +">");
			out.flush();
			do{
				if(cli!=null){	
					eing = in.readLine();
					if(eing==null || eing.equalsIgnoreCase("exit")){break;}
					//"[A" usw sind die Pfeiltasten --> wenn die bei anderen Clients ausgegeben werden verrutscht der Cursor
					eing = eing.replace("[A", "");
					eing = eing.replace("[B", "");
					eing = eing.replace("[C", "");
					eing = eing.replace("[D", "");
					if(!eing.equals("")){
						serv.sendMsg(name +": " +eing, this);
						//System.out.println(eing);
					}
					out.write(name +">");
					out.flush();
				}
			}
			while(!eing.startsWith("exit"));
			serv.logMsg(name +" hat den Chat verlassen!");
			serv.sendMsg(name +" hat den Chat verlassen!", this);
			writeMsg("Dere!\n");
			out.close();
			in.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		finally {
			for(int i=0;i<serv.clientlist.size();i++){
				if(serv.clientlist.get(i)==this){
					serv.clientlist.remove(i);
				}
			}
			if ( cli != null ){
				try { cli.close(); } catch ( IOException e ) { System.out.println(e); }
			}
		}
	
	}
	
	public void writeMsg(String msg){
		String STZ_DAVOR = 
			new String(new byte[] { 0x0b, 0x1b, '[', 'A', 0x1b, '7', 0x1b, '[', '1', 'L', '\r' });
		String STZ_DANACH = 
			new String(new byte[] { 0x1b, '8', 0x1b, '[', '1', 'B' });
		out.print(STZ_DAVOR);
		out.print(msg);
		out.print(STZ_DANACH);
		out.flush();
	}
	
	
}
