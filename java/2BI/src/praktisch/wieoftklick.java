package praktisch;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class wieoftklick extends JFrame implements ActionListener{
	JLabel text = new JLabel("Anzahl Klicks: 0");
	JButton klicki = new JButton("magst du mich?");
	int zahl=0;
	
	public void init(){
		setTitle("Zaehlbutton");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(text);
		klicki.addActionListener(this);
		cp.add(klicki);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new wieoftklick().init();

	}

	public void actionPerformed(ActionEvent arg0) {
		zahl++;
		text.setText("Anzahl Klicks: " +zahl);
		
	}

}
