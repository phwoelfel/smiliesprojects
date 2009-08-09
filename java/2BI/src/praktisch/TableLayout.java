package praktisch;

import java.awt.*;
import javax.swing.*;

public class TableLayout extends JFrame {
    static final long serialVersionUID = 0L;
    
    public TableLayout() {
        setTitle("My table layout: 2 x 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(350, 150);
  
        // build two columns
        JPanel columnA = new JPanel();
        JPanel columnB = new JPanel();
        columnA.setLayout(new BoxLayout(columnA, BoxLayout.Y_AXIS));
        columnB.setLayout(new BoxLayout(columnB, BoxLayout.Y_AXIS));

        // first row: add margins and fields
        JLabel vornameLabel = new JLabel("Vorname:", JLabel.RIGHT);
        JTextField vornameField = new JTextField(15);
        columnA.add(Box.createRigidArea(new Dimension(0,10)));
        columnA.add(vornameLabel);
        columnB.add(Box.createRigidArea(new Dimension(0,10)));
        columnB.add(vornameField);
        
        // second row: add gaps and fields
        JLabel nachnameLabel = new JLabel("Nachname:", JLabel.RIGHT);
        JTextField nachnameField = new JTextField(15);
        columnA.add(Box.createRigidArea(new Dimension(0,3)));
        columnA.add(nachnameLabel);
        columnB.add(Box.createRigidArea(new Dimension(0,3)));
        columnB.add(nachnameField);
        
        // area for columns
        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.X_AXIS));
         
        // add columns and gap
        table.add(columnA);
        table.add(Box.createRigidArea(new Dimension(8,0)));
        table.add(columnB);
         
        Container windowFlaeche = getContentPane();
        windowFlaeche.setLayout(new FlowLayout());
        windowFlaeche.add(table);

        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public static void main(String[] args) {
    	new TableLayout();
    }
}

