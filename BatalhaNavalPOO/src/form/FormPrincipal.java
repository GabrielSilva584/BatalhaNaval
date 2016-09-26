package form;

import board.Label;
import board.LabelLeft;
import board.LabelTop;
import game.GameControllerP1;
import board.Tabuleiro;
import game.Patrulha;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;
import net.ChatController;
import net.Connection;

/**
 *
 * @author Gabriel
 */
public class FormPrincipal extends javax.swing.JFrame{
    
    private Tabuleiro boardP1 = null, boardP2 = null;
    private LabelLeft labelL1 = null, labelL2 = null;
    private LabelTop labelT1 = null, labelT2 = null;
    private ChatController chat = null;
    private GameControllerP1 controller1 = null, controller2 = null;
    private Connection connection = null;
    boolean isConnected = false;
    
    public FormPrincipal(Tabuleiro boardP1, Tabuleiro boardP2) throws IOException {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        
        this.boardP1 = boardP1;
        this.boardP2 = boardP2;
        
        initBoardLabels();
        
        initBoards(jPJogo1, boardP1);
        initBoards(jPJogo2, boardP2);
        
        setLocationRelativeTo(null);
        statusCheck();
        
        DefaultCaret caret = (DefaultCaret)jTPChat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        chat = new ChatController(jTPChat.getStyledDocument());
        connection = new Connection(chat);
        
        atualizaIP();
    }
    
    private void initBoards(JPanel panel, Tabuleiro board){
      
        Dimension area = new Dimension(panel.getWidth(), panel.getHeight());

        board.setPreferredSize(area);
        board.setBackground(Color.white);
        panel.setLayout(new GridLayout(1, 1));
        
        panel.add(board);  
    }
    
    private void initLabel(JPanel panel, Label board){
      
        Dimension area = new Dimension(panel.getWidth(), panel.getHeight());

        board.setPreferredSize(area);
        board.setBackground(Color.white);
        panel.setLayout(new GridLayout(1, 1));
        
        panel.add(board);  
    }
    
    private void initBoardLabels(){
        labelL1 = new LabelLeft();
        initLabel(jPLeft1, labelL1);
        labelL2 = new LabelLeft();
        initLabel(jPLeft2, labelL2);
        
        labelT1 = new LabelTop();
        initLabel(jPTop1, labelT1);
        labelT2 = new LabelTop();
        initLabel(jPTop2, labelT2);
    }
    
    public void setController1(GameControllerP1 controller){
        this.controller1 = controller;
    }
    
    public void setController2(GameControllerP1 controller){
        this.controller2 = controller;
    }
    
    public void addController(Tabuleiro board, GameControllerP1 controller){
        board.addMouseListener(controller);
        board.addMouseMotionListener(controller);
    }
    
