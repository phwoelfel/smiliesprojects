package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionAnonymClass2 extends JFrame {
    static final long serialVersionUID = 0L;

	private JLabel countLabel = new JLabel("Anzahl Klicks links = 0");
	private JLabel countLabel2 = new JLabel("Anzahl Klicks rechts = 0");
    private int count = 0;
    private int count2 = 0;


    void init() {
        setTitle("ActionListener in anonym class");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 120);

   	 	JPanel bereich1 = new JPanel();
   	 	bereich1.add(countLabel);
   	 	bereich1.add(countLabel2);
  
   	 	JPanel bereich2 = new JPanel();
   	 	JButton button  = new JButton("Klicks zählen links");
   	 	JButton button2  = new JButton("Klicks zählen rechts");
   	 	bereich2.add(button);
   	 	bereich2.add(button2);
        
   	 	Container windowFlaeche = getContentPane();
   	 	windowFlaeche.add(bereich1, BorderLayout.NORTH);
   	 	windowFlaeche.add(bereich2, BorderLayout.CENTER);

   	 	button.addActionListener(new ActionListener() {
   	 	    public void actionPerformed(ActionEvent e) {
   	 	        countLabel.setText("Anzahl Klicks links= " + ++count);
   	 	    }
   	 	});
   	 	button2.addActionListener(new ActionListener() {
	 	    public void actionPerformed(ActionEvent e) {
	 	        countLabel2.setText("Anzahl Klicks rechts= " + ++count2);
	 	    }
	 	});
     	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new ActionAnonymClass2().init();
    }
}



