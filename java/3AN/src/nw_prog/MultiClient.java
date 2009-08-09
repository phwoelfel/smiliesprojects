package nw_prog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

import javax.swing.*;


public class MultiClient extends JFrame implements ActionListener, KeyListener{
	
	MulticastSocket socke = null;
	InetAddress IP = null;
	
	private static final int PORT = 5000;
	private static final String IP_ADR = "230.1.33.7"; 
	private static String name = "";
	
	protected JButton send_bt = new JButton("senden");
	protected JTextArea empf = new JTextArea(15, 30);
	protected JTextField send_tf = new JTextField(50);
	
	public MultiClient(){
		try {
			IP = InetAddress.getByName(IP_ADR);
			socke = new MulticastSocket(PORT);
			NLT nlt = new NLT(socke, IP, empf);
			nlt.start();
			
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(0);
		}
		
		name = JOptionPane.showInputDialog(this, "Bitte Namen eingeben", "Namen eingeben", JOptionPane.QUESTION_MESSAGE);
		sendMsg(name +" is here!");
		setTitle("Multicast Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel unten = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		empf.setEditable(false);
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		send_bt.addActionListener(this);
		send_tf.addKeyListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		cp.add(new JScrollPane(empf), BorderLayout.CENTER);
		
		unten.add(send_tf);
		unten.add(send_bt);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(unten, BorderLayout.SOUTH);
		//JPanel zum Container hinzufuegen Ende
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new MultiClient();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==send_bt){
			String msg = send_tf.getText();
			if(!msg.equals("")){
				sendMsg(name +": " +msg);
				send_tf.setText("");
			}
		}
		
	}
	
	public void sendMsg(String msg){
		DatagramPacket pack = new DatagramPacket(msg.getBytes(), msg.length(), IP, PORT);
		try {
			socke.send(pack);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ENTER) {
			String msg = send_tf.getText();
			if(!msg.equals("")){
				sendMsg(name +": " +msg);
				send_tf.setText("");
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
