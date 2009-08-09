package allerhand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Titelaendern extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected JButton start;
	protected JButton stop;
	protected JLabel wielang;
	protected JButton nachste;
	protected JButton vorige;
	protected JButton fenster;
	protected JLabel wielangf;
	
	JFrame fenst;
	
	protected int [][] stunden = new int [6][3];
	int stu = 0;
	
	ActionListener meins = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			Date datum = new Date();
			setTitle("Uhrzeit: " +datum.getHours() +":" +datum.getMinutes() +":" +datum.getSeconds());
			int stubis = stunden[stu][0]-datum.getHours();
			int minbis = stunden[stu][1]-datum.getMinutes();
			int secbis = stunden[stu][2]-datum.getSeconds();
			
			wielang.setText("Zeit bis zum Ende der " +(stu+1) +" Stunde: " +stubis +":" +minbis +":" +secbis);
		}
		
	};
	ActionListener meins2 = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			Date datum = new Date();
			setTitle("Uhrzeit: " +datum.getHours() +":" +datum.getMinutes() +":" +datum.getSeconds());
			
			int stubis = stunden[stu][0]-datum.getHours();
			int minbis = stunden[stu][1]-datum.getMinutes();
			int secbis = stunden[stu][2]-datum.getSeconds();
			
			wielangf.setText(stubis +":" +minbis +":" +secbis);
		}
		
	};
	Timer zeit =new Timer(1000, meins);
	Timer zeit2 =new Timer(1000, meins2);
	
	public Titelaendern(){
		setTitle("Uhrzeit: ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 100);
		
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
		
		start = new JButton("start");
		stop = new JButton("stop");
		wielang = new JLabel("");
		nachste = new JButton("nächste Stunde");
		vorige = new JButton("vorige Stunde");
		fenster = new JButton("in neuem Fenster aufmachen");
		wielangf = new JLabel("");
		
		fenst= new JFrame();
		fenst.getContentPane().setLayout(new FlowLayout());
		fenst.setTitle("");
		fenst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenst.setSize(50, 50);
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		start.addActionListener(this);
		stop.addActionListener(this);
		nachste.addActionListener(this);
		vorige.addActionListener(this);
		fenster.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		fenst.getContentPane().add(wielangf);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(start);
		cp.add(stop);
		cp.add(nachste);
		cp.add(vorige);
		cp.add(fenster);
		cp.add(wielang);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Titelaendern();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start){
			zeit.start();
		}
		if(e.getSource()==stop){
			zeit.stop();
		}
		
		if(e.getSource()==nachste){
			stu++;
		}
		if(e.getSource()==vorige){
			stu--;
		}
		if(stu<0){
			stu=0;
		}
		if(stu>5){
			stu=5;
		}
		if(e.getSource()==fenster){
			fenst.setVisible(true);
			zeit2.start();
		}
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
getTime liefert die Millisekunden die seit 1970 vergangen sind zurück
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