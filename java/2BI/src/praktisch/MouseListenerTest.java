package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MouseListenerTest extends JFrame implements ActionListener, MouseListener  {
    static final long serialVersionUID = 0L;

    private static final String CLEAR = "clear";
    private static final String nl = System.getProperty("line.separator");
    private JTextArea textArea = new JTextArea("", 15, 1);
    private JButton mouseButton = new JButton("Maus");
    private static ImageIcon[]pics;
    private JButton but;

    public MouseListenerTest() {
        setTitle("MouseListener");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(500, 400);

   	 	JPanel bereich1 = new JPanel();
   	 	bereich1.setLayout(new BorderLayout());
   	 	bereich1.add(new JScrollPane(textArea), BorderLayout.CENTER);

   	 	JButton clearButton = new JButton("Alles löschen");
   	 	
   	 	pics = new ImageIcon[3];
   	 	pics[0] = new ImageIcon("cat.gif");
   	 	pics[1] = new ImageIcon("tiger.gif");
   	 	pics[2] = new ImageIcon("elephant.gif");
   	 	
   	 	but = new JButton();
   	 	but.setPreferredSize(new Dimension(104, 104));
   	 	but.addMouseListener(this);
   	 	
   	 	JPanel oben = new JPanel();
   	 	oben.setLayout(new FlowLayout());
   	 	oben.add(but);
   	 	
   	 	bereich1.add(oben, BorderLayout.NORTH);
        
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.add(mouseButton);
   	 	bereich2.add(clearButton);

   	 	Container windowFlaeche = getContentPane();
   	 	windowFlaeche.add(bereich1, BorderLayout.CENTER);
   	 	windowFlaeche.add(bereich2, BorderLayout.SOUTH);

   	 	textArea.setEditable(false);
   	 	mouseButton.addMouseListener(this);
        
        clearButton.addActionListener(this);
        clearButton.setActionCommand(CLEAR);
        
     	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }
 
    // Methoden für den MouseListener **********************************
    public void mouseEntered(MouseEvent e) {
        mouseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public void mouseExited(MouseEvent e) {
        mouseButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseClicked(MouseEvent e) {
        String text = "Mausklick - Koordinaten: x=" + e.getX() + " y=" + e.getY();
        updateTextarea(text);
        
        
        
        // In diesem Beispiel sind nur ein Doppelklick mit der linken Maustaste
        // oder ein einfacher Klick mit der rechten Maustaste zugelassen
        if (e.getButton() == MouseEvent.BUTTON1) {
        	if(e.getClickCount()==1){
        		text = "===> zugelassen: linke Maustaste, Einfachklick";
        		but.setIcon(pics[0]);
        	}
        	else if(e.getClickCount() == 2){
                text = "===> zugelassen: linke Maustaste, Doppelklick";
                but.setIcon(pics[1]);
        	}
        	else{
                return;
        	}
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            if(e.getClickCount() == 1){
                text = "===> zugelassen: rechte Maustaste, Einfachklick";
            	but.setIcon(pics[2]);
            }
            else{
                return;
            }
        }
        else{
            return;
        }

        updateTextarea(text);
    }
    public void mousePressed(MouseEvent e) {
        String text = "Mouse pressed";
        if(e.getButton() == MouseEvent.BUTTON1)
            text += " - left mouse key";
        else if (e.getButton() == MouseEvent.BUTTON2)
            text += " - mouse wheel";
        else if (e.getButton() == MouseEvent.BUTTON3)
            text += " - right mouse key";
        updateTextarea(text);
    }
    public void mouseReleased(MouseEvent e) {
        updateTextarea("Mouse released");
    }
    // ********************************************************************
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(CLEAR))
            textArea.setText("");
    }

    private void updateTextarea(String s) {
        textArea.append(s + nl);
    }

    public static void main(String[] args) {
    	new MouseListenerTest();
    }
}

