/*
 * (c) by 2BI
 * HTL Wien 3 Rennweg
 * Philip Woelfel
 * philip.woelfel@frig.at
 */
class Figur {
	protected byte farbe;
	protected byte x,y;
	
	/*Konstruktoren*/
	public Figur(byte f, byte xx, byte yy){
		farbe = f;
		x = xx;
		y = yy;
	}
	
	public Figur(Figur f){
		farbe = f.farbe;
		x = f.x;
		y = f.y;
	}
	
	/*Methoden*/
	public byte testen(Figur[][] spielfeld, byte zielx, byte ziely, byte spieler){ 
		/*Rueckgabewerte:
		 * 0... nicht moeglich
		 * 1... moeglich, nichts uebersprungen
		 * 2... moeglich, Figur uebersprungen
		 */ 
		if(farbe==spieler){//Richtiger Spieler
			
			//ab hier nur bedingt richtig
			//hab nur getestet ob ein feld weiter gesprungen, und nicht ob vielleicht zwei und dann uebersprungen wurde
			
			if(farbe==0){//Weisse Figur, darf nur runter fahren
				//noch ein bissl ueberlegen ob man das muss oder nicht
				//int tmp=0;//muss gespeichert werden ob links oder rechts gefahren, zum ueberpruefen ob uebersprungen wurde
				//links...0, rechts...1
				if(zielx==(x-1)){//will in die richtige Reihe fahren
					if(ziely==(y-1)){//fahrt nach links
						return 1;
					}
					else if(ziely==(y+1)){//fahrt nach rechts
						return 1;
					}
					else{//falsches Feld
						return 0;
					}
				}
				//fehlt noch ueberpruefen ob er zwei weiter ist
				//jetzt ueberpruefen ob Feld leer ist
				if(spielfeld[(int)zielx][(int)ziely]==null){//Feld ist leer er darf fahren
					//jetzt ueberpruefen ob eine Figur uebersprungen wurde
					//ein eigener darf nicht uebersprungen werden
					//if(tmp==0){//nach links pruefen
						
					//}
					return 1;
				}
			}
			else{//Schwarze Figur, darf nur rauf fahren
				
			}
		}
		return 0;//falscher spieler, raus aus der Methode
	}
	
	public int getType(){
		return 0;
	}
}
