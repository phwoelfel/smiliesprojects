package Superklassen;
import java.applet.*;
import java.awt.*;
import java.util.*;

public class Stadtplanung_grafisch extends Applet{
	
	public void init(){
		setForeground(Color.black);
		setBackground(Color.white);
		resize(900, 800);
	}
	
	public void paint(Graphics g){
		Random irgendwas = new Random();
		/*
		Haus_grafisch test = new Haus_grafisch(123, 60, 80, 20, 120);
		test.malen(g);
		Reihenhaus_grafisch test2 = new Reihenhaus_grafisch(123, 300, 400, 100, 600);
		test2.malen(g);
		*/
		int[]nummern =hausn();
		//printArray.Int(nummern);
		
		Haus_grafisch[] neusiedl = new Haus_grafisch[10];
		for(int i=0; i<neusiedl.length;i++){
			int a = irgendwas.nextInt(2);
			if(a==0){
				neusiedl[i]=new Haus_grafisch(nummern[i], irgendwas.nextInt(500)+10, irgendwas.nextInt(500)+10, irgendwas.nextInt(500)+10, irgendwas.nextInt(500)+10);
			}
			else{
				neusiedl[i]=new Reihenhaus_grafisch(nummern[i], irgendwas.nextInt(500)+10, irgendwas.nextInt(500)+10, irgendwas.nextInt(500)+10, irgendwas.nextInt(500)+10);
			}
		}
		
		for(int i=0;i<neusiedl.length;i++){
			neusiedl[i].malen(g);
		}
	}
	public static int[] hausn(){
		Random irgendwas = new Random();
		int[] out = new int[100];
		
		for(int i=0; i<out.length;i++){
			int hn = irgendwas.nextInt(1000);
			if(num_exists(out, hn)){
				i--;
				continue;
			}
			else{
				out[i]=hn;
				//System.out.println(i +"   " +out[i] +"   " +hn);
			}
			
		}
		
		return out;
	}
	 public static boolean num_exists(int[] arr, int n){
		 boolean out = false;
		
		 for(int i=0;i<arr.length;i++){
			 if(arr[i]==n){
				 out=true;
				 break;
			 }
		 }
		 
		 return out;
	 }
}
class Haus_grafisch{
	public int hausnummer, breite, hoehe, x, y;
	
	public Haus_grafisch(int ha, int b, int h, int xa, int ya){
		hausnummer = ha;
		breite = b;
		hoehe = h;
		x = xa;
		y = ya;
	}
	
	public void malen(Graphics g){
		//Haus grundgeruest
		g.drawRect(x, y-hoehe, breite, hoehe);
		g.drawLine(x, y-hoehe, x+(breite/2), (y-hoehe)-(breite/2));
		g.drawLine(x+breite, y-hoehe, x+(breite/2), (y-hoehe)-(breite/2));
		
		//Hausnummer
		g.drawString("" +hausnummer, x, y+20);
		
		//Fenster, Tuer
		g.drawRect(x+breite/2-breite/8, y-hoehe+breite/4, breite/4, breite/4);
		g.drawLine(x+breite/2-breite/8, y-hoehe+breite/4+breite/8, x+breite/2+breite/8, y-hoehe+breite/4+breite/8);
		g.drawLine(x+breite/2, y-hoehe+breite/4, x+breite/2, y-hoehe+breite/2);
	}
	
	
	public String toString(){
		return "Das Haus " +hausnummer +" ist " +breite +"m breit, " +hoehe +"m hoch.";
	}
}

class Reihenhaus_grafisch extends Haus_grafisch{
	
	public Reihenhaus_grafisch(int ha, int b, int h, int xa, int ya){
		super(ha, b, h, xa, ya);
	}
	
	public String toString(){
		return "Das Haus " +hausnummer +" ist " +breite +"m breit, " +hoehe +"m hoch.";
	}
	public void malen(Graphics g){
		//Haus grundgeruest
		g.drawRect(x, y-hoehe, breite, hoehe);
		g.drawLine(x, y-hoehe, x+(breite/2), (y-hoehe)-(breite/2));
		g.drawLine(x+breite, y-hoehe, x+(breite/2), (y-hoehe)-(breite/2));
		
		//Hausnummer
		g.drawString("" +hausnummer, x, y+20);
		
		//Fenster, Tuer
		g.drawRect(x+breite/2-breite/8, y-hoehe+breite/4, breite/4, breite/4);
		g.drawLine(x+breite/2-breite/8, y-hoehe+breite/4+breite/8, x+breite/2+breite/8, y-hoehe+breite/4+breite/8);
		g.drawLine(x+breite/2, y-hoehe+breite/4, x+breite/2, y-hoehe+breite/2);
		
		int tmpx=x+breite;
		//Haus grundgeruest
		g.drawRect(tmpx, y-hoehe, breite, hoehe);
		g.drawLine(tmpx, y-hoehe, tmpx+(breite/2), (y-hoehe)-(breite/2));
		g.drawLine(tmpx+breite, y-hoehe, tmpx+(breite/2), (y-hoehe)-(breite/2));
		
		//Hausnummer
		g.drawString("" +hausnummer, tmpx, y+20);
		
		//Fenster, Tuer
		g.drawRect(tmpx+breite/2-breite/8, y-hoehe+breite/4, breite/4, breite/4);
		g.drawLine(tmpx+breite/2-breite/8, y-hoehe+breite/4+breite/8, tmpx+breite/2+breite/8, y-hoehe+breite/4+breite/8);
		g.drawLine(tmpx+breite/2, y-hoehe+breite/4, tmpx+breite/2, y-hoehe+breite/2);
	}
}
