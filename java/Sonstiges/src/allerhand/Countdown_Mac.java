package allerhand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.awt.Toolkit;


public class Countdown_Mac extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected JLabel wielang;

	
	protected int [][] stunden = new int [6][3];
	int stu = 0;
	
	ActionListener meins = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			Date datum = new Date();
			setTitle("" +check(datum.getHours()) +":" +check(datum.getMinutes()) +":" +check(datum.getSeconds()));
			
			int stubis = stunden[stu][0]-datum.getHours();
			int minbis = stunden[stu][1]-datum.getMinutes();
			int secbis = stunden[stu][2]-datum.getSeconds();
			
			wielang.setText(""  +"" +check(stubis) +":" +check(minbis) +":" +check(secbis));
		}
		
	};
	Timer zeit =new Timer(1000, meins);
	
	
	public int getY(){
	       int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	       return y;
	    }

	int y = getY();
	
	public Countdown_Mac(){
		System.out.println(y);
		setLocation(0,y-60);
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 60);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		stunden[0][0] = 8;
		stunden[0][1] = 50;
		stunden[0][2] = 60;
		stunden[1][0] = 9;
		stunden[1][1] = 40;
		stunden[1][2] = 60;
		stunden[2][0] = 10;
		stunden[2][1] = 45;
		stunden[2][2] = 60;
		stunden[3][0] = 11;
		stunden[3][1] = 35;
		stunden[3][2] = 60;
		stunden[4][0] = 12;
		stunden[4][1] = 25;
		stunden[4][2] = 60;
		stunden[5][0] = 13;
		stunden[5][1] = 25;
		stunden[5][2] = 60;
		

		wielang = new JLabel("");

		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang

		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(wielang);
		//JPanel zum Container hinzufuegen Ende
		
		zeit.start();
		setVisible(true);
		//setAlwaysOnTop(true);
		setResizable(false);

	}
	
	public static void main(String[] args) {
		new Countdown_Mac();
	}

	public void actionPerformed(ActionEvent e) {



	}
	
	public String check (int time){
		String tmp = ""+time;
		if(time>=0 && time<10){
			tmp = "0"+time;			
		}
		return tmp;
	}
	
	public int welcheStunde(){
		int out=0;
		Date dat = new Date();
		int stu = dat.getHours();
		int min = dat.getMinutes();
		
		for(int i=0; i<stunden.length;i++){
			if(stu<=stunden[i][0] && min<=stunden[i][1]){
				out = i;
			}
		}
		
		return out;
	}
	
}
/*
getTime liefert die Millisekunden die seit 1970 vergangen sind zurŸck
long t = datum.getTime();
long sec = t/1000;
long min = sec/60;
long h = min/60;
long d = h/24;
long w = d/7;
long m = d/30;
long j = d/365;
System.out.println("t: "+ t +"\nsec: " +sec +"\nmin: " +min +"\nh: " +h +"\nd: " +d +"\nw: " +w +"\nm: " +m +"\nj: " +j);
*/