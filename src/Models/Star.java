/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 *
 * @author pablo
 */
public class Star extends Forms {

    public Star(byte type, byte subtype, Point p1, Point p2, Color color1, Color color2) {
        super(type, subtype, p1, p2, color1, color2);
    }

    public Star() {
    }

    @Override
    public void draw(Graphics g, Graphics2D g2d) {
        int difX = Math.abs(p1.x - p2.x);

        switch (subtype) {
            case 0:
                g2d.setColor(color1);
                g2d.draw(createDefaultStar(p1.x, p1.y, difX / 2));
                g2d.setColor(color2);
                g2d.fill(createDefaultStar(p1.x, p1.y, difX / 2));
                break;
            case 1:
                g2d.setPaint(new RadialGradientPaint(
                        p1, 60, new float[]{0, 1},
                        new Color[]{color1, color2}));
                g2d.fill(createDefaultStar(p1.x, p1.y, difX / 2));
                break;
            case 2:
                g2d.setPaint(new RadialGradientPaint(
                        p1, 50, new float[]{0, 0.3f, 1},
                        new Color[]{color1, color2, Color.ORANGE}));
                g2d.fill(createDefaultStar(p1.x, p1.y, difX / 2));
                break;

        }
    }

    private Shape createDefaultStar(double centerX, double centerY, double radius) {
        return createStar(centerX, centerY, radius, radius * 2.63, 5, Math.toRadians(-18));
    }

    private Shape createStar(double centerX, double centerY,
            double innerRadius, double outerRadius,
            int numRays, double startAngleRad) {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++) {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0) {
                relX *= outerRadius;
                relY *= outerRadius;
            } else {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0) {
                path.moveTo(centerX + relX, centerY + relY);
            } else {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }
}
