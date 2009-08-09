import java.util.Scanner;


public class Rechner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("1 = Addieren; 2 = Subtrahieren; 3 = Multiplizieren; 4 = Dividieren");
		Scanner op= new Scanner(System.in);
		int eing =op.nextInt();
		double c = 0;
		System.out.println("Gib die erste Zahl ein");
		Scanner in= new Scanner(System.in);
		double a = in.nextInt();
		System.out.println("Gib die zweite Zahl ein");
		double b = in.nextInt();
		if (eing == 1){
			c = a+b;
		}else if (eing == 2) {
			c = a-b;
		}else if (eing == 3) {
			c = a*b;
		}else if (eing == 4){
			c = a/b;
		}else{
			System.out.println("Ungültiger Operator!");
		}
		System.out.println("Ergebnis: " +c);
	}

}
