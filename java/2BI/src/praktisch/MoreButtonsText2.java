package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MoreButtonsText2 extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;
    
    private final String NUM_PLUS  = "Zahl erhöhen";
    private final String NUM_MINUS = "Zahl vermindern";
    private final String NUM_PLUS2  = "Zahl um 2 erhöhen";
    private final String NUM_MINUS2 = "Zahl um 2 vermindern";

	private JLabel countLabel = new JLabel("Die Zahl = 0");
	private int count = 0;


    void init() {
        setTitle("Compare button text");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 120);

   	 	JPanel bereich1 = new JPanel();
   	 	bereich1.add(countLabel);
   	    	 
   	 	JButton button1 = new JButton(NUM_PLUS);
   	 	JButton button2 = new JButton(NUM_MINUS);
   	 	JButton button1_2 = new JButton(NUM_PLUS2);
	 	JButton button2_2 = new JButton(NUM_MINUS2);
        
        JPanel bereich2 = new JPanel();
   	 	bereich2.add(button1);
        bereich2.add(button2);
        bereich2.add(button1_2);
        bereich2.add(button2_2);
        
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
   	 	windowFlaeche.add(bereich2, BorderLayout.CENTER);

   	 	button1.addActionListener(this);
        button2.addActionListener(this);
        button1_2.addActionListener(this);
        button2_2.addActionListener(this);
        // no setActionCommand() used
   	 	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if      (cmd.equals(NUM_PLUS))  count++;
        else if (cmd.equals(NUM_MINUS)) count--;
        else if (cmd.equals(NUM_MINUS2)) count-=2;
        else if (cmd.equals(NUM_PLUS2)) count+=2;
        else return;
		countLabel.setText("Die Zahl = " + count);
	}

    public static void main(String[] args) {
    	new MoreButtonsText2().init();
    }
}

