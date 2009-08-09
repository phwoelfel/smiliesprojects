package praktisch;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class TextareaScrollPane extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;

    private static final String CLEAR = "clear";
    private static final String nl = System.getProperty("line.separator");
    private JTextArea textArea = new JTextArea("", 4, 1); // text, rows, cols
    private JTextArea textArea2 = new JTextArea("", 4, 1); // text, rows, cols


    void init() {
        setTitle("JTextArea und JScrollPane");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(390, 150);

   	 	JPanel bereich1 = new JPanel();
   	 	bereich1.setLayout(new BorderLayout());
   	 	
   	 	JPanel b2 = new JPanel();
   	 	b2.setLayout(new GridLayout(1, 2));
        
   	 	textArea.setEditable(false);
        // Add scroll bars to text area
   	 	JScrollPane textBereichMitBildlaufLeisten = new JScrollPane(textArea);
   	 	b2.add(textBereichMitBildlaufLeisten, BorderLayout.CENTER);
   	 	
	   	 textArea2.setEditable(false);
	     // Add scroll bars to text area
	 	JScrollPane textBereichMitBildlaufLeisten2 = new JScrollPane(textArea2);
	 	b2.add(textBereichMitBildlaufLeisten2, BorderLayout.CENTER);
	 	
	 	bereich1.add(b2);
	 	
   	 	JButton button1 = new JButton("Zahl schreiben");
   	 	JButton button2 = new JButton("Alles löschen");
   	 	

   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(button1);
        bereich2.add(button2);

   	 	Container windowFlaeche = getContentPane();
   	 	windowFlaeche.add(bereich1, BorderLayout.CENTER);
   	 	windowFlaeche.add(bereich2, BorderLayout.SOUTH);

   	 	button1.addActionListener(this);
   	 	button2.addActionListener(this);
        
   	 	button2.setActionCommand(CLEAR);
     	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {
		//A: 65 Z:90 ascii
		Random zuf = new Random();
        if (e.getActionCommand().equals(CLEAR)){
            textArea.setText("");
        }
        else{
        	int t = (zuf.nextInt(27)+65);
            textArea.append(t+ nl);
            textArea2.append((char)t+nl);
        }
	}

    public static void main(String[] args) {
    	new TextareaScrollPane().init();
    }
}

