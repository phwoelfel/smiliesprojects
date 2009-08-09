package ue_labels_checkbox_font;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComboBoxTest2 extends JFrame implements ItemListener {
    static final long serialVersionUID = 0L;
    
    private static String[] fontItems = {"Serif", "SansSerif", "MonoSpaced", "Dialog", "DialogInput"};
    private JComboBox fontComboBox = new JComboBox(fontItems);
    private JLabel displayLabel = new JLabel(fontItems[0]);
    
    private static String[] colors = {"Rot","Gruen", "Blau", "Gelb"};
    private JComboBox colorCombo = new JComboBox(colors);
    
    public ComboBoxTest2() {
        setTitle("JComboBox");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 200);
       
        JPanel bereich1 = new JPanel();
        bereich1.add(displayLabel);
  
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(fontComboBox);
   	 	bereich2.add(colorCombo);
        
   	 	Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);
        
        fontComboBox.addItemListener(this);
        colorCombo.addItemListener(this);
        	
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
            displayLabel.setFont(new Font((String)fontComboBox.getSelectedItem(), Font.PLAIN, 12));
        }
        if(e.getSource() == colorCombo){
        		if(((String)(colorCombo.getSelectedItem())).equals("Blau")){
        			displayLabel.setForeground(Color.BLUE);
        		}
        		if(((String)(colorCombo.getSelectedItem())).equals("Gruen")){
        			displayLabel.setForeground(Color.GREEN);
        		}
        		if(((String)(colorCombo.getSelectedItem())).equals("Rot")){
        			displayLabel.setForeground(Color.RED);
        		}
        		if(((String)(colorCombo.getSelectedItem())).equals("Gelb")){
        			displayLabel.setForeground(Color.YELLOW);
        		}
        }
    }
 
    public static void main(String[] args) {
    	new ComboBoxTest2();
    }
}

