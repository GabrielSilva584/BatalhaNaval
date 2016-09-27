package net;

import boats.Auxiliar;
import form.FormPrincipal;
import game.Game;
import game.GameControllerP2;
import java.awt.Point;
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
    private Game model1 = null, model2 = null;
    private GameControllerP2 controller2 = null;
    private Auxiliar boatAux = null;
    private boolean localReady, remoteReady;
    
    public Connection(ChatController remoteChat){
        cliente = null;
        servidor = null;
        buffer = null;
        chat = remoteChat;
        name = null;
        IP = null;
        remoteName = null;
        localReady = false;
        remoteReady = false;
    }
    
    public void setModel1(Game model) {
        this.model1 = model;
    }
    
    public void setModel2(Game model) {
        this.model2 = model;
    }
    
    public void setController2(GameControllerP2 controller) {
        this.controller2 = controller;
    }
    
    public void close(){
        PrintStream ps;
        try {
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("flw");
            chat.insertString("Você saiu...\n", 1);
            if(cliente != null)cliente.close();
            if(servidor != null)servidor.close();
            reset();
        } catch (IOException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            if(cliente != null){
                chat.insertString(remoteName + " saiu...\n", 1);
                cliente.close();
            }
            if(servidor != null)servidor.close();
            reset();
        } catch (IOException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reset(){
        cliente = null;
        servidor = null;
        buffer = null;
        name = null;
        IP = null;
        remoteName = null;
        notReady();
    }
    
    public boolean host(String n, String i){
        PrintStream ps;
        name = n;
        if(name.equals("")){
            name = "Host";
        }
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
        if(name.equals("")){
            name = "Client";
        }
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
        try{
            while(cliente!=null){
                buffer = new BufferedReader(new InputStreamReader(
                        cliente.getInputStream()));
                String aux = buffer.readLine();
                if(aux!=null){
                    if(aux.equals("msg")){
                        
                        chat.insertMessage(remoteName, buffer.readLine(), 3);
                        
                    }else if(aux.equals("flw")){
                        
                        disconnect();
                        
                    }else if(aux.equals("atk")){
                        
                        Point p = new Point();
                        p.x = Integer.parseInt(buffer.readLine());
                        p.y = Integer.parseInt(buffer.readLine());
                        model1.recebeAtaque(p.x, p.y);
                        
                        boolean acertou = false;
                        String type = "";
                        
                        if(model1.findNavio(p.x, p.y)!=null){
                            acertou = true;
                            type = model1.findNavio(p.x, p.y).getType();
                        }
                        
                        chat.attackedMessage(remoteName, p.x, p.y, acertou, type);
                        
                        PrintStream ps;
                        try{
                            ps = new PrintStream(cliente.getOutputStream());
                            ps.println("ai");
                            ps.println(p.x);
                            ps.println(p.y);
                            ps.println(type);
                            if(model1.FimDeJogo()){
                                ps.println("perdi");
                                chat.youLoseMessage(remoteName);
                            }
                        }catch(IOException ex){
                            ex.printStackTrace();
                        }
                        
                    }else if(aux.equals("ai")){
                        
                        int xAux = Integer.parseInt(buffer.readLine());
                        int yAux = Integer.parseInt(buffer.readLine());
                        aux = buffer.readLine();
                        if(aux.equals("")){
                            chat.attackResultMessage(false, "");
                        }else{
                            model2.addMarker(xAux, yAux, aux);
                            chat.attackResultMessage(true, aux);
                        }
                        
                    }else if(aux.equals("rdy")){
                        
                        remoteReady = true;
                        if(isEveryoneReady() && servidor!=null){
                            controller2.seuTurno();
                            chat.yourTurnMessage();
                        }
                        
                    }else if(aux.equals("suavez")){
                        
                        controller2.seuTurno();
                        chat.yourTurnMessage();
                        
                    }else if(aux.equals("perdi")){
                        
                        controller2.finalizaJogo();
                        chat.youWinMessage();
                        
                    }
                }
            }
            disconnect();
        }
        catch(IOException ex){
        }
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
    
    public void atk(int x, int y){
        PrintStream ps;
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("atk");
            ps.println(x);
            ps.println(y);
            chat.attackMessage(x, y);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void ready(){
        localReady = true;
        PrintStream ps;
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("rdy");
        }catch(IOException ex){
            ex.printStackTrace();
        }
        if(isEveryoneReady() && servidor!=null){
            controller2.seuTurno();
            chat.yourTurnMessage();
        }
    }
    
    public void fimDeTurno(){
        PrintStream ps;
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("suavez");
            chat.remoteTurnMessage(remoteName);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void notReady(){
        localReady = false;
        remoteReady = false;
    }
    
    public boolean isEveryoneReady(){
        return (localReady && remoteReady);
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
