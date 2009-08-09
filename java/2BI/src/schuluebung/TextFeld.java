package schuluebung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextFeld extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextArea ta1;
	protected JButton bu;
	
	public void init(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		ta1 = new JTextArea("Hallo wlet");
		bu = new JButton("dere");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		cp.add(ta1);
		cp.add(bu);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TextFeld().init();
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
