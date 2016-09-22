/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

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
    private Style red = null, black = null, blue = null, green = null;
    
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
