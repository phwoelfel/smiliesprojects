package asdf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fun2 extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private JButton but_ja = new JButton("ja");
	private JButton but_nein = new JButton("nein");
	private int clicks=0;
	
	/*
	 * Philip Wšlfel, 2BI
	 * 31.01.08
	 */
	public Fun2(){
		setTitle("Fun2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFrameLocation(280, 100);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(null);
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		JLabel text = new JLabel("Ich moechte blabla waehlen?");
		
		text.setBounds(60, 5, 250, 20);
		but_ja.setBounds(10, 30, 80, 25);
		but_nein.setBounds(90, 30, 80, 25);
		//Initialisierung der Inhalte Ende
		
		
		//Mouse Listener zu den Inhalten hinzufuegen Anfang
		but_ja.addMouseListener(this);
		//Mouse Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		cp.add(text);
		cp.add(but_ja);
		cp.add(but_nein);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Fun2();
	}


	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if(e.getSource()==but_ja){
			if(clicks%2==0){
				but_ja.setBounds(170, 30, 80, 25);
			}
			if(clicks%2==1){
				but_ja.setBounds(10, 30, 80, 25);
			}
			clicks++;
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
