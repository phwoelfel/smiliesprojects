package praktisch;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class zweibuttonwieoftklick extends JFrame implements ActionListener{
	JButton klicki = new JButton("0");
	JButton klicka = new JButton("0");
	int zahl1=0;
	int zahl2=0;
	
	public void init(){
		setTitle("Zwei Buttons zum zaehlen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		klicka.addActionListener(this);
		cp.add(klicka);
		klicki.addActionListener(this);
		cp.add(klicki);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new zweibuttonwieoftklick().init();

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==klicki){
			zahl1++;
			klicki.setText("" +zahl1);
		}
		else{
			zahl2++;
			klicka.setText("" +zahl2);
		}
	}
		
}

