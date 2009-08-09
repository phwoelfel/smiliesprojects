package allerhand;

public class Stringsplitting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String myqot = "Alcohol is a very necessary article ... It makes life bearable to millions of people who could not endure their existence if they were quite sober. It enables Parliament to do things at eleven at night that no sane person would do at eleven in the morning.";
		int a = 0;
		if(myqot.length()>79){
			while(a<80){
				System.out.println("anf" +a);
				if(myqot.indexOf(" ", a)<80){
					a=myqot.indexOf(" ", a+1);
					System.out.println("if" +a);
				}
				else{
					System.out.println("else" +a);
					break;
				}
			}
		}
		String anf = myqot.substring(0, a);
		String end = myqot.substring(a);
		System.out.println(myqot);
		System.out.println(a);
		System.out.println(anf);
		System.out.println(end);
		myqot = anf +"\n" +end;
	}

}
