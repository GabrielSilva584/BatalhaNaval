/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import board.Tabuleiro;
import form.FormPrincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.Connection;

/**
 *
 * @author Gabriel
 */
public class Main {
    public static void main(final String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Tabuleiro boardP1 = new Tabuleiro(1);
                    Tabuleiro boardP2 = new Tabuleiro(2);
                    
                    FormPrincipal view = new FormPrincipal(boardP1, boardP2);
                    
                    Game model1 = new Game();
                    Game model2 = new Game();
                    
                    view.getConnection().setModel1(model1);
                    view.getConnection().setModel2(model2);
                    
                    boardP1.setView(view);
                    boardP1.registerObserver(model1);
                    
                    boardP2.setView(view);
                    boardP2.registerObserver(model2);
                    
                    GameControllerP1 controller1 = new GameControllerP1();
                    GameControllerP2 controller2 = new GameControllerP2();
                    
                    controller1.addView(view);
                    controller1.addModel(model1);
                    
                    controller2.addView(view);
                    controller2.addModel(model2);
                    
                    view.setController1(controller1);
                    view.setController2(controller2);
                    
                    view.addController(boardP1, controller1);
                    view.addController(boardP2, controller2);
                    
                    view.setVisible(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inicializar o programa!");
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /*
        if(boardP1.vence()){
            System.out.println("Vc ganhou");
        }
        if(boardP2.vence()){
            System.out.println("Vc perdeu");
        }//*/
    }
}
