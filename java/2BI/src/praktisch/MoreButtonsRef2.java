package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MoreButtonsRef2 extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;

	private JLabel countLabel = new JLabel("Die Zahl = 0");
	private int count = 0;
    private JButton button1, button2, button3, button4;
    

    void init() {
        setTitle("Compare references");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 120);

   	 	JPanel bereich1 = new JPanel();
   	 	bereich1.add(countLabel);
          
   	 	button1 = new JButton("Zahl erhöhen");
   	 	button2 = new JButton("Zahl vermindern");
   	 	button3 = new JButton("Zahl um 2 erhöhen");
	 	button4 = new JButton("Zahl um 2 vermindern");
        
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
   	 	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if      (obj == button1) count++;
        else if (obj == button2) count--;
        else if (obj == button3) count+=2;
        else if (obj == button4) count-=2;
        else return;
		countLabel.setText("Die Zahl = " + count);
	}

    public static void main(String[] args) {
    	new MoreButtonsRef2().init();
    }
}

