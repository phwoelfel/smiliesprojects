
public class Stringbearbeitung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "Halli ich bins der Markus wohoooo";
		String a = "";
		for(int i=0;i<s.length();i++){
			if(i%2==0){
				a += (s.charAt(i)+"").toUpperCase();
			}
			else{
				a += s.charAt(i);
			}
		}
		System.out.println(a);
		
		String b = "";
		for(int i=s.length()-1;i>=0;i--){
			b += s.charAt(i); 
		}
		System.out.println(b);
		
		int as = 12;
		String sa = as +"";
	}

}
