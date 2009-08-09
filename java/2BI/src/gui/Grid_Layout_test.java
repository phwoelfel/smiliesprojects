package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Grid_Layout_test extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JButton but1;
	protected JButton but2;
	protected JButton but3;
	protected JButton but4;
	protected JButton but5;
	protected JButton but6;
	protected JButton but7;
	protected JLabel lab;
	
	public void init(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(3,2));
		
		but1 = new JButton("klick mich im 1");
		but2 = new JButton("klick mich im 2");
		but3 = new JButton("klick mich im 3");
		but4 = new JButton("klick mich im 4");
		but5 = new JButton("klick mich im 5");
		but6 = new JButton("klick mich im 6");
		but7 = new JButton("klick mich im 7");
		lab = new JLabel("");
		
		
		cp.add(but1);
		cp.add(but2);
		cp.add(but3);
		cp.add(but4);
		cp.add(but5);
		cp.add(but6);
		cp.add(lab);
		cp.add(but7);
		
		//cp.add(but1);
		//but1.addActionListener(this);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Grid_Layout_test().init();

	}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
