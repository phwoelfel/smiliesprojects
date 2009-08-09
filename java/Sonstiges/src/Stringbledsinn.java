
public class Stringbledsinn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "awelt";
		String b = "hallo";
		
		System.out.println(a.compareTo(b));
		
		if(a.compareTo(b)<0){
			System.out.println(a +", " +b);
		}
		else{
			System.out.println(b +", " +a);
		}
	}

}
