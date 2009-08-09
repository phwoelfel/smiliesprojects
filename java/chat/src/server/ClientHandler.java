/*
 * Created on Sep 21, 2005
 *
 */
package server;

//import java.awt.*;
//import java.awt.event.*;

//import javax.swing.*;
import java.io.*;
import java.net.*;
//import java.util.*;


public class ClientHandler extends Thread {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean active;
    private Server serv;
    private Socket sock;
    
    public ClientHandler(Server serve,Socket socket) {
        super();
        active=true;
        serv=serve;
        sock=socket;
       try{
           out=new ObjectOutputStream(sock.getOutputStream());
           in= new ObjectInputStream(sock.getInputStream());
       }catch(IOException e1){
           System.err.println("IOE at getting streams at clienthandler");
       }
    }

    public void run(){
        while(active){
            try{
                String msg=(String)in.readUTF();
                if(msg.equalsIgnoreCase("/exit")){
                    active=false;
                    System.out.println("Client closed Connection");
                    serv.terminate(this);
                }else
                    serv.chat(msg);
            }catch(IOException e3){
                active=false;
                System.err.println("IOE at runing Clienthandler");
            }catch(ClassCastException e5){
                System.err.println("Classcastex at Clienthandler");
            }/* catch (ClassNotFoundException e) {
                System.err.println("CNFEx");
            }*/
            
        }
        try{
            in.close();
            out.close();
            sock.close();
        }catch(IOException ex4){
            System.err.println("Error at closing streams at ClientHandler");
        }
    }
    public void send(String msg){
        try{
            out.writeUTF(msg);
            out.flush();
        }catch(IOException e2){
            System.err.println("IOE at sending msg to client at ClientHandler");
            active=false;
        }
    }
    public void exit(){
        active=false;
    }
}
