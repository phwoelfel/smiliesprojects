import java.util.Scanner;


public class Markus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		//direkt in der Main methode:
		Scanner in = new Scanner(System.in);
		int z=0;
		do{
			System.out.println("eingeben:");
			z = in.nextInt();
		}while(z<10 || z>20);
		
		
		//mit extra methode
		int ein = einlesen("Hey!", 10, 50);
		System.out.println("Eingabe: " +ein);*/
		
		Scanner in = new Scanner(System.in);
		int z=0;
		z=in.nextInt();
		int b = in.nextInt();
		
		System.out.println(z +" "+ b);
		
		char c = (char)z;
		System.out.println(c);
		
		
		for(int i=32; i<=126; i++){
			System.out.print(i +"\t");
			if((i+1)%8==0){
				System.out.print("\n");
			}
		}
	}
	
	public static int einlesen(String nachr, int min, int max){
		Scanner in = new Scanner(System.in);
		int z=0;
		do{
			System.out.println(nachr);
			z = in.nextInt();
		}while(z<min || z>max);
		
		return z;
	}

}
