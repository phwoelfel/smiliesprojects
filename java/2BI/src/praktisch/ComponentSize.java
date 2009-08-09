package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComponentSize extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;
    
    private static final String PLUS  = "plus";
    private static final String MINUS = "minus";
    private Container windowFlaeche;
    private JButton button;
    private JButton buttona;

 
    void init() {
        setTitle("Change component size");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(260, 200);

        JPanel bereich1 = new JPanel();
   	 	button = new JButton("Schaltfläche1");
   	 	bereich1.add(button);
    	 	
   	 	JButton button1 = new JButton("Vergrößern");
   	 	JButton button2 = new JButton("Verkleinern");
        
        // set equal size for both buttons
        button1.setPreferredSize(button2.getPreferredSize());
        
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(button1);
        bereich2.add(button2);
        
        buttona = new JButton("Schaltfläche2");
        
        JPanel p = new JPanel();
        p.add(buttona);
        
        windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
      //windowFlaeche.add(button, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);
        windowFlaeche.add(p, BorderLayout.SOUTH);
 
   	 	button1.addActionListener(this);
   	 	button2.addActionListener(this);
        
        button1.setActionCommand(PLUS);
  	 	button2.setActionCommand(MINUS);
        
        setResizable(false);
      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        Dimension d = button.getPreferredSize(); // get width and height
        Dimension d2 = buttona.getPreferredSize();
        
        if (cmd.equals(PLUS)) {
            d.width += 10; d.height += 10;
            d2.width += 5; d2.height += 5;// increase both
        }
        else if (cmd.equals(MINUS)) {
            d.width -= 10; d.height -= 10;
            d2.width -= 5; d2.height -= 5;// decrease both
        }
  
        windowFlaeche.invalidate(); // destroy current layout
        button.setPreferredSize(d); // set new button size
        buttona.setPreferredSize(d2);
        windowFlaeche.validate();   // build new layout
	}

    public static void main(String[] args) {
    	new ComponentSize().init();
    }
}

