import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


public class xmltest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader in=null;
		try {
			in = new BufferedReader(new InputStreamReader(xmltest.class.getResourceAsStream("/tmp/simon_daller@hotmail.com (2009-07-10T21.09.48+0200).xml"), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		InputSource src = new InputSource(in);
		DOMParser parser = new DOMParser();
		try {
			parser.parse(src);
			System.out.println(parser);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
