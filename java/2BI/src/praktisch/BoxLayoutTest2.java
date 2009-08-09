package praktisch;

import java.awt.*;
import javax.swing.*;

public class BoxLayoutTest2 extends JFrame {
    static final long serialVersionUID = 0L;

    public BoxLayoutTest2() {
        setTitle("Box layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(400, 240); // w, h

        JButton buttonA = new JButton("Play");
        JButton buttonB = new JButton("Rewind");
        JButton buttonC = new JButton("Forward");
        JButton buttonD = new JButton("Pause");
        JButton buttonE = new JButton("Stop");

        Container windowFlaeche = getContentPane();
        
        String ho = JOptionPane.showInputDialog(this, "Horizontaler Abstand", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        String ve = JOptionPane.showInputDialog(this, "Vertikaler Abstand", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        int hor = Integer.parseInt(ho);
        int ver = Integer.parseInt(ve);
        
      //*********************************************************
      // for test comment RigidArea or VerticalGlue or both
      //*********************************************************
        windowFlaeche.setLayout(new BoxLayout(windowFlaeche, BoxLayout.Y_AXIS)); // or Y_AXIS
        windowFlaeche.add(buttonA);
        windowFlaeche.add(buttonB);
        windowFlaeche.add(Box.createRigidArea(new Dimension(hor, ver))); // h, v
        windowFlaeche.add(buttonC);
        windowFlaeche.add(buttonD);
        windowFlaeche.add(Box.createVerticalGlue()); // for Y_AXIS
        //windowFlaeche.add(Box.createHorizontalGlue()); // for X_AXIS
        windowFlaeche.add(buttonE);
        
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new BoxLayoutTest2();
    }
}

