package praktisch;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class CursorTest2 extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;
    
    Random zuf = new Random();
    private int z=1;
    JButton button  = new JButton("ändere Cursor");
	
    static int[] cursors = new int[] { Cursor.DEFAULT_CURSOR,   Cursor.CROSSHAIR_CURSOR,
              Cursor.HAND_CURSOR,      Cursor.MOVE_CURSOR,      Cursor.WAIT_CURSOR,
              Cursor.TEXT_CURSOR,      Cursor.N_RESIZE_CURSOR,  Cursor.E_RESIZE_CURSOR,
              Cursor.S_RESIZE_CURSOR,  Cursor.W_RESIZE_CURSOR,  Cursor.NE_RESIZE_CURSOR,
              Cursor.NW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR };
    

    void init() {
        setTitle("Cursor ‰ndern");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(400, 120);
   	 	
   	 	JPanel bereich = new JPanel();
   	 	
   	 	bereich.add(button);
   	 	getContentPane().add(bereich, BorderLayout.CENTER);

   	 	button.addActionListener(this);
     	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

	public void actionPerformed(ActionEvent e) {
        if(z%2==0){
            setCursor(Cursor.DEFAULT_CURSOR);
            button.setText("Cursor ändern");
        }
        else{
        		int randomCursor = cursors[zuf.nextInt(cursors.length)];
            setCursor(new Cursor(randomCursor));
            button.setText("Cursor zurücksetzen");
        }
        z++;
	}

    public static void main(String[] args) {
    	new CursorTest2().init();
    }
}

