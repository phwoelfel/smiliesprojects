package ue_labels_checkbox_font;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBoxTest2 extends JFrame implements ItemListener {
    static final long serialVersionUID = 0L;
    
    private String fontName;
    private int    fontSize;
    
    ImageIcon grau = new ImageIcon("lilie-grey.gif");
    ImageIcon farbe = new ImageIcon("lilie-colored.gif");
    
    private JLabel label = new JLabel(farbe);
    private JCheckBox chBox = new JCheckBox("bunt", true);

    public CheckBoxTest2() {
        setTitle("JCheckBox");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(400, 200);

        JPanel bereich1 = new JPanel();
        bereich1.add(label);

        JPanel bereich2 = new JPanel();
        bereich2.add(chBox);

        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        chBox.addItemListener(this);
        fontName = label.getFont().getName();
        fontSize = label.getFont().getSize();

        setResizable(true);
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() != chBox) return;
        
        if (e.getStateChange() == ItemEvent.SELECTED) { // checked
            label.setIcon(farbe);
        }
        else {                                          // not checked
            label.setIcon(grau);
        }
    }

    public static void main(String[] args) {
    	new CheckBoxTest2();
    }
}

