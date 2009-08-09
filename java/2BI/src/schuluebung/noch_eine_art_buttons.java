package schuluebung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class noch_eine_art_buttons extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected JButton but1;
	protected JButton but2;
	
	public void init(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		but1 = new JButton("ja");
		but2 = new JButton("nein");
		but1.setActionCommand("jaha");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		but1.addActionListener(this);
		but2.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		cp.add(but1);
		cp.add(but2, BorderLayout.NORTH);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new noch_eine_art_buttons().init();
	}

	public void actionPerformed(ActionEvent e) {
		Object x = e.getSource();
		if(x==but1){
			System.out.println("button1");
		}
		if(e.getActionCommand().equals("nein")){
			System.out.println("button2");
		}
	}

}
