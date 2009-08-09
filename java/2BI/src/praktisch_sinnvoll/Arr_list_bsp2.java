package praktisch_sinnvoll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Arr_list_bsp2 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JButton einlesen = new JButton("Einlesen");
	JButton lenSort = new JButton("LenSort");
	JButton alphSort = new JButton("AlphSort");
	JButton ausg = new JButton("ausgeben");
	
	JTextArea eingabe = new JTextArea(10, 10);
	JTextArea ausgabe = new JTextArea(10, 10);
	
	ArrayList<String> words ;//= new ArrayList<String>();
	
	public Arr_list_bsp2(){
		setTitle("ArrayList Uebung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		JScrollPane scpe = new JScrollPane(eingabe);
		JScrollPane scpa = new JScrollPane(ausgabe);
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		einlesen.addActionListener(this);
		lenSort.addActionListener(this);
		alphSort.addActionListener(this);
		ausg.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		center.add(scpe);
		center.add(scpa);
		
		south.add(einlesen);
		south.add(lenSort);
		south.add(alphSort);
		south.add(ausg);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(center, BorderLayout.CENTER);
		cp.add(south, BorderLayout.SOUTH);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Arr_list_bsp2();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==einlesen){
			words = einlesen();
			delDouble(words);
		}
		if(e.getSource()==ausg){
			printAL(words);
		}
		if(e.getSource()==lenSort){
			lenSort(words);
			printAL(words);
		}
		if(e.getSource()==alphSort){
			alphSort(words);
			printAL(words);
		}
	}
	
	public ArrayList<String> einlesen (){
		ArrayList<String> out = new ArrayList<String>();
		String in=eingabe.getText();
		String [] saetze=in.split("\n");
		String [][] woerter = new String[saetze.length][];		
		
		for(int i=0;i<saetze.length;i++){
			woerter[i] = new String[saetze[i].length()];
			woerter[i] = saetze[i].split(" ");
		}
		
		for(int i=0;i<woerter.length;i++){
			for(int j=0;j<woerter[i].length;j++){
				out.add(woerter[i][j]);
			}
		}
		
		return out;
	}
	
	public void delDouble(ArrayList<String> in){
		//ArrayList<String> out = new ArrayList<String>();
		
		for(int i=0;i<in.size();i++){
			for(int j=0;j<in.size();j++){
				if(in.get(i).equals(in.get(j)) && i!=j){
					in.remove(j);
				}
			}
		}
		
		//return out;
	}
	
	public void lenSort(ArrayList<String> in){
		String tmp ="";
		for(int i=0;i<in.size();i++){
			for(int j=0;j<in.size();j++){
				if(in.get(j).length()>in.get(i).length() && i!=j){
					tmp=in.get(i);
					in.set(i, in.get(j));
					in.set(j, tmp);
				}
			}
		}
	}
	
	public void alphSort(ArrayList<String> in){
		TreeSet<String> ts = new TreeSet<String>();
		
		for(int i=0;i<in.size();i++){
			ts.add(in.get(i));
		}
		
		String [] arr =  ts.toArray(new String[1]);
		in.clear();
		
		for(int i=0;i<arr.length;i++){
			in.add(arr[i]);
		}
	}
	
	public void printAL(ArrayList<String> in){
		ausgabe.setText("");
		for(int i=0;i<in.size();i++){
			//System.out.println(in.get(i));
			ausgabe.append(in.get(i) +"\n");
		}
	}
}
