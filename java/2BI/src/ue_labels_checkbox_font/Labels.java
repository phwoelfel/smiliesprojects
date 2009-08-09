package ue_labels_checkbox_font;

import java.awt.*;
import javax.swing.*;

public class Labels extends JFrame {
    static final long serialVersionUID = 0L;
    
    private static final ImageIcon smile = new ImageIcon("smile.gif");

    public Labels() {
        setTitle("More about labels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(300, 120);
                        
        JLabel lab1 = new JLabel("Label with image left", smile, JLabel.LEFT);
        JLabel lab2 = new JLabel("Label with image right", smile, JLabel.RIGHT);
        lab2.setHorizontalTextPosition(JLabel.LEFT);
        JLabel lab3 = new JLabel("Label with tool tip", JLabel.CENTER);
        lab3.setToolTipText("This label has no image");
        
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(lab1, BorderLayout.NORTH);
        windowFlaeche.add(lab2, BorderLayout.CENTER);
        windowFlaeche.add(lab3, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new Labels();
    }
}

