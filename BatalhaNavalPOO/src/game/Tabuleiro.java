/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author viber
 */
public class Tabuleiro extends JPanel{
    
    private ArrayList<Observer> observers;
    protected final static String backgroundPath = "img/agua.jpg";
    private BufferedImage image;
    private int matriz[][] = new int[10][10];
    public Tabuleiro() {
       try {
          URL url=getClass().getResource("");
          image = ImageIO.read(new File(backgroundPath));
       } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao carregar arquivos!");
       }//*/
       observers = new ArrayList<Observer>();
    }
    
    public void registerObserver(Observer ob){
        observers.add(ob);
    }
    
    private void drawBoard(Graphics2D g){
        g.drawImage(image, 0, 0, null);
        
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        for(int i=0;i<=10;++i)g.drawLine(0, i*30, 300, i*30);
        for(int i=0;i<=10;++i)g.drawLine(i*30, 0, i*30, 300);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawBoard(g2);
        for(Observer ob : observers){
            ob.update(null, g);
        }
    }
    
    void inicializarTabuleiro(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                matriz[i][j]=0;//0 é água
            }
        }
    
    }
    
    int vence(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(matriz[i][j]!=0 && matriz[i][j]!=-1) {//-1 é navio destruído
                    return 0;//não acabou o jogo
                } 
            }
        }
        return 1;//todos os navios foram destruidos
    }
}
