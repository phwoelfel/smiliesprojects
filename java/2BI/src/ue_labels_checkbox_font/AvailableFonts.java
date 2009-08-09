package ue_labels_checkbox_font;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AvailableFonts extends JFrame implements ItemListener {
    static final long serialVersionUID = 0L;

    private JLabel label = new JLabel("");
    private JComboBox comBox;

    public AvailableFonts() {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        comBox = new JComboBox(fontNames);

        label.setText(fontNames[0]);
        Font font = label.getFont();
        label.setFont(new Font(fontNames[0], font.getStyle(), font.getSize() + 2));

        setTitle("All available fonts");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 200);

        JPanel bereich1 = new JPanel();
        bereich1.add(label);

   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(comBox);

   	 	Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        comBox.addItemListener(this);

        setResizable(true);
      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comBox) {
            String fontName = (String)comBox.getSelectedItem();
            label.setText(fontName);
            Font font = label.getFont();
            label.setFont(new Font(fontName, font.getStyle(), font.getSize()));
        }
    }

    public static void main(String[] args) {
    	new AvailableFonts();
    }
}

