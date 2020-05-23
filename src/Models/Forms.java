/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.io.Serializable;

/**
 *
 * @author pablo
 */
public abstract class Forms extends Path2D.Double implements Serializable{
    public byte type;
    public byte subtype;
    public Point p1, p2;
    public Color color1, color2;

    public Forms(){}

    
    public Forms(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2) {
        this.type = type;
        this.subtype = subtype;
        this.p1 = p1;
        this.p2 = p2;
        this.color1 = color1;
        this.color2 = color2;
    }
 
    public abstract void draw(Graphics g, Graphics2D g2d);
}
