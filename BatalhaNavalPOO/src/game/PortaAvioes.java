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
public class PortaAvioes extends Navios {
     public PortaAvioes(){
        tam = 5;
        
        initMatriz();
        
        try {
           imageh = ImageIO.read(new File(backgroundPath+"portaavioes.png"));
           imagev = ImageIO.read(new File(backgroundPath+"portaavioes - vertical.png"));
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao carregar arquivos!");
        }
    }
}