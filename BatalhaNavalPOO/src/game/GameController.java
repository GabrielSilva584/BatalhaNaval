/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import form.FormPrincipal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class GameController implements MouseListener, MouseMotionListener, ActionListener{

    private FormPrincipal view;
    private Game model;
    
    public void addView(Observer view){
        this.view = (FormPrincipal)view;
    }
    
    public void addModel(Game model){
        this.model = model;
    }
    
    public void drawMouseQuadrante(Graphics2D g) {
        
        int width = view.getBoard1().getWidth()/10;
        int height = view.getBoard1().getHeight()/10;
        
        int qx = model.getMouseCoord().x/width;
        int qy = model.getMouseCoord().y/height;
        
        int squareWidth = g.getClip().getBounds().width / 10;
        int squareHeight = g.getClip().getBounds().height / 10;

        g.setColor(Color.red);
        g.setStroke(new BasicStroke(4));
        g.drawRect(qx * squareWidth, qy * squareHeight, squareWidth, squareHeight);
        g.setColor(Color.BLACK);
    }
    
   
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("X: " + e.getX() + "\tY: " + e.getY());
        
        int width = view.getBoard1().getWidth()/10;
        int height = view.getBoard1().getHeight()/10;
        
        Navios n = model.findNavio(e.getX()/width, e.getY()/height);
        if(n != null){
            model.setSelectedNavio(n);
        } else {
            model.getSelectedNavio().setQuadrante(e.getX()/width, e.getY()/height);
            model.setSelectedNavio(null);;
        }
        
        view.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        model.getMouseCoord().setLocation(e.getX(), e.getY());
        view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
