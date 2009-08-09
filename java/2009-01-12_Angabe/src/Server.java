import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


/**
 * Ein Server, an dem Clients on-line Fragebögen ausfüllen können. Die 
 * Benutzeroberfläche zeigt die jeweils aktuellsten Umfrageergebnisse an.
 */
// TODO (3 Punkte) Verhindere mögliche Datenfehler bei gleichzeitiger Ausführung
// mehrerer Interview-Objekte (bezieht sich auf die ganze Klasse).
public class Server extends JFrame {

	/** Server horcht auf diesem Port */
	private static final int SERVER_PORT = 23;
	
	/** Programmsymbol */
	private static final Image icon = 
		new ImageIcon(Server.class.getResource("group.png")).getImage();

	
	/** Socket, auf dem dieser Server Verbindungen entgegennimmt */
	private final ServerSocket serverSocket;
	
	/** Ordnet jeder Frage des Fragebogens die Anzahl der "Ja"-Antworten zu */
	private final Map<String, Integer> jaAntworten = 
		new LinkedHashMap<String, Integer>();
	
	/** Anzahl der gerade bearbeiteten Fragebögen */
	private int inBearbeitung = 0;

	/** Angezeigte Anzahl der gerade bearbeiteten Fragebögen */
	private final JLabel anzInBearbeitung = new JLabel("0");
	
	/** Anzahl der bereits (vollständig beantworteten Fragebögen */
	private int beantwortet = 0;
	
	/** Angezeigte Anzahl der bereits (vollständig) beantworteten Fragebögen */
	private final JLabel anzBeantwortet = new JLabel("0");
	
	/** Umfrageergebnisse werden hier angezeigt */
	private final JTextArea ergebnisse = new JTextArea();
	
	/** Beendet den Umfrage-Server */
	private final JButton beenden = new JButton("Beenden");
	
	
	/**
	 * Erzeugt eine neue Instanz des Umfrage-Servers, die auf dem angegebenen
	 * Port horcht, baut das Benutzerinterface auf und zeigt es an.
	 * 
	 * @param port
	 *  Port, auf dem dieser Server horchen soll
	 */
	public Server(int port) throws IOException {
		// Noch keine "Ja"-Antworten erhalten
		for (String f : new Fragebogen()) {
			jaAntworten.put(f, 0);
		}
		
		// Server-Socket öffnen
		serverSocket = new ServerSocket(port);

		// TODO (5 Punkte) Benutzeroberfläche erzeugen so wie aus der
		// Dokumentation ersichtlich
		setTitle("Online Meinungsumfrage");
		setIconImage(icon);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel oben = new JPanel();
		oben.setLayout(new GridLayout(1,4));
		
		oben.add(new JLabel("in Bearbeitung: "));
		oben.add(anzInBearbeitung);
		oben.add(new JLabel("beantwortet: "));
		oben.add(anzBeantwortet);
		oben.setBorder(BorderFactory.createTitledBorder("Frageboegen"));
		cp.add(oben, BorderLayout.NORTH);
		
		//ergebnisse.setEnabled(false);
		ergebnisse.setEditable(false);
		JScrollPane jsp = new JScrollPane(ergebnisse);
		jsp.setBorder(BorderFactory.createTitledBorder("Aktuelle Ergebnisse"));
		cp.add(jsp, BorderLayout.CENTER);
		
		JPanel unten = new JPanel();
		unten.setLayout(new FlowLayout(FlowLayout.RIGHT));
		unten.add(beenden);
		cp.add(unten, BorderLayout.SOUTH);
		
		for(String f: new Fragebogen()){
			ergebnisse.append(f +": 0%\n");
		}
			
		
		// ...
		
		// Anwendung beim Schließen des Fenster und durch Button beenden
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		beenden.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// Anwendung beenden
				System.exit(0);
			}
		});

		// Mindestgröße, damit Ergebnisbereich nicht zu klein
		setSize(600, 450);
		setVisible(true);
	}


	/**
	 * Informiert den Server, dass ein weiteres Interview begonnen hat. Damit 
	 * erhöht sich die Anzahl der Fragebögen, die gerade bearbeitet werden, um 
	 * eins, und die Anzeige wird entsprechend aktualisiert.
	 */
	// TODO (3 Punkte) Implementiere diese Methode so wie oben beschrieben.
	public  void interviewBegonnen() {
		inBearbeitung ++;
		anzInBearbeitung.setText(inBearbeitung+"");
		
		// ...

	}
	
	
	/**
	 * Informiert den Server, dass das Interview mit dem angegebenen Fragebogen
	 * beendet ist. Damit verringert sich die Anzahl der Fragebögen, die gerade
	 * bearbeitet werden, um eins, und die Anzeige wird entsprechend 
	 * aktualisiert.
	 * Nur wenn der Fragebogen vollständig beantwortet ist, werden die Antworten 
	 * in den Umfrageergebnissen berücksichtigt, und die Anzeige der 
	 * Umfrageergebnisse wird auch aktualisiert.
	 */
	// TODO (5 Punkte) Implementiere diese Methode so wie oben beschrieben.
	public  void interviewBeendet(Fragebogen fragebogen) {
		inBearbeitung --;
		anzInBearbeitung.setText(inBearbeitung +"");
		
		if(fragebogen.alleBeantwortet()){
			ergebnisse.setText("");
			beantwortet++;
			anzBeantwortet.setText(beantwortet +"");
			for(String f: fragebogen){
				if(fragebogen.getAntwort(f)){
					jaAntworten.put(f, jaAntworten.get(f)+1);
				}
				//System.out.println("Prozent: " +(jaAntworten.get(f)*100)/beantwortet +", ja: " +jaAntworten.get(f) +", beant: " +beantwortet);
				ergebnisse.append(f +": " + (jaAntworten.get(f)*100)/beantwortet +"%\n");
				//10=G 3 = A p=? G=(A*100)/p p=(A*100)/G
			}
		}
		// ...

	}

	
	/**
	 * Nimmt laufend neue Clientverbindungen an und bedient dann jede Verbindung 
	 * mit einer neuen Instanz von {@link Interview}.
	 * IOExceptions müssen von der aufrufenden Methode behandelt werden!
	 */
	void verbindungenAnnehmen() throws IOException {
		try {
			while (true) {
				// Auf Clientverbindung warten und Client in 
				// neuem Thread bedienen
				new Interview(this, serverSocket.accept());
			}

		} finally {
			// Aufräumen
			serverSocket.close();
		}
	}


	/**
	 * Erzeugt und startet eine Instanz des Umfrage-Servers.
	 * 
	 * @param args
	 *  nicht verwendet
	 */
	public static void main(String[] args) {
		// Look & Feel dieser Plattform verwenden
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		try {
			// Server-Instanz erzeugen
			Server server = new Server(SERVER_PORT);
			
			// Verbindungen annehmen 
			server.verbindungenAnnehmen();
			
		} catch (IOException ioEx) {
			// Fehler beim Erzeugen der Server-Instanz
			JOptionPane.showMessageDialog(null, 
					ioEx.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}

}
