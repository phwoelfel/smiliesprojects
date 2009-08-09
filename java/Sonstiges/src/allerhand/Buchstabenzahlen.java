package allerhand;

public class Buchstabenzahlen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char [] sa ={'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x' ,'y', 'z'};
		int [] ia = new int[26];
		int b=0;

		String txt = "karl und claudia beide verheiratet aber nicht miteinander werden auf einer bergtour von einem schweren schneesturm ueberrascht sie koennen sich gerade noch zu einer huette durchkaempfen dort angekommen bereiten sie sich auf eine uebernachtung vor immerhin gibt es einen kasten voll decken schlafsaecke allerdings nur ein bett als gentleman wei karl natuerlich was sich gehoert und sagt claudia sie schlafen im bett ich nehme den schlafsack gerade hat karl den reiverschluss des schlafsackes zugezogen und die augen geschlossen da toent es aus dem bett karl mir ist kalt karl kriecht aus dem schlafsack nimmt eine decke und breitet sie ueber claudia aus dann mummelt er sich zum zweiten mal in den schlafsack und beginnt ins reich der traeume zu gleiten noch mal ist zu hoeren karl mir ist immer noch kalt das gleiche spiel karl wutzelt sich aus dem schlafsack breitet eine weitere decke ueber claudia und legt sich wieder schlafen kaum hat er die augen geschlossen da sagt sie kaaarl mir ist ja immer noch sooooo kalt dieses mal bleibt karl wo er ist und antwortet claudia ich habe eine idee wir sind hier oben doch ganz allein niemand wird je erfahren was heute nacht passiert wir koennen doch einfach so tun als waeren wir miteinander verheiratet claudia haucht entzueckt oh ja dass waere schoen daraufhin bruellt karl dann halts maul und hol dir deine scheidecke gefaelligst selbst";
		
		for(int i=0;i<txt.length();i++){
			char c = txt.charAt(i);
			b++;
			for(int j=0;j<sa.length;j++){
				if(c==sa[j]){
					ia[j]++;
				}
			}
		}
		
		double asdf=0;
		System.out.println(b);
		for(int i=0;i<ia.length;i++){
			System.out.println(sa[i] +": " +ia[i]);
			double a = (double)ia[i]/(double)1156;
			//a = a*1000;
			//a = Math.round(a);
			//a = a/1000;
			asdf+=a;
			System.out.println(a);
			//System.out.println(sa[i]);
		}
		System.out.println(asdf);
	}

}
