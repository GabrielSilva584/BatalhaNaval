/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author viber
 */

public abstract class Navios {
    protected int tam;
    private int ocupado[][] = null;
    protected boolean rotacao = false;
    protected int x, y;
    protected final static String backgroundPath = "src/img/";
    protected BufferedImage imageh;
    protected BufferedImage imagev;

    public void initMatriz(){
        ocupado = new int[tam][2];
        for(int i=0;i<tam;i++){
            for(int j=0;j<2;j++){
                ocupado[i][j]=0;
            }
        }
    }

    public void setQuadrante(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void rotacionar(){
        rotacao = !rotacao;
    }
    
    void pos(int tam, int vertIni, int horIni, boolean rotacao){
        ocupado[0][0] = vertIni;
        ocupado[0][1] = horIni;
        for(int i=1;i==tam-1;i++){
            if(!rotacao){
                ocupado[i][0] = vertIni;
                ocupado[i][1] = horIni+i;
            }
            else{
                ocupado[i][0] = vertIni+i;
                ocupado[i][1] = horIni;
            }
        }
        while(ocupado[tam-1][1]>9){
            for(int j=0;j<tam;j++){
                ocupado[j][1] = ocupado[j][1] - 1;
            }
        }
        while(ocupado[tam-1][0]>9){
            for(int j=0;j<tam;j++){
                ocupado[j][0] = ocupado[j][0] - 1;
            }
        }
        System.out.println(""+ocupado[0][1]+ocupado[0][0]);
        setQuadrante(ocupado[0][1],ocupado[0][0]);
    }
    
    public void draw(Graphics2D g) {
        BufferedImage aux = null;
        
        int squareWidth = g.getClip().getBounds().width / 10;
        int squareHeight = g.getClip().getBounds().height / 10;
        
        int x0 = x * squareWidth;
        int y0 = y * squareHeight;
        if(!rotacao){
            squareWidth*=tam;
            aux = imageh;
        }
        else{
            squareHeight*=tam;
            aux = imagev;
        }
        
        g.drawImage(aux, x0, y0, null);
    }
    
    public void drawToCoord(Graphics2D g, int x, int y) {
        pos(tam,y,x,rotacao);
        draw(g);
    }
}