package ue_labels_checkbox_font;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComboBoxTest extends JFrame implements ItemListener {
    static final long serialVersionUID = 0L;
    
    private static String[] fontItems = {"Serif", "SansSerif", "MonoSpaced", "Dialog", "DialogInput"};
    private JComboBox fontComboBox = new JComboBox(fontItems);
    private JLabel displayLabel = new JLabel(fontItems[0]);
    
    public ComboBoxTest() {
        setTitle("JComboBox");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 200);
       
        JPanel bereich1 = new JPanel();
        bereich1.add(displayLabel);
  
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(fontComboBox);
        
   	 	Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);
        
        fontComboBox.addItemListener(this);
 
        setResizable(true);
      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }
    
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == fontComboBox) {
            displayLabel.setText((String)fontComboBox.getSelectedItem());
          //displayLabel.setText((String)e.getItem());
        }
    }
 
    public static void main(String[] args) {
    	new ComboBoxTest();
    }
}

