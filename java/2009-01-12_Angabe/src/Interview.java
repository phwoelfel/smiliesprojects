import java.io.*;
import java.net.Socket;


/**
 * Lässt die Fragen eines {@link Fragebogen}s von einem Client beantworten und
 * gibt den Fragebogen an den {@link Server} zurück (auch wenn der Fragebogen
 * nicht vollständig ausgefüllt wurde).
 */
public class Interview extends Thread {

	/** Clientverbindung, die bedient wird */
	private final Socket client;

	/** Server, der alle Clientverbindungen verwaltet */
	private final Server server;

	/** Sende-Stream zum Client */
	private PrintWriter sender;

	/** Empfangs-Stream vom Client */
	private BufferedReader empfaenger;

	
	/**
	 * Neuen Client-Thread erzeugen und diesen starten
	 * 
	 * @param client die Clientverbindung, die von diesem Thread zu 
	 * 	bedienen ist
	 */
	public Interview(Server server, Socket client) {
		this.server = server;
		this.client = client;
		start();
	}


	/**
	 * Führt das Interview in folgenden Schritten durch:
	 * 1. Erzeugt einen neuen, leeren Fragebogen.
	 * 2. Meldet an den Server, dass das Interview begonnen hat.
	 * 3. Begrüßt den Client und läßt ihn der Reihe nach die Fragen beantworten.
	 * 4. Meldet an den Server, dass das Interview beendet ist und übergibt ihm
	 *    den Fragenbogen -- auch wenn es einen Fehler gegeben hat und der 
	 *    Fragebogen unvollständig beantwortet ist!
	 * 5. Räumt die Clientverbindung wieder auf.
	 */
	public void run() {
		// Neuer Fragebogen
		Fragebogen fragebogen = new Fragebogen();

		// An den Server melden, dass das Interview begonnen hat
		server.interviewBegonnen();
		
		try {
			// Keep-alive
			client.setKeepAlive(true);
			
			// Empfangen von der Clientverbindung
			empfaenger = new BufferedReader(
				new InputStreamReader(client.getInputStream()));

			// Senden an die Clientverbindung (mit Auto-Flush und der richtigen
			// Zeichencodierung für den Windows telnet-Client)
			sender = new PrintWriter(
				new OutputStreamWriter(client.getOutputStream(), "Cp437"), true);

			// TODO (5 Punkte) Den Interviewablauf hier so implementieren wie
			// in der Dokumentation beschrieben
			
			sender.println("Willkommen bei der Meinungsumfrage");
			sender.println("Bitte geben sie bei jeder Frae an ob sie zustimmen oder nicht");
			
			String eing ="";
			for(String frage:fragebogen){
				do{
					sender.print(frage +" (j/n):");
					sender.flush();
					
					eing = empfaenger.readLine();
					
					if(eing.equals("j")){
						fragebogen.setAntwort(frage, true);
						break;
					}
					else if(eing.equals("n")){
						fragebogen.setAntwort(frage, false);
						break;
					}
					else{
						sender.println("Bitte mit j oder n antworten!");
					}
					
				}while(!eing.equals("j") || !eing.equals("n"));
			}
			
			sender.println("Danke fuer die Teilnahme"); 
			
			// ...
			
		} catch (Exception ex) {
			// Hier keine Fehlerbehandlung nötig!
			
		} finally {
			// TODO (3 Punkte) Hier alles durchführen, was am Ende eines 
			// Interviews auf jeden Fall (auch nach Exceptions) gemacht
			// werden muss.
			try {
				server.interviewBeendet(fragebogen);
				sender.close();
				empfaenger.close();
				client.close();
			} catch (IOException e) {
				
			}
			// ...
		}
	}

}
