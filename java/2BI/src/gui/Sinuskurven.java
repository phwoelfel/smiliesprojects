package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sinuskurven extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected JTextField bereich_anf;
	protected JTextField bereich_ende;
	protected JTextField sinus;
	protected JTextField mult;
	protected JTextField verschiebung;
	
	protected JLabel bereich_anf_text;
	protected JLabel bereich_mitte_text;
	protected JLabel bereich_ende_text;
	protected JLabel sinus_text;
	protected JLabel klammerzu;
	
	protected JRadioButton plus;
	protected JRadioButton minus;
	
	protected JButton rechnen;
	
	protected Graphics g;
	protected JPanel malen;
	
	public void init(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel eing = new JPanel();
		eing.setLayout(new FlowLayout());
		
		malen = new JPanel();
		malen.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		g = malen.getGraphics();
		
		bereich_anf = new JTextField(5);
		bereich_ende = new JTextField(5);
		sinus = new JTextField(5);
		mult = new JTextField(5);
		verschiebung = new JTextField(5);
		
		bereich_anf_text = new JLabel("[");
		bereich_mitte_text = new JLabel(" , ");
		bereich_ende_text = new JLabel("]");
		sinus_text = new JLabel(" * sin(");
		klammerzu = new JLabel(")");

		
		rechnen = new JButton("Berechnen");
		
		plus = new JRadioButton("+");
		minus = new JRadioButton("-");
		
		ButtonGroup plmi = new ButtonGroup();
		plmi.add(plus);
		plmi.add(minus);
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		rechnen.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den Container hinzufuegen Anfang
		eing.add(bereich_anf_text);
		eing.add(bereich_anf);
		eing.add(bereich_mitte_text);
		eing.add(bereich_ende);
		eing.add(bereich_ende_text);
		eing.add(mult);
		eing.add(sinus_text);
		eing.add(sinus);
		/*
		eing.add(klammerzu);
		eing.add(plus);
		eing.add(minus);
		eing.add(verschiebung);
		*/
		eing.add(rechnen);
		//Inhalte zu den Container hinzufuegen Ende
		
		
		//Container zum JFrame hinzufuegen Anfang
		cp.add(eing, BorderLayout.NORTH);
		//Container zum JFrame hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Sinuskurven().init();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rechnen){
			double sin = Double.parseDouble(sinus.getText());
			int bereich_end = Integer.parseInt(bereich_ende.getText());
			int xa=0;
			int ya=(int)Math.sin(sin);
			int xb, yb;
			for(xb=10;xb<bereich_end;xb+=10){
				yb = (int)Math.sin(sin+xb);
				g.drawLine(xa,ya,xb,yb);
				ya=yb;
				xa+=10;
			}
			
		}
		
	}

}
