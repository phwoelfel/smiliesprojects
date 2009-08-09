import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

/*
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("");
			
		} catch (SQLException ex) {
			System.err.println("MySQL Error: " +ex);
			ex.printStackTrace();
		}

 */


/*
 * TODO:
 * 		Anzeige der Inhalte einer Table
 * 
 * 
 * 		Allgemeines SQL Befehl ausfuehren
 * 		Datenbank erstellen
 * 		Datenbank loeschen
 * 		Inhalte einfuegen
 * 		Inhalte bearbeiten
 * 		Inhalte loeschen
 * 		Table loeschen
 * 		Table erstellen
 * 		Table bearbeiten
 */
public class Mysql_admin extends JFrame implements ActionListener, ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	//Datenbankverbindungseinstellungen
	private Connection con;
	private String DBUser = "root"; //Datenbankuser
	private String DBPasswort = "feuerwehr1992"; //Datenbankpasswort
	private String DBHost = "localhost"; //Datenbankhost
	private int DBPort = 3306; //Datenbankport
	private String DBUrl = "jdbc:mysql://" +DBHost +":" +DBPort +"/"; //Datenbankurl
	
	//Allgemeine Variablen
	protected ArrayList<String> dbs = new ArrayList<String>(); //ArrayList fuer Datenbanken
	protected ArrayList<String> cur_tables = new ArrayList<String>(); //ArrayList fuer Tables aktueller Datenbank
	protected String cur_db = ""; //zurzeit ausgewaehlte Datenbank
	protected String cur_table = ""; //zurzeit ausgewaehlte Table
	
	//GUI Elemente
	private Container cp = null;
	private JPanel P_t_cont[] = null;
	private JPanel P_center = new JPanel();
	
	private JList L_dbs = null; //Liste fuer Datenbanken
	private JList L_tables = null; //Liste fuer Tables
	
	private JLabel[][] La_t_cont = null;
	private JTable T_t_cont = new JTable();
	
	public Mysql_admin(){
		setTitle("MySQL Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel P_west = new JPanel();
		P_west.setLayout(new GridLayout(1, 3));
		
		P_center.setLayout(new BoxLayout(P_center, BoxLayout.Y_AXIS));
		//Container Ende
		
		
		//Datenbankverbindung Anfang
		/*
		DBHost = JOptionPane.showInputDialog(this, "Bitte Host eingeben", "Host", JOptionPane.QUESTION_MESSAGE);
		DBPort = Integer.parseInt(JOptionPane.showInputDialog(this, "Bitte Port eingeben", "Port", JOptionPane.QUESTION_MESSAGE));
		DBUser = JOptionPane.showInputDialog(this, "Bitte Username eingeben", "Username", JOptionPane.QUESTION_MESSAGE);
		DBPasswort = JOptionPane.showInputDialog(this, "Bitte Passwort eingeben", "Passwort", JOptionPane.QUESTION_MESSAGE);
		DBUrl = "jdbc:mysql://" +DBHost +":" +DBPort +"/";
		*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DBUrl, DBUser, DBPasswort);
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Connector nicht gefunden!");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("MySQL Error: " +e);
		}
		//Datenbankverbindung Ende
		
		
		//Initialisierung der Inhalte Anfang
		getDatabases();
		
		L_dbs = new JList(dbs.toArray());
		L_tables = new JList();
		//Initialisierung der Inhalte Ende
		
		
		//Listener zu den Inhalten hinzufuegen Anfang
		L_dbs.addListSelectionListener(this);
		L_tables.addListSelectionListener(this);
		//Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		P_west.add(new JScrollPane(L_dbs));
		P_west.add(new JScrollPane(L_tables));
		
		P_center.add(T_t_cont);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(P_west, BorderLayout.WEST);
		cp.add(P_center, BorderLayout.CENTER);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Mysql_admin();
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if(e.getSource()==L_dbs){
				cur_db = (String) L_dbs.getSelectedValue();
				//System.out.println(cur_db);
				try {
					Statement st = con.createStatement();
					st.executeQuery("use " +cur_db);
					getTables();
					L_tables.removeAll();
					L_tables.setListData(cur_tables.toArray());
				} catch (SQLException ex) {
					System.err.println("MySQL Error: " +ex);
					ex.printStackTrace();
				}
			}
			if(e.getSource()==L_tables){
				cur_table = (String) L_tables.getSelectedValue();
				System.out.println(cur_table);
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from " +cur_table);
					
					
					rs.next();
					ResultSetMetaData rsmd = rs.getMetaData();
					int cols = rsmd.getColumnCount();
					rs.previous();
					
					rs.last();
					int rows = rs.getRow();
					rs.first();
					rs.previous();
					
					
					
					La_t_cont = new JLabel[rows+1][cols];
					P_t_cont = new JPanel[rows+1];
					
					
					for(int i=1;i<=cols; i++){
						System.out.print(rsmd.getColumnName(i) +"\t");
						La_t_cont[0][i-1] = new JLabel(rsmd.getColumnName(i));
						
					}
					int r = 1;
					while(rs.next()){
						System.out.println();
						for(int i=1;i<=cols; i++){
							System.out.print(rs.getObject(i) +"\t");
							La_t_cont[r][i-1] = new JLabel(rs.getObject(i) +"");
						}
						r++;
					}
					System.out.println("\nrows: " +rows +"\ncols: " +cols);
					
					for(int i=0;i<La_t_cont.length;i++){
						P_t_cont[i] = new JPanel(new BoxLayout(P_center, BoxLayout.X_AXIS));
						for(int j=0;j<La_t_cont[i].length;j++){
							P_t_cont[i].add(La_t_cont[i][j]);
						}
					}
					
					/*
					 * P_t_cont[0] = new JPanel(new BoxLayout(P_t_cont[0], BoxLayout.X_AXIS));
					 * P_t_cont[0].add(La_t_cont[0][i-1]);
					 * P_t_cont[r] = new JPanel(new BoxLayout(P_t_cont[r], BoxLayout.X_AXIS));
					 * P_t_cont[r].add(La_t_cont[r][i-1]);
					 */
					
					/*
					for(int i=0;i<P_t_cont.length;i++){
						P_center.add(P_t_cont[i]);
					}
					*/
				} catch (SQLException ex) {
					System.err.println("MySQL Error: " +ex);
					ex.printStackTrace();
				}
			}
        }
	}
	
	
	
	protected void getDatabases(){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("show databases");
			
			while(rs.next()){
				dbs.add(rs.getString("Database"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void getTables(){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("show tables");
			cur_tables.removeAll(cur_tables);
			
			while(rs.next()){
				cur_tables.add(rs.getString("Tables_in_" +cur_db));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
