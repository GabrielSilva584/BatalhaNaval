/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import board.LabelLeft;
import form.FormPrincipal;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Gabriel
 */
public class ChatController {
    private StyledDocument doc = null;
    private Style red = null, black = null, blue = null, green = null, orange = null;
    
    public ChatController(StyledDocument remoteDoc){
        doc = remoteDoc;
        
        StyleContext sc = new StyleContext();
        
        black = sc.addStyle("Black", null);
        black.addAttribute(StyleConstants.Foreground, Color.black);
        
        red = sc.addStyle("Red", null);
        red.addAttribute(StyleConstants.Foreground, Color.red);
        
        blue = sc.addStyle("Blue", null);
        blue.addAttribute(StyleConstants.Foreground, Color.blue);
        
        green = sc.addStyle("Green", null);
        green.addAttribute(StyleConstants.Foreground, Color.green.darker());
        
        orange = sc.addStyle("Orange", null);
        orange.addAttribute(StyleConstants.Foreground, Color.orange.darker());
    }
    
    public void insertString(String msg, int color){
        try {
            doc.insertString(doc.getLength(), msg, selectColor(color));
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertMessage(String name, String msg, int nameColor){
        try {
            doc.insertString(doc.getLength(), name + ": ", selectColor(nameColor));
            doc.insertString(doc.getLength(), msg+"\n", selectColor(0));
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void attackMessage(int x, int y){
        try {
            doc.insertString(doc.getLength(), "Você atacou a Coordenada " 
                    + new LabelLeft().coordToString(y+1) + (x+1) +"! ", orange);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void attackResultMessage(boolean acertou, String type, int rest){
        try {
            if(acertou){
                doc.insertString(doc.getLength(), "Acertou um(a) " + type, orange);
            }else{
                doc.insertString(doc.getLength(), "Não acertou nada!", orange);
            }
            doc.insertString(doc.getLength(), " (" + rest + " torpedos restantes)\n", orange);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void attackedMessage(String remoteName, int x, int y, boolean acertou, String type, int rest){
        try {
            doc.insertString(doc.getLength(), remoteName + " te atacou na Coordenada "
                    + new LabelLeft().coordToString(y+1) + (x+1) +"! ", orange);
            attackResultMessage(acertou, type, rest);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void youWinMessage(){
        try {
            doc.insertString(doc.getLength(), "Você venceu!\n", red);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void youLoseMessage(String remoteName){
        try {
            doc.insertString(doc.getLength(), remoteName +" venceu!\n", red);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void yourTurnMessage(){
        try {
            doc.insertString(doc.getLength(), "Seu turno...\n", red);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remoteTurnMessage(String remoteName){
        try {
            doc.insertString(doc.getLength(), "Turno de " + remoteName + "...\n", red);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // 0 = Player Local, 1 = Player Remoto
    public void readyMessage(String remoteName, int i){
        if(i==0)remoteName = "Você";
        try {
            doc.insertString(doc.getLength(), remoteName + " está pronto!\n", red);
        } catch (BadLocationException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Style selectColor(int color){
        switch (color) {
            case 1:
                return red;
            case 2:
                return blue;
            case 3:
                return green;
            default:
                return black;
        }
    }
}
