package net;

import Forms.FormPrincipal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Gabriel
 */
public class Connection {
    private Socket cliente;
    private ServerSocket servidor;
    private BufferedReader buffer;
    private ChatController chat;
    private String name, IP, remoteName;
    
    public Connection(ChatController remoteChat){
        cliente = null;
        servidor = null;
        buffer = null;
        chat = remoteChat;
        name = null;
        IP = null;
    }
    
    public void close(){
        try {
            cliente.close();
            if(servidor != null)servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean host(String n, String i){
        PrintStream ps;
        name = n;
        IP = i;
        try {
            servidor = new ServerSocket(12345);
            cliente  = servidor.accept();
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(name);
            buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            remoteName = buffer.readLine();
            chat.insertString(remoteName
                    +" ("+cliente.getInetAddress().getHostAddress()+")"
                    +" conectou-se...\n", 1);
            return true;
        } catch (IOException ex) {
            chat.insertString("Erro de conexão!\n", 1);
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean connect(String n, String i){
        PrintStream ps;
        name = n;
        IP = i;
        try {
            cliente = new Socket(IP, 12345);
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(name);
            buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            remoteName = buffer.readLine();
            chat.insertString("Você se conectou ao servidor de "
                    +remoteName+"!\n", 1);
            return true;
        } catch (IOException ex) {
            chat.insertString("Erro de conexão!\n", 1);
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void listen(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(cliente!=null){
                        buffer = new BufferedReader(new InputStreamReader(
                                cliente.getInputStream()));
                        String aux = buffer.readLine();
                        if(aux!=null && aux.equals("msg")){
                            chat.insertMessage(remoteName, buffer.readLine(), 3);
                        }
                    }
                }
                catch(IOException ex){
                }
            }
        }).start();     
    }
    
    public void send(String msg){
        PrintStream ps;
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("msg");
            ps.println(msg);
            chat.insertMessage(name, msg, 2);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public String getIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return "Desconhecido";
        }
    }
}
