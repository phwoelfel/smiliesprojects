package Superklassen;

public class Mitarbeiter_schuluebung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		Mitarbeiter[] hakler = new Mitarbeiter[10];
		for(int i=0; i<hakler.length;i++){
			if(i%2==0){
				hakler[i] = new Mitarbeiter("gina" +i);
			}
			else{
				if(i%3==0){
					hakler[i] = new Angestellter("franz" +i);
				}
				else{
					hakler[i] = new Arbeiter("fritz" +i);
				}
			}
		}
		
		for(int i=0; i<hakler.length;i++){
			System.out.println(hakler[i]);
		}
		*/
		Mitarbeiter fritz = new Angestellter("Maier");
		System.out.println(fritz);//Maier ist ein Angestellter
		if(fritz instanceof Arbeiter){
			System.out.println(((Arbeiter)fritz).dienst());
		}
	}

}
class Mitarbeiter{
	public String name;
	public Mitarbeiter(String n){
		name=n;
	}
	public String toString(){
		return name +" ist ein Mitarbeiter.";
	}
}

class Angestellter extends Mitarbeiter{
	public Angestellter(String n){
		super(n);
	}
	public String toString(){
		return name +" ist ein Angestellter.";
	}
	public String dienst(){
		return name +" arbeitet von 8-17 Uhr";
	}

}

class Arbeiter extends Mitarbeiter{
	public Arbeiter(String n){
		super(n);
	}
	public String toString(){
		return name +" ist ein Arbeiter.";
	}
	public String dienst(){
		return name +" arbeitet im Schichtdienst";
	}

}