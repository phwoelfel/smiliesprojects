package netchat;
import java.io.IOException;
import java.util.Scanner;


public class TCPClient extends Thread {
	protected java.net.Socket socket;
	protected java.io.PrintWriter os ;
	protected java.io.BufferedReader is;
	protected String username;
	protected Thread th;
	protected java.io.BufferedReader input;
	
	
	public static void main(String[] args) {
		try {
			TCPClient app = new TCPClient();
			app.start();
			
		} catch (java.io.IOException e) {
			System.out.println(e.toString());
		}
	}

	public TCPClient() throws java.io.IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Host");
		String host = scan.next();
		System.out.println("Enter Port");
		int port = scan.nextInt();
		System.out.println("Enter Username");
		username = scan.next();
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
			System.err.println("Cannot open host");
			System.exit(1);
		} catch (java.io.IOException e) {
			System.err.println("IOException: " + e.toString());
			System.exit(1);
		}
		
	}
	
	public void run(){
		String userInput="";
		while(!userInput.equals("exit")){
			try {
				if ((userInput = input.readLine()) != null) {
					if(userInput.equals("exit")){
						break;
					}
					if (userInput.length() > 0) {
						os.println(username +": " +userInput);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		os.println(username +" disconected!");
		this.closeConnection();
	}
	
	protected boolean readMsg(){
		try {
			if(is==null)
				return false;
			String msg=is.readLine();
			if(msg != null)
				System.out.println(msg);
			
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


}
