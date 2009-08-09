package praktisch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextareaEditable extends JFrame implements ActionListener {
    static final long serialVersionUID = 0L;
    
    private JTextArea editableTextArea = new JTextArea("", 4, 17); // text, rows, cols
    private JTextArea readonlyTextArea = new JTextArea("", 4, 17);


    void init() {
        setTitle("Editable JTextArea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrameLocation(390, 150);
              
        JPanel bereich1 = new JPanel();
        bereich1.setLayout(new BorderLayout());
        JScrollPane textBereichMitBildlaufLeisten = new JScrollPane(editableTextArea);
        bereich1.add(textBereichMitBildlaufLeisten, BorderLayout.CENTER);
        editableTextArea.setEditable(true);  // editable
        
        JPanel bereich2 = new JPanel();
        bereich2.setLayout(new BorderLayout());
        JScrollPane textBereichMitBildlaufLeisten2 = new JScrollPane(readonlyTextArea);
        bereich2.add(textBereichMitBildlaufLeisten2, BorderLayout.CENTER);
        readonlyTextArea.setEditable(false); // not editable
        
        JButton button = new JButton("Text zählen");
        JPanel bereich3 = new JPanel();
        bereich3.add(button);
        
        Container windowFlaeche = getContentPane();
        windowFlaeche.add(bereich1, BorderLayout.WEST);
        windowFlaeche.add(bereich2, BorderLayout.EAST);
        windowFlaeche.add(bereich3, BorderLayout.SOUTH);

        button.addActionListener(this);
        setVisible(true);
    }

    public void setFrameLocation(int frameWidth, int frameHeight) {
        setSize(frameWidth, frameHeight);
        Dimension screen = getToolkit().getScreenSize();
        setLocation((screen.width - frameWidth)/2, (screen.height - frameHeight)/2);
    }

    public void actionPerformed(ActionEvent e) {
       String text = editableTextArea.getText();
       String[]arr = text.split("\n");
       readonlyTextArea.setText("");
       for(int i=0;i<arr.length;i++){
    	   readonlyTextArea.append(arr[i].length() +"\n");
           editableTextArea.requestFocusInWindow(); // set cursor   
    	   
       }
        
    }

    public static void main(String[] args) {
        new TextareaEditable().init();
    }
}

