package schuluebung;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;


public class HMs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Hash Maps
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("hallo", "hello");
		hm.put("tschüss", "bye");
		hm.put("juhu", "bam oida");
		
		//System.out.println(hm.get("juhu"));
		
		TreeSet<String> ts = new TreeSet<String>(hm.keySet());
		Iterator<String> it = ts.iterator();
		while(it.hasNext()){
			String tmp = (String) it.next();
			System.out.println(tmp.hashCode());
			System.out.println(tmp);
		}
	}

}
