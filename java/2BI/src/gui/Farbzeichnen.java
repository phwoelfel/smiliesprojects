package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Werkzeuge: 
 * 	1: Linie
 * 	2: Rechteck
 * 	3: Kreis
 * 	4: Freihand Zeichnen
 * 
 */

public class Farbzeichnen extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	protected JRadioButton line;
	protected JRadioButton rect;
	protected JRadioButton circ;
	protected JRadioButton free;
	protected JButton clear;
	protected JRadioButton rot;
	protected JRadioButton gruen;
	protected JRadioButton blau;
	protected JRadioButton bgrot;
	protected JRadioButton bggruen;
	protected JRadioButton bgblau;
	protected JRadioButton bgweis;
	protected JLabel farbeninfo;
	protected JLabel bgfarbeninfo;
	
	protected JPanel malen;
	
	protected int werkz;
	protected int x_1=0;
	protected int x_2=0;
	protected int y_1=0;
	protected int y_2=0;
	protected boolean click=false;
	protected int xpos_old;
	protected int ypos_old;
	
	public void init(){
		setTitle("Zeichnen by Philip Woelfel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel werkzeuge = new JPanel();
		werkzeuge.setLayout(new FlowLayout());
		
		malen = new JPanel();
		malen.setLayout(new FlowLayout());
		
		JPanel werkzeuge2 = new JPanel();
		werkzeuge2.setLayout(new FlowLayout());
		
		JPanel farben = new JPanel();
		farben.setLayout(new GridLayout(3, 1));
		
		JPanel bgfarben = new JPanel();
		bgfarben.setLayout(new GridLayout(4, 1));
		//Container Ende
		
		//Initialisierung der Inhalte Anfang
		line = new JRadioButton("Linie");
		rect = new JRadioButton("Rechteck");
		circ = new JRadioButton("Kreis");
		free = new JRadioButton("Freihand Zeichnen");
		ButtonGroup rbw = new ButtonGroup();
		rbw.add(line);
		rbw.add(rect);
		rbw.add(circ);
		rbw.add(free);
		
		clear = new JButton("Clear");
		
		rot = new JRadioButton("Rot");
		gruen = new JRadioButton("Gruen");
		blau = new JRadioButton("Blau");
		ButtonGroup rbf = new ButtonGroup();
		rbf.add(rot);
		rbf.add(gruen);
		rbf.add(blau);
		
		bgrot = new JRadioButton("Rot");
		bggruen = new JRadioButton("Gruen");
		bgblau = new JRadioButton("Blau");
		bgweis = new JRadioButton("Weiß");
		ButtonGroup rbbg = new ButtonGroup();
		rbbg.add(bgrot);
		rbbg.add(bggruen);
		rbbg.add(bgblau);
		rbbg.add(bgweis);
		
		farbeninfo = new JLabel("Farbe auswählen");
		bgfarbeninfo = new JLabel("Hintergrundfarbe auswählen");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		line.addActionListener(this);
		rect.addActionListener(this);
		circ.addActionListener(this);
		free.addActionListener(this);
		clear.addActionListener(this);
		
		rot.addActionListener(this);
		gruen.addActionListener(this);
		blau.addActionListener(this);
		
		bgrot.addActionListener(this);
		bggruen.addActionListener(this);
		bgblau.addActionListener(this);
		bgweis.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den Container hinzufuegen Anfang
		werkzeuge.add(line);
		werkzeuge.add(rect);
		werkzeuge.add(circ);
		werkzeuge.add(free);
		
		werkzeuge2.add(clear);
		
		farben.add(rot);
		farben.add(gruen);
		farben.add(blau);
		
		bgfarben.add(bgrot);
		bgfarben.add(bggruen);
		bgfarben.add(bgblau);
		bgfarben.add(bgweis);
		//werkzeuge.add(free);
		
		malen.addMouseListener(this);
		malen.addMouseMotionListener(this);
		//Inhalte zu den Container hinzufuegen Ende
		
		
		//Container zum JFrame hinzufuegen Anfang
		cp.add(werkzeuge, BorderLayout.NORTH);
		cp.add(malen, BorderLayout.CENTER);
		cp.add(werkzeuge2, BorderLayout.SOUTH);
		//cp.add(farben, BorderLayout.WEST);
		cp.add(bgfarben, BorderLayout.EAST);
		//Container zum JFrame hinzufuegen Ende	
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Farbzeichnen().init();
	}

	public void actionPerformed(ActionEvent e) {
		//Werkzeuge
		if(line.isSelected()){
			werkz=1;
		}
		if(rect.isSelected()){
			werkz=2;
		}
		if(circ.isSelected()){
			werkz=3;
		}
		if(free.isSelected()){
			werkz=4;
		}
		
		//Clear
		if(e.getSource()==clear){
			malen.repaint();
		}
		
		//Vordergrundfarben
		if(rot.isSelected()){
			malen.setForeground(Color.red);
		}
		if(blau.isSelected()){
			malen.setForeground(Color.blue);
		}
		if(gruen.isSelected()){
			malen.setForeground(Color.green);
		}
		
		//Hintergrundfarben
		if(bgrot.isSelected()){
			malen.setBackground(Color.red);
		}
		if(bgblau.isSelected()){
			malen.setBackground(Color.blue);
		}
		if(bggruen.isSelected()){
			malen.setBackground(Color.green);
		}
		if(bgweis.isSelected()){
			malen.setBackground(Color.white);
		}
	}

	public void mouseClicked(MouseEvent me) {
		if(click){//Wenn das zweite mal geklickt
			click=false;
			x_2=me.getX();
			y_2=me.getY();
			Graphics g = malen.getGraphics();
			switch(werkz){
				case 1:	g.drawLine(x_1, y_1, x_2, y_2);
						break;
				case 2:	if(x_1<x_2 && y_1<y_2){//links oben rechts unten
							g.drawRect(x_1, y_1, x_2-x_1, y_2-y_1);
						}
						if(x_1<x_2 && y_1>y_2){//links unten rechts oben
							g.drawRect(x_1, y_2, x_2-x_1, y_1-y_2);
						}
						if(x_1>x_2 && y_1>y_2){//rechts unten links oben
							g.drawRect(x_2, y_2, x_1-x_2, y_1-y_2);
						}
						if(x_1>x_2 && y_1<y_2){//rechts oben links unten
							g.drawRect(x_2, y_1, x_1-x_2, y_2-y_1);
						}
						break;
				case 3:	if(x_1<x_2 && y_1<y_2){//links oben rechts unten
							g.drawOval(x_1, y_1, x_2-x_1, y_2-y_1);
						}
						if(x_1<x_2 && y_1>y_2){//links unten rechts oben
							g.drawOval(x_1, y_2, x_2-x_1, y_1-y_2);
						}
						if(x_1>x_2 && y_1>y_2){//rechts unten links oben
							g.drawOval(x_2, y_2, x_1-x_2, y_1-y_2);
						}
						if(x_1>x_2 && y_1<y_2){//rechts oben links unten
							g.drawOval(x_2, y_1, x_1-x_2, y_2-y_1);
						}
						break;
				case 4:
						break;
			}
		}
		else{//Wenn das erste mal geklickt
			click=true;
			x_1=me.getX();
			y_1=me.getY();
		}
	}

	public void mouseEntered(MouseEvent me) {
		int xpos=me.getX();
		int ypos=me.getY();
		xpos_old=xpos;
		ypos_old=ypos;
	}

	public void mouseExited(MouseEvent me) {
		
	}

	public void mousePressed(MouseEvent me) {
		int xpos=me.getX();
		int ypos=me.getY();
		xpos_old=xpos;
		ypos_old=ypos;
	}

	public void mouseReleased(MouseEvent me) {
		
	}

	public void mouseDragged(MouseEvent me) {
		if(free.isSelected()){
			int xpos=me.getX();
			int ypos=me.getY();
			Graphics g = malen.getGraphics();
			
			g.drawLine(xpos_old, ypos_old, xpos, ypos);
			xpos_old=xpos;
			ypos_old=ypos;
		}
	}

	public void mouseMoved(MouseEvent me) {
		
	}

}
