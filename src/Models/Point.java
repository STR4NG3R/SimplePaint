/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 *
 * @author pablo
 */
public class Point extends Point2D implements Serializable{
    public int x, y;
    
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Point(int x, int y) {
        this.x =  x;
        this.y =  y;
    }    

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
    
}
