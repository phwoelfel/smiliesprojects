package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionAnotherClass3 extends JFrame {
    static final long serialVersionUID = 0L;
   
    private JLabel countLabel = new JLabel("Anzahl Klicks links = 0");
	private JLabel countLabel2 = new JLabel("Anzahl Klicks rechts = 0");
    private int count = 0;
    private int count2 = 0;
   	JButton button  = new JButton("Klicks z�hlen links");
	JButton button2  = new JButton("Klicks z�hlen rechts");


    void init() {
        setTitle("ActionListener in another class");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 120);

   	 	JPanel bereich1 = new JPanel();
   	 	bereich1.add(countLabel);
   	 	bereich1.add(countLabel2);
 
   	 	JPanel bereich2 = new JPanel();
	 	bereich2.add(button);
	 	bereich2.add(button2);
        
   	 	Container windowFlaeche = getContentPane();
   	 	windowFlaeche.add(bereich1, BorderLayout.NORTH);
   	 	windowFlaeche.add(bereich2, BorderLayout.CENTER);

   	 	button.addActionListener(new MyActionListenerA(this));
   	 	button2.addActionListener(new MyActionListenerA(this));
     	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }
    
    public void updateLabel(ActionEvent e) {
	    	if(e.getSource()==button){
	        countLabel.setText("Anzahl Klicks links = " + ++count);
	    	}
	    	if(e.getSource()==button2){
	    		 countLabel2.setText("Anzahl Klicks rechts = " + ++count2);
	    	}
    }
 

    public static void main(String[] args) {
    		new ActionAnotherClass3().init();
    }
}

class MyActionListenerA implements ActionListener {
    
    private ActionAnotherClass3 actionAC;
       
    public MyActionListenerA(ActionAnotherClass3 aac) {
        actionAC = aac;
    }
    
    public void actionPerformed(ActionEvent e) {
        actionAC.updateLabel(e);
    }
}
