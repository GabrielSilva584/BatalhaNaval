/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.Navios.backgroundPath;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author viber
 */
public class Cruzador extends Navios {
     public Cruzador(){
        tam = 4;
        type = "Cruzador";
        
        initMatriz();
        
        try {
           imageh = ImageIO.read(new File(backgroundPath+"cruzador.png"));
           imagev = ImageIO.read(new File(backgroundPath+"cruzador - vertical.png"));
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao carregar arquivos!");
        }
    }
    
}
