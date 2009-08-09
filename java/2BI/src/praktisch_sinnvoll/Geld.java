package praktisch_sinnvoll;

public class Geld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* //Muenzen Test
		Muenze m = new Muenze(352);
		System.out.println(m);
		
		EuroMuenze em = new EuroMuenze(1234);
		System.out.println(em);
		*/
		
		System.out.println("-----------Kassa--------------");
		Kassa k = new Kassa(15);
		System.out.println(k);
		System.out.println("-------------------------");
		
		System.out.println("-----------Geld mit dem bezahlt wird--------------");
		Kassa k2 = new Kassa(15);
		System.out.println(k2);
		System.out.println("-------------------------");
		
		/*
		k.einzahlen(k2);
		
		System.out.println("-----------Kassa1--------------");
		System.out.println(k);
		System.out.println("-------------------------");
		*/
		int preis = 1234;
		System.out.println("Preis: " +preis +" cent");

		Kassa k3 = k.bezahlen(preis, k2);
		System.out.println("-----------Wechselgeld--------------");
		System.out.println(k3);
		System.out.println("-------------------------");
		
		
		/* //Transfer und Suche Test
		String suche = "50 Cent";
		System.out.println("Anzahl an " +suche +" Muenzen: " +k.anzahlMuenzen(suche));
		
		String transfs="1.0 Euro";
		int transfi = -15;
		System.out.println("Anzahl an " +transfs +" Muenzen: " +k.anzahlMuenzen(transfs));
		k.transfer(transfs, transfi);
		System.out.println("Anzahl an " +transfs +" Muenzen: " +k.anzahlMuenzen(transfs));
		*/
	}

}
