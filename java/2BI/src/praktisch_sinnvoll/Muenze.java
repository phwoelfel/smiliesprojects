package praktisch_sinnvoll;

class Muenze {
	protected int wert;
	protected String bezeichnung;
	
	public Muenze(int weat){
		wert = weat;
		if(wert<100){
			bezeichnung = weat +" Cent";
		}
		else{
			double w = wert;
			w=w/100;
			bezeichnung = w +" Euro";
		}
	}
	
	public String toString() {
		return bezeichnung;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public int getWert() {
		return wert;
	}

}
