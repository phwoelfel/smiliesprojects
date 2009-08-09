package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Kreuzprodukt_vektoren extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JTextField vektor1;
	protected JLabel vektor1_t;
	protected JTextField vektor2;
	protected JLabel vektor2_t;
	protected JTextField ergebnis;
	protected JLabel ergt;
	protected JButton clear;
	protected JButton rechnen;
	protected JLabel info;
	
	protected Container mal_frame;
	
	public void init(){
		setTitle("Kreuzprodukt berchnen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		vektor1 = new JTextField(5);
		vektor1_t = new JLabel("Vektor 1: ");
		vektor2 = new JTextField(5);
		vektor2_t = new JLabel("Vektor 2: ");
		ergebnis = new JTextField(20);
		ergt = new JLabel("Ergebnis:");
		clear = new JButton("Clear");
		rechnen = new JButton("Berechnen");
		info = new JLabel("Bitte Vektoren in der Form (x,y,z) eingeben");
		
		mal_frame = new JFrame().getContentPane();
		mal_frame.setLayout(new FlowLayout());
		
		Container norden = new JFrame().getContentPane();
		norden.setLayout(new FlowLayout());
		
		norden.add(vektor1_t);
		norden.add(vektor1);
		vektor1.setText("(20,15,10)");
		norden.add(vektor2_t);
		norden.add(vektor2);
		vektor2.setText("(15,10,20)");
		norden.add(ergt);
		norden.add(ergebnis);
		
		
		Container sueden = new JFrame().getContentPane();
		sueden.setLayout(new FlowLayout());
		
		sueden.add(info);
		rechnen.addActionListener(this);
		sueden.add(rechnen);
		clear.addActionListener(this);
		sueden.add(clear);
		
		cp.add(norden, BorderLayout.NORTH);
		cp.add(sueden, BorderLayout.SOUTH);
		cp.add(mal_frame, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Kreuzprodukt_vektoren().init();

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rechnen){
			String v1 = vektor1.getText();
			v1 = v1.substring(1, v1.length()-1);
			String v2 = vektor2.getText();
			v2 = v2.substring(1, v2.length()-1);
			
			String[]v1_ar=v1.split(",");
			String[]v2_ar=v2.split(",");
			int[]v1_a=str_to_int_arr(v1_ar);
			int[]v2_a=str_to_int_arr(v2_ar);
			
			int[]erg = new int[3];
			erg[0]=v1_a[1]*v2_a[2]-v2_a[1]*v1_a[2];
			erg[1]=-(v1_a[0]*v2_a[2]-v2_a[0]*v1_a[2]);
			erg[2]=v1_a[0]*v2_a[1]-v2_a[0]*v1_a[1];
			
			ergebnis.setText("(" +erg[0] +"," +erg[1] +"," +erg[2] +")");
			
			Graphics g = mal_frame.getGraphics();
			int hoehe = mal_frame.getHeight();
			//System.out.println(hoehe);
			int breite = mal_frame.getWidth();
			//System.out.println(breite);
			
			g.drawLine(10, 10, 10, hoehe-10);
			g.drawLine(10, 10, breite-10, 10);
			g.drawLine(breite-10, 10, breite-10, hoehe-10);
			g.drawLine(10, hoehe-10, breite-10, hoehe-10);
			
			for(int i=10; i<breite; i+=10){
				g.drawLine(i, 5, i, 15);
			}
			for(int i=10; i<hoehe; i+=10){
				g.drawLine(5, i, 15, i);
			}
			
			g.drawLine(v1_a[0]*10,v1_a[1]*10,v2_a[0]*10,v2_a[1]*10);
			g.drawString("(" +v1_a[0] +"," +v1_a[1] +")", v1_a[0]*10+10,v1_a[1]*10+10);
			g.drawString("(" +v2_a[0] +"," +v2_a[1] +")", v2_a[0]*10+10,v2_a[1]*10+10);
		}
		
		if(e.getSource()==clear){
			vektor1.setText("");
			vektor2.setText("");
			ergebnis.setText("");
			mal_frame.repaint();
		}
		
	}
	public int[] str_to_int_arr(String [] str_arr){
		int [] int_arr = new int[str_arr.length];
		
		for(int i=0;i<int_arr.length;i++){
			int_arr[i]=Integer.parseInt(str_arr[i]);
		}
		
		return int_arr;
	}

}
