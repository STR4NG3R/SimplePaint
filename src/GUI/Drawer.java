/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import java.awt.geom.AffineTransform;
import Models.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.accessibility.Accessible;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * <h1>This programs helps to draw in JPanel</h1>
 * <p>
 * First version and implementations of drawer
 *
 * @author pablo
 * @since 2019
 */
public class Drawer extends JPanel implements MouseListener, MouseMotionListener {

    public CoordsListenner coordScreen;
    boolean isDrawing;
    static byte clickCounter;
    public static Color selectedColor[];
    byte savedType, savedSubType;
    boolean didPrint;
    MenuR menu;
    FreeMode free;

    callBackDrawing cbDrawing = new callBackDrawing() {
        @Override
        public void drawing(boolean isDrawing) {
            repaint();
            isDrawing = true;
        }
    };

    public Drawer() {
        didPrint = false;
        free = new FreeMode();
        clickCounter = 0;
        sp = new JScrollPane(this);
        free.moveTo(0, 0);
        isDrawing = false;
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(wheelListener);
        selectedColor = new Color[2];
        selectedColor[0] = Color.WHITE;
        selectedColor[1] = Color.BLACK;
        setMinimumSize(new Dimension(600, 400));
        coordScreen = new CoordsListenner();
        setResolution(4000, 800);
        coordScreen.setBackground(Color.WHITE);
        repaint();
        initMenuBar();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Point [] coordsDrawer = new Point[2];
        for (int i = 0; i < 2; i++) {
            coordsDrawer[i] = new Point(0, 0);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(4));
        if (clickCounter == 1) {
            coordsDrawer[0].x = x;
            coordsDrawer[0].y = y;
            coordsDrawer[1].x = x1;
            coordsDrawer[1].y = y1;
            paintForms(g, g2d, coordsDrawer, true, "");
        } else if (ToolBar.typeSelected == 8) {
            paintForms(g, g2d, coordsDrawer, didPrint, "");
        }
        
        clickCounter = 0;
        Color savedSelectedColor[];
        savedSelectedColor = new Color[2];
        savedType = ToolBar.typeSelected;
        savedSubType = ToolBar.subtypeSelected;
        savedSelectedColor[0] = selectedColor[0];
        savedSelectedColor[1] = selectedColor[1];

        for (Iterator<Forms> i = GlobalListForms.listForms.iterator(); i.hasNext();) {
            System.out.println("repaint");
            Forms a = i.next();
            String text = "";
            didPrint = true;
            coordsDrawer[0] = a.p1;
            coordsDrawer[1] = a.p2;
            ToolBar.typeSelected = a.type;
            ToolBar.subtypeSelected = a.subtype;
            selectedColor[0] = a.color1;
            selectedColor[1] = a.color2;
            if (ToolBar.typeSelected == 6) {
                Text temp = (Text) a;
                text = temp.text;
                System.out.println(text);
            }
            paintForms(g, g2d, coordsDrawer, false, text);
        }

        if (didPrint) {
            selectedColor[0] = savedSelectedColor[0];
            selectedColor[1] = savedSelectedColor[1];
            ToolBar.typeSelected = savedType;
            ToolBar.subtypeSelected = savedSubType;
        }

    }

