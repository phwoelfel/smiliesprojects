package ip_rechner;


public class printArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public static void Int( int [] ia){
		System.out.print("{");
		for(int i=0;i<ia.length;i++){
			if(i==(ia.length-1)){
				System.out.print(ia[i] +"}\n");
			}
			else{
				System.out.print(ia[i] +", ");
			}
		}
	
	}
	
	public static void Double(double [] ia){
		System.out.print("{");
		for(int i=0;i<ia.length;i++){
			if(i==(ia.length-1)){
				System.out.print(ia[i] +"}\n");
			}
			else{
				System.out.print(ia[i] +", ");
			}
		}
	
	}
	
	public static void Float(float [] ia){
		System.out.print("{");
		for(int i=0;i<ia.length;i++){
			if(i==(ia.length-1)){
				System.out.print(ia[i] +"}\n");
			}
			else{
				System.out.print(ia[i] +", ");
			}
		}
	
	}
	
	public static void Long(long [] ia){
		System.out.print("{");
		for(int i=0;i<ia.length;i++){
			if(i==(ia.length-1)){
				System.out.print(ia[i] +"}\n");
			}
			else{
				System.out.print(ia[i] +", ");
			}
		}
	
	}
	
	public static void Bool(boolean [] ia){
		System.out.print("{");
		for(int i=0;i<ia.length;i++){
			if(i==(ia.length-1)){
				System.out.print(ia[i] +"}\n");
			}
			else{
				System.out.print(ia[i] +", ");
			}
		}
	
	}
	
	public static void String(String [] ia){
		System.out.print("{");
		for(int i=0;i<ia.length;i++){
			if(i==(ia.length-1)){
				System.out.print(ia[i] +"}\n");
			}
			else{
				System.out.print(ia[i] +", ");
			}
		}
	
	}
	
	public static void Char(char [] ia){
		System.out.print("{");
		for(int i=0;i<ia.length;i++){
			if(i==(ia.length-1)){
				System.out.print(ia[i] +"}\n");
			}
			else{
				System.out.print(ia[i] +", ");
			}
		}
	
	}

}
