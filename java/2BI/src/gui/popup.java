package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class popup extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	public void init(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		JMenuBar mbar = new JMenuBar();
		JMenu m = new JMenu("test");
		m.add("eins");
		m.add("zwei");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		m.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		mbar.add(m);
		this.setJMenuBar(mbar);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new popup().init();
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
