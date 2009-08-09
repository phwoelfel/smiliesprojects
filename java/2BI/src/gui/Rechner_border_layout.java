package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Rechner_border_layout extends JFrame implements ActionListener{
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
		setSize(800, 600);
		
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		Container norden = new JFrame().getContentPane();
		norden.setLayout(new FlowLayout());
		
		Container sueden = new JFrame().getContentPane();
		sueden.setLayout(new FlowLayout());
		
		Container mitte = new JFrame().getContentPane();
		mitte.setLayout(new GridLayout(2,2));
		//Container Ende
		
		//Initialisierung der Inhalte Anfang
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
		//Initialisierung der Inhalte Ende
		
		
		norden.add(wert1_t);
		norden.add(wert1);
		norden.add(wert2_t);
		norden.add(wert2);
		norden.add(ergt);
		norden.add(ergebnis);
		
		rechnen.addActionListener(this);
		sueden.add(rechnen);
		clear.addActionListener(this);
		sueden.add(clear);
		sueden.add(kurz);
		
		ButtonGroup rb_group = new ButtonGroup();
		rb_group.add(add);
		rb_group.add(sub);
		rb_group.add(mult);
		rb_group.add(div);
		
		mitte.add(add);
		mitte.add(sub);
		mitte.add(mult);
		mitte.add(div);
		
		
		//Container zum JFrame hinzufuegen
		cp.add(norden, BorderLayout.NORTH);
		cp.add(sueden, BorderLayout.SOUTH);
		cp.add(mitte, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Rechner_border_layout().init();

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
