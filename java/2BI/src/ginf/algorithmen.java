package ginf;

public class algorithmen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Summe aller Zahlen bis n
		int n=100;
		int summe=0;
		
		//linear
		for(int i=0;i<=n;i++){
			summe+=i;
		}
		System.out.println(summe +"\n");
		
		//Gau§sche Zahlensumme
		summe=0;
		summe=(n*(n+1))/2;
		System.out.println(summe +"\n");
		
		//Rekursion
		summe=0;
		summe=sum(n);
		System.out.println(summe +"\n");
	}
	
	public static int sum(int n){
		if(n==0)return 0;
		if(n==1)return 1;
		
		return n+sum(n-1);
	}

}
