package netchat;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.DefaultCaret;


public class GUIClient extends JFrame implements  ActionListener {
	private static final long serialVersionUID = 1L;
	protected java.net.Socket socket;
	protected java.io.PrintWriter os ;
	protected java.io.BufferedReader is;
	protected String username;
	protected Thread th;
	protected java.io.BufferedReader input;
	protected JTextField inText;
	protected JTextArea outText;
	protected JScrollPane outScroll;
	protected JTextArea connectedUsers;
	protected JScrollPane userScroll;
	protected JButton sendButton;
	
	public static void main(String[] args) {
		try {
			GUIClient app = new GUIClient();
			
		} catch (java.io.IOException e) {
			System.out.println(e.toString());
		}
	}

	public GUIClient() throws java.io.IOException {
		setTitle("NetChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		//Container Anfang
		Container cp = getContentPane();
		
		JPanel unten = new JPanel();
		unten.setLayout(new BorderLayout());
		//Container Ende
		
		
		//Initialisierung der Inhalte Anfang
		inText = new JTextField();
		
		outText = new JTextArea();
		outScroll = new JScrollPane(outText);
		outScroll.setAutoscrolls(true);
		outText.setEditable(false);
		outText.setForeground(Color.BLACK);
		
		//connectedUsers = new JTextArea();
		//userScroll = new JScrollPane(connectedUsers);
		//outText.setBackground(Color.BLACK);
		
		DefaultCaret caret = (DefaultCaret) outText.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	   // DefaultCaret caret2 = (DefaultCaret) connectedUsers.getCaret();
	   // caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    
	    sendButton = new JButton("send");
		//Initialisierung der Inhalte Ende
		
		
		//Action Listener zu den Inhalten hinzufuegen Anfang
		inText.addActionListener(this);
		sendButton.addActionListener(this);
		//Action Listener zu den Inhalten hinzufuegen Ende
		
		
		//Inhalte zu den JPanel hinzufuegen Anfang
		unten.add(inText, BorderLayout.CENTER);
		unten.add(sendButton, BorderLayout.EAST);
		//Inhalte zu den JPanel hinzufuegen Ende
		
		
		//JPanel zum Container hinzufuegen Anfang
		cp.add(unten, BorderLayout.SOUTH);
		cp.add(outScroll, BorderLayout.CENTER);
		//cp.add(userScroll, BorderLayout.EAST);
		//JPanel zum Container hinzufuegen Ende
		String host="";
		int port=0;
		
		host = JOptionPane.showInputDialog(this, "Enter Host", "Host", JOptionPane.QUESTION_MESSAGE);
		port = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Port", "Port", JOptionPane.QUESTION_MESSAGE));
		username = JOptionPane.showInputDialog(this, "Enter Username", "Username", JOptionPane.QUESTION_MESSAGE);
		
		try {	
			socket = new java.net.Socket(host, port);
			os = new java.io.PrintWriter(socket.getOutputStream(), true);
			is = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
			input = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			
			os.write(username +" connected!\n");
			os.flush();
			
			th=new Thread(){
				public void run(){
					while(readMsg()){}
				}
			};
			
			th.start();
		
		} catch (java.net.UnknownHostException e) {
			//System.err.println("Cannot open host");
			JOptionPane.showMessageDialog(this, "Cannot Open Host", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (java.io.IOException e) {
			JOptionPane.showMessageDialog(this, "IOException: " +e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			//System.err.println("IOException: " + e.toString());
			System.exit(1);
		}
		setVisible(true);
		inText.grabFocus();
	}
	
	protected boolean readMsg(){
		try {
			if(is==null)
				return false;
			String msg=is.readLine();
			if(msg != null){
				outText.append(msg +"\n");
			}
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return true;
	}
	
	public void closeConnection(){
		try {
			th.interrupt();
			os.close();
			is.close();
			is=null;
			os=null;
			input.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void finalize(){
		this.closeConnection();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==inText || e.getSource()==sendButton){
			String userInput = inText.getText();
			if(userInput.equals("exit")){
				os.println(username +" disconnected!");
				System.exit(0);
			}
			else{
				if(!userInput.equals("")){
					os.println(username +": " +userInput);
					inText.setText("");
				}
			}
		}
	}


}
