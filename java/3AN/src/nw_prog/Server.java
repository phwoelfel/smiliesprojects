package nw_prog;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;

public class Server implements Runnable {
	private ServerSocket socket;
	private int port;
	private HashSet<ClientHandle> clients;
	
	public static void main(String[] args) {
		Server a = new Server(5000);
	}
	
	public Server(int port) {
		this.port = port;							// Port setzen
		this.clients = new HashSet<ClientHandle>();	// Array mit client initalisieren
		
		try {
			this.socket = new ServerSocket(this.port);	// server erstelen
			System.out.println("Server auf Port " +this.port +" gestartet!");
			
			// Akzeptierungsthread starten
			new Thread(this).start();
			// auch im try-catch block.. weil ohne server, kein hear thread ^^
		}
		catch(IOException e) {
			System.out.println("Cannot create server. Reason: " + e.getMessage());
		}
	}

	public void run() {
		// Wartet darauf, dass Clients verbinden.
		// Wichtig: Der try-catch Block muss innerhalb der Schleife liegen.
		// Sonst, sollte ein Client nicht verbinden können, können nie wieder Clients
		// verbinden und dadurch kackt der Server ab.
		// Wenn der try-catch Block innerhalb liegt dann läuft die Schleife weiter 
		// auch wenn ein Client verbinden konnte.
		while(true) {
			try {
				new ClientHandle(this, this.socket.accept());
			}
			catch(IOException e) {
				System.out.println("Client connection failed. Reason: " + e.getMessage());
			}
		}
		
		// Server schliessen
	}
	
	// fügt einen client hinzu
	public void AddClient(ClientHandle handle) {
		this.clients.add(handle);
	}
	
	// löscht ihn wieder raus
	public void RemoveClient(ClientHandle handle) {
		this.clients.remove(handle);
	}
	
	// kontrolliert ob der nickname schon vergeben ist.
	// da nur der nickname einzigartig ist, kann man sich auf den beschränken.
	// sonst würde man das mi dem ganzen ClientHandle objekt machen
	public boolean Contains(String nickname) {
		for(ClientHandle temp : this.clients) {
			if(temp.GetNickname().equals(nickname))
				return true;
		}
		return false;
	}
	
	// sendet an alle
	public void Broadcast(ClientHandle handle, String msg) {
		// Einfach alle Clients durchgehen und allen schicken.
		for(ClientHandle temp : this.clients) {
			// Den Sender lassen wir aus.
			// Indem sowiso der Nickname einzigartig ist, ersparen wir uns die 
			// CompareTo Methode.
			// wenn man dem Sender die nachricht auch schicken will (z.b. als Rück-
			// koppelung, dass das senden ordnungsgemäß funktioniert, einfach die
			// IF abfrage weglassen.
			if(!handle.GetNickname().equals(temp.GetNickname())) {
				temp.SendMsg(handle.GetNickname() + ": " + msg);
			}
		}
	}
}
