package praktisch;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class FontAndFontSize extends JFrame implements ActionListener, ItemListener {
    static final long serialVersionUID = 0L;

    private static final String PLUS  = "plus";
    private static final String MINUS = "minus";
   
    private static final String[] fontItems = { "Dialog", "Serif", "SansSerif",
                                                "MonoSpaced", "DialogInput"};
    private JComboBox fontComboBox = new JComboBox(fontItems);
    private String fontName = fontItems[0];
    private Container windowFlaeche;
    private JLabel dispLab;

    public FontAndFontSize() {
        setTitle("Change font");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(360, 220);
    	
        JPanel bereichN = new JPanel();
   	 	dispLab = new JLabel(fontItems[0]);
   	 	bereichN.add(dispLab);
 
	   	 String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	     fontComboBox = new JComboBox(fontNames);
	     
	     dispLab.setText(fontNames[0]);
	     Font font = dispLab.getFont();
	     dispLab.setFont(new Font(fontNames[0], font.getStyle(), font.getSize() + 2));
   	 	
   	 	JButton increaseButton = new JButton("Vergrößern");
   	 	JButton decreaseBbutton = new JButton("Verkleinern");
        increaseButton.setActionCommand(PLUS);
        decreaseBbutton.setActionCommand(MINUS);

        // set equal size for both buttons (same as decreaseBbutton)
        increaseButton.setPreferredSize(decreaseBbutton.getPreferredSize());

   	 	JPanel bereichC = new JPanel();
   	 	bereichC.add(fontComboBox);
   	 	bereichC.add(increaseButton);
        bereichC.add(decreaseBbutton);

        JRadioButton plainRadio = new JRadioButton("plain", false);
        JRadioButton boldRadio  = new JRadioButton("bold", true);
        JCheckBox italicBox     = new JCheckBox("italic", false);
        
        JPanel bereichS = new JPanel();
        bereichS.add(plainRadio);
        bereichS.add(boldRadio);
        bereichS.add(italicBox);

        // build button group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(plainRadio);
        buttonGroup.add(boldRadio);
        plainRadio.setBackground(new Color(241,246,139));
        boldRadio.setBackground(new Color(241,246,139));

        windowFlaeche = getContentPane();
        windowFlaeche.add(bereichN, BorderLayout.NORTH);
        windowFlaeche.add(bereichC, BorderLayout.CENTER);
        windowFlaeche.add(bereichS, BorderLayout.SOUTH);

   	 	increaseButton.addActionListener(this);
   	 	decreaseBbutton.addActionListener(this);

        fontComboBox.addItemListener(this);
        plainRadio.addItemListener(this);
        boldRadio.addItemListener(this);
        italicBox.addItemListener(this);

        setResizable(true);
      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    // handling for increaseButton & decreaseBbutton
	public void actionPerformed(ActionEvent e) {
                
        Font font = dispLab.getFont();
        int fontSize = font.getSize();

        String cmd = e.getActionCommand();
        if      (cmd.equals(PLUS))  fontSize = fontSize*2;
        else if (cmd.equals(MINUS)) fontSize = fontSize/2;
        
        Font newFont = new Font(fontName, font.getStyle(), fontSize);
        dispLab.setFont(newFont);
        repaintWindow();
	}

	// handling for fontComboBox, plainRadio, boldRadio & italicBox
	public void itemStateChanged(ItemEvent e) {

        Font font = dispLab.getFont();
        int style = font.getStyle();

	    if (e.getSource() == fontComboBox) {
	        fontName = (String)fontComboBox.getSelectedItem();
           dispLab.setText(fontName);
        }
        else {
            String cmd = ((JToggleButton)e.getSource()).getActionCommand();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if      (cmd.equals("plain"))  style &= ~Font.BOLD;   // off
                else if (cmd.equals("bold"))   style |=  Font.BOLD;   // on
                else if (cmd.equals("italic")) style |=  Font.ITALIC; // on 
            }
            else if (cmd.equalsIgnoreCase("italic"))
                style &= ~Font.ITALIC; // off
        }

        dispLab.setFont(new Font(fontName, style, font.getSize()));
        repaintWindow();
	}

    private void repaintWindow() {
        windowFlaeche.invalidate();
        windowFlaeche.validate();
    }

    public static void main(String[] args) {
    	new FontAndFontSize();
    }
}

