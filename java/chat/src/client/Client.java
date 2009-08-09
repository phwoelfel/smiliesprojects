/*
 * Created on Sep 21, 2005
 *
 */
package client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import java.io.*;
import java.net.*;
//import java.nio.ByteBuffer;
//import java.nio.channels.DatagramChannel;
//import java.util.*;
import both.MyThread;
import server.Server;



public class Client extends JFrame implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 3671323979970219132L;
    /**
     * 
     */
    private String server;
    private String username;
    private Socket sock;
    private MulticastSocket discover;
    private DatagramPacket dgp;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private JTextField text;
    private JButton send;
    private JTextArea chat;
    private MyThread recvthr;
//    private JTextArea debug;
//    private JDialog debugwindow;
    private Server serv;
    private boolean serverset;
    private byte buf[];
    private JScrollPane sp;
    
    
    public Client()   {
        super("Chat");
        serverset=false;
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception evt) {}
        
        /*
        debugwindow=new JDialog();
        debug=new JTextArea();
        debug.setLineWrap(true);
        debug.setEditable(false);     
        debugwindow.getContentPane().add(debug);
        debugwindow.setSize(400,300);
        debugwindow.show();
        System.setErr(new PrintStream(System.err){
            public void print(String str){
                debug.append(str);
            }
            public void println(String str){
                debug.append(str+"\n");
            }
            public void println(Object obj){
                debug.append(obj.toString()+"\n");
            }
            public void print(Object obj){
                debug.append(obj.toString());
            }
        });
        System.setOut(new PrintStream(System.out){
            public void print(String str){
                debug.append(str);
            }
            public void println(String str){
                debug.append(str+"\n");
            }
            public void println(Object obj){
                debug.append(obj.toString()+"\n");
            }
            public void print(Object obj){
                debug.append(obj.toString());
            }
       });
        */
        text=new JTextField();
        text.addActionListener(this);
        send=new JButton("send");
        send.addActionListener(this);
        JPanel bottom=new JPanel(new BorderLayout());
        bottom.add(text,BorderLayout.CENTER);
        bottom.add(send,BorderLayout.EAST);
        this.getContentPane().add(bottom,BorderLayout.SOUTH);
        chat=new JTextArea(){
            private static final long serialVersionUID = -8274865030565514204L;

            public void append(String str){
                super.append(str);
                sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
            }
         };
        chat.setLineWrap(true);
        chat.setEditable(false);
        sp=new JScrollPane(chat);
        this.getContentPane().add(sp,BorderLayout.CENTER);
        //this.getContentPane().add(chat,BorderLayout.CENTER);
        
        try{
            //System.out.println("creating multicastsocket");
            discover=new MulticastSocket(21457);
            discover.joinGroup(InetAddress.getByName("224.1.1.113"));
            String msg="Server?";
            //DatagramChannel dgmch=discover.getChannel();
            //dgmch.connect(discover.getRemoteSocketAddress());
            //dgmch.configureBlocking(false);
            dgp = new DatagramPacket(msg.getBytes(), msg.length(),
                    InetAddress.getByName("224.1.1.113"), 21457);
            //System.out.println("Sending Query");
            discover.send(dgp);
            //ByteBuffer buf2;
            //buf2=ByteBuffer.allocate(512);
            //buf2.put(msg.getBytes());
            //dgmch.send(buf2,discover.getRemoteSocketAddress());
            buf=new byte[512];
            dgp=new DatagramPacket(buf,buf.length);
            discover.receive(dgp);
            server=new String(dgp.getData());
            MyThread recv=new MyThread(){
                public /*synchronized*/ void run(){
                    //System.out.print("Thread started for multicast receive");
                    serverset=false;
                    try{
                        buf=new byte[512];
                        dgp=new DatagramPacket(buf,buf.length);
                        
                        discover.receive(dgp);
                    }catch(IOException ex){
                        System.err.println("IOE at receiving multicast");
                    }
                    serverset=true;
                    //System.out.println("Notifying");
                    //notifyAll();
                }
            };
            //do {
                recv.start();
                //whole programm waits infinite if no datagrampacket is received but why?
                /* Waits infinite but why? 
                synchronized(this) {
                    try {
                        wait(500L);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted waiting for thread to finish");
                    }
                } */
                try{
                    //System.out.println("Joining receivethread");
                    recv.join(500L);
                }catch(InterruptedException e){
                    //System.out.println("Something rec?");
                }
                //server=new String(dgp.getData());
                //System.out.println("srecv: "+server);
            //}while(server.indexOf("?")!=-1&&!interrupted);
            
            if(serverset){
                //msg found
                server=new String(dgp.getData());
                if(server.indexOf("?")!=-1){
                    //System.out.println("Server? received");
                    //server=JOptionPane.showInputDialog(null,"Server eingeben","");
                    serv=new Server(true);
                    server="localhost";
                    
                }
            }else{
                serv=new Server(false);
                server="localhost";
            }
            
        }catch(IOException e8){
            System.err.println("IOE at multicast");
        }/*catch(Exception e9){
            System.err.println("Exception at multicast: " + e9.getMessage()+"server: "+server);
        }*/
        // connect to Server
        //System.out.println(server);
        try{
            //server=JOptionPane.showInputDialog(null,"Server eingeben"+InetAddress.getLocalHost().getCanonicalHostName(),"");
            //System.out.println(server);
            //InetAddress.getByName(server).getCanonicalHostName()
            sock=new Socket(server.trim(),21456);
            out=new ObjectOutputStream(sock.getOutputStream());
            in= new ObjectInputStream(sock.getInputStream());
            username=JOptionPane.showInputDialog(null,"Username eingeben","");
        }catch(UnknownHostException ex){
            JOptionPane.showMessageDialog(null,"Server with this Address not found!","Server unreachable",JOptionPane.ERROR_MESSAGE);
            System.err.println("Server not found");
            System.exit(1);
        }catch(IOException e){
            System.err.println("IOException");
            System.exit(1);
        }catch(ClassCastException e6){
            System.err.println("ClassCasteEx at initializing sockets");
            
        }
        JPanel title=new JPanel();
        title.add(new JLabel("user: "+username));     
        this.getContentPane().add(title,BorderLayout.NORTH);
        
        recvthr=new MyThread(){
            public void run(){
                while(active){
                    try{
                        String str=(String)in.readUTF();
                        //System.out.println(str);
                        chat.append(str+"\n");
                    }catch(ClassCastException e1){
                        System.err.println("ClassCastException at Recvthr");
                    }catch(IOException e2){
                        System.err.println("IOE at Recvthr");
                    } /*catch (ClassNotFoundException e) {
                        System.err.println("CNFEx");
                    }*/
                }
            }
        };
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing( WindowEvent e )
                    {
                        recvthr.exit();
                        
                        try{
                            out.writeUTF(username+" disconnected");
                           out.writeUTF("/exit");
                           out.flush();
                            in.close();
                            out.close();
                            sock.close();
                            if(serv!=null){
                                serv.exit();
                            }
                        }catch(IOException ex5){
                            System.err.println("IOE at closing socket");
                        }catch(Exception ex7){
                            System.err.println("Error at closing sockets");
                        }
                         System.exit( 0 );
                    }
                 });
        this.setVisible(true);
        this.setSize(600,400);
        recvthr.start();
        try{
        out.writeUTF(username+" connected");
        out.flush();
        }catch(IOException ex){
            System.err.println("Can't send User connected msg");
        }
        repaint();
        this.paintAll(this.getGraphics());
        
    }

    
    /**
     * @param args
     */
    public static void main(String[] args) {
       new Client();

    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==send||e.getSource()==text){
            try{
            out.writeUTF(username+": "+text.getText());
            out.flush();
            text.setText("");
            }catch(IOException ex){
                System.err.println("IOException");
                
            }
        }
        
    }
    
    public void paint(Graphics g){
        super.paint(g);
    }
    
}
