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
    private PrintStream ps;
    private ChatController chat;
    private String name, IP, remoteName;
    private Game model1 = null, model2 = null;
    private GameControllerP2 controller2 = null;
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
            
            buffer = new BufferedReader(new InputStreamReader(
                        cliente.getInputStream()));
            String aux;
            
            while(cliente!=null){
                aux = buffer.readLine();
                if(aux!=null){
                    if(aux.equals("msg")){
                        
                        chat.insertMessage(remoteName, buffer.readLine(), 3);
                        
                    }else if(aux.equals("flw")){
                        
                        disconnect();
                        
                    }else if(aux.equals("atk")){
                        
                        int x = Integer.parseInt(buffer.readLine());
                        int y = Integer.parseInt(buffer.readLine());
                        int atkRestantes = Integer.parseInt(buffer.readLine());
                        
                        listenAtk(x, y, atkRestantes);
                        
                    }else if(aux.equals("ai")){
                        
                        int x = Integer.parseInt(buffer.readLine());
                        int y = Integer.parseInt(buffer.readLine());
                        String type = buffer.readLine();
                        int atkRestantes = Integer.parseInt(buffer.readLine());
                        
                        listenAi(x, y, atkRestantes, type);
                        
                    }else if(aux.equals("rdy")){
                        
                        chat.readyMessage(remoteName, 1);
                        remoteReady = true;
                        
                        if(isEveryoneReady() && servidor!=null){
                            fimDeTurno();
                            chat.yourTurnMessage();
                            controller2.seuTurno();
                        }
                        
                    }else if(aux.equals("mt")){
                        
                        chat.remoteTurnMessage(remoteName);
                        
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
    
    public void listenAtk(final int x, final int y, final int atkRestantes){
        new Thread(new Runnable(){
            @Override
            public void run() {
                model1.recebeAtaque(x, y);

                boolean acertou = false;
                String type = "";

                if(model1.findNavio(x, y)!=null){
                    acertou = true;
                    type = model1.findNavio(x, y).getType();
                }

                chat.attackedMessage(remoteName, x, y, acertou, type, atkRestantes);

                PrintStream ps;
                try{
                    ps = new PrintStream(cliente.getOutputStream());
                    ps.println("ai");
                    ps.println(x);
                    ps.println(y);
                    ps.println(type);
                    ps.println(atkRestantes);
                }catch(IOException ex){
                    ex.printStackTrace();
                }

                if(model1.FimDeJogo()){
                    try {
                        ps = new PrintStream(cliente.getOutputStream());
                        ps.println("perdi");
                    } catch (IOException ex) {
                        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    chat.youLoseMessage(remoteName);
                }else if(atkRestantes==0){
                    controller2.seuTurno();
                    chat.yourTurnMessage();
                    fimDeTurno();
                }
            } 
        }).start();
    }
    
    public void listenAi(final int x, final int y, final int atkRestantes, final String type){
        new Thread(new Runnable(){
            @Override
            public void run() {   
                if(type.equals("")){
                    chat.attackResultMessage(false, "", atkRestantes);
                }else{
                    model2.addMarker(x, y, type);
                    chat.attackResultMessage(true, type, atkRestantes);
                }
            }}).start();
    }
    
    public void send(String msg){
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("msg");
            ps.println(msg);
            chat.insertMessage(name, msg, 2);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void atk(int x, int y, int atkRestantes){
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("atk");
            ps.println(x);
            ps.println(y);
            ps.println(atkRestantes);
            chat.attackMessage(x, y);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void ready(){
        localReady = true;
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("rdy");
        }catch(IOException ex){
            ex.printStackTrace();
        }
        chat.readyMessage(remoteName, 0);
        if(isEveryoneReady() && servidor!=null){
            fimDeTurno();
            controller2.seuTurno();
            chat.yourTurnMessage();
        }
    }
    
    public void fimDeTurno(){
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println("mt");
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
