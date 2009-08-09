package tests;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Fun3 extends JFrame implements ListSelectionListener {
	static final long serialVersionUID = 8450847887550371560L;
	private static final String[] listItems = {"Fun1", "Fun2"};
	private JList dataList = new JList(listItems);
	public Fun3() {
		setUndecorated(true);
		setBounds(0, 0, 250, 100);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fenster = getSize();
		setLocation((screen.width-fenster.width) / 2, (screen.height-fenster.height) / 2);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel unten = new JPanel();
		unten.setLayout(new FlowLayout());
		
		JPanel oben = new JPanel();
		oben.setLayout(new FlowLayout());
		//Container Ende
		
		
		
		//Initialisierung der Inhalte Anfang
		JLabel text = new JLabel("Waehlen sie eine Applikation aus");
		
		dataList.setVisibleRowCount(2);
		//Initialisierung der Inhalte Ende

		
		//Listener zu den Inhalten hinzufuegen Anfang
		dataList.addListSelectionListener(this);
		//Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		oben.add(text);
		unten.add(dataList);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(oben, BorderLayout.NORTH);
		cp.add(unten, BorderLayout.CENTER);
		//JPanel zum Container hinzufuegen Ende


		setVisible(true);
	}

	

	private void openApplication(String prog) {
		closeSplashScreen();
		if(prog.equals("Fun1")){
			new Fun1();
		}
		if(prog.equals("Fun2")){
			new Fun2_visible();
		}
	}

	private void closeSplashScreen() {
		setVisible(false);
		dispose();
	}

	public static void main(String[] args) {
		new Fun3();
	}



	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			String inhalt = (String)dataList.getSelectedValue();
			//System.out.println(inhalt);
			
			openApplication(inhalt);
        }
	}
}
