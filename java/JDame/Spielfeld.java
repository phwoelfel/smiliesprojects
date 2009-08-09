/*
 * (c) by 2BI
 * HTL Wien 3 Rennweg
 * Philip Woelfel
 * philip.woelfel@frig.at
 */
class Spielfeld {
	protected Figur[][] spielfiguren;
	protected byte spieler;
	
	public Spielfeld(){
		
	}
	
	//zum bewegen der Figur im Spielfeld
	public void bewegen(byte startx, byte starty, byte zielx, byte ziely){//sollte funktionieren
		Figur tmp;//zum zwischenspeichern der Figur
		int result = spielfiguren[startx][starty].testen(spielfiguren, zielx, ziely, spieler); //Wert der testen Methode
		if(result==1){
			//einfach auf das zielfeld setzen und das andere loeschen
			spielfiguren[zielx][ziely] = spielfiguren[startx][starty];
			spielfiguren[startx][starty] = null;
		}
		if(result==2){
			//zuerst einfach auf das zielfeld setzen und das andere loeschen
			spielfiguren[zielx][ziely] = spielfiguren[startx][starty];
			spielfiguren[startx][starty] = null;
			
			//jetzt noch den uebersprungenen loeschen
			if(zielx>startx){//springt nacht unten
				if(ziely<starty){//zielfeld ist weiter links
					spielfiguren[zielx-1][ziely+1] = null;
				}
				if(ziely>starty){//zielfeld ist weiter rechts
					spielfiguren[zielx-1][ziely-1] = null;
				}
			}
			if(zielx<startx){//springt nach oben
				if(ziely<starty){//zielfeld ist weiter links
					spielfiguren[zielx+1][ziely+1] = null;
				}
				if(ziely>starty){//zielfeld ist weiter rechts
					spielfiguren[zielx+1][ziely-1] = null;
				}
			}
		}
	}
}