    public void paintForms(Graphics g, Graphics2D g2d, Point coordsDrawer[], boolean i, String text) {
        Forms newForm;
        Color selectedColor[] = this.selectedColor;
        g.setColor(selectedColor[0]);
        g2d.setColor(selectedColor[0]);
        switch (ToolBar.typeSelected) {
            case 0:
                System.out.println("SELECTION");
                break;
            case 1:
                System.out.println("OVAL");
                newForm = new Circle(ToolBar.typeSelected, ToolBar.subtypeSelected,
                        coordsDrawer[0], coordsDrawer[1],
                        selectedColor[0], selectedColor[1]);
                newForm.draw(g, g2d);
                GlobalListForms.listForms.add(newForm, i);
                break;
            case 2:
                System.out.println("RECTANGLE");
                newForm = new Square(ToolBar.typeSelected, ToolBar.subtypeSelected,
                        coordsDrawer[0], coordsDrawer[1],
                        selectedColor[0], selectedColor[1]);
                newForm.draw(g, g2d);
                GlobalListForms.listForms.add(newForm, i);
                break;
            case 3:
                System.out.println("SQUARE");
                break;
            case 4:
                System.out.println("ARROW");
                newForm = new Arrow(ToolBar.typeSelected, ToolBar.subtypeSelected,
                        coordsDrawer[0], coordsDrawer[1],
                        selectedColor[0], selectedColor[1]);
                newForm.draw(g, g2d);
                g2d.draw(newForm);
                GlobalListForms.listForms.add(newForm, i);
                break;
            case 5:
                System.out.println("STAR");
                newForm = new Star(ToolBar.typeSelected, ToolBar.subtypeSelected,
                        coordsDrawer[0], coordsDrawer[1],
                        selectedColor[0], selectedColor[1]);
                newForm.draw(g, g2d);
                GlobalListForms.listForms.add(newForm, i);
                break;
            case 6:
                System.out.println("TEXT");
                if (i) {
                    InputDialog in = new InputDialog();
                    in.showDialog(xOnScreen, yOnScreen);
                    System.out.println("before...");
                    text = in.text;
                    clickCounter = 0;
                }
                System.out.println(text);
                if (text != null) {
                    switch (ToolBar.subtypeSelected) {
                        case 0:
                            newForm = new Text(ToolBar.typeSelected, ToolBar.subtypeSelected,
                                    coordsDrawer[0], coordsDrawer[1],
                                    selectedColor[0], selectedColor[1],
                                    text);
                            newForm.draw(g, g2d);
                            GlobalListForms.listForms.add(newForm, i);
                            break;
                    }
                }
                break;
            case 7:
                System.out.println("IMAGE");
                break;
            case 8:
                if (isDrawing) {
                    System.out.println("FREE");
                    free.lineTo(coordScreen.getXs(), coordScreen.getYs());
                    g2d.draw(free);
                } else {
                    GlobalListForms.listForms.add(free, i);
                    free = new FreeMode();
                }
                break;
        }
    }

    public void changeResolution(int width, int height) {
        width = getWidth() - width;
        height = getHeight() - height;
        setSize(new Dimension(width, height));
    }
     public void setResolution(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void render(Graphics2D g2d) {
        AffineTransform at = new AffineTransform();
        g2d.setTransform(at);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void revalidate() {
        super.revalidate();

    }
    
    int x, y, x1, y1;
    int xOnScreen, yOnScreen;

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        if (ToolBar.typeSelected == 8) {
            free.moveTo(e.getX(), e.getY());
            isDrawing = true;
        } else if (ToolBar.typeSelected != 0) {
            if (clickCounter == 1) {
                x1 = e.getX();
                y1 = e.getY();
                xOnScreen = e.getXOnScreen();
                yOnScreen = e.getYOnScreen();
                isDrawing = true;
                repaint();
                return;
            }
            x = e.getX();
            y = e.getY();
            clickCounter++;
        } else {
            isDrawing = false;
            clickCounter = 0;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDrawing = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        coordScreen.updateCoords(e.getX(), e.getY());

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //coordScreen.updateCoords(0, 0);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDrawing) {
            repaint();
        }
        coordScreen.updateCoords(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        coordScreen.updateCoords(e.getX(), e.getY());
    }

    void initMenuBar() {
        menu = new MenuR(
                new String[]{
                    "File",
                    "View",
                    "About"
                }, new String[]{
                    "Open",
                    "New",
                    "Save",
                    "Save As",
                    "separador",
                    "Properties",
                    "Close",
                    "separar menu",
                    "Refresh",
                    "separar menu",
                    "About Simple Paint",
                    "separador",
                    "About Me"
                });
        JMenuItem me[] = menu.getItems();
        for (int i = 0; i < menu.getItems().length; i++) {
            if (me[i] != null) {
                me[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object event = e.getSource();
                        if (event == me[0]) {
                            JFileChooser fileC = new JFileChooser();
                            fileC.showOpenDialog(null);
                            Files.path = fileC.getSelectedFile().getPath();
                            Files.leer();
                        } else if (event == me[6]) {
                            int i = JOptionPane.showConfirmDialog(null, "Are you sure to quit?", "Warning", JOptionPane.YES_NO_OPTION);
                            if (i == 0) {
                                System.exit(1);
                            }
                        } else if (event == me[3]) {//Save As
                            String name;
                            JFileChooser fileC = new JFileChooser();
                            //name = fileC.get;
                            fileC.showOpenDialog(null);
                            Files.path = fileC.getCurrentDirectory().getPath();
                            name = fileC.getSelectedFile().getName();
                            Files.guardar(name);
                        } else if (event == me[1]) {//New
                            GlobalListForms.listForms.clear();
                            repaint();
                        } else if (event == me[2]) {//Save
                            if (Files.path == "") {
                                String name;
                                JFileChooser fileC = new JFileChooser();
                                //name = fileC.get;
                                fileC.showOpenDialog(null);
                                Files.path = fileC.getCurrentDirectory().getPath();
                                name = fileC.getSelectedFile().getName();
                                Files.guardar(name);
                            } else {
                                Files.guardar();
                            }
                        } else if (event == me[5]) {//Properties

                        } else if (event == me[8]) {//REFRESH
                            repaint();
                        }
                    }
                });
            }
        }
    }

