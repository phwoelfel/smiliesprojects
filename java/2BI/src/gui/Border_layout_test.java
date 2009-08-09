package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Border_layout_test extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JButton buta;
	protected JButton butb;
	protected JButton buti;
	protected JButton butii;
	protected JButton butiii;
	protected JButton butiv;
	protected JButton but3;
	protected JButton but4;
	protected JButton but5;
	
	public void init(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		buta = new JButton("klick mich im north a");
		butb = new JButton("klick mich im north b");
		buti = new JButton("klick mich im center I");
		butii = new JButton("klick mich im center II");
		butiii = new JButton("klick mich im center III");
		butiv = new JButton("klick mich im center IV");
		but3 = new JButton("klick mich im west");
		but4 = new JButton("klick mich im east");
		but5 = new JButton("klick mich im south");
		
		Container norden = new JFrame().getContentPane();
		norden.setLayout(new FlowLayout());
		
		norden.add(buta);
		norden.add(butb);
		cp.add(norden, BorderLayout.NORTH);
		
		Container mitte = new JFrame().getContentPane();
		mitte.setLayout(new GridLayout(2,2));
		
		mitte.add(buti);
		mitte.add(butii);
		mitte.add(butiii);
		mitte.add(butiv);
		cp.add(mitte, BorderLayout.CENTER);
		
		cp.add(but3, BorderLayout.WEST);
		cp.add(but4, BorderLayout.EAST);
		cp.add(but5, BorderLayout.SOUTH);
		
		//cp.add(but1);
		//but1.addActionListener(this);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Border_layout_test().init();

	}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
