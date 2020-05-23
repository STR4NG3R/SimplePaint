/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pablo
 */
public class ColorBar extends JPanel implements MouseListener {

    public SelectedColors selectedColors[] = new SelectedColors[2];

    public ColorBar() {

        Color rainbow[] = new Color[]{
            new Color(255, 255, 255),
            new Color(0, 0, 255),
            new Color(255, 0, 0),
            new Color(255, 0, 255),
            new Color(0, 255, 0),
            new Color(0, 255, 255),
            new Color(255, 255, 0)
        };
        selectedColors[0] = new SelectedColors((byte) 1);
        selectedColors[1] = new SelectedColors((byte) 2);
        JLabel colors[] = new JLabel[8];
        GridBagLayout gridB = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();
        GridLayout grid = new GridLayout();
        grid.setRows(9);
        setLayout(gridB);

        constraint.weightx = 32;
        constraint.weighty = 32;

        for (int i = 0; i < colors.length - 1; i++) {
            colors[i] = new JLabel();
            colors[i].setOpaque(true);
            constraint.gridy = i;
            colors[i].setPreferredSize(new Dimension(32, 32));
            colors[i].setBackground(rainbow[i]);
            colors[i].addMouseListener(this);
            add(colors[i], constraint);
        }

        constraint.gridy = 7;
        colors[7] = new JLabel();
        add(colors[7], constraint);
        colors[7].setOpaque(true);
        colors[7].setBackground(Color.BLACK);
        colors[7].setPreferredSize(new Dimension(32, 32));
        constraint.gridy = 8;
        add(selectedColors[0], constraint);
        constraint.gridy = 9;
        add(selectedColors[1], constraint);
        selectedColors[0].addMouseListener(this);
        selectedColors[1].addMouseListener(this);
        selectedColors[0].isSelected = true;
        selectedColors[0].setSelected();
        colors[7].addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
                updateColorsDrawer(newColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    void updateColorsDrawer(JLabel a) {
        if (selectedColors[0].isSelected) {
            selectedColors[0].updateColor(a.getBackground());
            Drawer.selectedColor[0] =  a.getBackground();
        } else {
            selectedColors[1].updateColor(a.getBackground());
            Drawer.selectedColor[1] = a.getBackground();
        }
    }

    void updateColorsDrawer(Color c) {
        if (selectedColors[0].isSelected) {
            selectedColors[0].updateColor(c);
            Drawer.selectedColor[0] = c;
        } else {
            selectedColors[1].updateColor(c);
            Drawer.selectedColor[1] = c;
        }
    }
        
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel a = (JLabel) e.getSource();
            updateColorsDrawer(a);
        } else {
            selectedColors[0].isSelected = false;
            selectedColors[0].isSelected = false;
            selectedColors[0].setBackground(Color.WHITE);
            selectedColors[1].setBackground(Color.WHITE);
            if (e.getSource() == selectedColors[0]) {
                selectedColors[0].setSelected();
                selectedColors[0].isSelected = true;
            } else {
                selectedColors[1].isSelected = true;
                selectedColors[1].setSelected();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

class SelectedColors extends JPanel {

    JLabel color;
    public boolean isSelected;

    public SelectedColors(byte i) {
        isSelected = false;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        color = new JLabel();
        color.setOpaque(true);
        color.setBackground(Color.BLACK);
        if(i == 1)
        color.setBackground(Color.WHITE);
        add(new JLabel("C " + i), BorderLayout.NORTH);
        add(color, BorderLayout.CENTER);
        color.setPreferredSize(new Dimension(32, 32));
        setPreferredSize(new Dimension(32, 48));
    }

    public void updateColor(Color newColor) {
        color.setBackground(newColor);
    }

    public void setSelected() {
        setBackground(Color.GREEN);
    }

    Color getColor() {
        return color.getBackground();
    }
}
