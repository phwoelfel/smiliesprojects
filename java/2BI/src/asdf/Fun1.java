package asdf;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Fun1 extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private JButton but1 = new JButton("ja");
	private JButton but2 = new JButton("nein");
	
	
	/*
	 * Philip Wšlfel, 2BI
	 * 31.01.08
	 */
	public Fun1(){
		setTitle("Fun1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFrameLocation(200, 100);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel unten = new JPanel();
		unten.setLayout(new FlowLayout());
		
		JPanel oben = new JPanel();
		oben.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		JLabel text = new JLabel("Dieser Test ist schwer?");
		but1.setActionCommand("ja");
		but2.setActionCommand("nein");
		//Initialisierung der Inhalte Ende
		
		
		//Mouse Listener zu den Inhalten hinzufuegen Anfang
		but1.addMouseListener(this);
		but2.addMouseListener(this);
		//Mouse Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		oben.add(text);
		unten.add(but1);
		unten.add(but2);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(oben, BorderLayout.NORTH);
		cp.add(unten, BorderLayout.CENTER);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Fun1();
	}


	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==but1 && but1.getActionCommand()=="ja"){
			but2.setActionCommand("ja");
			but1.setActionCommand("nein");
			
			but2.setText("ja");
			but1.setText("nein");
		}
		if(e.getSource()==but2 && but2.getActionCommand()=="ja"){
			but1.setActionCommand("ja");
			but2.setActionCommand("nein");
			
			but1.setText("ja");
			but2.setText("nein");
		}
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }
}
