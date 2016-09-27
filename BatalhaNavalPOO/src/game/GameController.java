/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import form.FormPrincipal;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Gabriel
 */
public abstract class GameController implements MouseListener, MouseMotionListener, ActionListener{
    
    protected boolean mouseInside = false;
    protected FormPrincipal view;
    protected Game model;
    
    public void addView(FormPrincipal view){
        this.view = view;
    }
    
    public void addModel(Game model){
        this.model = model;
    }
    
    public abstract void drawMouseQuadrante(Graphics2D g);

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInside = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mouseInside){
                    view.repaint();
                }
            }
        }).start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInside = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!mouseInside){
                    view.repaint();
                }
            }
        }).start();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        model.getMouseCoord().setLocation(e.getX(), e.getY());
        view.repaint();
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
    
    @Override
    public abstract void mouseDragged(MouseEvent e);
    
    @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public abstract void mousePressed(MouseEvent e);

    @Override
    public abstract void mouseReleased(MouseEvent e);

}
