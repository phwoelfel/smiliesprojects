package praktisch_sinnvoll;

class Cd implements Comparable<Cd>{
	public String interpret;
	public String titel;
	public int jahr;
	
	public Cd(String inter, String tit, int j){
		interpret = inter;
		titel = tit;
		jahr = j;
	}
	
	public int compareTo(Cd c){
		int out;
		
		out = interpret.compareTo(c.interpret);
		if(out!=0){
			return out;
		}
		
		out = jahr-c.jahr;
		if(out!=0){
			return out;
		}
		
		out = titel.compareTo(c.titel);
		
		return out;
	}
	
	public String toString(){
		return interpret +": " +titel +", " +jahr;
	}
}
