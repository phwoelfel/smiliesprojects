package praktisch_sinnvoll;

import java.util.Random;

class Kassa {
	private EuroMuenze [] muenzen = new EuroMuenze[8];
	private int [] anzahl = new int[muenzen.length];
	
	/*Konstruktoren:
		ohne Parameter: mit Anzahl 0 bei allen
		mit einem int Parameter: maximale Anzahl an muenzen
		mit einem int Array Parameter: mit werten des Arrays füllen
	*/
	
	public Kassa(){// Alle mit Anzahl 0 initialisiert
		muenzen[0] = new EuroMuenze(1);
		muenzen[1] = new EuroMuenze(2);
		muenzen[2] = new EuroMuenze(5);
		muenzen[3] = new EuroMuenze(10);
		muenzen[4] = new EuroMuenze(20);
		muenzen[5] = new EuroMuenze(50);
		muenzen[6] = new EuroMuenze(100);
		muenzen[7] = new EuroMuenze(200);
		 
		
		Random zuf = new Random();
		for(int i=0;i<muenzen.length;i++){
			anzahl[i]=0;
		}
	}
	public Kassa(int maxAnz){//mit Zufahlszahlen mit dem Maximalen wert maxAnz initialisiert
		muenzen[0] = new EuroMuenze(1);
		muenzen[1] = new EuroMuenze(2);
		muenzen[2] = new EuroMuenze(5);
		muenzen[3] = new EuroMuenze(10);
		muenzen[4] = new EuroMuenze(20);
		muenzen[5] = new EuroMuenze(50);
		muenzen[6] = new EuroMuenze(100);
		muenzen[7] = new EuroMuenze(200);
		 
		
		Random zuf = new Random();
		for(int i=0;i<muenzen.length;i++){
			anzahl[i]=zuf.nextInt(maxAnz);
		}
	}
	
	public Kassa(int[] AnzArray){//mit den Zahlen die im Array stehen initialisert
		muenzen[0] = new EuroMuenze(1);
		muenzen[1] = new EuroMuenze(2);
		muenzen[2] = new EuroMuenze(5);
		muenzen[3] = new EuroMuenze(10);
		muenzen[4] = new EuroMuenze(20);
		muenzen[5] = new EuroMuenze(50);
		muenzen[6] = new EuroMuenze(100);
		muenzen[7] = new EuroMuenze(200);
		 
		if(AnzArray.length<muenzen.length){
			System.err.println("Das Array ist zu klein! Die mindestlaenge ist " +muenzen.length +"!!");
		}
		else{
			Random zuf = new Random();
			for(int i=0;i<muenzen.length;i++){
				anzahl[i]=AnzArray[i];
			}
		}
	}
	
	public String toString(){
		String out="";
		double sum=0;
		
		for(int i=0;i<muenzen.length;i++){
			out += muenzen[i].bezeichnung +": " +anzahl[i] +" Stueck\n";
			sum += muenzen[i].getWert() * anzahl[i];
		}
		out += "Gesamtwert in Cent: " +(int)sum +" c";
		out += "\nGesamtwert in Euro: " +sum/100 +" €";
		return out;
	}
	
	public EuroMuenze[] getMuenzen(){
		return muenzen;
	}
	public int[] getAnzahl(){
		return anzahl;
	}
	
	public int getSum(){
		int sum=0;
		for(int i=0;i<muenzen.length;i++){
			sum += muenzen[i].getWert() * anzahl[i];
		}
		return sum;
	}
	
	public int anzahlMuenzen(String s){
		 for(int i = 0; i<muenzen.length;i++){
			 if(muenzen[i].bezeichnung.equals(s)){
				 return anzahl[i];
			 }
		 }
		 return 0;
	 }
	 
	 public void transfer(String s, int anz){
		 for(int i = 0; i<muenzen.length;i++){
			 if(muenzen[i].bezeichnung.equals(s)){
				 int w = anzahl[i]+anz;
				 if(w>0){
					 anzahl[i]+=anz;
				 }
				 else{
					 System.err.println("ERROR: Es ist nicht genuegend Geld in der Kassa!");
				 }
			 }
		 }
	 }
	 
	 public void einzahlen(Kassa k){
		 for(int i = 0; i<k.getMuenzen().length;i++){
			 transfer(k.getMuenzen()[i].bezeichnung, k.getAnzahl()[i]);
		 }
	 }
	 
	 public Kassa bezahlen(int preis, Kassa in){
		 if(preis>in.getSum()){
			 System.err.println("ERROR: Nicht genuegend Geld vorhanden!");
			 return in;
		 }
		 else{
			 Kassa out = new Kassa();
			 
			 /*
			  * den Preis von dem Gesamtwert der Kassa abziehen (in cent)
			  * if(ergebnis > 0)
			  * mit schleife immer den größt möglichen wert subtrahieren solang > 0
			  * if((sum-muenzen[i].getWert())>0)
			  * 	dann subtrahieren
			  * 
			 */
			 
			 int restGeld = in.getSum()-preis;
			 if(restGeld>0){
				 do{
					 for(int i=muenzen.length-1;i>0;i--){
						 if((restGeld-muenzen[i].getWert())>=0){
							 restGeld-=muenzen[i].getWert();
							 out.getAnzahl()[i]++;
							 break;
						 }
					 }
				 }while(restGeld>0);
			 }
			 
			 return out;
		 }
	 }
	 
}
