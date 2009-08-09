package praktisch_sinnvoll;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class Lotto extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JTextField in1 = new JTextField(5);
	JTextField in2 = new JTextField(5);
	JTextField in3 = new JTextField(5);
	JTextField in4 = new JTextField(5);
	JTextField in5 = new JTextField(5);
	JTextField in6 = new JTextField(5);
	JTextField in7 = new JTextField(5);
	
	JLabel out1 = new JLabel("3er: 0");
	JLabel out2 = new JLabel("4er: 0");
	JLabel out3 = new JLabel("5er: 0");
	JLabel out4 = new JLabel("6er: 0");
	JLabel out5 = new JLabel("5er mit ZZ: 0");
	
	JTextField anz = new JTextField(5);
	
	JButton start = new JButton("start");
	
	int dreier=0, vierer=0, fuenfer=0, sechser=0, fuenferzs=0, zusz=0, uzusz=0;
	
	JLabel leer = new JLabel("");
	
	TreeSet<Integer> userz = new TreeSet<Integer>();
	TreeSet<Integer> lottoz = new TreeSet<Integer>();
	
	public Lotto(){
		setTitle("Lottoooo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(590, 150);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(3, 1));
		
		JPanel eing = new JPanel();
		eing.setLayout(new FlowLayout());
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		JPanel ausg = new JPanel();
		ausg.setLayout(new FlowLayout());//BorderLayout(1, 6)
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		anz.setText("2000");
		/*
		in1.setText("15");
		in2.setText("27");
		in3.setText("34");
		in4.setText("40");
		in5.setText("23");
		in6.setText("17");
		in7.setText("35");
		*/
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		start.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		eing.add(in1);
		eing.add(in2);
		eing.add(in3);
		eing.add(in4);
		eing.add(in5);
		eing.add(in6);
		eing.add(in7);
		
		buttons.add(start);
		
		ausg.add(anz);
		ausg.add(leer);
		ausg.add(out1);
		ausg.add(out2);
		ausg.add(out3);
		ausg.add(out4);
		ausg.add(out5);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(eing);
		cp.add(buttons);
		cp.add(ausg);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Lotto();
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println(getSize());
		if(e.getSource()==start){
			out1.setText("3er: 0");
			out2.setText("4er: 0");
			out3.setText("5er: 0");
			out4.setText("6er: 0");
			dreier=0;
			vierer=0;
			fuenfer=0;
			sechser=0;
			
			int lanz = Integer.parseInt(anz.getText());
			
			//System.out.println("lanz: " +lanz);
			String err = pruefen();
			if(err.equals("")){
				while(lanz!=0){
					einlesen();
					ziehung();
					auswerten();
					lanz--;
					//System.out.println("hallo schleife: " +lanz);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, err, "Fehler!!", JOptionPane.ERROR_MESSAGE);
				//System.out.println("errrrr");
			}
		}
	}
	
	public void ziehung(){
		Random zuf = new Random();
		lottoz.clear();
		while(lottoz.size()!=6){
			lottoz.add(zuf.nextInt(45)+1);
		}
		zusz = zuf.nextInt(45)+1;
	}
	
	public void auswerten(){
		Iterator<Integer> it = lottoz.iterator();
			
		while(it.hasNext()){
			userz.add(it.next());
		}
		int laenge = userz.size();
		
		//System.out.println("laenge? " +laenge);
		
		switch(laenge){
			case 9:
				//dreier
				dreier++;
				out1.setText("3er: " +dreier);
				break;
			case 8:
				//vierer
				vierer++;
				out2.setText("4er: " +vierer);
				break;
			case 7:
				//fuenfer
				if(uzusz==zusz){
					fuenferzs++;
					out5.setText("5er mit ZZ: " +fuenferzs);
				}
				else{
					fuenfer++;
					out3.setText("5er: " +fuenfer);
				}
				break;
			case 6:
				//sechser
				sechser++;
				out4.setText("6er: " +sechser);
				break;
			
			default: //System.out.println("default?"); 
					break;
		}
		
	}
	
	public void einlesen(){
		userz.clear();
		userz.add(Integer.parseInt(in1.getText()));
		userz.add(Integer.parseInt(in2.getText()));
		userz.add(Integer.parseInt(in3.getText()));
		userz.add(Integer.parseInt(in4.getText()));
		userz.add(Integer.parseInt(in5.getText()));
		userz.add(Integer.parseInt(in6.getText()));
		uzusz=Integer.parseInt(in7.getText());
	}
	
	public String pruefen(){
		String out ="";
		
		
		if(in1.getText().equals("") || in2.getText().equals("") || in3.getText().equals("") || in4.getText().equals("") || in5.getText().equals("") || in6.getText().equals("") || in7.getText().equals("") || anz.getText().equals("")){
			out="Eine oder mehrere Angaben wurden nicht gemacht";
		}
		else{
			int z1, z2, z3, z4, z5, z6, z7, a1;
			z1 = Integer.parseInt(in1.getText());
			z2 = Integer.parseInt(in2.getText());
			z3 = Integer.parseInt(in3.getText());
			z4 = Integer.parseInt(in4.getText());
			z5 = Integer.parseInt(in5.getText());
			z6 = Integer.parseInt(in6.getText());
			z7 = Integer.parseInt(in7.getText());
			
			a1 = Integer.parseInt(anz.getText());
		
			if(z1<1 || z1>45){
				out="Die erste Zahl ist nicht gültig";
			}
			if(z2<1 || z2>45){
				out="Die zweite Zahl ist nicht gültig";
			}
			if(z3<1 || z3>45){
				out="Die dritte Zahl ist nicht gültig";
			}
			if(z4<1 || z4>45){
				out="Die vierte Zahl ist nicht gültig";
			}
			if(z5<1 || z5>45){
				out="Die fünfte Zahl ist nicht gültig";
			}
			if(z6<1 || z6>45){
				out="Die sechste Zahl ist nicht gültig";
			}
			if(z7<1 || z7>45){
				out="Die siebente Zahl ist nicht gültig";
			}
			if(a1<0){
				out="Die Anzahl ist zu niedrig";
			}
		}
		//System.out.println("out: " +out);
		return out;
	}

}
