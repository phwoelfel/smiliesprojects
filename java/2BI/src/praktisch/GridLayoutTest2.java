package praktisch;

import java.awt.*;
import javax.swing.*;

public class GridLayoutTest2 extends JFrame {
    static final long serialVersionUID = 0L;

    public GridLayoutTest2() {
        setTitle("Grid layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(339, 140);

        JButton buttonA = new JButton("Play");
        JButton buttonB = new JButton("Rewind");
        JButton buttonC = new JButton("Forward");
        JButton buttonD = new JButton("Pause");
        JButton buttonE = new JButton("Stop");

        Container windowFlaeche = getContentPane();
        /*
        String ho = JOptionPane.showInputDialog(this, "Horizontaler Abstand", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        String ve = JOptionPane.showInputDialog(this, "Vertikaler Abstand", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        int hor = Integer.parseInt(ho);
        int ver = Integer.parseInt(ve);
        String ro = JOptionPane.showInputDialog(this, "Rows", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        String co = JOptionPane.showInputDialog(this, "Cols", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        int row = Integer.parseInt(ro);
        int col = Integer.parseInt(co);
        */
        String ein = JOptionPane.showInputDialog(this, "Rows, Cols, Horizontaler Abstand, Vertikaler Abstand", "Ausrichtung", JOptionPane.QUESTION_MESSAGE);
        String [] arr = ein.split(" ");
        int[]ia = new int[arr.length];
        for(int i=0;i<ia.length;i++){
        		ia[i]= Integer.parseInt(arr[i]);
        }
        getContentPane().setLayout(new GridLayout(ia[0], ia[1], ia[2], ia[3])); // rows, cols, hgap, vgap
        windowFlaeche.add(buttonA);
        windowFlaeche.add(buttonB);
        windowFlaeche.add(buttonC);
        windowFlaeche.add(buttonD);
        windowFlaeche.add(buttonE);

        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new GridLayoutTest2();
    }
}


