package praktisch;

import java.awt.*;
import javax.swing.*;

public class FlowLayoutTest2 extends JFrame {
    static final long serialVersionUID = 0L;

    public FlowLayoutTest2() {
        setTitle("Flow layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(300, 100);
        
        String ein = JOptionPane.showInputDialog(this, "Ausrichtung angeben (R, L, C)", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        if(ein.equals("R"))
        		getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
        if(ein.equals("L"))
        		getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        if(ein.equals("C"))
        		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        else{
        		setLayout(new FlowLayout()); // default is CENTER
        }      
        JButton buttonA = new JButton("Play");
        JButton buttonB = new JButton("Rewind");
        JButton buttonC = new JButton("Forward");
        JButton buttonD = new JButton("Pause");
        JButton buttonE = new JButton("Stop");
        
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(buttonA);
        windowFlaeche.add(buttonB);
        windowFlaeche.add(buttonC);
        windowFlaeche.add(buttonD);
        windowFlaeche.add(buttonE);

        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new FlowLayoutTest2();
    }
}

