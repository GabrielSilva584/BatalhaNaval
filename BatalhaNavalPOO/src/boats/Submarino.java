/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import static boats.Navios.backgroundPath;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author viber
 */
public class Submarino extends Navios {
     public Submarino(){
        tam = 3;
        type = "Submarino";
        
        initMatriz();
        
        try {
           imageh = ImageIO.read(new File(backgroundPath+"submarino.png"));
           imagev = ImageIO.read(new File(backgroundPath+"submarino - vertical.png"));
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao carregar arquivos!");
        }
    }
}
