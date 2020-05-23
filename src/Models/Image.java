/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author pablo
 */
public class Image extends Forms{

    public Image(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2) {
        super(type, subtype, p1, p2, color1, color2);
    }

    @Override
    public void draw(Graphics g, Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
