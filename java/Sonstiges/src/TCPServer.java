public class TCPServer {
	public static void main(String args[]) {
		try {
			TCPServer app = new TCPServer();
		} catch (java.io.IOException e) {
			System.out.println(e.toString());
		}
	}

	public TCPServer() throws java.io.IOException {
		System.out.println("Server started...");
		java.net.Socket clientSocket = null;
		java.io.PrintStream outStream = null;
		java.io.BufferedReader reader = null;
		String line = null;
		java.net.ServerSocket serverSocket = null;
		boolean runningFlag = true;
		try {
			serverSocket = new java.net.ServerSocket(7777);
			while (runningFlag == true) {
				//serverSocket.setSoTimeout(20000);
				System.out.println("Waiting for client to connect...");
				clientSocket = serverSocket.accept();
				System.out.println("Client connection received from: "
						+ clientSocket.getInetAddress().getHostName());
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(clientSocket
								.getInputStream()));
				outStream = new java.io.PrintStream(clientSocket
						.getOutputStream());
				boolean b = true;
				while(b){
					line = reader.readLine();
					if ((line != null) && (line.length() > 0)) {
						if(line.equals("exit")){
							b=false;
							serverSocket.close();
						}
						else{
							System.out.println("Received message: " + line);
							outStream.println("Received message: " + line);
							
						}
					}
				}
				//serverSocket.close();
				//runningFlag = false;
			}
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
		try {
			if (clientSocket != null)
				clientSocket.close();
			serverSocket.close();
		} catch (java.io.IOException e) {
		}
		System.out.println("Exiting...");
	}
}
