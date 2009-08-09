import java.util.*;

public class Taschenrechner_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner tast = new Scanner(System.in);
		
		System.out.print("Rechenzeichen: ");
		String rz = tast.next();
		System.out.print("Zahl 1: ");
		int z1 = tast.nextInt();
		System.out.print("Zahl 2: ");
		int z2 = tast.nextInt();
		
		char crz = rz.charAt(0);
		int erg = 0;
		
		switch(crz){
			case '+': erg = z1+z2;
					break;
					
			case '-': erg = z1-z2;
					break;
			
			case '*': erg = z1*z2;
					break;
			
			case '/': erg = z1/z2;
					break;
			
			default: System.out.println("Ungültiges Rechenzeichen");
		}
		
		System.out.println("Ergebnis: " +erg);
	}

}
