package tests;

/**
 *
 * Beschreibung.
 *
 * @version 2.0 vom 13.05.2008
 * @author 
 */

import java.util.*;
import java.io.*;

public class harry_potter {
	
	//Philip Wölfel, 2BI
	
	static ArrayList<Card> allCards = new ArrayList<Card>();
	static ArrayList<Card> p1 = new ArrayList<Card>();
	static ArrayList<Card> p2 = new ArrayList<Card>();
	
	public static void main(final String[] args) {

		readDataFile();

		// Die Funktion shuffle "mischt" die Karten in allCards und teilt sie
		// auf die beiden Spieler (p1,p2) auf
		shuffle();

		// Nun wird 1 Spielrunde mit zufälliger Auswahl simuliert !!

		int wahl = (int) (1 + Math.random() * 5);
		System.out.println("Spieler 1" + p1.get(0));
		System.out.println("\nSpieler 2" + p2.get(0));

		System.out.println("\nAuswahl: " + wahl);

		rundenwertung(wahl);

		System.out.println("\nSpieler 1 hat " + p1.size() + " Karten");
		System.out.println("Spieler 2 hat " + p2.size() + " Karten");
	}

	public static void rundenwertung(int kat) {
		/*
		 * werte[kat] der ersten karte wird verglichen dann wird karte zu
		 * gewinner gegeben
		 */
		int sp1 = p1.get(0).werte[kat - 1];
		int sp2 = p2.get(0).werte[kat - 1];

		// System.out.println(sp1 +"\n");
		// System.out.println(sp2);

		if (sp1 > sp2) {// spieler 1 hat bessere karte#
			// System.out.println("sp1>sp2");
			p1.add(p1.get(0));// eigene karte hinten dran
			p1.remove(0);// erste löschen
			p1.add(p2.get(0));// karte von spieler 2 geben
			p2.remove(0);// spieler 2 karte wegnehmen
		} else {
			// System.out.println("sp1<sp2");
			p2.add(p2.get(0));// eigene karte hinten dran
			p2.remove(0);// erste löschen
			p2.add(p1.get(0));// karte von spieler 1 geben
			p1.remove(0);// spieler 1 karte wegnehmen
		}
	}

	public static void readDataFile() {

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("cards.txt"));
			String line;
			while ((line = in.readLine()) != null) {
				// System.out.println(line);
				String[] zeile = line.split(";");
				allCards.add(new Card(zeile[0], Integer.parseInt(zeile[1]),	Integer.parseInt(zeile[2]), Integer.parseInt(zeile[3]), Integer.parseInt(zeile[4]), Integer.parseInt(zeile[5])));
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Ein/Ausgabe Fehler");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e2) {
					System.out.println("Fehler beim schließen");
				}
			}
		}

	}

	public static void shuffle() {
		int actpl = 1;
		Card tmp;
		int rindex;
		while (allCards.size() > 0) {
			rindex = (int) (1 + Math.random() * allCards.size());
			rindex--;
			tmp = allCards.get(rindex);
			allCards.remove(rindex);
			if (actpl == 1) {
				p1.add(tmp);
				actpl = 2;
			} else {
				p2.add(tmp);
				actpl = 1;
			}
		}
	}
}

class Card {
	public String name;

	public int[] werte = new int[5];

	public Card(String n, int m, int s, int mu, int w, int z) {
		name = n;
		werte[0] = m;
		werte[1] = s;
		werte[2] = mu;
		werte[3] = w;
		werte[4] = z;
	}

	public String toString() {
		return "\n\n  "+name+"\n\n(1) Magie      =  "+werte[0]+
        "\n(2) Schlauheit =  "+werte[1]+
        "\n(3) Mut        =  "+werte[2]+
        "\n(4) Weisheit   =  "+werte[3]+
        "\n(5) Zorn       =  "+werte[4];
	}
}
