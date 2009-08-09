package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MoreButtonsCmd2 extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;
    
    private final String PLUS  = "plus";
    private final String MINUS = "minus";
    private final String PLUS2  = "plus2";
    private final String MINUS2 = "minus2";

	private JLabel countLabel = new JLabel("Die Zahl = 0");
	private int count = 0;


    void init() {
        setTitle("Compare command string");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 120);

   	 	JPanel bereich1 = new JPanel();
   	 	bereich1.add(countLabel);
    	 	
   	 	JButton button1 = new JButton("Zahl erhöhen");
   	 	JButton button2 = new JButton("Zahl vermindern");
   	 	JButton button3 = new JButton("Zahl um 2 erhöhen");
	 	JButton button4 = new JButton("Zahl um 2 vermindern");
        
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(button1);
        bereich2.add(button2);
        bereich2.add(button3);
        bereich2.add(button4);
        
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
   	 	windowFlaeche.add(bereich2, BorderLayout.CENTER);

   	 	button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        
        button1.setActionCommand(PLUS);
   	 	button2.setActionCommand(MINUS);
   	 	button3.setActionCommand(PLUS2);
	 	button4.setActionCommand(MINUS2);
     	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if      (cmd.equals(PLUS))  count++;
        else if (cmd.equals(MINUS)) count--;
        else if (cmd.equals(PLUS2)) count+=2;
        else if (cmd.equals(MINUS2)) count-=2;
        else return;
		countLabel.setText("Die Zahl = " + count);
	}

    public static void main(String[] args) {
    	new MoreButtonsCmd2().init();
    }
}

