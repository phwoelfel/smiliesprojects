package allerhand;

public class bin_dez {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		int durch=2;
		int x=0;
		double erg=0;
		
		for(int i=1;i<=500;i++){
			x++;
			if(x==1 || x==2){
				erg += 1/Math.pow(2, i);
				System.out.println(erg);
				System.out.println("haha");
				System.out.println(i);
			}
			if(x==4){
				x=0;
			}
		}
		System.out.println(erg);
		*/
		
		System.out.println(dezbin(25));
	}
	
	public static String dezbin(int dez){
		String out="";
		int z = dez;	
		do{
			out = (z%2)+out;
			z /= 2;
		}while(z!=0);
		return out;
	}

}
