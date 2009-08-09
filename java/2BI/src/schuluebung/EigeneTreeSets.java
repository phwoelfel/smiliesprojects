package schuluebung;

import java.util.TreeSet;

public class EigeneTreeSets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeSet<Auto> at = new TreeSet<Auto>();
		at.add(new Auto("BMW", "320", 100));
		at.add(new Auto("VW", "Golf", 170));
		at.add(new Auto("BMW", "350", 150));
		at.add(new Auto("VW", "Golf", 50));
		
		Auto[] ar = at.toArray(new Auto[3]);
		
		for(int i=0;i<ar.length;i++){
			System.out.println(ar[i]);
		}
	}

}

class Auto implements Comparable<Auto>{
	public String marke;
	public String type;
	public int leistung;

	public Auto(String m, String t, int l){
		marke=m;
		type=t;
		leistung=l;
	}

	public int compareTo(Auto o) {
		int result = marke.compareTo(o.marke);
		if(result!=0){
			return result;
		}
		
		result = type.compareTo(o.type);
		if(result!=0){
			return result;
		}
		
		result =leistung-o.leistung;
		
		return result;
	}
	
	public String toString(){
		return "Marke: " +marke +", Typ: " +type +", Leistung: " +leistung;
	}
}