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
public class Arrow extends Forms {

    public Arrow(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2) {
        super(type, subtype, p1, p2, color1, color2);
    }

//->
    void updateStrokeRight() {
        //Esta linea - es la que realmente importa
        /*moveTo(0, 10);
            lineTo(36, 10);*/
        moveTo(p1.x, p1.y);
        lineTo(p2.x, p2.y);
        // es \ esta linea
        moveTo(p2.x - 10, p2.y - 15);
        lineTo(p2.x, p2.y);
        /*moveTo(36 - 16, 0);
            lineTo(36, 10);*/
        //es esta / linea
        moveTo(p2.x - 10, p2.y + 15);
        lineTo(p2.x, p2.y);
    }

    //<->
    void updateStrokeBidirectional() {
        moveTo(p1.x, p1.y);
        lineTo(p2.x, p2.y);
        // es \ esta linea
        moveTo(p2.x - 10, p2.y - 10);
        lineTo(p2.x, p2.y);
        /*moveTo(36 - 16, 0);
            lineTo(36, 10);*/
        //es esta / linea
        moveTo(p2.x - 10, p2.y + 10);
        lineTo(p2.x, p2.y);
        //Inverse
        moveTo(p1.x + 10, p1.y - 10);
        lineTo(p1.x, p1.y);
        moveTo(p1.x + 10, p1.y + 10);
        lineTo(p1.x, p1.y);
    }

    //<-
    void updateStrokeLeft() {
        moveTo(p1.x, p1.y);
        lineTo(p2.x, p2.y);
        moveTo(p1.x + 10, p1.y - 10);
        lineTo(p1.x, p1.y);
        moveTo(p1.x + 10, p1.y + 10);
        lineTo(p1.x, p1.y);
    }

    @Override
    public void draw(Graphics g, Graphics2D g2d) {
        switch (subtype) {
            case 0:
                updateStrokeRight();
                break;
            case 1:
                updateStrokeLeft();
                break;
            case 2:
                updateStrokeBidirectional();
                break;
        }
    }

}
