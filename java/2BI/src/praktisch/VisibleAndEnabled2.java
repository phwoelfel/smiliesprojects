package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VisibleAndEnabled2 extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;

    private static final String HIDE    = "hide";
    private static final String DISABLE = "disable";
    private static final ImageIcon greenBall = new ImageIcon("green-rect.gif");
    private static final ImageIcon redBall   = new ImageIcon("red-rect.gif");

    private JButton button;
    JButton button1 = new JButton("mache unsichtbar");
	JButton button2 = new JButton("deaktivieren");

    
    void init() {
        setTitle("Visible and Enabled components");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 120);
        
        button = new JButton("SchaltflŠche", greenBall);
        button.setDisabledIcon(redBall);
        
        JPanel bereich1 = new JPanel();
   	 	bereich1.add(button);
   	 	        
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(button1);
        bereich2.add(button2);
        
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
   	 	windowFlaeche.add(bereich2, BorderLayout.CENTER);
        
        // Übernimm die Größe von button1
   	 	button2.setPreferredSize(button1.getPreferredSize());
     
   	 	button1.addActionListener(this);
   	 	button2.addActionListener(this);
        
        button1.setActionCommand(HIDE);
        button2.setActionCommand(DISABLE);
        
     	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(HIDE)) {
            button.setVisible(!button.isVisible());
            if(button.isVisible()){
            		button1.setText("mache unsichtbar");
            }
            else{
            		button1.setText("mache sichtbar");
            }
        }
        else if (cmd.equals(DISABLE)) {
            button.setEnabled(!button.isEnabled());
            if(button.isEnabled()){
        			button2.setText("deaktivieren");
	        }
	        else{
	        		button2.setText("aktivieren");
	        }
        }
	}

    public static void main(String[] args) {
    	new VisibleAndEnabled2().init();
    }
}

