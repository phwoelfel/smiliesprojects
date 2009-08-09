package Superklassen;

public class Superklasse {

	public static void main(String[] args) {
		LKW hallo = new LKW(400, 15, 10000, 10);
		System.out.println(hallo);

	}

}

class Fahrzeug{
	public int leistung;
	public int gewicht;
	public double wert;
	
	public Fahrzeug(int l, int g, double w){
		leistung = l;
		gewicht = g;
		wert = w;
	}
	public String toString(){
		return "Leistung: " +leistung +"PS, Gewicht: " +gewicht +"t, Wert: " +wert +"€";
	}
}

class LKW extends Fahrzeug{
	public int nutzlast;
	
	public LKW(int l, int g, double w,int n){
		super(l, g, w);
		nutzlast = n;
	}
	public String toString(){
		return "Leistung: " +leistung +"PS, Gewicht: " +gewicht +"t, Wert: " +wert +"€, Nutzlast: " +nutzlast +"t.";
	}
}

class PKW extends Fahrzeug{
	public int sitzplaetze;
	
	public PKW(int l, int g, double w,int s){
		super(l, g, w);
		sitzplaetze = s;
	}
	public String toString(){
		return "Leistung: " +leistung +", Gewicht: " +gewicht +", Wert: " +wert +", Sitzplätze: " +sitzplaetze;
	}
}