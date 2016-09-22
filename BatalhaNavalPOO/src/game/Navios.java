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
    private int ini[][];
    private boolean rotacao;
    Point quadrante;

    public Point getQuadrante() {
        return quadrante;
    }

    public void setQuadrante(int x, int y){
        quadrante.setLocation(x, y);
    }
    
    void pos(int tam, int ini[][], boolean rotacao){
        
    }
    
    public abstract void draw(Graphics2D g);
}
