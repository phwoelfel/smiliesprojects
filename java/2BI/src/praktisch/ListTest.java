package praktisch;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;


public class ListTest extends JFrame implements ListSelectionListener {
    static final long serialVersionUID = 0L;
    
    private static final String[] listItems = {"one", "two", "three", "four", "five",
                                               "six", "seven", "eight", "nine", "ten"};
    private JList dataList = new JList(listItems);
    private JLabel displayLabel = new JLabel(listItems[0]);
    private JTextArea textArea = new JTextArea("", 4, 1); // text, rows, cols

    public ListTest() {
        setTitle("JList");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setFrameLocation(180, 220);
        
        JPanel bereich1 = new JPanel();
        bereich1.add(displayLabel);

        // 6 Reihen zum Anzeigen; 8 ist default
        dataList.setVisibleRowCount(6);   
        
        // Packe die Liste in JScrollPane und in JPanel ein
   	 	JPanel bereich2 = new JPanel();
   	 	bereich2.setLayout(new GridLayout());
   	 	bereich2.add(new JScrollPane(dataList));
   	 	
   	 	textArea.setEditable(false);
   	 	// Add scroll bars to text area
	 	JScrollPane scroll = new JScrollPane(textArea);
	 	bereich2.add(scroll);
	 	
   	 	Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.NORTH);
        windowFlaeche.add(bereich2, BorderLayout.CENTER);

        dataList.addListSelectionListener(this);

        setResizable(false);
      	setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
    	setSize(frameWidth, frameHeight);
    	Dimension screen = getToolkit().getScreenSize();
    	setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            ListModel lModel = dataList.getModel();
            int[] indices = dataList.getSelectedIndices();
            
            displayLabel.setText("");
            textArea.setText("");
            for (int i=0; i < indices.length; i++) {
                String item = (String)lModel.getElementAt(indices[i]);
                displayLabel.setText(displayLabel.getText() + " " + item);
                textArea.append(item +"\n");
            }
        }
    }

    public static void main(String[] args) {
    	new ListTest();
    }
}