    public void statusCheck(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                jBEnviar.setEnabled(isConnected);
                jBDesconectar.setEnabled(isConnected);
                jBConectar.setEnabled(!isConnected);
                jBIniciarServer.setEnabled(!isConnected);
                jTFMensagem.setEnabled(isConnected);
                jTFIP.setEnabled(!isConnected);
                jTFNome.setEnabled(!isConnected);
            } 
        }).start();
    }
    
    public void isConnecting(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                jBConectar.setEnabled(false);
                jBIniciarServer.setEnabled(false);
                jTFIP.setEnabled(false);
                jTFNome.setEnabled(false);
            } 
        }).start();
    }
    
    public void atualizaIP(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                jLLocalIP2.setText(connection.getIP());
            }
        }).start();
    }
    
    public JPanel getBoard1() {
        return jPJogo1;
    }
    
    public JPanel getBoard2() {
        return jPJogo2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTFMensagem = new javax.swing.JTextField();
        jBEnviar = new javax.swing.JButton();
        jBDesconectar = new javax.swing.JButton();
        jBIniciarServer = new javax.swing.JButton();
        jLNome = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jLPorta = new javax.swing.JLabel();
        jTFIP = new javax.swing.JTextField();
        jBConectar = new javax.swing.JButton();
        jLLocalIP2 = new javax.swing.JLabel();
        jLLocalIP1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPChat = new javax.swing.JTextPane();
        jPJogo2 = new javax.swing.JPanel();
        jPJogo1 = new javax.swing.JPanel();
        jPLeft1 = new javax.swing.JPanel();
        jPTop1 = new javax.swing.JPanel();
        jPLeft2 = new javax.swing.JPanel();
        jPTop2 = new javax.swing.JPanel();
        jBAjuda = new javax.swing.JButton();
        jLPortaAvioes = new javax.swing.JLabel();
        jLDestroyer = new javax.swing.JLabel();
        jLCruzador = new javax.swing.JLabel();
        jLSubmarino = new javax.swing.JLabel();
        jLPatrulha = new javax.swing.JLabel();
        jBRotacionar = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor");

        jTFMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFMensagemActionPerformed(evt);
            }
        });

        jBEnviar.setText("Enviar");
        jBEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEnviarActionPerformed(evt);
            }
        });

        jBDesconectar.setText("Desconectar");
        jBDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDesconectarActionPerformed(evt);
            }
        });

        jBIniciarServer.setText("Hostear");
        jBIniciarServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIniciarServerActionPerformed(evt);
            }
        });

        jLNome.setText("Nick:");

        jTFNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomeActionPerformed(evt);
            }
        });

        jLPorta.setText("IP:");

        jTFIP.setText("127.0.0.1");
        jTFIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIPActionPerformed(evt);
            }
        });

        jBConectar.setText("Conectar");
        jBConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConectarActionPerformed(evt);
            }
        });

        jLLocalIP2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLLocalIP2.setForeground(new java.awt.Color(0, 0, 255));
        jLLocalIP2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLLocalIP2.setText("127.0.0.1");
        jLLocalIP2.setToolTipText("IP desta máquina, clique para atualizar.");
        jLLocalIP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLLocalIP2MouseClicked(evt);
            }
        });

        jLLocalIP1.setText("Seu IP:");

        jTPChat.setEditable(false);
        jTPChat.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTPChat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTPChatCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jTPChat);

        jPJogo2.setBackground(new java.awt.Color(255, 255, 255));
        jPJogo2.setMaximumSize(new java.awt.Dimension(300, 300));
        jPJogo2.setMinimumSize(new java.awt.Dimension(300, 300));
        jPJogo2.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout jPJogo2Layout = new javax.swing.GroupLayout(jPJogo2);
        jPJogo2.setLayout(jPJogo2Layout);
        jPJogo2Layout.setHorizontalGroup(
            jPJogo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPJogo2Layout.setVerticalGroup(
            jPJogo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPJogo1.setBackground(new java.awt.Color(255, 255, 255));
        jPJogo1.setMaximumSize(new java.awt.Dimension(300, 300));
        jPJogo1.setMinimumSize(new java.awt.Dimension(300, 300));
        jPJogo1.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout jPJogo1Layout = new javax.swing.GroupLayout(jPJogo1);
        jPJogo1.setLayout(jPJogo1Layout);
        jPJogo1Layout.setHorizontalGroup(
            jPJogo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPJogo1Layout.setVerticalGroup(
            jPJogo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPLeft1.setBackground(new java.awt.Color(255, 255, 255));
        jPLeft1.setMaximumSize(new java.awt.Dimension(20, 300));
        jPLeft1.setMinimumSize(new java.awt.Dimension(20, 300));
        jPLeft1.setPreferredSize(new java.awt.Dimension(20, 300));

        javax.swing.GroupLayout jPLeft1Layout = new javax.swing.GroupLayout(jPLeft1);
        jPLeft1.setLayout(jPLeft1Layout);
        jPLeft1Layout.setHorizontalGroup(
            jPLeft1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPLeft1Layout.setVerticalGroup(
            jPLeft1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPTop1.setBackground(new java.awt.Color(255, 255, 255));
        jPTop1.setMaximumSize(new java.awt.Dimension(300, 20));
        jPTop1.setMinimumSize(new java.awt.Dimension(300, 20));
        jPTop1.setPreferredSize(new java.awt.Dimension(300, 20));

        javax.swing.GroupLayout jPTop1Layout = new javax.swing.GroupLayout(jPTop1);
        jPTop1.setLayout(jPTop1Layout);
        jPTop1Layout.setHorizontalGroup(
            jPTop1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPTop1Layout.setVerticalGroup(
            jPTop1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPLeft2.setBackground(new java.awt.Color(255, 255, 255));
        jPLeft2.setMaximumSize(new java.awt.Dimension(20, 300));
        jPLeft2.setMinimumSize(new java.awt.Dimension(20, 300));
        jPLeft2.setPreferredSize(new java.awt.Dimension(20, 300));

        javax.swing.GroupLayout jPLeft2Layout = new javax.swing.GroupLayout(jPLeft2);
        jPLeft2.setLayout(jPLeft2Layout);
        jPLeft2Layout.setHorizontalGroup(
            jPLeft2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPLeft2Layout.setVerticalGroup(
            jPLeft2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPTop2.setBackground(new java.awt.Color(255, 255, 255));
        jPTop2.setPreferredSize(new java.awt.Dimension(300, 20));

        javax.swing.GroupLayout jPTop2Layout = new javax.swing.GroupLayout(jPTop2);
        jPTop2.setLayout(jPTop2Layout);
        jPTop2Layout.setHorizontalGroup(
            jPTop2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPTop2Layout.setVerticalGroup(
            jPTop2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jBAjuda.setText("Ajuda");
        jBAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAjudaActionPerformed(evt);
            }
        });

        jLPortaAvioes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/portaavioes.png"))); // NOI18N
        jLPortaAvioes.setToolTipText("");

        jLDestroyer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/destroyer.png"))); // NOI18N

        jLCruzador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cruzador.png"))); // NOI18N

        jLSubmarino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/submarino.png"))); // NOI18N

        jLPatrulha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/patrulha.png"))); // NOI18N
        jLPatrulha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLPatrulhaMouseClicked(evt);
            }
        });

        jBRotacionar.setText("Rotacionar");
        jBRotacionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRotacionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTFMensagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAjuda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBDesconectar))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLPorta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLPortaAvioes)
                                    .addComponent(jLDestroyer)
                                    .addComponent(jLCruzador)
                                    .addComponent(jLSubmarino)
                                    .addComponent(jLPatrulha)
                                    .addComponent(jBRotacionar))
                                .addGap(243, 243, 243)
                                .addComponent(jPLeft1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPTop1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPJogo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPLeft2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPTop2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPJogo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 32, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLLocalIP1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLLocalIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBIniciarServer)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPTop2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPLeft2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPJogo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPTop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPLeft1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPJogo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLPortaAvioes)
                                .addGap(18, 18, 18)
                                .addComponent(jLDestroyer)
                                .addGap(18, 18, 18)
                                .addComponent(jLCruzador)
                                .addGap(18, 18, 18)
                                .addComponent(jLSubmarino)
                                .addGap(18, 18, 18)
                                .addComponent(jLPatrulha)
                                .addGap(18, 18, 18)
                                .addComponent(jBRotacionar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLPorta)
                            .addComponent(jLNome)
                            .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLLocalIP1)
                            .addComponent(jLLocalIP2)
                            .addComponent(jBConectar)
                            .addComponent(jBIniciarServer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEnviar)
                    .addComponent(jBDesconectar)
                    .addComponent(jBAjuda))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDesconectarActionPerformed
        connection.close();
        isConnected = false;
        statusCheck();
    }//GEN-LAST:event_jBDesconectarActionPerformed

    private void jBIniciarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIniciarServerActionPerformed
        chat.insertString("Aguardando Conexão...\n", 1);
        isConnecting();
        new Thread(new Runnable() {
            @Override
            public void run() {
                isConnected = connection.host(jTFNome.getText(),jTFIP.getText());
                statusCheck();
                connection.listen();
                isConnected = false;
                statusCheck();
            }
        }).start();
    }//GEN-LAST:event_jBIniciarServerActionPerformed

    private void jBEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnviarActionPerformed
        connection.send(jTFMensagem.getText());
        jTFMensagem.setText("");
    }//GEN-LAST:event_jBEnviarActionPerformed

    private void jTFMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFMensagemActionPerformed
        jBEnviarActionPerformed(evt);
    }//GEN-LAST:event_jTFMensagemActionPerformed

    private void jTFNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomeActionPerformed

    private void jTFIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIPActionPerformed

    private void jBConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConectarActionPerformed
        isConnecting();
        new Thread(new Runnable() {
            @Override
            public void run() {
                isConnected = connection.connect(jTFNome.getText(), jTFIP.getText());
                statusCheck();
                connection.listen();
                isConnected = false;
                statusCheck();
                connection.disconnect();
            }
        }).start();
    }//GEN-LAST:event_jBConectarActionPerformed

    private void jLLocalIP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLLocalIP2MouseClicked
        atualizaIP();
    }//GEN-LAST:event_jLLocalIP2MouseClicked

    private void jTPChatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTPChatCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPChatCaretUpdate

    private void jBAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAjudaActionPerformed
        new FormAjuda(null,true).setVisible(true);
    }//GEN-LAST:event_jBAjudaActionPerformed

    private void jLPatrulhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLPatrulhaMouseClicked
        controller1.setN(new Patrulha());
    }//GEN-LAST:event_jLPatrulhaMouseClicked

    private void jBRotacionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRotacionarActionPerformed
        controller1.rotacionarN();
    }//GEN-LAST:event_jBRotacionarActionPerformed
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAjuda;
    private javax.swing.JButton jBConectar;
    private javax.swing.JButton jBDesconectar;
    private javax.swing.JButton jBEnviar;
    private javax.swing.JButton jBIniciarServer;
    private javax.swing.JButton jBRotacionar;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLCruzador;
    private javax.swing.JLabel jLDestroyer;
    private javax.swing.JLabel jLLocalIP1;
    private javax.swing.JLabel jLLocalIP2;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLPatrulha;
    private javax.swing.JLabel jLPorta;
    private javax.swing.JLabel jLPortaAvioes;
    private javax.swing.JLabel jLSubmarino;
    private javax.swing.JPanel jPJogo1;
    private javax.swing.JPanel jPJogo2;
    private javax.swing.JPanel jPLeft1;
    private javax.swing.JPanel jPLeft2;
    private javax.swing.JPanel jPTop1;
    private javax.swing.JPanel jPTop2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFIP;
    private javax.swing.JTextField jTFMensagem;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTextPane jTPChat;
    // End of variables declaration//GEN-END:variables
    
    public void update(int i, Graphics g){
        if(i==1){
            controller1.drawMouseQuadrante((Graphics2D) g);
        }
        if(i==2){
            controller2.drawMouseQuadrante((Graphics2D) g);
        }
    }
}
