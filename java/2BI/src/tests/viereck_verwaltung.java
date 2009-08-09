package tests;

import java.util.*;

public class viereck_verwaltung {
//	Philip Wšlfel, 2BI
	
	public static void main(String[] args) {
		TreeSet<Viereck> ts = new TreeSet<Viereck>();
		Random zuf = new Random();

		int ende = zuf.nextInt(20);//zufällige Anzahl an Elementen
		// System.out.println(ende);
		for (int i = 0; i < ende; i++) {
			int was = zuf.nextInt(2);//zufällig bestimmt ob Quadrat oder Rechteck
			if (was == 1) {
				ts.add(new Viereck(zuf.nextDouble() * 100));
			} else {
				ts.add(new Viereck(zuf.nextDouble() * 100,
						zuf.nextDouble() * 100));
			}
		}
		//zum test ob richtig sortiert Quadrat dann Rechteck
		ts.add(new Viereck(5, 5)); 
		ts.add(new Viereck(5));
		

		System.out.println("Vorher:");
		Iterator<Viereck> it = ts.iterator();
		while (it.hasNext()) {
			Viereck v = (Viereck) it.next();
			System.out.println(v);
		}
		System.out.println("Anzahl: " + ts.size() + "\n");

		Iterator<Viereck> it2 = ts.iterator();
		while (it2.hasNext()) {
			Viereck v = (Viereck) it2.next();
			if (v.typ.equals("Quadrat")) {
				it2.remove();
				// System.out.println("test");
			}
		}

		System.out.println("Nacher:");
		Iterator<Viereck> it3 = ts.iterator();
		while (it3.hasNext()) {
			Viereck v = (Viereck) it3.next();
			System.out.println(v);
		}
		System.out.println("Anzahl: " + ts.size() + "\n");
	}

}

class Viereck implements Comparable<Viereck> {
	public String typ;
	public double a;
	public double b;

	public Viereck(double s) {
		a = s;
		b = s;
		typ = "Quadrat";
	}

	public Viereck(double s1, double s2) {
		a = s1;
		b = s2;
		typ = "Rechteck";
	}

	public int compareTo(Viereck v) {
		int result;
		result = (int) ((a * b) - (v.a * v.b));

		if (result != 0) {
			return result;
		}

		return typ.compareTo(v.typ);
	}

	public String toString() {
		return "a: " + a + ", b: " + b + ", Typ: " + typ + ", Fläche: " + a * b;
	}

}