    public JScrollPane sp;

    MouseWheelListener wheelListener = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            System.out.println(e.getUnitsToScroll());
            int units = e.getUnitsToScroll();
            changeResolution(100, 100);
        }
    };
}

interface callBackDrawing {

    public void drawing(boolean isDrawing);
}

class CoordsListenner extends JPanel {

    int x, y;
    public JLabel coordsLabel;

    CoordsListenner() {
        coordsLabel = new JLabel();
        add(coordsLabel);
        coordsLabel.setText("X:0 Y:0");
    }

    public int getXs() {
        return x;
    }

    public int getYs() {
        return y;
    }

    void updateCoords(int x, int y) {
        this.x = x;
        this.y = y;
        coordsLabel.setText("X:" + x + " Y:" + y);
    }

}

class MenuR {

    private JMenuBar barraxd;
    private JMenuItem items[];

    public MenuR(String nombreMenus[], String[] nombresItems) {
        byte i;
        JMenu menus[];
        barraxd = new JMenuBar();
        menus = new JMenu[nombreMenus.length];
        items = new JMenuItem[nombresItems.length];

        for (i = 0; i < nombreMenus.length; i++) {
            menus[i] = new JMenu(nombreMenus[i]);
        }

        byte j = 0;
        for (i = 0; i < nombresItems.length; i++) {
            if (nombresItems[i].equals("separar menu")) {
                j++;
            } else if (nombresItems[i].equals("separador")) {
                menus[j].addSeparator();
            } else {
                items[i] = new JMenuItem(nombresItems[i]);
                menus[j].add(items[i]);
            }
        }

        for (i = 0; i < menus.length; i++) {
            barraxd.add(menus[i]);
        }

    }

    /**
     * @return the items para poner eventos y hacerlo chido
     */
    public JMenuItem[] getItems() {
        return items;
    }

    /**
     * @return the barraxd para colocarla barra
     */
    public JMenuBar getBarra() {
        return barraxd;
    }

}

class InputDialog extends JDialog implements Accessible {

    public String text;

    public InputDialog() throws HeadlessException {
    }
    
    public void showDialog(int x, int y) {

        /* Window w = new Window();
        pane.setWantsInput(true);
        pane.setSelectionValues(selectionValues);
        pane.setInitialSelectionValue(initialSelectionValue);
        pane.setComponentOrientation(((parentComponent == null) ?
            getRootFrame() : parentComponent).getComponentOrientation());

        int style = styleFromMessageType(messageType);
        JDialog dialog = pane.createDialog(parentComponent, title, style);

        pane.selectInitialValue();
        dialog.show();
        dialog.dispose();

        Object value = pane.getInputValue();

        if (value == UNINITIALIZED_VALUE) {
            return null;
        }
        return value;*/
        JPanel pane = new JPanel();
        //setAlwaysOnTop(true);
        setModal(true);
        JTextField textInput = new JTextField();
        JButton a = new JButton("OK");
        //setTitle("Please Type");
        pane.setLayout(new BorderLayout());
        pane.add(new JLabel("Please Text Your Input"), BorderLayout.NORTH);
        pane.add(textInput, BorderLayout.CENTER);
        pane.setLocation(x, y);
        pane.add(a, BorderLayout.SOUTH);
        text = "";
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textInput.getText();
                setModal(false);
                dispose();
            }
        });
        
        getContentPane().add(pane);
        setLocation(x, y);
        setMinimumSize(new Dimension(300,100));
        setAlwaysOnTop(true);
        setUndecorated(true);
        setVisible(true);
        
        //setUndecorated(true);
        //setMinimumSize(new Dimension(200, 150));
        //setVisible(true);
    }
}
