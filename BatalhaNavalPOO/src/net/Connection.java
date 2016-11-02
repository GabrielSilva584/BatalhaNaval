package net;

import boats.Navios;
import form.FormPrincipal;
import game.Game;
import game.GameControllerP2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Connection {
    static final int COLOR_BLACK = 0;
    static final int COLOR_RED = 1;
    static final int COLOR_BLUE = 2;
    static final int COLOR_GREEN = 3;
    static final int COLOR_ORANGE = 4;
    
    static final int LOCAL_PLAYER = 0;
    static final int REMOTE_PLAYER = 1;
    
    static final String INPUT_MSG = "a";
    static final String INPUT_END = "b";
    static final String INPUT_ATK = "c";
    static final String INPUT_LOST = "d";
    static final String INPUT_TURN = "e";
    static final String INPUT_READY = "f";
    static final String INPUT_SYNC_INIT = "g";
    static final String INPUT_SYNC_END = "h";
    static final String INPUT_LOAD_STATE_CONFIRM = "i";
    static final String INPUT_LOAD_YES = "j";
    static final String INPUT_LOAD_NO= "k";
    
    static final String MSG_YOU = "Você ";
    static final String MSG_WIN = "ganhou!\n";
    static final String MSG_LOST = "perdeu!\n";
    static final String MSG_YOUR_TURN = "Seu turno...\n";
    static final String MSG_REMOTE_TURN1 = "Turno de ";
    static final String MSG_REMOTE_TURN2 = "...\n";
    static final String MSG_READY = " está pronto\n";
    static final String MSG_CONN_ERROR = "Erro de conexão!\n";
    static final String MSG_EXITED = " saiu...\n";
    static final String MSG_REMOTE_CONNECTED = " conectou-se...\n";
    static final String MSG_YOU_CONNECTED = "Você se conectou ao servidor de ";
    
    private Game[] state;
    private Socket cliente;
    private ServerSocket servidor;
    private BufferedReader buffer;
    private PrintStream ps;
    private ChatController chat;
    private String name, IP, remoteName;
    private Game model1 = null, model2 = null;
    private GameControllerP2 controller2 = null;
    private boolean localReady, remoteReady, isSynchronizing = false;
    private int ataques;
    private FormPrincipal view;
    
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
        ataques = 0;
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

    public void setView(FormPrincipal view) {
        this.view = view;
    }
    
    public void close(){
        try {
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(INPUT_END);
            chat.insertString(MSG_YOU + MSG_EXITED, COLOR_RED);
            connectionEnded();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void disconnect(){
        if(remoteName!=null)chat.insertString(remoteName + MSG_EXITED, COLOR_RED);
        connectionEnded();
    }
    
    public void connectionEnded(){
        try {
            if(cliente != null)cliente.close();
            if(servidor != null)servidor.close();
            reset();
            model1.init();
            model2.init();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reset(){
        cliente = null;
        servidor = null;
        buffer = null;
        name = null;
        IP = null;
        remoteName = null;
        localReady = false;
        remoteReady = false;
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
                    + MSG_REMOTE_CONNECTED, COLOR_RED);
            
            return true;
        } catch (IOException ex) {
            chat.insertString(MSG_CONN_ERROR, COLOR_RED);
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
            chat.insertString(MSG_YOU_CONNECTED +remoteName + "!\n", COLOR_RED);
            return true;
        } catch (IOException ex) {
            chat.insertString(MSG_CONN_ERROR, COLOR_RED);
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void listen(){
        try{
            buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String aux;
            
            while(cliente!=null){
                aux = null;
                if(!isSynchronizing)aux = buffer.readLine();
                if(aux!=null){
                    if(aux.equals(INPUT_MSG)){
                        
                        chat.insertMessage(remoteName, buffer.readLine(), COLOR_GREEN);
                        
                    }else if(aux.equals(INPUT_END)){
                        
                        disconnect();
                        
                    }else if(aux.equals(INPUT_ATK)){
                        
                        ataques--;
                        int x = Integer.parseInt(buffer.readLine());
                        int y = Integer.parseInt(buffer.readLine());
                        
                        model1.recebeAtaque(x, y);

                        boolean acertou = false;
                        String type = "";

                        if(model1.findNavio(x, y)!=null){
                            acertou = true;
                            type = model1.findNavio(x, y).getType();
                        }

                        chat.attackMessage(remoteName, x, y, acertou, type, REMOTE_PLAYER);

                        if(model1.FimDeJogo()){
                            try {
                                ps = new PrintStream(cliente.getOutputStream());
                                ps.println(INPUT_LOST);
                            } catch (IOException ex) {
                                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            chat.insertString(MSG_YOU + MSG_LOST, COLOR_RED);
                        }else if(ataques==0){
                            controller2.seuTurno();
                            chat.insertString(MSG_YOUR_TURN, COLOR_RED);
                            fimDeTurno();
                        }
                        
                    }else if(aux.equals(INPUT_READY)){
                        
                        chat.insertString(remoteName + MSG_READY, COLOR_RED);
                        remoteReady = true;
                        
                        if(isEveryoneReady() && servidor!=null){
                            view.setEnabledCarregar(false);
                            view.setEnabledSalvar(true);
                            fimDeTurno();
                            chat.insertString(MSG_YOUR_TURN, COLOR_RED);
                            controller2.seuTurno();
                        }
                        
                    }else if(aux.equals(INPUT_TURN)){
                        
                        ataques = 3;
                        chat.insertString(MSG_REMOTE_TURN1 + remoteName 
                                + MSG_REMOTE_TURN2, COLOR_RED);
                        
                    }else if(aux.equals(INPUT_LOST)){
                        
                        controller2.finalizaJogo();
                        chat.insertString(MSG_YOU + MSG_WIN, COLOR_RED);
                        
                    }else if(aux.equals(INPUT_SYNC_INIT)){
                        
                        synchronizeReceive();
                        
                    }else if(aux.equals(INPUT_LOAD_STATE_CONFIRM)){
                        
                        int i = JOptionPane.showConfirmDialog(null, remoteName 
                                + " está carregando um estado anterior. Você Aceita?",
                                "Carregar Estado", JOptionPane.YES_NO_OPTION );
                        
                        ps = new PrintStream(cliente.getOutputStream());
                        if(i == 0){
                            view.enableBoats(false);
                            view.setEnabledCarregar(false);
                            view.setEnabledIniciar(false);
                            ps.println(INPUT_LOAD_YES);
                            ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());
                            state = (Game[])is.readObject();
                            model1.changeInto(state[1]);
                            model2.changeInto(state[0]);
                            chat.insertString("Estado carregado.\n", COLOR_RED);
                        }else{
                            ps.println(INPUT_LOAD_NO);
                        }
                    }else if(aux.equals(INPUT_LOAD_YES)){
                        view.enableBoats(false);
                        view.setEnabledCarregar(false);
                        view.setEnabledIniciar(false);
                        ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
                        os.writeObject(state);
                        model1.changeInto(state[0]);
                        model2.changeInto(state[1]);
                        chat.insertString(remoteName + " aceitou o pedido. Estado carregado\n", COLOR_RED);
                        fimDeTurno();
                        controller2.seuTurno();
                        chat.insertString(MSG_YOUR_TURN, COLOR_RED);
                    }
                    else if(aux.equals(INPUT_LOAD_NO)){
                        chat.insertString(remoteName + " não aceitou o pedido.\n", COLOR_RED);
                    }
                }
            }
        }
        catch(IOException ex){
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(String msg){
        try{
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(INPUT_MSG);
            ps.println(msg);
            chat.insertMessage(name, msg, COLOR_BLUE);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void atk(int x, int y, int atkRestantes){
        try{
            ataques--;
            setEnabledSalvar();
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(INPUT_ATK);
            ps.println(x);
            ps.println(y);
            String type = "";
            boolean acertou = false;
            Navios n = model2.findNavio(x, y);
            if(n!=null){
                acertou = true;
                type = n.getType();
            }
            chat.attackMessage(remoteName, x, y, acertou, type, LOCAL_PLAYER);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void synchronizeSend(){
        try{
            isSynchronizing = true;
            localReady = true;
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(INPUT_READY);
            chat.insertString(MSG_YOU + MSG_READY, COLOR_RED);
            ps.println(INPUT_SYNC_INIT);
            ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
            os.writeObject(model1);
            if(isEveryoneReady() && servidor!=null){
                view.setEnabledCarregar(false);
                fimDeTurno();
                controller2.seuTurno();
                chat.insertString(MSG_YOUR_TURN, COLOR_RED);
            }
            isSynchronizing = false;
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void synchronizeReceive(){
        try{
            isSynchronizing = true;
            ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());
            model2.changeInto((Game)is.readObject());
            isSynchronizing = false;
        }catch(IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fimDeTurno(){
        try{
            ataques = 3;
            setEnabledSalvar();
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(INPUT_TURN);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean isEveryoneReady(){
        boolean ready = (localReady && remoteReady);
        if(ready)view.setEnabledCarregar(false);
        return ready;
    }
    
    public String getIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return "Desconhecido";
        }
    }
    
    public Game[] getState(){
        state = new Game[2];
        state[0] = model1;
        state[1] = model2;
        
        return state;
    }
    
    public void setState(Game[] state){
        try {
            this.state = state;
            ps = new PrintStream(cliente.getOutputStream());
            ps.println(INPUT_LOAD_STATE_CONFIRM);
            chat.insertString("Aguardando confirmação do jogador remoto para carregar o estado.\n", COLOR_RED);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setEnabledSalvar(){
        if(ataques == 3){
            view.setEnabledSalvar(true);
        }else{
            view.setEnabledSalvar(false);
        }
    }
}
