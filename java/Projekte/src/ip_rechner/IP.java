package ip_rechner;
/* 
 * MZ = 256-(spez. Quad der SNM)
 * subnetzadr = floor((spez. Quad der ip)/MZ)*MZ
 * 
 */

class IP {
	/*
	 * (c) by Philip Woelfel
	 * philip.woelfel@frig.at
	 */
	
	private int [] Adresse = null; //Adresse in Dezimaler Form
	
	
	/* Standardkonstruktor
	 * Adresse wird mit 0.0.0.0 initialisiert!
	 */
	public IP (){
		Adresse = new int [] {0, 0, 0, 0};
		//System.out.println("Adress was initialized with 192.168.0.0!!");
	}
	/* Konstruktor
	 * Parameter:
	 * 		Int Array mit der Adresse in Dezimaler Form
	 * 
	 * Throws:
	 * 		Exception wenn das mitgegeben Array nicht lang genug ist
	 */
	public IP (int[] adr) throws Exception{
		if(adr.length<4){
			throw new Exception("Adress is too short!");
		}
		Adresse = adr;
	}
	/* Konstruktor
	 * Parameter:
	 * 		String mit der Adresse in Dezimaler Form
	 * 
	 * Throws:
	 * 		Exception wenn sich der mitgegebene String nicht in ein Array mit einer mindestlaenge von 4 umwandeln laesst
	 */
	public IP(String adr) throws Exception{
		adr = adr.replace(".", ";");
		String [] adrar = adr.split(";");
		if(adrar.length<4){
			throw new Exception("Adress is too short!");
		}
		Adresse = new int[4];
		for(int i=0;i<4;i++){
			Adresse[i] = Integer.parseInt(adrar[i]); 
		}
	}
	/* Konstruktor
	 * Parameter:
	 * 		2D int Array mit IP in binaerer Form
	 * 
	 * Throws:
	 * 		Exception wenn das Array zu klein ist
	 */
	public IP(int[][] binadr) throws Exception{
		if(binadr.length<4 || binadr[0].length<8 || binadr[1].length<8 || binadr[2].length<8 || binadr[3].length<8){
			throw new Exception("Adress is too short!");
		}
		Adresse = new int[4];
		for(int i=0;i<4;i++){
			for(int j=0;j<8;j++){
				Adresse[i] += binadr[i][j]*Math.pow(2, 8-(j+1));
			}
		}
	}
	/* Konstruktor
	 * Parameter:
	 * 		IP Objekt
	 * 
	 */
	public IP(IP nip){
		Adresse = nip.Adresse;
	}
	
	/*
	 * Rueckgabewert:
	 * 		String mit der IP in binaerer Form
	 */
	public String toString(){
		return Adresse[0] +"." +Adresse[1] +"." +Adresse[2] +"." +Adresse[3];
	}
	/*
	 * Rueckgabewert:
	 * 		String mit der IP in binaerer Form
	 */
	public String getBinaryString(){
		String outa[] = new String[4];
		String out = "";
		for(int i=0;i<Adresse.length;i++){
			int z = Adresse[i];
			outa[i]="";
			do{
				outa[i] = (z%2) +outa[i];
				z/= 2;
			}while(z!=0);
		}
		
		for(int i=0;i<outa.length;i++){
			while(outa[i].length()<8){
				outa[i] = "0" + outa[i];
			}
			out += outa[i];
			if(i!=outa.length-1){
				out += ".";
			}
		}
		return out;
	}
	/*
	 * Rueckgabewert:
	 * 		2D int Array mit mit dem binaeren Wert der IP Adresse
	 * 			erster Index steht fuer das Quad, zweiter fuer die Stelle
	 */
	public int[][] getBinary(){
		int out[][] = new int[4][8];
		for(int i=0;i<Adresse.length;i++){
			int z = Adresse[i];
			int j = 7;
			do{
				//System.out.println("z: " +z +", z%2: " +z%2);
				out[i][j] = (z%2);
				z/= 2;
				j--;
			}while(z!=0);
		}
		
		return out;
	}
	
	/*
	 * Rueckgabewert:
	 * 		String mit der Adresse in dezimaler Form
	 */
	public String getAdressString(){
		return Adresse[0] +"." +Adresse[1] +"." +Adresse[2] +"." +Adresse[3];
	}
	/*
	 * Rueckgabewert:
	 * 		Int Array mit der Adresse in dezimaler Form
	 */
	public int[] getAdress(){
		int[]ar = new int[Adresse.length];
		for(int i=0;i<ar.length;i++){
			ar[i]=Adresse[i];
		}
		return ar;
	}
	
	/*
	 * Parameter:
	 * 		Int Array mit der Adresse in Dezimaler Form
	 * 
	 * Throws:
	 * 		Exception wenn das mitgegeben Array nicht lang genug ist
	 */
	public void setAdress(int[] adr) throws Exception{
		if(adr.length<4){
			throw new Exception("Adress is to short!");
		}
		Adresse = new int[4];
		for(int i=0;i<Adresse.length;i++){
			Adresse[i] = adr[i];
		}
		
	}
	/*
	 * Parameter:
	 * 		String mit der Adresse in Dezimaler Form
	 * 
	 * Throws:
	 * 		Exception wenn sich der mitgegebene String nicht in ein Array mit einer mindestlaenge von 4 umwandeln laesst
	 */
	public void setAdress(String adr) throws Exception{
		adr = adr.replace(".", ";");
		String [] adrar = adr.split(";");
		if(adrar.length<4){
			throw new Exception("Adress is to short!");
		}
		Adresse = new int[4];
		for(int i=0;i<4;i++){
			Adresse[i] = Integer.parseInt(adrar[i]); 
		}
	}
	
	/*
	 * Parameter:
	 * 		int Zahl welches Quad ersetzt werden soll
	 * 		int Zahl mit was es ersetzt werden soll
	 * 
	 */
	public void setQuad(int quad, int adr){
		Adresse[quad]=adr;
	}
	
	/*
	 * Rueckgabewert:
	 * 		int zahl mit der Anzahl der vorkommenden Einsen in der binaeren IP
	 */
	public int getOnes(){
		int[][] bin = getBinary();
		int anz=0;
		for(int i=0;i<bin.length;i++){
			for(int j=0;j<bin[i].length;j++){
				if(bin[i][j]==1){
					anz++;
				}
			}
		}
		return anz;
	}
	
}
