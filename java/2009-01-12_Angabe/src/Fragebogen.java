import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * Ein Fragebogen, dessen Fragen man mit "Ja" ({@code true}) oder "Nein" 
 * ({@code false}) beantworten kann. Alle Fragebögen enthalten dieselben Fragen. 
 * Die Fragen werden aus einer Datei-Ressource gelesen.
 */
public class Fragebogen implements Iterable<String> {
	
	/** Relativer Pfad der Datei mit den Fragen für alle Fragebögen */
	private static final String FRAGEN_DATEI = "fragen.txt";
	
	/** Zeichencodierung von {@link #FRAGEN_DATEI} */
	private static final String FRAGEN_CODIERUNG = "UTF-8";
	
	/** Fragen in der Reihenfolge, in der sie gestellt werden */
	private static final List<String> fragen = new ArrayList<String>();
	
	// Fragen aus Datei einlesen
	{
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
				Fragebogen.class.getResourceAsStream(FRAGEN_DATEI), FRAGEN_CODIERUNG));
			
			String frage;
			while ((frage = reader.readLine()) != null) {
				fragen.add(frage);
			}
			reader.close();
			
		} catch (Exception ex) {
			// Als RuntimeException weiterwerfen
			throw new RuntimeException(ex);
		}
	}
	
	/** Fragen mit Antworten oder mit {@code null}, wenn noch keine Antwort */
	private final Map<String, Boolean> fragenUndAntworten;
	
	
	/**
	 * Erzeugt einen neuen Fragebogen mit den Fragen aus {@link #FRAGEN_DATEI}.
	 */
	public Fragebogen() {
		// Fragen, aber noch keine Antworten
		fragenUndAntworten = new LinkedHashMap<String, Boolean>(fragen.size());
		for (String f : fragen) {
			fragenUndAntworten.put(f, null);
		}
	}
	
	
	/**
	 * Ordnet der angegebenen Frage eine bestimmte Antwort zu.
	 * 
	 * @param frage
	 * 	Fragetext, zu dem die Antwort gehört
	 * @param antwort
	 *  Antwort auf diese Frage
	 * @throws NoSuchElementException
	 *  falls diese Frage in diesem Fragebogen nicht existiert
	 */
	public void setAntwort(String frage, boolean antwort) {
		if (! fragenUndAntworten.containsKey(frage)) {
			throw new IllegalArgumentException(
					"Frage nicht gefunden: \"" + frage + "\"");
		}
		
		fragenUndAntworten.put(frage, antwort);
	}
	
	
	/**
	 * Findet heraus, ob die angegebene Frage schon beantwortet wurde.
	 * 
	 * @param frage
	 * 	Fragetext, der untersucht werden soll
	 * @return
	 *  {@code true}, wenn diese Frage schon beantwortet wurde; sonst {@code false}
	 * @throws NoSuchElementException
	 *  falls diese Frage in diesem Fragebogen nicht existiert
	 */
	public boolean istBeantwortet(String frage) {
		return (get(frage) != null);
	}

	
	/**
	 * Findet heraus, ob schon <i>alle</i> Fragen dieses Fragebogens beantwortet
	 * wurden.
	 * 
	 * @return
	 *  {@code true}, schon alle Fragen beantwortet wurden; sonst {@code false}
	 */
	public boolean alleBeantwortet() {
		return !fragenUndAntworten.values().contains(null);
	}
	
	
	/**
	 * Liefert die Antwort auf die angegebene Frage. Diese Frage muss schon
	 * beantwortet worden sein.
	 * 
	 * @param frage
	 * 	Fragetext, der untersucht werden soll
	 * @return
	 *  Antwort auf diese Frage
	 * @throws NoSuchElementException
	 *  falls diese Frage in diesem Fragebogen nicht existiert
	 * @throws IllegalStateException
	 *  falls diese Frage noch nicht beantwortet wurde
	 */
	public boolean getAntwort(String frage) {
		Boolean antwort = get(frage);
		
		if (antwort == null) {
			throw new IllegalStateException(
					"Frage noch nicht beantwortet: \"" + frage + "\"");
		}
		
		return antwort;
	}
	
	
	/**
	 * Liefert die Antwort auf die angegebene Frage, oder {@code null}, wenn die
	 * Frage noch nicht beantwortet wurde.
	 * 
	 * @param frage
	 * 	Fragetext, der untersucht werden soll
	 * @return
	 *  {@link Boolean#TRUE} oder {@link Boolean#FALSE}, falls diese Frage 
	 *  beantwortet wurde; {@code null}, wenn es noch keine Antwort gibt
	 * @throws NoSuchElementException
	 *  falls diese Frage in diesem Fragebogen nicht existiert
	 */
	private Boolean get(String frage) {
		if (! fragenUndAntworten.containsKey(frage)) {
			throw new NoSuchElementException(
					"Frage nicht gefunden: \"" + frage + "\"");
		}
		
		return fragenUndAntworten.get(frage);
	}


	/**
	 * Liefert eine Sammlung der Fragen dieses Fragebogens, in 
	 * derselben Reihenfolge, in der sie in der Datei stehen.
	 */
	public Collection<String> getFragen() {
		return Collections.unmodifiableSet(fragenUndAntworten.keySet());
	}
	
	
	/**
	 * Liefert einen Iterator über die Fragen dieses Fragebogens, in 
	 * derselben Reihenfolge, in der sie in der Datei stehen.
	 */
	
	public Iterator<String> iterator() {
		return fragenUndAntworten.keySet().iterator();
	}
	
}
