package praktisch_sinnvoll;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Primz extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JRadioButton primzRB = new JRadioButton("Primzahlen");
	JRadioButton primfaktzRB = new JRadioButton("Primfaktorenzerlegung");
	JTextField primzTF = new JTextField(15);
	JTextArea ausgabe = new JTextArea("", 5, 15);
	JButton berechnen = new JButton("Berechnen");
	JButton loeschen = new JButton("Loeschen");
	
	public Primz(){
		setTitle("Primzahlen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 210);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(2, 2));
		
		JPanel lt = new JPanel(); //left top
		lt.setLayout(new FlowLayout());
		
		JPanel rt = new JPanel(); // right top
		rt.setLayout(new GridLayout(2, 1));
		
		JPanel lb = new JPanel(); //left bottom
		lb.setLayout(new FlowLayout());
		
		JPanel rb = new JPanel(); //right bottom
		rb.setLayout(new GridLayout(2, 1));
		
		JPanel rbt = new JPanel(); //right bottom top
		rbt.setLayout(new FlowLayout());
		
		JPanel rbb = new JPanel();
		rbb.setLayout(new FlowLayout()); //right bottom bottom
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		JScrollPane scp = new JScrollPane(ausgabe);
		
		ButtonGroup rbg = new ButtonGroup();
		rbg.add(primzRB);
		rbg.add(primfaktzRB);
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		loeschen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ausgabe.setText("");
			}
		});
		
		berechnen.addActionListener(this);		
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		lt.add(primzTF);
		
		rt.add(primzRB);
		rt.add(primfaktzRB);
		
		lb.add(scp);
		
		rbt.add(berechnen);
		rbb.add(loeschen);
		
		rb.add(rbt);
		rb.add(rbb);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(lt);
		cp.add(rt);
		cp.add(lb);
		cp.add(rb);
		//JPanel zum Container hinzufuegen Ende
		
		TreeSet<String> a = new TreeSet<String>();
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Primz();
		/*
		ArrayList<Integer> arr = PrZa(12);
		for(int i=0;i<arr.size();i++){
			System.out.println(arr.get(i));
		}
		*/
	}

	public void actionPerformed(ActionEvent e) {
		int z = Integer.parseInt(primzTF.getText());
		ausgabe.setText("");
		if(primzRB.isSelected()){
			ArrayList<Integer> arr = PrZa(z);
			for(int i=0;i<arr.size();i++){
				ausgabe.append(arr.get(i) +"\n");
			}
		}
		if(primfaktzRB.isSelected()){
			ArrayList<Integer> arr = PrFaZer(z);
			for(int i=0;i<arr.size();i++){
				ausgabe.append(arr.get(i) +"\n");
			}
		}
		
	}
	
	public ArrayList<Integer> PrZa (int z){
		ArrayList <Integer> out = new ArrayList<Integer>();
		out.add(2);
		boolean weiter=false;
		for(int i = 3; i<=z; i+=2){
			for(int j=0;j<out.size();j++){
				if(i%out.get(j)==0){
					weiter=false;
					break;
				}
				else{
					weiter=true;
				}
			}
			if(weiter){
				out.add(i);
			}
		}
		
		return out;
	}
	
	public ArrayList<Integer> PrFaZer (int z){
		ArrayList <Integer> primfakt = new ArrayList<Integer>();
		ArrayList <Integer> priz = PrZa(z);
		
		do{
			for(int i=0; i<priz.size();i++){
				if(z%priz.get(i)==0){
					primfakt.add(priz.get(i));
					z/=priz.get(i);
					break;
				}
			}
			
		}while(z>1);
		
		return primfakt;
	}
}
