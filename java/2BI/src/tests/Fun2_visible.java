package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fun2_visible extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private JButton but_ja1 = new JButton("ja");
	private JButton but_ja2 = new JButton("ja");
	private JButton but_nein = new JButton("nein");
	
	
	/*
	 * Philip Wšlfel, 2BI
	 * 31.01.08
	 */
	public Fun2_visible(){
		setTitle("Fun2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFrameLocation(250, 100);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel unten = new JPanel();
		unten.setLayout(new GridLayout(1,3));
		
		JPanel unten2 = new JPanel();
		unten2.setLayout(new FlowLayout());
		
		JPanel oben = new JPanel();
		oben.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		JLabel text = new JLabel("Ich moechte blabla waehlen?");
		
		but_ja2.setVisible(false);
		//Initialisierung der Inhalte Ende
		
		
		//Mouse Listener zu den Inhalten hinzufuegen Anfang
		but_ja1.addMouseListener(this);
		but_ja2.addMouseListener(this);
		//Mouse Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		oben.add(text);
		unten.add(but_ja1);
		unten.add(but_nein);
		unten.add(but_ja2);
		unten2.add(unten);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(oben, BorderLayout.NORTH);
		cp.add(unten2, BorderLayout.CENTER);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Fun2_visible();
	}


	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if(e.getSource()==but_ja1){
			but_ja1.setVisible(false);
			but_ja2.setVisible(true);
		}
		if(e.getSource()==but_ja2){
			but_ja1.setVisible(true);
			but_ja2.setVisible(false);
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }
}
