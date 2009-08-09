package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Woelfel_22_11_07 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected JTextField obentext;
	protected JTextField untentext;
	
	protected JButton[][] linksbutton = new JButton[3][3];
	protected JButton[] rechtsbutton = new JButton[3];
	
	public Woelfel_22_11_07(){
		setTitle("Woelfel Philip Test 22.11.07");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 330);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 3));
		
		JPanel lp = new JPanel();
		lp.setLayout(new GridLayout(3, 3));
		
		JPanel mp = new JPanel();
		mp.setLayout(new GridLayout(3, 1));
		JPanel mp2 = new JPanel();
		mp2.setLayout(new FlowLayout());
		
		JPanel rp = new JPanel();
		rp.setLayout(new GridLayout(3, 1));
		
		JPanel up = new JPanel();
		up.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		JButton clear = new JButton("clear");
		
		obentext = new JTextField(20);
		untentext = new JTextField(20);
		//obentext.disable();
		//untentext.disable();
		
		int zahl=1;
		for(int i=0; i<linksbutton.length;i++){
			for(int j=0; j<linksbutton[i].length;j++){
				linksbutton[i][j] = new JButton("" +zahl);
				linksbutton[i][j].setActionCommand("Links");
				zahl++;
			}
		}
		rechtsbutton[0] = new JButton("A");
		rechtsbutton[1] = new JButton("B");
		rechtsbutton[2] = new JButton("C");
		rechtsbutton[0].setActionCommand("Rechts");
		rechtsbutton[1].setActionCommand("Rechts");
		rechtsbutton[2].setActionCommand("Rechts");
		JLabel leer = new JLabel("");
		JLabel leer2 = new JLabel("");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				obentext.setText("");
				untentext.setText("");
				//System.out.println("w: " +getWidth());
				//System.out.println("h: " +getHeight());
			}
		});
		
		for(int i=0; i<linksbutton.length;i++){
			for(int j=0; j<linksbutton[i].length;j++){
				linksbutton[i][j].addActionListener(new MeineAction());
			}
		}
		
		rechtsbutton[0].addActionListener(new MeineAction());
		rechtsbutton[1].addActionListener(new MeineAction());
		rechtsbutton[2].addActionListener(new MeineAction());
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		for(int i=0; i<linksbutton.length;i++){
			for(int j=0; j<linksbutton[i].length;j++){
				lp.add(linksbutton[i][j]);
			}
		}
		
		rp.add(rechtsbutton[0]);
		rp.add(rechtsbutton[1]);
		rp.add(rechtsbutton[2]);
		
		mp.add(leer);
		mp.add(mp2, BorderLayout.CENTER);
		mp.add(leer2);
		mp2.add(obentext);
		mp2.add(untentext);
		
		up.add(clear);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		center.add(lp);
		center.add(mp);
		center.add(rp);
		cp.add(center, BorderLayout.CENTER);
		cp.add(up, BorderLayout.SOUTH);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Woelfel_22_11_07();
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	class MeineAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Links")){
				obentext.setText("Links");
				for(int i=0; i<linksbutton.length;i++){
					for(int j=0; j<linksbutton[i].length;j++){
						if(e.getSource()==linksbutton[i][j]){
							untentext.setText(linksbutton[i][j].getText());
						}
					}
				}
			}
			if(e.getActionCommand().equals("Rechts")){
				obentext.setText("Rechts");
				for(int i=0; i<rechtsbutton.length;i++){
					if(e.getSource()==rechtsbutton[i]){
						untentext.setText(rechtsbutton[i].getText());
					}
				}
			}
		}
		
		
	}
	
}
