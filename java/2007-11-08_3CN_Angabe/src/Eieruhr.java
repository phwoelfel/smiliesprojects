import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;




/**
 * Ein Eieruhr-Server für mehrere Clients. Siehe Datei liesmichzuerst.html
 * 
 * @author F. Kasper
 */
public class Eieruhr extends Thread {

	/** Port, auf dem der Server Verbindungen entgegennimmt */
	private static final int SERVER_PORT = 5000; //Port auf 5000 geaendert, da 23 unter Mac OS X nicht erlaubt
	
	/** Zeitlicher Abstand zwischen zwei Punkten (in sec) an der Client-Konsole */
	private static final int TICK_INTERVALL = 1;
	
	/** Zeitlicher Abstand zwischen zwei Läutsignalen (in sec) an der Client-Konsole */
	private static final int RING_INTERVALL = 2;
	
	/** Verbindung zum Client, der von diesem Thread bedient wird */
	private final Socket client;

	
	/**
	 * Erzeugt einen neuen Thread, der den Client an der angegebenen Verbindung
	 * bedient.
	 * 
	 * @param client
	 * 	Clientverbindung
	 */
	public Eieruhr(Socket client) {
		this.client = client;
	}


	/**
	 * Steuert den Ablauf der Eieruhr eines Clients. Für jeden Client läuft
	 * diese Methode unabhängig von allen anderen Clients ab.
	 */
	@Override
	public void run() {

		// TODO (12 Punkte) Implementiere diese Methode. Die Beispiele der
		// letzten Unterrichtsstunden eignen sich als Ausgangspunkt...
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
			
			String zeit="";
			Scanner sc=null;
			
			//Eingabe der Kochzeit
			do{
				out.write("Kochzeit in Sekunden: ");
				out.flush();
				zeit = in.readLine();
				sc = new Scanner(zeit);
				if(!sc.hasNextInt()){
					out.write("Ungueltige Eingabe, bitte nochmal!" +System.getProperty("line.separator"));
					out.flush();
				}
			}
			while(!sc.hasNextInt());
			
			//Log: Kochzeit gestartet
			System.out.println("Client mit der IP " +client.getInetAddress().getHostName() +"(" +client.getInetAddress().getHostAddress() +") hat mit Kochzeit begonnen(" +zeit +" sek).");
			
			//Punkte ausgeben
			zeit = zeit.trim(); //gibt sonst Probleme bei "123 " und aehnliche
			int time = Integer.parseInt(zeit);
			for(int i=0;i<time;i++){
				sleep(TICK_INTERVALL*1000);
				out.write(".");
				out.flush();
			}
			
			//Log: Kochzeit beendet
			System.out.println("Client mit der IP " +client.getInetAddress().getHostName() +"(" +client.getInetAddress().getHostAddress()  +") hat Kochzeit beendet");
			
			out.write(System.getProperty("line.separator"));
			out.flush();
			
			//RRIINNGGG ausgabe:
			do{
				out.write("RRRIIIINNNNGGG!!!!\u0007" +System.getProperty("line.separator"));
				out.flush();
				sleep(RING_INTERVALL*1000);
			}
			while(client.getInputStream().available()==0);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			if ( client != null ){
				try {
					String ip = client.getInetAddress().getHostName() +"(" +client.getInetAddress().getHostAddress() +")";
					client.close();
					System.out.println("Verbindung mit Client mit der IP " +ip +" beendet!");
				} catch ( IOException e ) { System.out.println("Fehler beim disconnect: " +e); }
			}
			if( out != null){
				out.close();
			}
			if( in != null){
				try { in.close(); } catch (IOException e) {	System.out.println("Fehler beim disconnect(InputStream): " +e);}
			}
		}
	}


	/**
	 * Startet den Eieruhr-Server.
	 */
	public static void main(String[] args) {

		// TODO (12 Punkte) Implementiere diese Methode. Die Beispiele der
		// letzten Unterrichtsstunden eignen sich als Ausgangspunkt...
		Socket sock = null;
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(SERVER_PORT);
			System.out.println("Server gestartet auf Port " +SERVER_PORT);
			do{
				sock = server.accept();
				Eieruhr clh = new Eieruhr(sock);
				clh.start();
				System.out.println("Verbunden mit " +sock.getInetAddress().getHostName() +"(" +sock.getInetAddress().getHostAddress() +")");
			}while(true);
		} catch (IOException e) {
			System.err.println("Fehler beim erstellen des Servers auf Port " +SERVER_PORT);
			e.printStackTrace();
		}
		finally {
			if ( server != null ){
				try { server.close(); } catch ( IOException e ) { System.out.println(e); }
			}
		}
	}
}
