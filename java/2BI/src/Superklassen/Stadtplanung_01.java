package Superklassen;

import java.util.*;


public class Stadtplanung_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random irgendwas = new Random();
		
		Haus h1 = new Haus(irgendwas.nextInt(100), irgendwas.nextInt(50), irgendwas.nextInt(50), irgendwas.nextInt(50), irgendwas.nextBoolean());
		System.out.println(h1);
		Reihenhaus rh1 = new Reihenhaus(irgendwas.nextInt(100), irgendwas.nextInt(50), irgendwas.nextInt(50), irgendwas.nextInt(50), irgendwas.nextBoolean());
		System.out.println(rh1);
	}
	
}
class Haus{
	public int hausnummer, breite, laenge, hoehe;
	public boolean fassadeOk;
	
	public Haus(int ha, int b, int l, int h, boolean f){
		hausnummer = ha;
		breite = b;
		laenge = l;
		hoehe = h;
		fassadeOk = f;
	}
	
	public void renovieren(){
		fassadeOk = true;
	}
	
	public String toString(){
		if(fassadeOk){
			return "Das Haus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist Ok.";
		}
		else{
			return "Das Haus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist renovierungsbedürftig.";
		}
	}
}

class Reihenhaus extends Haus{
	
	public Reihenhaus(int ha, int b, int l, int h, boolean f){
		super(ha, b, l, h, f);
	}
	
	public String toString(){
		if(fassadeOk){
			return "Das Reihenhaus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist Ok.";
		}
		else{
			return "Das Reihenhaus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist renovierungsbedürftig.";
		}
	}
}