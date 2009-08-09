package multithreading;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Protokoll extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JTextArea protarea;
	protected JScrollPane jsp;
	
	public Protokoll(){
		setTitle("Protokoll");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		protarea = new JTextArea(15, 40);
		jsp = new JScrollPane(protarea);
		//Initialisierung der Inhalte Ende
		
		
		//Inhalte zum Container hinzufuegen Anfang
		cp.add(jsp);
		//Inhalte zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public synchronized void protokollieren(String txt){
		protarea.append(Thread.currentThread().getName());
		protarea.append(txt);
		protarea.append("\n");
	}
	
	public static void main(String[] args) {
		Protokoll prt = new Protokoll();
		Protokollierer pt1 = new Protokollierer(prt);
		pt1.start();
		Protokollierer pt2 = new Protokollierer(prt);
		pt2.start();
		Protokollierer pt3 = new Protokollierer(prt);
		pt3.start();
	}


}


class Protokollierer extends Thread{
	protected Protokoll pt;
	protected Date dt;
	
	public Protokollierer(Protokoll prt){
		pt = prt;
		dt = new Date();
	}
	
	public void run(){
		while(true){
			pt.protokollieren("yay");
			Date d2 = new Date();
			if((d2.getTime() - dt.getTime())>5000){
				System.out.println("Stoooop!");
				break;
			}
		}
	}
}