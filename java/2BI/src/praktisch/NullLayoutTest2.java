package praktisch;

import java.awt.*;

import javax.swing.*;

public class NullLayoutTest2 extends JFrame {
    static final long serialVersionUID = 0L;

    public NullLayoutTest2() {
        setTitle("Manual layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(290, 110);

        JButton buttonA = new JButton("Play");
        JButton buttonB = new JButton("Rewind");
        JButton buttonC = new JButton("Forward");
        JButton buttonD = new JButton("Pause");
        JButton buttonE = new JButton("Stop");
        
        JPanel bereich = new JPanel();
        bereich.setLayout(null);              // no layout specified
        buttonA.setBounds(10,  10,  80, 26);  // x, y, width, height
        buttonB.setBounds(10,  50, 120, 26);  // x, y, width, height
        buttonC.setBounds(150,  50, 120, 26);  // x, y, width, height
        buttonD.setBounds(100, 10,  80, 26);  // x, y, width, height
        buttonE.setBounds(190, 10,  80, 26);  // x, y, width, height
        
        bereich.add(buttonA);
        bereich.add(buttonB);
        bereich.add(buttonC);
        bereich.add(buttonD);
        bereich.add(buttonE);
        getContentPane().add(bereich, BorderLayout.CENTER);

        setResizable(false); // should be used for null-layout
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new NullLayoutTest2();
    }
}

