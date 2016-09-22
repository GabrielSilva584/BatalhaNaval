/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class Game implements Observer{
    private Navios n1[][] = new Navios[10][10];
    private Point mouseCoord;
    private Navios selectedNavio = null;
    
    public Game(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                n1[i][j] = null;
            }
        }
        mouseCoord = new Point();
        
        init();
    }

    public Point getMouseCoord() {
        return mouseCoord;
    }

    public void setMouseCoord(Point mouseCoord) {
        this.mouseCoord = mouseCoord;
    }

    public Navios getSelectedNavio() {
        return selectedNavio;
    }

    public void setSelectedNavio(Navios selectedNavio) {
        this.selectedNavio = selectedNavio;
    }
    
    private void init() {
        
    }
    
    public Navios findNavio(int x, int y) {
        return n1[x][y];
    }
    
    public void draw(Graphics2D g){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(n1[i][j]!=null)n1[i][j].draw(g);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        draw((Graphics2D) arg);
    }
    
}
