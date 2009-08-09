package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Taschenrechner extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JButton[][] buttons = new JButton[4][3];
	public Taschenrechner(){
		setTitle("Taschenrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel tasten = new JPanel();
		tasten.setLayout(new GridLayout(4, 3));
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Taschenrechner();
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
