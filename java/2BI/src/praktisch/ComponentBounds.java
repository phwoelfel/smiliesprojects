package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComponentBounds extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;
    
    private static final String LEFT  = "left";
    private static final String PLUS  = "plus";
    private static final String MINUS = "minus";
    private static final String RIGHT = "right";
    private Container windowFlaeche;
    private JPanel bereich1;
    private JButton button;

    public ComponentBounds() {
        setTitle("Change component bounds");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(500, 200);

        // no layout used, locations must be made explicitly 
        bereich1 = new JPanel();
        bereich1.setLayout(null);                        // null layout
        bereich1.setPreferredSize(new Dimension(0, 36)); // only height
        
   	 	button = new JButton("Schaltfläche");
   	 	button.setBounds(144, 5, 205, 26);       // x, y, width, height
   	 	button.setPreferredSize(new Dimension(205, 26)); // set new size
        
   	 	bereich1.add(button);
   	 	    	   	 	
   	 	JButton button1 = new JButton("Links");
   	 	JButton button2 = new JButton("Vergrößern");
   	 	JButton button3 = new JButton("Verkleinern");
   	 	JButton button4 = new JButton("Rechts");
         
   	 	JPanel bereich2 = new JPanel();
        bereich2.add(button1);
   	 	bereich2.add(button2);
        bereich2.add(button3);
        bereich2.add(button4);
        
        // equal width for all buttons
        Dimension db3 = button3.getPreferredSize();
        button1.setPreferredSize(db3);
        button2.setPreferredSize(db3);
        button4.setPreferredSize(db3);
        
        windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        
        button1.setActionCommand(LEFT);
        button2.setActionCommand(PLUS);
  	 	button3.setActionCommand(MINUS);
        button4.setActionCommand(RIGHT);
        
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
        Dimension db = button.getPreferredSize();   // height = 26, width = 105
        Point p      = button.getLocation();        // x = 193, y = 5
        Dimension dp = bereich1.getPreferredSize(); // width = 0, height = 36
        if (cmd.equals(PLUS)) {
            db.width += 20; db.height += 20; dp.height += 20; 
        }
        else if (cmd.equals(MINUS)) {
            db.width -= 20; db.height -= 20; dp.height -= 20;
        }
        else if (cmd.equals(LEFT)) {
            p.x -= 30;
        }
        else if (cmd.equals(RIGHT)) {
            p.x += 30;
        }

        windowFlaeche.invalidate();
        bereich1.setPreferredSize(dp);
        button.setPreferredSize(db);
        button.setBounds(p.x, p.y, db.width, db.height);
        windowFlaeche.validate();
	}

    public static void main(String[] args) {
    	new ComponentBounds();
    }
}

