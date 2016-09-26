/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author viber
 */

public abstract class Navios {
    private int tam;
    private final int ocupado[][] = new int[tam][2];
    private boolean rotacao;
    Point quadrante;

    public Point getQuadrante() {
        return quadrante;
    }

    public void setQuadrante(int x, int y){
        quadrante.setLocation(x, y);
    }
    
    void pos(int tam, int vertIni, int horIni, boolean rotacao){
        setQuadrante(horIni,vertIni);
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
    }
    
    public abstract void draw(Graphics2D g);
}