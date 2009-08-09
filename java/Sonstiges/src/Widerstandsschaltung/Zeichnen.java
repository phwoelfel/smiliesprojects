package Widerstandsschaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Werkzeuge:
 * 	1...Widerstand
 * 	2...Spannungsquelle
 * 	3...Stromquelle
 * 	4...Verbindung
 * 
 * Position:
 * 	1...Senkrecht
 * 	2...Waagrecht
 * 
 * TODO:
 * 	String eingabe bei abfrage der groese der arrays abfangen
 * 	verhindern, dass ein W, eine SpQ oder eine StQ dort bleibt, wenn man mit der Maus aus dem Fenster fahrt
 */

public class Zeichnen extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	private JPanel malen;
	private JRadioButton Widerstand;
	private JRadioButton Spannungsquelle;
	private JRadioButton Stromquelle;
	private JRadioButton Verbindung;
	private JRadioButton Waagrecht;
	private JRadioButton Senkrecht;
	private JTextField Wert;
	private JLabel Wert_text;
	
	private int werkz=1;
	private int pos=1;
	private int clicked=1;
	private int xmove_old=0;
	private int ymove_old=0;
	private int xclick=0;
	private int yclick=0;
	private int xclick2=0;
	private int yclick2=0;
	private int wpos=0;
	private int sppos=0;
	private int stpos=0;
	
	private Widerstand[] w;
	private Spannungsquelle[] sp;
	private Stromquelle[] st;
	
	public Zeichnen(){
		setTitle("Widerstandsnetzwerk Zeichnen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setBackground(Color.white);
		
		//Abfrage der Werte und anlegen der Arrays Anfang
		int anzw = 5;//Integer.parseInt(JOptionPane.showInputDialog(this, "Bitte geben sie die Anzahl der Widerstaende ein", "Anzahl der Widerstaende", JOptionPane.QUESTION_MESSAGE));
		int anzsp = 5;//Integer.parseInt(JOptionPane.showInputDialog(this, "Bitte geben sie die Anzahl der Spannungsquellen ein", "Anzahl der Spannungsquellen", JOptionPane.QUESTION_MESSAGE));
		int anzst = 5;//Integer.parseInt(JOptionPane.showInputDialog(this, "Bitte geben sie die Anzahl der Stromquellen ein", "Anzahl der Stromquellen", JOptionPane.QUESTION_MESSAGE));
		
		w = new Widerstand[anzw];
		sp = new Spannungsquelle[anzsp];
		st = new Stromquelle[anzst];
		//Abfrage der Werte und anlegen der Arrays Ende
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		malen = new JPanel();
		malen.setLayout(new FlowLayout());
		
		JPanel werkzeuge = new JPanel();
		werkzeuge.setLayout(new GridLayout(2, 1));
		
		JPanel art = new JPanel();
		art.setLayout(new FlowLayout());
		
		JPanel position = new JPanel();
		position.setLayout(new FlowLayout());
		
		JPanel werte = new JPanel();
		werte.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		Widerstand = new JRadioButton("Widerstand");
		Spannungsquelle = new JRadioButton("Spannungsquelle");
		Stromquelle = new JRadioButton("Stromquelle");
		Verbindung = new JRadioButton("Verbindung");
		
		Waagrecht = new JRadioButton("Waagrecht");
		Senkrecht = new JRadioButton("Senkrecht");
		
		Wert = new JTextField(10);
		Wert_text = new JLabel("Ohm");
		
		ButtonGroup wz = new ButtonGroup();
		wz.add(Widerstand);
		wz.add(Spannungsquelle);
		wz.add(Stromquelle);
		wz.add(Verbindung);
		
		ButtonGroup ws = new ButtonGroup();
		ws.add(Waagrecht);
		ws.add(Senkrecht);
		
		Widerstand.setSelected(true);
		Senkrecht.setSelected(true);
		
		malen.addMouseListener(this);
		malen.addMouseMotionListener(this);
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		Widerstand.addActionListener(this);
		Spannungsquelle.addActionListener(this);
		Stromquelle.addActionListener(this);
		Verbindung.addActionListener(this);
		
		Waagrecht.addActionListener(this);
		Senkrecht.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		art.add(Widerstand);
		art.add(Spannungsquelle);
		art.add(Stromquelle);
		art.add(Verbindung);
		
		position.add(Waagrecht);
		position.add(Senkrecht);
		
		werte.add(Wert);
		werte.add(Wert_text);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		werkzeuge.add(art);
		werkzeuge.add(position);
		cp.add(werte, BorderLayout.NORTH);
		cp.add(malen, BorderLayout.CENTER);
		cp.add(werkzeuge, BorderLayout.SOUTH);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Zeichnen();
	}

	public void actionPerformed(ActionEvent e) {
		
		if(Widerstand.isSelected()){
			werkz=1;
			Wert_text.setText("Ohm");
		}
		if(Spannungsquelle.isSelected()){
			werkz=2;
			Wert_text.setText("Volt");
		}
		if(Stromquelle.isSelected()){
			werkz=3;
			Wert_text.setText("Ampere");
		}
		if(Verbindung.isSelected()){
			werkz=4;
		}
		
		if(Senkrecht.isSelected()){
			pos=1;
		}
		if(Waagrecht.isSelected()){
			pos=2;
		}
	}

	public void mouseClicked(MouseEvent me) {
		Graphics g=malen.getGraphics();
		int wert;
		
		
		if(Wert.getText().equals("")){
			wert=1;
		}
		else{
			wert = Integer.parseInt(Wert.getText());
		}
		
		
		if(werkz==1){
			if(wpos>w.length-1)
				return;
			if(pos==1){
				w[wpos] = new Widerstand(wert, me.getX(), me.getY(), 1);
				w[wpos].draw(g, 1);
			}
			if(pos==2){
				w[wpos] = new Widerstand(wert, me.getX(), me.getY(), 2);
				w[wpos].draw(g, 1);
			}
			wpos++;
		}
		if(werkz==2){
			if(sppos>sp.length-1)
				return;
			
			if(pos==1){
				sp[sppos] = new Spannungsquelle(wert, me.getX(), me.getY(), 1);
				sp[sppos].draw(g, 1);
			}
			if(pos==2){
				sp[sppos] = new Spannungsquelle(wert, me.getX(), me.getY(), 2);
				sp[sppos].draw(g, 1);
			}
			sppos++;
		}
		if(werkz==3){
			if(stpos>st.length-1)
				return;
			
			if(pos==1){
				st[stpos] = new Stromquelle(wert, me.getX(), me.getY(),1);
				st[stpos].draw(g, 1);
			}
			if(pos==2){
				st[stpos] = new Stromquelle(wert, me.getX(), me.getY(), 2);
				st[stpos].draw(g, 1);
			}
			stpos++;
		}
		if(werkz==4){
			if(clicked==1){
				clicked=2;
				xclick=me.getX();
				yclick=me.getY();
				return;
			}
			if(clicked==2){
				clicked=1;
				xclick2=me.getX();
				yclick2=me.getY();
				g.drawLine(xclick, yclick, xclick2, yclick2);
				return;
			}
		}
	}

	public void mouseEntered(MouseEvent me) {
		Graphics g = malen.getGraphics();
		Widerstand bla = new Widerstand(1, xmove_old, ymove_old);
		if(pos==1){
			bla.draws(xmove_old, ymove_old, g, 0);
		}
		else{
			bla.draww(xmove_old, ymove_old, g, 0);
		}
	}
	public void mouseExited(MouseEvent me) {
		xmove_old=me.getX();
		ymove_old=me.getY();
	}
	
	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseDragged(MouseEvent me) {
	}

	public void mouseMoved(MouseEvent me) {
		
		int xmove=me.getX();
		int ymove=me.getY();
		Graphics g=malen.getGraphics();
		
		g.setColor(Color.white);
		
		if(werkz==1){
			Widerstand w = new Widerstand(1);
			if(pos==1){
				w.draws(xmove_old, ymove_old, g, 0);
			}
			if(pos==2){
				w.draww(xmove_old, ymove_old, g, 0);
			}
		}
		if(werkz==2){
			Spannungsquelle spq = new Spannungsquelle(1);
			if(pos==1){
				spq.draws(xmove_old, ymove_old, g, 0);
			}
			if(pos==2){
				spq.draww(xmove_old, ymove_old, g, 0);
			}
		}
		if(werkz==3){
			Stromquelle st = new Stromquelle(1);
			if(pos==1){
				st.draws(xmove_old, ymove_old, g, 0);
			}
			if(pos==2){
				st.draww(xmove_old, ymove_old, g, 0);
			}
		}
		
		g.setColor(Color.black);
		if(werkz==1){
			Widerstand w = new Widerstand(1);
			if(pos==1){
				w.draws(xmove, ymove, g, 0);
			}
			if(pos==2){
				w.draww(xmove, ymove, g, 0);
			}
		}
		if(werkz==2){
			Spannungsquelle spq = new Spannungsquelle(1);
			if(pos==1){
				spq.draws(xmove, ymove, g, 0);
			}
			if(pos==2){
				spq.draww(xmove, ymove, g, 0);
			}
		}
		if(werkz==3){
			Stromquelle st = new Stromquelle(1);
			if(pos==1){
				st.draws(xmove, ymove, g, 0);
			}
			if(pos==2){
				st.draww(xmove, ymove, g, 0);
			}
		}
		xmove_old=xmove;
		ymove_old=ymove;
		
		
		for(int i=0; i<wpos;i++){
			w[i].draw(g, 0);
		}
		for(int i=0;i<sppos;i++){
			sp[i].draw(g, 0);
		}
		for(int i=0; i<stpos;i++){
			st[i].draw(g, 0);
		}
	}

}
