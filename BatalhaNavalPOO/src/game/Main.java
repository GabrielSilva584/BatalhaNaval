/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import form.FormPrincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    Tabuleiro boardP1 = new Tabuleiro();
                    Tabuleiro boardP2 = new Tabuleiro();
                    FormPrincipal view = new FormPrincipal(boardP1, boardP2);
                    Game model = new Game();
                    boardP1.registerObserver(view);
                    boardP1.registerObserver(model);
                    boardP2.registerObserver(view);
                    boardP2.registerObserver(model);
                    GameController controller = new GameController();
                    controller.addView(view);
                    controller.addModel(model);
                    view.addController(controller);
                    view.setVisible(true);
                } catch (IOException ex) {
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
