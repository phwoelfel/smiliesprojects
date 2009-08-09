package nw_prog;

import java.io.*;
import javax.swing.*;

class CloseThread extends Thread{
	private ChatServ cserv = null;
	private int time;
	private JButton buto = null;
	public CloseThread(ChatServ serva, JButton but, int tim){
		buto = but;
		cserv = serva;
		time = tim;
		cserv.logMsg("Server wird in " +time +" Sekunden beendet!");
		cserv.sendMsg("Server wird in " +time +" Sekunden beendet!", null);
		buto.setText("Server wird in " +time +" Sekunden beendet!");
		buto.setEnabled(false);	
	}
	
	public void run(){
		if(cserv.clientlist.size()!=0){
			do{
				try {
					this.sleep(1000);
					time--;
					if(time==0){break;}
					cserv.logMsg("Server wird in " +time +" Sekunden beendet!");
					cserv.sendMsg("Server wird in " +time +" Sekunden beendet!", null);
					buto.setText("Server wird in " +time +" Sekunden beendet!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}while(time > 0);
		}
		try{
			cserv.serv.close();
			cserv = null;
			System.exit(0);
		}
		catch(IOException ea){
			
		}
		
	}
}
