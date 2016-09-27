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
public class Destroyer extends Navios {
     public Destroyer(){
        tam = 4;
        type = "Destroyer";
        
        initMatriz();
        
        try {
           imageh = ImageIO.read(new File(backgroundPath+"destroyer.png"));
           imagev = ImageIO.read(new File(backgroundPath+"destroyer - vertical.png"));
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Falha ao carregar arquivos!");
        }
    }
}
