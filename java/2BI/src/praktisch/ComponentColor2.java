package praktisch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;


public class ComponentColor2 extends JFrame implements ActionListener{
    static final long serialVersionUID = 0L;
    JButton button = new JButton("Schaltfläche");
    JButton h = new JButton("Hintergrund");
    JButton t = new JButton("Text");
    void init() {
        setTitle("Component color");
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	setFrameLocation(300, 100);

        JPanel bereich1 = new JPanel();
   	 	button.setBackground(Color.YELLOW);
   	 	button.setForeground(Color.RED);
   	 	bereich1.add(button);
        
        h.addActionListener(this);
        t.addActionListener(this);
   	 	
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(h);
        p.add(t);
        
        windowFlaeche.add(p);

      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new ComponentColor2().init();
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==h){
			button.setBackground(randomColor());
		}
		if(e.getSource()==t){
			button.setForeground(randomColor());
		}
	}
	
	public Color randomColor(){
		Random zuf = new Random();
		
		return new Color(zuf.nextInt(256), zuf.nextInt(256), zuf.nextInt(256));
	}
}

