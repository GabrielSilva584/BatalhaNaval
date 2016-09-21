/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;
import net.Connection;

/**
 *
 * @author Aluno
 */
public class FormPrincipal extends javax.swing.JFrame {

    private Connection connection = new Connection();
    boolean isConnected = false;
    
    
    
    public FormPrincipal() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
        statusCheck();
        
        DefaultCaret caret = (DefaultCaret)jTAChat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
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
        jSPChat = new javax.swing.JScrollPane();
        jTAChat = new javax.swing.JTextArea();
        jTFMensagem = new javax.swing.JTextField();
        jBEnviar = new javax.swing.JButton();
        jBFecharServer = new javax.swing.JButton();
        jBIniciarServer = new javax.swing.JButton();
        jLNome = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jLPorta = new javax.swing.JLabel();
        jTFIP = new javax.swing.JTextField();
        jBConectar = new javax.swing.JButton();
        jLLocalIP2 = new javax.swing.JLabel();
        jLLocalIP1 = new javax.swing.JLabel();

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

        jSPChat.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTAChat.setEditable(false);
        jTAChat.setColumns(20);
        jTAChat.setLineWrap(true);
        jTAChat.setRows(5);
        jSPChat.setViewportView(jTAChat);

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

        jBFecharServer.setText("Sair");
        jBFecharServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFecharServerActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLNome)
                                    .addComponent(jLPorta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFNome)
                                    .addComponent(jTFIP, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBIniciarServer))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLLocalIP1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLLocalIP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFMensagem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBEnviar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBFecharServer)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSPChat, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 657, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNome)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBConectar)
                    .addComponent(jBIniciarServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLPorta)
                    .addComponent(jLLocalIP2)
                    .addComponent(jLLocalIP1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSPChat, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEnviar)
                    .addComponent(jBFecharServer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBFecharServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFecharServerActionPerformed
        connection.close();
    }//GEN-LAST:event_jBFecharServerActionPerformed

    private void jBIniciarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIniciarServerActionPerformed
        jTAChat.append("Aguardando Conexão...\n");
        isConnecting();
        new Thread(new Runnable() {
            @Override
            public void run() {
                isConnected = connection.host(jTAChat);
                connection.listen(jTAChat);
                statusCheck();
            }
        }).start();
    }//GEN-LAST:event_jBIniciarServerActionPerformed

    private void jBEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnviarActionPerformed
        connection.send(jTFNome.getText(), jTFMensagem.getText(), jTAChat);
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
                isConnected = connection.connect(jTFNome.getText(), jTFIP.getText(), jTAChat);
                connection.listen(jTAChat);
                statusCheck();
            }
        }).start();
    }//GEN-LAST:event_jBConectarActionPerformed

    private void jLLocalIP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLLocalIP2MouseClicked
        atualizaIP();
    }//GEN-LAST:event_jLLocalIP2MouseClicked
    
    public void statusCheck(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                jBEnviar.setEnabled(isConnected);
                jBFecharServer.setEnabled(isConnected);
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
    private javax.swing.JButton jBFecharServer;
    private javax.swing.JButton jBIniciarServer;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLLocalIP1;
    private javax.swing.JLabel jLLocalIP2;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLPorta;
    private javax.swing.JScrollPane jSPChat;
    private javax.swing.JTextArea jTAChat;
    private javax.swing.JTextField jTFIP;
    private javax.swing.JTextField jTFMensagem;
    private javax.swing.JTextField jTFNome;
    // End of variables declaration//GEN-END:variables
}
