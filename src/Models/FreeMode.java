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
public class FreeMode extends Forms{
    int x, y;
    
    public FreeMode(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2, int _x, int _y) {
        super(type, subtype, p1, p2, color1, color2);
        x = _x;
        y = _y;
        moveTo(x, y);
    }
    
    public FreeMode() {
    }

    @Override
    public void draw(Graphics g, Graphics2D g2d) {
        lineTo(x, y);
    }
    
}
