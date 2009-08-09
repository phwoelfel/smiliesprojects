package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class umrechner_rb extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextField eingabe;
	protected JLabel eingt;
	protected JTextField ergebnis;
	protected JLabel ergt;
	protected JButton clear;
	protected JButton rechnen;
	
	protected JRadioButton ats_eur;
	protected JRadioButton eur_ats;
	protected JRadioButton usd_eur;
	protected JRadioButton eur_usd;
	protected JRadioButton dez_bin;
	protected JRadioButton bin_dez;
	
	public void init(){
		setTitle("Umrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(280, 300);
		
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		eingabe = new JTextField(20);
		eingt = new JLabel("Eingabe: ");
		ergebnis = new JTextField(20);
		ergt = new JLabel("Ergebnis:");
		clear = new JButton("Clear");
		rechnen = new JButton("Berechnen");
		
		ats_eur = new JRadioButton("ATS-->EUR");
		eur_ats = new JRadioButton("EUR-->ATS");
		usd_eur = new JRadioButton("USD-->EUR");
		eur_usd = new JRadioButton("EUR-->USD");
		dez_bin = new JRadioButton("DEZ-->BIN");
		bin_dez = new JRadioButton("BIN-->DEZ");
		
		cp.add(eingt);
		cp.add(eingabe);
		cp.add(ergt);
		cp.add(ergebnis);
		
		rechnen.addActionListener(this);
		cp.add(rechnen);
		clear.addActionListener(this);
		cp.add(clear);
		
		ButtonGroup rb_group = new ButtonGroup();
		rb_group.add(ats_eur);
		rb_group.add(eur_ats);
		rb_group.add(usd_eur);
		rb_group.add(eur_usd);
		rb_group.add(dez_bin);
		rb_group.add(bin_dez);
		cp.add(ats_eur);
		cp.add(eur_ats);
		cp.add(usd_eur);
		cp.add(eur_usd);
		cp.add(dez_bin);
		cp.add(bin_dez);		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new umrechner_rb().init();

	}

	public void actionPerformed(ActionEvent e) {
		double eur_ats_faktor = 13.7603;
		double eur_usd_faktor = 1.4137;
		if(e.getSource()==rechnen){
			if(eur_ats.isSelected()){
				String eing = eingabe.getText();
				double eur = Double.parseDouble(eing);
				double ats = eur*eur_ats_faktor;
				ergebnis.setText("" +ats);
			}
			if(ats_eur.isSelected()){
				String eing = eingabe.getText();
				double ats = Double.parseDouble(eing);
				double eur = ats/eur_ats_faktor;
				ergebnis.setText("" +eur);
			}
			if(usd_eur.isSelected()){
				String eing = eingabe.getText();
				double usd = Double.parseDouble(eing);
				double eur = usd/eur_usd_faktor;
				ergebnis.setText("" +eur);
			}
			if(eur_usd.isSelected()){
				String eing = eingabe.getText();
				double eur = Double.parseDouble(eing);
				double usd = eur*eur_usd_faktor;
				ergebnis.setText("" +usd);
			}
			if(dez_bin.isSelected()){
				String eing = eingabe.getText();
				int dez = Integer.parseInt(eing);
				String out = "";
				do{
					out =(dez%2) +out;
					dez/= 2;
				}while(dez!=0);
				ergebnis.setText(out);
			}
			if(bin_dez.isSelected()){
				String eing = eingabe.getText();
				char zeich;
				int out=0;
				int hochz=0;
				for(int i=eing.length()-1;i>=0;i--){
					zeich=eing.charAt(i);
					int z = Integer.parseInt("" +zeich);
					out += z*Math.pow(2, hochz);
					hochz++;
				}
				ergebnis.setText("" +out);
			}
		}
		if(e.getSource()==clear){
			eingabe.setText("");
			ergebnis.setText("");
		}
		//System.out.println(auswahl.getSelectedItem());
	}//Ende actionPerformed

}
