package praktisch;

import java.awt.*;
import javax.swing.*;

public class BorderLayoutTest2 extends JFrame {
    static final long serialVersionUID = 0L;

    public BorderLayoutTest2() {
        setTitle("Border layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(300, 140);

        JButton northB = new JButton("north");
        JButton centerB = new JButton("center");
        JButton southB = new JButton("south");
        JButton eastB = new JButton("east");
        JButton westB = new JButton("west");
                
        Container windowFlaeche = getContentPane();
        
      //*********************************************************
      // for test comment some next line(s) or change margin size
      //*********************************************************
        
        String ho = JOptionPane.showInputDialog(this, "Horizontaler Abstand", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        String ve = JOptionPane.showInputDialog(this, "Vertikaler Abstand", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        int hor = Integer.parseInt(ho);
        int ver = Integer.parseInt(ve);
        getContentPane().setLayout(new BorderLayout(hor, ver)); // horizontal, vertical gap
        
        String eing = JOptionPane.showInputDialog(this, "Kombination von: ncsew", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        if(eing.indexOf("n") != -1)
        windowFlaeche.add(northB, BorderLayout.NORTH);
        if(eing.indexOf("c") != -1)
        windowFlaeche.add(centerB, BorderLayout.CENTER);
        if(eing.indexOf("s") != -1)
        windowFlaeche.add(southB, BorderLayout.SOUTH);
        if(eing.indexOf("e") != -1)
        windowFlaeche.add(eastB, BorderLayout.EAST);
        if(eing.indexOf("w") != -1)
        windowFlaeche.add(westB, BorderLayout.WEST);
        
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new BorderLayoutTest2();
    }
}

