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
public class Square extends Forms {

    public Square(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2) {
        super(type, subtype, p1, p2, color1, color2);
    }
    
    public Square(){}

    @Override
    public void draw(Graphics g, Graphics2D g2d) {
        int difX = Math.abs(p1.x - p2.x);
        int difY = Math.abs(p1.y - p2.y);
        switch (subtype) {
            case 0:
                g.setColor(color1);
                g.drawRect(p1.x, p1.y, difX, difY);
                g.setColor(color2);
                g.fillRect(p1.x, p1.y, difX, difY);
                break;
            case 1:
                g.setColor(color1);
                g.draw3DRect(p1.x, p1.y, difX, difY, true);
                g.setColor(color2);
                g.fillRect(p1.x, p1.y, difX, difY);
                break;
            case 2:
                g.setColor(color1);
                g.drawRoundRect(p1.x, p1.y, difX, difY, 1, 4);
                g.setColor(color2);
                g.fillRoundRect(p1.x, p1.y, difX, difY, 1, 4);
                break;
        }

    }

}
