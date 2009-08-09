package ue_labels_checkbox_font;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ButtonGroupTest2 extends JFrame implements ItemListener {
    static final long serialVersionUID = 0L;
    

    private JLabel displayLabel = new JLabel("1 0 0 0 1 1");
    
    JToggleButton[] arr = new JToggleButton[6];

    
    void init() {
        setTitle("ButtonGroup");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(500, 100);

        JPanel bereich1 = new JPanel();
        bereich1.add(displayLabel);
        
        arr[0] = new JRadioButton("Rechteck", true); // on
        arr[1] = new JCheckBox("Viereck", false);    // off
        arr[2] = new JCheckBox("Dreieck", false);    // off
        arr[3] = new JRadioButton("Oval", false);  // off
        arr[4] = new JRadioButton("Quader", true);
        arr[5] = new JRadioButton("WŸrfel", true);
        
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(arr[0]);
   	 	bereich2.add(arr[1]);
   	 	bereich2.add(arr[2]);
   	 	bereich2.add(arr[3]);
   	 	bereich2.add(arr[4]);
   	 	bereich2.add(arr[5]);

   	 	Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        // Radio-Button für Quader ist nicht in der Gruppe !!!
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(arr[0]);
        buttonGroup.add(arr[1]);
        buttonGroup.add(arr[2]);
        buttonGroup.add(arr[3]);
        
        // Die Gruppe auch optisch darstellen
        arr[0].setBackground(Color.ORANGE);
        arr[1].setBackground(Color.ORANGE);
        arr[2].setBackground(Color.ORANGE);
        arr[3].setBackground(Color.ORANGE);
        
        // Listener zuordnen
        arr[0].addItemListener(this);
        arr[1].addItemListener(this);
        arr[2].addItemListener(this);
        arr[3].addItemListener(this);
        arr[4].addItemListener(this);
        arr[5].addItemListener(this);

        // Fenstergröße ändern verbieten
        setResizable(false);
        
        // Fenster anzeigen
      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public void itemStateChanged(ItemEvent e) {
    		String out ="";
        for(int i=0; i<arr.length;i++){
        		if(arr[i].isSelected()){
        			out += "1 ";
        		}
        		else{
        			out += "0 ";
        		}
        }
        displayLabel.setText(out);
    }

    public static void main(String[] args) {
    	new ButtonGroupTest2().init();
    }
}

