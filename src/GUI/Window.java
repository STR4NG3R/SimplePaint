/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author pablo
 */
public class Window extends JFrame {

    public Window() throws HeadlessException {        
        setTitle("Simple Paint");
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(getSize().height + "  " +getSize().width);
        setMinimumSize(new Dimension(928, 513 ));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        JPanel container = new JPanel();
        ColorBar colorBar = new ColorBar();
        container.setLayout(new BorderLayout());
        Drawer drawer = new Drawer();
        ToolBar toolbar = new ToolBar();
        FormsGUIList list = new FormsGUIList();
        setJMenuBar(drawer.menu.getBarra());
        
        drawer.setListForms(list);
        drawer.setOwner(this);
        
        container.add(drawer.sp, BorderLayout.CENTER);
        container.add(drawer.coordScreen, BorderLayout.SOUTH);
        container.add(toolbar, BorderLayout.NORTH);
        container.add(colorBar, BorderLayout.EAST);
        container.add(list, BorderLayout.WEST);
        
        setContentPane(container);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                toolbar.revalidate();
                drawer.revalidate();
            }
        });
    }
}

