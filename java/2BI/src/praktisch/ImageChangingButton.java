package praktisch;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class ImageChangingButton extends JFrame implements ActionListener, MouseListener {
    static final long serialVersionUID = 0L;

	private JLabel countLabel = new JLabel("Anzahl Klicks = 0");
    private static ImageIcon shieldred, shieldgreen, shieldyellow, tk;
    private ImageIcon [] cats, pics;
    private JButton button1, button2, button3;
    private int count = 0;

    public ImageChangingButton() {
        
        setTitle("Image changing buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(400, 200);
        
        // If the programm will be packed into a jar-file,
        // use class loader to read images
        shieldred    = new ImageIcon("shieldred.gif");
        shieldgreen  = new ImageIcon("shieldgreen.gif");
        shieldyellow = new ImageIcon("shieldyellow.gif");
        tk = new ImageIcon("choice.gif");
        
        cats = new ImageIcon[2];
        cats[0] = new ImageIcon("tiger.gif");
        cats[1] = new ImageIcon("cat.gif");
        
        pics = new ImageIcon[2];
        pics[0] = new ImageIcon("elephant.gif");
        pics[1] = new ImageIcon("mac.gif");
        
        setIconImage(shieldyellow.getImage()); // set frame image

        JPanel bereich1 = new JPanel();
        bereich1.add(countLabel);

        button1 = new JButton(" Klick mich / Alt + p ", shieldred);
        button1.setPressedIcon(shieldyellow);
        button1.setMnemonic('p');  // ALT + p
        button1.setToolTipText("Die mnemonische Kürzel ist: Alt + p");
        
        button2 = new JButton(shieldred);
        
        button3 = new JButton(tk);
        button3.setMargin(new Insets(0, 0, 0, 0));
        
        JPanel bereich2 = new JPanel();
        bereich2.add(button1);
        bereich2.add(button2);
        bereich2.add(button3);

        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        button1.addActionListener(this);
        button1.addMouseListener(this);
        button2.addActionListener(this);
        button2.addMouseListener(this);
        button3.addActionListener(this);
        button3.addMouseListener(this);
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {
		countLabel.setText("Anzahl Klicks = " + ++count);
	}

    public void mousePressed(MouseEvent e) { /* ignored */ }
    public void mouseClicked(MouseEvent e) { /* ignored */ }
    public void mouseReleased(MouseEvent e) {
    	if(e.getSource() == button3){
	    	Random zuf =new Random();
	    	button3.setPressedIcon(pics[zuf.nextInt(2)]);
    	}
    }

    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        if(e.getSource() == button1){
        	button1.setIcon(shieldgreen);
        }
        if(e.getSource() == button2){
        	button2.setIcon(shieldgreen);
        }
        if(e.getSource() == button3){
        	Random zuf =new Random();
        	button3.setIcon(cats[zuf.nextInt(2)]);
        }
    }

    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if(e.getSource() == button1){
        	button1.setIcon(shieldred);
        }
        if(e.getSource() == button2){
        	button2.setIcon(shieldred);
        }
        if(e.getSource() == button3){
        	button3.setIcon(tk);
        }
    }

    public static void main(String[] args) {
    	new ImageChangingButton();
    }
}