package praktisch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonArray2 extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;
    
    private int rows = 7, cols = 11;
    private JButton[][] buttons = new JButton[rows][cols];
    private int[][] counts = new int[rows][cols];

    public ButtonArray2() {
        setTitle("Button array");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(596, 298);
               
        Container windowFlaeche = getContentPane();
        windowFlaeche.setLayout(new GridLayout(rows, cols));
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setActionCommand("" + i + " " + j);
                buttons[i][j].addActionListener(this);
                windowFlaeche.add(buttons[i][j]);
            }
        }
        setVisible(true);
    }
    
    public void setFrameLocation(int frameWidth, int frameHeight) {
        setSize(frameWidth, frameHeight);
        Dimension screen = getToolkit().getScreenSize();
        setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }
    
    public void actionPerformed(ActionEvent e) {
        String coordinates = e.getActionCommand();
        setTitle(coordinates);
        String[] indexes = coordinates.split(" ");
        int r = Integer.parseInt(indexes[0]);
        int c = Integer.parseInt(indexes[1]);
        counts[r][c]++;
        
        // print counts
        for (int i=0; i<counts.length;i++) {
            for (int j=0; j<counts[i].length; j++){
            		buttons[i][j].setText("" +counts[i][j]);
                System.out.print(counts[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
    	new ButtonArray2();
    }
}

