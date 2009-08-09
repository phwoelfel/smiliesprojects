package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class umrechner extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextField eingabe;
	protected JLabel eingt;
	protected JTextField ergebnis;
	protected JLabel ergt;
	protected JButton clear;
	
	protected JButton eur_ats;
	protected JButton ats_eur;
	protected JButton usd_eur;
	protected JButton eur_usd;
	protected JButton dez_bin;
	protected JButton bin_dez;
	
	public void init(){
		setTitle("Umrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(280, 300);
		
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		eur_ats = new JButton("EUR-->ATS");
		ats_eur = new JButton("ATS-->EUR");
		usd_eur = new JButton("USD-->EUR");
		eur_usd = new JButton("EUR-->USD");
		dez_bin = new JButton("DEZ-->BIN");
		bin_dez = new JButton("BIN-->DEZ");
		eingabe = new JTextField(20);
		eingt = new JLabel("Eingabe: ");
		ergebnis = new JTextField(20);
		ergt = new JLabel("Ergebnis:");
		clear = new JButton("Clear");
		
		cp.add(eingt);
		cp.add(eingabe);
		cp.add(ergt);
		cp.add(ergebnis);
		
		eur_ats.addActionListener(this);
		cp.add(eur_ats);
		ats_eur.addActionListener(this);
		cp.add(ats_eur);
		eur_usd.addActionListener(this);
		cp.add(eur_usd);
		usd_eur.addActionListener(this);
		cp.add(usd_eur);
		dez_bin.addActionListener(this);
		cp.add(dez_bin);
		bin_dez.addActionListener(this);
		cp.add(bin_dez);
		
		cp.add(clear);
		clear.addActionListener(this);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new umrechner().init();

	}

	public void actionPerformed(ActionEvent e) {
		double eur_ats_faktor = 13.7603;
		double eur_usd_faktor = 1.4137;
		if(e.getSource()==eur_ats){
			String eing = eingabe.getText();
			double eur = Double.parseDouble(eing);
			double ats = eur*eur_ats_faktor;
			ergebnis.setText("" +ats);
		}
		if(e.getSource()==ats_eur){
			String eing = eingabe.getText();
			double ats = Double.parseDouble(eing);
			double eur = ats/eur_ats_faktor;
			ergebnis.setText("" +eur);
		}
		if(e.getSource()==usd_eur){
			String eing = eingabe.getText();
			double usd = Double.parseDouble(eing);
			double eur = usd/eur_usd_faktor;
			ergebnis.setText("" +eur);
		}
		if(e.getSource()==eur_usd){
			String eing = eingabe.getText();
			double eur = Double.parseDouble(eing);
			double usd = eur*eur_usd_faktor;
			ergebnis.setText("" +usd);
		}
		if(e.getSource()==clear){
			eingabe.setText("");
			ergebnis.setText("");
		}
		if(e.getSource()==dez_bin){
			String eing = eingabe.getText();
			int dez = Integer.parseInt(eing);
			String out = "";
			do{
				out =(dez%2) +out;
				dez/= 2;
			}while(dez!=0);
			ergebnis.setText(out);
		}
		if(e.getSource()==bin_dez){
			String eing = eingabe.getText();
			char zeich;
			int out=0;
			int hochz=0;
			for(int i=eing.length()-1;i>=0;i--){
				zeich=eing.charAt(i);
				int z = Integer.parseInt("" +zeich);
				//System.out.println("z: " +z);
				out += z*Math.pow(2, hochz);
				//System.out.println("out: " +out);
				hochz++;
			}
			ergebnis.setText("" +out);
		}
		
	}//Ende actionPerformed

}
