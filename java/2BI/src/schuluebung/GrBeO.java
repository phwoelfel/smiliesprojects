package schuluebung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GrBeO extends JFrame implements ActionListener{
	JLabel lab1 = new JLabel("Einfach nur Text");
	JTextField tf1 = new JTextField(20);
	JButton but1 = new JButton("Klick mich");
	
	public void init(){
		setTitle("Mein erster JFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(lab1);
		cp.add(tf1);
		but1.addActionListener(this);
		cp.add(but1);
		setVisible(true);
	}
	
	public static void main (String [] args) throws IOException{
		new GrBeO().init();
		BufferedReader eing = new BufferedReader(new InputStreamReader(System.in));
	}

	public void actionPerformed(ActionEvent arg0) {
			System.out.println("klick");
		
	}
	
}