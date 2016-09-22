package Forms;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;
import net.ChatController;
import net.Connection;

/**
 *
 * @author Gabriel
 */
public class FormPrincipal extends javax.swing.JFrame {
    
    private ChatController chat = null;
    private Connection connection = null;
    boolean isConnected = false;
    
    public FormPrincipal() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
        statusCheck();
        
        DefaultCaret caret = (DefaultCaret)jTPChat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        chat = new ChatController(jTPChat.getStyledDocument());
        connection = new Connection(chat);
        
        atualizaIP();
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
        jBSair = new javax.swing.JButton();
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

        jBSair.setText("Sair");
        jBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSairActionPerformed(evt);
            }
        });

        jBIniciarServer.setText("Hostear");
        jBIniciarServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIniciarServerActionPerformed(evt);
            }
        });

        jLNome.setText("Nome:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTFMensagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBSair))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLPorta)
                        .addGap(21, 21, 21)
                        .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNome, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLLocalIP1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLLocalIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(jBConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBIniciarServer)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(368, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLPorta))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLNome)
                        .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBConectar)
                        .addComponent(jBIniciarServer)
                        .addComponent(jLLocalIP1)
                        .addComponent(jLLocalIP2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEnviar)
                    .addComponent(jBSair))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        connection.close();
        isConnected = false;
        statusCheck();
    }//GEN-LAST:event_jBSairActionPerformed

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
    
    public void statusCheck(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                jBEnviar.setEnabled(isConnected);
                jBSair.setEnabled(isConnected);
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormPrincipal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBConectar;
    private javax.swing.JButton jBEnviar;
    private javax.swing.JButton jBIniciarServer;
    private javax.swing.JButton jBSair;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLLocalIP1;
    private javax.swing.JLabel jLLocalIP2;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLPorta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFIP;
    private javax.swing.JTextField jTFMensagem;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTextPane jTPChat;
    // End of variables declaration//GEN-END:variables
}
