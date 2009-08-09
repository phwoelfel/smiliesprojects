package ip_rechner;

import java.util.Scanner;


public class SNM_Umrechner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String go = "";
		do{
			System.out.println("SNM oder Kurzform eingeben: ");
			String ips = in.next();
			
			try {
				IP snm = new IP(ips);
				System.out.println("Kurzform: " +snm.getOnes());
			} catch (Exception e) {
				int[]ar = new int[32];
				for(int a = 0;a<Integer.parseInt(ips);a++){
					ar[a]=1;
				}
				int[][] bin = new int[4][8];
				int z=0;
				for(int i=0;i<bin.length;i++){
					for(int j=0;j<bin[i].length;j++){
						bin[i][j]=ar[z];
						z++;
					}
				}
				IP snm2 = null;
				try {
					snm2 = new IP(bin);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println(snm2);
			}
			System.out.println("Nomal? y/n");
			go = in.next();
		}while(!go.equals("n"));
	}

}
