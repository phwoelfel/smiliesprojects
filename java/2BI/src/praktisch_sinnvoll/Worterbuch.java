package praktisch_sinnvoll;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Worterbuch extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JButton suchen = new JButton("Suchen");
	private JButton speichern = new JButton("Speichern");
	private JButton update = new JButton("Update");
	private JTextField eingabe = new JTextField(15);
	private JTextArea ausgabe = new JTextArea(10, 20);
	private JScrollPane scrolli = new JScrollPane(ausgabe);
	private HashMap<String, String> inhalt = new HashMap<String, String>();
	
	public Worterbuch(){
		setTitle("Worterbuch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(2, 2));
		
		JPanel lo = new JPanel();
		lo.setLayout(new FlowLayout());
		
		JPanel ro = new JPanel();
		ro.setLayout(new FlowLayout());
		
		JPanel lu = new JPanel();
		lu.setLayout(new FlowLayout());

		JPanel ru = new JPanel();
		ru.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("worte.txt"));
			String line;
			String[] worter;
			while((line=in.readLine())!=null){
				worter = line.split(";");
				//System.out.println(line);
				//System.out.println(worter[0] +" ; " +worter[1]);
				inhalt.put(worter[0], worter[1]);
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		suchen.addActionListener(this);
		//speichern.addActionListener(this);
		update.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		lo.add(eingabe);
		ro.add(suchen);
		lu.add(scrolli);
		//ru.add(speichern);
		ru.add(update);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(lo);
		cp.add(ro);
		cp.add(lu);
		cp.add(ru);		
		//JPanel zum Container hinzufuegen Ende
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				speichern();
				//System.out.println("gespeichert");
				System.exit(0);
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Worterbuch();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==update){
			inhalt.put(eingabe.getText(), ausgabe.getText());
		}
		if(e.getSource()==suchen){
			//System.out.println("suchen");
			if(inhalt.containsKey(eingabe.getText())){
				ausgabe.setText(inhalt.get(eingabe.getText()));
			}
		}
	}
	
	public void speichern(){
		//System.out.println("Speichern beginnt");
		TreeSet<String> keys = new TreeSet<String>(inhalt.keySet());
		String[]keyarr = keys.toArray(new String[1]);
		try {
			PrintWriter pw = new PrintWriter("worte.txt");
			for(int i=0;i<keyarr.length;i++){
				pw.println(keyarr[i] +";" +inhalt.get(keyarr[i]));
			}
			pw.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}

}
