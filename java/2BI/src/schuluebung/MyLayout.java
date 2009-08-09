package schuluebung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyLayout extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected JButton b1;
	protected JButton b2;
	protected JButton b3;
	protected JButton b4;
	protected JButton b5;
	protected JButton b6;
	
	public void init(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(2,2));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3,1));
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		b1 = new JButton("a");
		b2 = new JButton("b");
		b3 = new JButton("c");
		b4 = new JButton("1");
		b5 = new JButton("2");
		b6 = new JButton("3");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		
		cp.add(b4);
		cp.add(b5);
		cp.add(b6);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(p1);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyLayout().init();
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
