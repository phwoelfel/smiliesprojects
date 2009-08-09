package nw_prog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ClientHandle implements Runnable {
	private Socket socket;			// Verbindung zum Client
	private Server server;			// Server
	private BufferedWriter out;		// Zum senden
	private BufferedReader in;		// Zum empfangen
	private boolean isLoggedIn;		// Ist gesetzt (auf true) wenn der Client eingelogged, eine gültigen Nickname, hat.
	private String Nickname;		// Nickname des Users
	
	// Wird aufgerufen wenn ein Client verbinden will.
	public ClientHandle(Server server, Socket socket) {
		this.server = server;		
		this.socket = socket;
		this.isLoggedIn = false;		// wird auf false gesetzt, da der user noch keine nickname hat.
		System.out.println("Neue Clientverbindung");
		try {
			// Streams holn
			OutputStream outStream = socket.getOutputStream();		
			InputStream  inStream  = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(inStream,"utf8");
			OutputStreamWriter isw = new OutputStreamWriter(outStream,"utf8");
			out = new BufferedWriter(isw);
			in  = new BufferedReader(isr);
			
			// Solange fragen bis er einen gültigen Nickname hat.
			while(this.isLoggedIn != true) {
				this.SendMsg("Enter your Nickname: ");							// Anfrage senden
				if(!this.server.Contains(this.Nickname = this.in.readLine())) {	// Eingaben speichern und kontrollieren ob der Nickname schon verwendet wird.
					this.server.AddClient(this);								// Wenn alles geklappt hat, client adden
					this.isLoggedIn = true;										// und als einglogged makrieren damit er aus der schleife kann.
				}
			}
			
			// hear thread starten
			new Thread(this).start();
			
			// muss alles im try-catch block stehen. weil wenn er die streams ned bekommen .. darf er logicher nicht nach dem nickname fragen.
		}
		catch(IOException e) {
			System.out.println("Cannot get Streams. Reason: " + e.getMessage());
		}
	}

	// hear thread
	public void run() {
		// Solange der Client connected ist
		while(this.socket.isConnected()) {
			// try-catch block in der schliefe.. somit können fehler beim empfangen passieren ohne, dass der client abschmiert.
			try {
				// nachricht empfangen und an alle weiterleiten
				this.server.Broadcast(this, this.in.readLine());
			}
			catch(IOException e) {
				System.out.println("Cannot send Broadcast. Reason: " + e.getMessage());
			}
		}
		// wenn der client nicht mehr verbunden ist, client entfernen
		this.server.RemoveClient(this);
		
		try {
			// socket schliessen
			this.socket.close();
		}
		catch(IOException e) {
			System.out.println("Cannot close connection. Reason: " + e.getMessage());
		}
	}
	
	// sendet eine nachricht.
	public void SendMsg(String msg) {
		try {
			// schreibt.
			this.out.write(msg);
			// ka was sich das bringt.. aba wird schon passen ^
			this.out.flush();
		}
		catch(IOException e) {
			System.out.println("Cannot send '" + msg + "'. Reason: " + e.getMessage());
		}
	}
	
	// brauch ich für diverse fkt's im Server
	public String GetNickname() {
		return this.Nickname;
	}
}
