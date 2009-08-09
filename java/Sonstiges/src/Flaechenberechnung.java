import java.util.Scanner;

public class Flaechenberechnung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a = 0.0;
		double b = 0.0;
		double c = 0.0;
		double h = 0.0;
		double erg= 0.0;
		System.out.println("1 = Dreieck,\n\r2 = Rechteck, \n\r3 = Kreis");
		Scanner in = new Scanner(System.in);
		int eing = in.nextInt();
			if (eing == 1) {
				System.out.println("Gib die c-Seite ein!");
				c = in.nextDouble();
				System.out.println("Gib die Höhe auf c ein!");
				h = in.nextDouble();
				erg= (c*h)/2;
				System.out.println("Das Ergebnis ist "+erg);
			}else if( eing == 2){
				System.out.println("Gib die a-Seite ein!");
				a = in.nextDouble();
				System.out.println("Gib die b-Seite ein!");
				b = in.nextDouble();
				erg= a*b;
				System.out.println("Das Ergebnis ist " +erg);
			}else if (eing == 3){
				System.out.println("Gib den Radius ein!");
				a= in.nextDouble();
				erg = a*a*3.14159265;
			}
	}
}
