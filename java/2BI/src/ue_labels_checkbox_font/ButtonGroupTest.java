package ue_labels_checkbox_font;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ButtonGroupTest extends JFrame implements ItemListener {
    static final long serialVersionUID = 0L;
    
    private static final String RECHTECK = "Rechteck";
    private static final String VIERECK  = "Viereck";
    private static final String OVAL     = "Oval";
    private static final String QUADER   = "Quader";

    private JLabel displayLabel = new JLabel(RECHTECK);

    
    void init() {
        setTitle("ButtonGroup");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(300, 100);

        JPanel bereich1 = new JPanel();
        bereich1.add(displayLabel);

        JRadioButton rectRadio = new JRadioButton(RECHTECK, true); // on
        JCheckBox checkBox     = new JCheckBox(VIERECK, false);    // off
        JRadioButton ovalRadio = new JRadioButton(OVAL, false);    // off
        JRadioButton quadRadio = new JRadioButton(QUADER, false);  // off
        
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(rectRadio);
   	 	bereich2.add(checkBox);
   	 	bereich2.add(ovalRadio);
   	 	bereich2.add(quadRadio);

   	 	Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        // Radio-Button für Quader ist nicht in der Gruppe !!!
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rectRadio);
        buttonGroup.add(checkBox);
        buttonGroup.add(ovalRadio);
        
        // Die Gruppe auch optisch darstellen
        rectRadio.setBackground(Color.ORANGE);
        checkBox.setBackground(Color.ORANGE);
        ovalRadio.setBackground(Color.ORANGE);
        
        // Eigene ID für jeden Button
        rectRadio.setActionCommand(RECHTECK);
        checkBox.setActionCommand(VIERECK);
        ovalRadio.setActionCommand(OVAL);
        quadRadio.setActionCommand(QUADER);
        
        // Listener zuordnen
        rectRadio.addItemListener(this);
        checkBox.addItemListener(this);
        ovalRadio.addItemListener(this);
        quadRadio.addItemListener(this);

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
        String cmd = ((JToggleButton)e.getSource()).getActionCommand();
        if (e.getStateChange() == ItemEvent.SELECTED) { // selected
            if      (cmd.equals(RECHTECK)) displayLabel.setText(RECHTECK);
            else if (cmd.equals(VIERECK))  displayLabel.setText(VIERECK);
            else if (cmd.equals(OVAL))     displayLabel.setText(OVAL);
            else if (cmd.equals(QUADER))   displayLabel.setText(QUADER);
        }
        else if (cmd.equals(QUADER))                    // deselected
            displayLabel.setText("Quader ausgeschaltet");
    }

    public static void main(String[] args) {
    	new ButtonGroupTest().init();
    }
}

