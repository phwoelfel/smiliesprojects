/*
 * Created on Sep 21, 2005
 *
 */
package server;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.*;
import both.MyThread;
//import javax.swing.LookAndFeel;

public class Server extends JFrame implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = -1312472727357784201L;
    /**
     * 
     */
    private ServerSocket sock;
    private MulticastSocket discover;
    private Vector clients;
    private JTextArea status;
    private JButton exitbutton;
    private MyThread listener,recv;
    private DatagramPacket dgp;
    private JScrollPane sp;
    
    public Server(boolean show)  {
        super("Chatserver");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception evt) {}
        
        //LookAndFeel.
        clients=new Vector();
        try{
            sock=new ServerSocket(21456);      
        }catch(IOException ex1){
            System.err.println("IOEx at creating Server");
            System.exit(1);            
        }
        status=new JTextArea(){
           /**
             * 
             */
            private static final long serialVersionUID = 1633752814597458302L;

        public void append(String str){
               super.append(str);
               sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
           }
        };
        status.setLineWrap(true);
        status.setEditable(false);
        sp=new JScrollPane(status);
        
        this.getContentPane().add(sp,BorderLayout.CENTER);
        //this.getContentPane().add(status,BorderLayout.CENTER);
        JPanel pan=new JPanel();
        exitbutton=new JButton("exit");
        exitbutton.addActionListener(this);
        pan.add(exitbutton);
        this.getContentPane().add(pan,BorderLayout.SOUTH);
        
        System.setErr(new PrintStream(System.err){
            public void print(String str){
                status.append(str);
            }
            public void println(String str){
                status.append(str+"\n");
            }
            public void println(Object obj){
                status.append(obj.toString()+"\n");
            }
            public void print(Object obj){
                status.append(obj.toString());
            }
        });
        System.setOut(new PrintStream(System.out){
            public void print(String str){
                status.append(str);
            }
            public void println(String str){
                status.append(str+"\n");
            }
            public void println(Object obj){
                status.append(obj.toString()+"\n");
            }
            public void print(Object obj){
                status.append(obj.toString());
            }
       });
        listener=new MyThread(){
            public void run(){
                //System.out.println("Started listener");
                while(active){
                    System.out.println("Waiting for new Client");
                    try{
                    createClientHandler(sock.accept());
                    }catch(IOException ex2){
                        System.err.println("IOEx at accepting Client");
                    }
                }
            }
           
        };
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing( WindowEvent e )
                    {
                        listener.exit();
                        for(int i=0;i<clients.size();i++){
                            ((ClientHandler)clients.get(i)).exit();
                            ((ClientHandler)clients.get(i)).interrupt();
                        }
                        try{
                            sock.close();
                        }catch(IOException ex5){
                            System.err.println("Error at closing socket");
                        }
                         System.exit( 0 );
                    }
                 });
        //creating multicastsocket
        try{
            //System.out.println("creating multicastsocket");
            discover=new MulticastSocket(21457);
            discover.joinGroup(InetAddress.getByName("224.1.1.113"));
            
            byte buf[]=new byte[512];
            dgp=new DatagramPacket(buf,buf.length);
            recv=new MyThread(){
                public void run(){
                    String msg;
                    try{
                    msg=InetAddress.getLocalHost().getHostAddress()/*.getHostName()*/;
                    }catch(UnknownHostException ex){
                        /*try{
                        msg=InetAddress.getLocalHost().toString();
                        }catch(UnknownHostException ex2){*/
                            msg="127.0.0.1";
                        //}
                    }
                    //System.out.println("msg: "+msg);
                    byte buf[]=new byte[512];
                    while(active){
                            try{
                                buf=new byte[512];
                                dgp=new DatagramPacket(buf,buf.length);
                            discover.receive(dgp);
                            String recd=new String(dgp.getData());
                            //System.out.println(";"+recd+";");
                            if(recd.indexOf("?")!=-1){
                                //System.out.println("msg: "+msg);
                                dgp=new DatagramPacket(msg.getBytes(),msg.length(),InetAddress.getByName("224.1.1.113"), 21457);
                                discover.send(dgp);
                            }
                            }catch(IOException ex){
                                System.err.println("IOE at receiving multicast");
                            }
                        }
                }
            };
            recv.start();
           
            
        }catch(IOException e8){
            System.err.println("IOE at multicast");
        }catch(Exception e9){
            System.err.println("Exception at multicast: " + e9.getMessage());
        }
        
        this.setVisible(show);
        this.setSize(600,400);
        System.out.println("Ended Initializing starting listener");
        listener.start();
    }
    
    public Server(){
        this(true);
    }
    
    
    private synchronized void createClientHandler(Socket socket){
        ClientHandler ch=new ClientHandler(this,socket);
        clients.add(ch);
        ch.start();
    }
   
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitbutton){
            listener.exit();
            for(int i=0;i<clients.size();i++){
                ((ClientHandler)clients.get(i)).exit();
                ((ClientHandler)clients.get(i)).interrupt();
            }
            try{
                sock.close();
            }catch(IOException ex5){
                //System.err.println("Error at closing socket");
            }
             System.exit( 0 );
        }

    }
    
    public void chat(String msg){
        //System.out.println("chat: "+msg);
        for(int i=0;i<clients.size();i++){
                ((ClientHandler)clients.get(i)).send(msg);
                if(!((ClientHandler)clients.get(i)).isAlive()){
                    clients.remove(i);
                    i--;
                }
            
        }
    }
    public void terminate(ClientHandler ch){
        clients.remove(ch);
    }

    public synchronized void exit(){
        for(int i=0;i<clients.size();i++){
            ((ClientHandler)clients.get(i)).send("Server exited");
            terminate(((ClientHandler)clients.get(i)));
        }
        recv.exit();
        recv.interrupt();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        new Server();

    }

}
