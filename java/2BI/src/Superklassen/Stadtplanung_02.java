package Superklassen;

import java.io.*;
import java.util.*;



public class Stadtplanung_02 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Random irgendwas = new Random();
		BufferedReader eing = new BufferedReader(new InputStreamReader(System.in));
		
		int[]nummern =hausn();
		//printArray.Int(nummern);
		
		Haus1[] neusiedl = new Haus1[100];
		for(int i=0; i<neusiedl.length;i++){
			int a = irgendwas.nextInt(2);
			if(a==0){
				neusiedl[i]=new Haus1(nummern[i], irgendwas.nextInt(50)+10, irgendwas.nextInt(50)+10, irgendwas.nextInt(50)+10, irgendwas.nextBoolean());
			}
			else{
				neusiedl[i]=new Reihenhaus1(nummern[i], irgendwas.nextInt(50)+10, irgendwas.nextInt(50)+10, irgendwas.nextInt(50)+10, irgendwas.nextBoolean());
			}
		}
		String nomal;
		do{
			int waswillich;
			System.out.println("Alles(1)   nur renovierungsbeduerftige(2)");
			String wasw=eing.readLine();
			waswillich=Integer.parseInt(wasw);
			
			sort(neusiedl);
			
			switch(waswillich){
				case 1: for(int i=0;i<neusiedl.length;i++){
							System.out.println(neusiedl[i]);
						}
						break;
				
				case 2: for(int i=0;i<neusiedl.length;i++){
							if(neusiedl[i].fassadeOk){}
							else{
								System.out.println(i +": " +neusiedl[i]);
							}
						}
						System.out.println("Welches wollen sie renovieren?");
						int renov;
						String reno=eing.readLine();
						renov=Integer.parseInt(reno);
						neusiedl[renov].renovieren();
						System.out.println(neusiedl[renov]);
						break;
			}
			System.out.println("Nomal?");
			nomal = eing.readLine();
			
		}while(nomal.equalsIgnoreCase("ja"));
		
	}
	
	public static void sort(Haus1[] a){
		int tmp1=0;
		int tmp2=0;
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				if(a[i].hausnummer<a[j].hausnummer){
					tmp1=a[i].hausnummer;
					tmp2=a[j].hausnummer;
					a[i].hausnummer=tmp2;
					a[j].hausnummer=tmp1;
				}
			}
		}
	}
	
	
	public static int[] hausn(){
		Random irgendwas = new Random();
		int[] out = new int[100];
		
		for(int i=0; i<out.length;i++){
			int hn = irgendwas.nextInt(1000);
			if(num_exists(out, hn)){
				i--;
				continue;
			}
			else{
				out[i]=hn;
				//System.out.println(i +"   " +out[i] +"   " +hn);
			}
			
		}
		
		return out;
	}
	 public static boolean num_exists(int[] arr, int n){
		 boolean out = false;
		
		 for(int i=0;i<arr.length;i++){
			 if(arr[i]==n){
				 out=true;
				 break;
			 }
		 }
		 
		 return out;
	 }
	
}
class Haus1{
	protected int hausnummer, breite, laenge, hoehe;
	protected boolean fassadeOk;
	
	public Haus1(int ha, int b, int l, int h, boolean f){
		hausnummer = ha;
		breite = b;
		laenge = l;
		hoehe = h;
		fassadeOk = f;
	}
	
	public void renovieren(){
		fassadeOk = true;
	}
	
	public String toString(){
		if(fassadeOk){
			return "Das Haus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist Ok.";
		}
		else{
			return "Das Haus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist renovierungsbedŸrftig.";
		}
	}
	public int getWasBinIch(){
		return 1;
	}
}

class Reihenhaus1 extends Haus1{
	
	public Reihenhaus1(int ha, int b, int l, int h, boolean f){
		super(ha, b, l, h, f);
	}
	
	public String toString(){
		if(fassadeOk){
			return "Das Reihenhaus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist Ok.";
		}
		else{
			return "Das Reihenhaus " +hausnummer +" ist " +breite +"m breit, " +laenge +"m lang, " +hoehe +"m hoch, die Fassade ist renovierungsbedŸrftig.";
		}
	}
	public int getWasBinIch(){
		return 2;
	}
}