/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author pablo
 */
public class Text extends Forms {
    public String text;
    
    public Text(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2, String text) {
        super(type, subtype, p1, p2, color1, color2);
        this.text = text;
    }

    @Override
    public void draw(Graphics g, Graphics2D g2d) {
        Font a = new Font("Arial", Font.ITALIC, 23);
        g.setFont(a);
        g.drawString(text, p1.x, p1.y);
    }

}
