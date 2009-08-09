package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TextFieldTest extends JFrame implements ActionListener, FocusListener {
    static final long serialVersionUID = 0L;

    private JTextField displayFld = new JTextField("Hier kommt die Eingabe", 28);
    private JTextField textFld    = new JTextField("Neuman", 8);  // text, columns
    private JPasswordField pswFld = new JPasswordField(8);

    public TextFieldTest() {
        setTitle("JTextField und JPasswordField");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 200);
        
        displayFld.setEditable(false);  // read only
        displayFld.setFocusable(false); // ignore Tab key
        
        textFld.selectAll();            // preselect text

        JPanel bereich1 = new JPanel();
        bereich1.add(displayFld);

   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(new JLabel("Name: "));
   	 	bereich2.add(textFld);
   	 	bereich2.add(new JLabel("     Passwort: "));
   	 	bereich2.add(pswFld);

   	 	Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        textFld.addActionListener(this);
        textFld.addFocusListener(this);
        pswFld.addActionListener(this);
        pswFld.addFocusListener(this);

        //setResizable(false);
      	setVisible(true);
        textFld.requestFocusInWindow();
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }
    
    // Tabulator Taste
    // =================================================
    // Callback methods for FocusListener
    // =================================================
    public void focusGained(FocusEvent e) {/* ignored */}
    public void focusLost(FocusEvent e) {
        handleTextFieldEntry(e.getSource());
    }
    
    // Enter Taste
    // =================================================
    // Callback method for ActionListener
    // =================================================
    public void actionPerformed(ActionEvent e) {
        handleTextFieldEntry(e.getSource()); 
    }
    
    private void handleTextFieldEntry(Object o) {
        
      // Field identification by its reference  
      if (o == pswFld) {
          String psw = String.valueOf(pswFld.getPassword());
          if (psw!="") {
              displayFld.setText(psw);
              pswFld.setText("");
          }
      }
      else if (o == textFld) {
          String text    = textFld.getText();
          String selText = textFld.getSelectedText();
          if (text!="") {
              displayFld.setText((selText != null) ? selText : text);
              textFld.setText("");
          }
      }
 
      // Field identification by casting object reference
      // Only possible for one (in frame) field of this type 
//    if (o instanceof JPasswordField) {
//        JPasswordField f = (JPasswordField)o;
//        displayLabel.setText(String.valueOf(f.getPassword()));
//        pswFld.setText("");
//    }
//    else if (o instanceof JTextField) {
//        JTextField f = (JTextField)o;
//        displayLabel.setText(f.getText());
//        textFld.setText("");
//    }
     
      if (displayFld.getText().length() == 0)
          displayFld.setText(" ");
    }

    public static void main(String[] args) {
    	new TextFieldTest();
    }
}

