package multithreading;

import javax.swing.*;
import java.awt.*;

public class Konto extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JLabel anf;
	protected JLabel ist;
	
	protected double stand;
	protected JLabel ausg;
	
	public Konto(double anfangsstand){
		stand = anfangsstand;
		setTitle("Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 100);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(2, 1));
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		anf = new JLabel("Anfangsstand: " +stand);
		ist = new JLabel("Iststand: " +stand);
		//Initialisierung der Inhalte Ende
		
		
		//Inhalte zum Container hinzufuegen Anfang
		cp.add(anf);
		cp.add(ist);
		//Inhalte zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public synchronized void buchen(double betr){
		stand += betr;
		ist.setText("Iststand: " +stand);
	}
	
	
	public static void main(String[] args) {
		Konto k = new Konto(10000);
		Bearbeiter b1 = new Bearbeiter(k,12345, 100000);
		b1.start();
		Bearbeiter b2 = new Bearbeiter(k,12345, 100000);
		b2.start();
		Bearbeiter b3 = new Bearbeiter(k,12345, 100000);
		b3.start();
	}


}


class Bearbeiter extends Thread{
	protected int durchg;
	protected double betrag;
	protected Konto konto;
	public Bearbeiter(Konto kont,double betr, int durchgaenge){
		betrag = betr;
		durchg = durchgaenge;
		konto = kont;
	}
	
	public void run(){
		for(int i=0;i<durchg;i++){
			konto.buchen(betrag);
			konto.buchen(-betrag);
		}
	}
}