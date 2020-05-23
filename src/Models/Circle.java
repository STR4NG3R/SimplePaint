/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 *
 * @author pablo
 */
public class Circle extends Forms implements Serializable{

    public Circle(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2) {
        super(type, subtype, p1, p2, color1, color2);
    }

    public Circle() {
    }

    public void setCoord(int x, int y, int which) {
        if (which == 0) {
            p1.x = x;
            p1.y = y;
        } else {
            p2.x = x;
            p2.y = y;
        }
    }

    @Override
    public void draw(Graphics g, Graphics2D g2d) {
        switch (subtype) {
            case 0:
                int difX = Math.abs(p1.x - p2.x);
                int difY = Math.abs(p1.y - p2.y);
                g.setColor(color1);
                g.drawOval(p1.x, p1.y, difX, difY);
                g.setColor(color2);
                g.fillOval(p1.x, p1.y, difX, difY);
                break;
        }
    }

}
