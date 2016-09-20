package net;

import Forms.Servidor2;
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
    
    public Connection(){
        cliente = null;
        servidor = null;
        buffer = null;
    }
    
    public void close(){
        try {
            cliente.close();
            servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean host(final JTextArea jTAChat){
        try {
            servidor = new ServerSocket(12345);
            cliente  = servidor.accept();
            jTAChat.append("Cliente "
                    +cliente.getInetAddress().getHostAddress()
                    +" conectou-se...\n");
            return true;
        } catch (IOException ex) {
            jTAChat.append("Erro de conexão!");
            Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean connect(String nome, String IP, final JTextArea jTAChat){
        try {
            cliente = new Socket(IP, 12345);
            jTAChat.append(nome+" se conectou ao servidor!\n");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            jTAChat.append("Erro de conexão!\n");
            return false;
        }
    }
    
    public void listen(final JTextArea jTAChat){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(cliente != null){
                    try{
                        while(true){
                            buffer = new BufferedReader(new InputStreamReader(
                                    cliente.getInputStream()));
                            String msg = buffer.readLine();
                            jTAChat.append(msg+"\n");
                        }
                    }
                    catch(IOException ex){
                    }
                }
            }
        }).start();     
    }
    
    public void send(String nome, String msg, final JTextArea jTAChat){
        PrintStream ps;
        try{
            ps = new PrintStream(cliente.getOutputStream());
            String msg2 = nome + ": " + msg;
            ps.println(msg2);
            jTAChat.append(msg2 + "\n");            
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
