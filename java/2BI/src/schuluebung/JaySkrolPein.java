package schuluebung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JaySkrolPein extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	protected JScrollPane sp;
	protected JTextArea ta;
	protected JButton bu1;
	
	public JaySkrolPein(){
		setTitle("Titel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel haha = new JPanel();
		haha.setLayout(new FlowLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		ta = new JTextArea("hasdgkashjdfkjashkdflasdghjflaskhdgkjsdkajfjkhgsdflkjsdhjhgfsaluufjhsavdglsjkdhgfs", 10, 10);
		sp = new JScrollPane(ta);
		bu1 = new JButton("hallo");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		haha.add(sp);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(haha, BorderLayout.CENTER);
		cp.add(bu1, BorderLayout.NORTH);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JaySkrolPein();
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}

}
