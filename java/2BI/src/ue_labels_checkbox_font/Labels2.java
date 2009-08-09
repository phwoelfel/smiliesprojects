package ue_labels_checkbox_font;

import java.awt.*;
import javax.swing.*;

public class Labels2 extends JFrame {
    static final long serialVersionUID = 0L;
    
    private static final ImageIcon one = new ImageIcon("one.gif");
    private static final ImageIcon two = new ImageIcon("two.gif");
    private static final ImageIcon three = new ImageIcon("three.gif");
    private static final ImageIcon four = new ImageIcon("four.gif");
    public Labels2() {
        setTitle("More about labels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(300, 120);
        
        JPanel links = new JPanel();
        links.setLayout(new GridLayout(2, 0));
        
        JPanel rechts = new JPanel();
        rechts.setLayout(new GridLayout(2, 0));
        
        JLabel lab1 = new JLabel("eins", one, JLabel.LEFT);
        JLabel lab2 = new JLabel("zwei", two, JLabel.LEFT);
        JLabel lab3 = new JLabel("drei", three, JLabel.RIGHT);
        lab3.setHorizontalTextPosition(JLabel.LEFT);
        JLabel lab4 = new JLabel("drei", four, JLabel.RIGHT);
        lab4.setHorizontalTextPosition(JLabel.LEFT);
        
        Container cp = getContentPane();
        links.add(lab1);
        links.add(lab2);
        rechts.add(lab3);
        rechts.add(lab4);
        cp.add(links, BorderLayout.WEST);
        cp.add(rechts, BorderLayout.EAST);
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new Labels2();
    }
}

