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
 * @author Gabriel
 */
public class Auxiliar extends Navios{
    //Embarcação auxiliar para anotar as informações recebidas do player remoto
    
    public Auxiliar(int x, int y, String type){
        tam = 1;
        this.type = type;
        initMatriz();
        
        ocupado[0][1]=x;
        ocupado[0][0]=y;
        
    }
}
