/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import form.FormPrincipal;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Gabriel
 */
public class GameControllerP2 extends GameController{
    
    private boolean mouseInside = false;
    private FormPrincipal view;
    private Game model;
    
    @Override
    public void drawMouseQuadrante(Graphics2D g){
        if(mouseInside){
            int width = view.getBoard1().getWidth()/10;
            int height = view.getBoard1().getHeight()/10;

            int qx = model.getMouseCoord().x/width;
            int qy = model.getMouseCoord().y/height;

            int squareWidth = g.getClip().getBounds().width / 10;
            int squareHeight = g.getClip().getBounds().height / 10;

            g.setColor(Color.red);
            g.drawRect(qx * squareWidth, qy * squareHeight, squareWidth, squareHeight);
            g.setColor(Color.black);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
