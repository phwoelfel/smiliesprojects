package praktisch;

import java.awt.*;
import javax.swing.*;

public class ComponentColor extends JFrame {
    static final long serialVersionUID = 0L;
 
    void init() {
        setTitle("Component color");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(300, 100);

        JPanel bereich1 = new JPanel();
        JButton button = new JButton("Schaltfläche");
   	 	button.setBackground(Color.YELLOW);
   	 	button.setForeground(Color.RED);
   	 	bereich1.add(button);
          
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);

      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new ComponentColor().init();
    }
}

