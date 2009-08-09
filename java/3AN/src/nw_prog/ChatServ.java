package nw_prog;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class ChatServ extends JFrame implements ActionListener {
	protected ArrayList<CLHandler> clientlist = new ArrayList<CLHandler>();
	private static final int SERVERPORT = 5000;
	private boolean shutdown = false;
	private static final int SHUTTIME = 30;
	
	private JButton closeButton = new JButton("beenden");
	private JTextArea logArea = new JTextArea(10, 10);
	
	protected ServerSocket serv = null;
	

	
	public ChatServ(){
		//Gui Elemente
		setTitle("Chat Server");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800, 600);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		closeButton.addActionListener(this);
		logArea.setEnabled(false);
		
		JScrollPane jsp = new JScrollPane(logArea);
		cp.add(jsp, BorderLayout.CENTER);
		cp.add(closeButton, BorderLayout.SOUTH);
		
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(!shutdown){
					startShut();
				}
			}
		});
		
		
		setVisible(true);
		//ende
		
		Socket sock = null;
		
		try {
			serv = new ServerSocket(SERVERPORT);
			logMsg("Server gestartet auf Port " +SERVERPORT);
			//serv.setSoTimeout(60000);
			
			while(true){
				if(serv != null){
					try{
						if(!serv.isClosed()){
							sock = serv.accept();
							//PrintStream out = new PrintStream(sock.getOutputStream());
							CLHandler th = new CLHandler(sock, this);
							th.start();
							clientlist.add(th);
							logMsg("Verbunden mit " +sock.getInetAddress().getHostName() +"(" +sock.getInetAddress().getHostAddress() +"):" +sock.getPort());
						}
					}
					catch(IOException ioe){
						//ioe.printStackTrace();
						if(serv.isClosed()){
							logMsg("ServerSocket closed!");
						}else{
							logMsg("Fehler beim verbinden!");
						}
					}
				}
				else{
					break;
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
	public static void main(String[] args) {
		ChatServ cs = new ChatServ();
	}
	
	public void startShut(){
		CloseThread clt = new CloseThread(this, closeButton, SHUTTIME);
		clt.start();
		shutdown=true;
	}
	
	public synchronized void logMsg(String msg){
		logArea.append(msg +"\n");
		logArea.setCaretPosition(logArea.getText().length());
	}
	
	public void sendMsg(String msg, CLHandler not){
		for(int i=0;i<clientlist.size();i++){
			CLHandler clh = clientlist.get(i);
			if(clh != not){
				clh.writeMsg(msg +"\n");
			}
		}
	}
	
	public boolean checkName(String nam){
		nam = nam.toLowerCase().replaceAll("[^a-z0-9]", "");
		for(int i=0;i<clientlist.size();i++){
			CLHandler clh = clientlist.get(i);
			String cln = clh.name.toLowerCase().replaceAll("[^a-z0-9]", "");
			//System.out.println("nam: "+nam +"| cln: " +cln +"| contains: " +nam.contains(cln));
			if(cln!=""){
				if(nam.contains(cln)){
					return false;
				}
			}
		}
		
		return true;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==closeButton){
			startShut();
		}
	}
}



