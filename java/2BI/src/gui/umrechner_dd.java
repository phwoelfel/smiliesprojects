package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class umrechner_dd extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextField eingabe;
	protected JLabel eingt;
	protected JTextField ergebnis;
	protected JLabel ergt;
	protected JButton clear;
	protected JButton rechnen;
	
	protected Choice auswahl;
	
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
		auswahl = new Choice();
		
		cp.add(eingt);
		cp.add(eingabe);
		cp.add(ergt);
		cp.add(ergebnis);
		
		rechnen.addActionListener(this);
		cp.add(rechnen);
		clear.addActionListener(this);
		cp.add(clear);
		
		auswahl.add("ATS-->EUR");
		auswahl.add("EUR-->ATS");
		auswahl.add("USD-->EUR");
		auswahl.add("EUR-->USD");
		auswahl.add("DEZ-->BIN");
		auswahl.add("BIN-->DEZ");

		cp.add(auswahl);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new umrechner_dd().init();

	}

	public void actionPerformed(ActionEvent e) {
		double eur_ats_faktor = 13.7603;
		double eur_usd_faktor = 1.4137;
		if(e.getSource()==rechnen){
			if(auswahl.getSelectedItem().equals("EUR-->ATS")){//EUR-->ATS
				String eing = eingabe.getText();
				double eur = Double.parseDouble(eing);
				double ats = eur*eur_ats_faktor;
				ergebnis.setText("" +ats);
			}
			if(auswahl.getSelectedItem().equals("ATS-->EUR")){//ATS-->EUR
				String eing = eingabe.getText();
				double ats = Double.parseDouble(eing);
				double eur = ats/eur_ats_faktor;
				ergebnis.setText("" +eur);
			}
			if(auswahl.getSelectedItem().equals("USD-->EUR")){//USD-->EUR
				String eing = eingabe.getText();
				double usd = Double.parseDouble(eing);
				double eur = usd/eur_usd_faktor;
				ergebnis.setText("" +eur);
			}
			if(auswahl.getSelectedItem().equals("EUR-->USD")){//EUR-->USD
				String eing = eingabe.getText();
				double eur = Double.parseDouble(eing);
				double usd = eur*eur_usd_faktor;
				ergebnis.setText("" +usd);
			}
			if(auswahl.getSelectedItem().equals("DEZ-->BIN")){//DEZ-->BIN
				String eing = eingabe.getText();
				int dez = Integer.parseInt(eing);
				String out = "";
				do{
					out =(dez%2) +out;
					dez/= 2;
				}while(dez!=0);
				ergebnis.setText(out);
			}
			if(auswahl.getSelectedItem().equals("BIN-->DEZ")){//BIN-->DEZ
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
