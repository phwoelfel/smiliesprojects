package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Rechner extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextField wert1;
	protected JLabel wert1_t;
	protected JTextField wert2;
	protected JLabel wert2_t;
	protected JTextField ergebnis;
	protected JLabel ergt;
	protected JButton clear;
	protected JButton rechnen;
	
	protected JRadioButton add;
	protected JRadioButton sub;
	protected JRadioButton mult;
	protected JRadioButton div;
	
	protected JCheckBox kurz;
	
	public void init(){
		setTitle("Rechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(260, 300);
		
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		wert1 = new JTextField(5);
		wert1_t = new JLabel("Wert 1: ");
		wert2 = new JTextField(5);
		wert2_t = new JLabel("Wert 2: ");
		ergebnis = new JTextField(20);
		ergt = new JLabel("Ergebnis:");
		clear = new JButton("Clear");
		rechnen = new JButton("Berechnen");
		kurz = new JCheckBox("kuerzen?");
		
		add = new JRadioButton("Addieren");
		sub = new JRadioButton("Subtrahieren");
		mult = new JRadioButton("Multiplizieren");
		div = new JRadioButton("Dividieren");
		
		cp.add(wert1_t);
		cp.add(wert1);
		cp.add(wert2_t);
		cp.add(wert2);
		cp.add(ergt);
		cp.add(ergebnis);
		
		rechnen.addActionListener(this);
		cp.add(rechnen);
		clear.addActionListener(this);
		cp.add(clear);
		
		ButtonGroup rb_group = new ButtonGroup();
		rb_group.add(add);
		rb_group.add(sub);
		rb_group.add(mult);
		rb_group.add(div);
		
		cp.add(add);
		cp.add(sub);
		cp.add(mult);
		cp.add(div);
		
		cp.add(kurz);
		
		//but1.addActionListener(this);
		//cp.add(but1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Rechner().init();

	}

	public void actionPerformed(ActionEvent e) {
		double w1 = Double.parseDouble(wert1.getText());
		double w2 = Double.parseDouble(wert2.getText());
		double out=0;
		
		if(e.getSource()==rechnen){
			if(add.isSelected()){
				out=w1+w2;
			}
			if(sub.isSelected()){
				out=w1-w2;
			}
			if(mult.isSelected()){
				out=w1*w2;
			}
			if(div.isSelected()){
				out=w1/w2;
			}
			if(kurz.isSelected()){
				out = kuerzen(out);
			}
			ergebnis.setText("" +out);
		}
		if(e.getSource()==clear){
			ergebnis.setText("");
			wert1.setText("");
			wert2.setText("");
		}
		
	}
	
	public static double kuerzen(double zahl){
		double out=0;
		out=zahl*100;
		out = Math.round(out);
		out/=100;
		return out;
	}

}
