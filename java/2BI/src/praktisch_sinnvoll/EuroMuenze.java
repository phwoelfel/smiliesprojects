package praktisch_sinnvoll;

import java.text.DecimalFormat;

class EuroMuenze extends Muenze {
	private String waehrung;
	
	public EuroMuenze(int weat) {
		super(weat);
		waehrung = "Û";
	}
	
	public String getWaehrung(){
		return waehrung;
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("##0.00");
		double w = wert;
		w/=100;
		df.format(w);
		
		return wert +" Cent: " +waehrung +" " +w;
	}
}
