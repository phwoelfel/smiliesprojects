package praktisch_sinnvoll;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class MusicPlayer extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JTextField eingabe = new JTextField(20);
	private JTextArea ausgabe = new JTextArea(20, 50);
	private JScrollPane scrolli = new JScrollPane(ausgabe);
	private JButton submit = new JButton("ausgeben");
	
	private TreeSet<Cd> CDts = new TreeSet<Cd>();
	private String[] interpr= {"AC/DC", "AC/DC", "Dire Straits", "Bruce Springsteen", "Red Hot Chilli Peppers", "Eric Clapton", "Rammstein"};
	private String[] tit = {"Highway to Hell", "Back in Black", "Private Investigation", "Greatest Hits", "Blood Sugar Sex Magic", "The Cream of Clapton", "Herzeleid" };
	private int[] ja= {1979, 1980, 1982, 1984, 1991, 1994, 1995};
	
	public MusicPlayer(){
		setTitle("MusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel oben = new JPanel();
		oben.setLayout(new FlowLayout());
		
		JPanel mitte = new JPanel();
		mitte.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		submit.addActionListener(this);
		eingabe.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		oben.add(eingabe);
		oben.add(submit);
		
		mitte.add(ausgabe);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(oben, BorderLayout.NORTH);
		cp.add(mitte, BorderLayout.CENTER);
		//JPanel zum Container hinzufuegen Ende
		
		for(int i=0;i<interpr.length;i++){
			CDts.add(new Cd(interpr[i], tit[i], ja[i]));
		}
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MusicPlayer();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit || e.getSource()==eingabe){
			ausgabe.setText("");
			String eing = eingabe.getText();
			
			Scanner scan = new Scanner(eing);
			
			if(scan.hasNextInt()){
				//Nach Jahr suchen
				int ein = Integer.parseInt(eing);
				Iterator<Cd> it = CDts.iterator();
				while(it.hasNext()){
					Cd tmp = it.next();
					if(tmp.jahr==ein){
						//System.out.println(tmp);
						String t = tmp.toString();
						ausgabe.append(t +"\n");
					}
				}
			}
			else{
				//nach Interpret suchen
				Iterator<Cd> it = CDts.iterator();
				while(it.hasNext()){
					Cd tmp = it.next();
					if(tmp.interpret.equals(eing)){
						//System.out.println(tmp);
						String t = tmp.toString();
						ausgabe.append(t +"\n");
					}
				}
			}
		}
		
	}

}
