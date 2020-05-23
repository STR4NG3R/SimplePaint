/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author pablo
 */
public class ToolBar extends JPanel implements ActionListener {

    static byte typeSelected;
    static byte subtypeSelected;
    private JComboBox buttonContainer[];

    public ToolBar() {
        JButton[] tools;
        buttonContainer = new JComboBox[9];
        ComboBoxRenderer render;
        typeSelected = 0;
        subtypeSelected = 0;
        JPanel panel = new JPanel();
        setLayout(new BorderLayout());

        String[][] infoImages = new String[][]{
            {"Selección", "/resources/cursor.png"},
            {"Circulo", "/resources/circle.png"},
            {"Cuadrado", "/resources/square.png"},
            {"Rectangulo", "/resources/rectangle.png"},
            {"Flecha", "/resources/arrow.png"},
            {"Estrella", "/resources/star.png"},
            {"Texto", "/resources/text.png"},
            {"Imagen", "/resources/picture.png"},
            {"Libre", "/resources/pencil.png"}    
        };

        String[][] subButtons = new String[][]{
            {"n"},
            {"n"},
            {"3D Rectangulo", "resources/3drectangle.png"},
            {"n"},
            {"n"},
            {"Izquierda", "resources/leftArrow.png"},
            {"Bidireccional", "resources/bothSides.png"},
            {"n"},
            {"BiC Estrella", "resources/bistarcolor.png"},
            {"TriC Estrella", "resources/tristarcolor.png"}
        };
        setBorder(null);

        render = new ComboBoxRenderer();
        tools = new JButton[9];

        for (int i = 0, j = 0; i < tools.length; i++) {
            try {
                buttonContainer[i] = new JComboBox();
                Image img = ImageIO.read(getClass().getResource(infoImages[i][1]));
                tools[i] = new JButton(infoImages[i][0], new ImageIcon(img));
                /*panel.add(tools[i]);
                panel.add(tools[i]);*/
                tools[i].setMargin(new Insets(0, 0, 0, 0));
                //tools[i].setBorder(null);
                buttonContainer[i].setActionCommand(i + "");
                //tools[i].setActionCommand(i + "");
                //tools[i].addActionListener(this);
                buttonContainer[i].addItem(tools[i]);
                buttonContainer[i].setRenderer(render);
                panel.add(buttonContainer[i]);
                buttonContainer[i].addActionListener(this);
            } catch (Exception e) {
            }
        }

        for (int j = 0, i = 0; j < 10; j++) {
            if (!subButtons[j][0].equals("n")) {
                buttonContainer[i].addItem(new JButton(subButtons[j][0]));
            } else {
                i++;
            }
        }
        JScrollPane sp = new JScrollPane(panel);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(sp, BorderLayout.CENTER);
    }

    @Override
    public void revalidate() {
        super.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Drawer.clickCounter = 0;
        typeSelected = Byte.valueOf(command);
        subtypeSelected = (byte) buttonContainer[typeSelected].getSelectedIndex();
        buttonContainer[typeSelected].setSelectedIndex(0);
        System.out.println("Changed selected type: " + typeSelected
                + "\nsubtypeSelected: " + subtypeSelected);
    }
}

class ComboBoxRenderer implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof JButton) {
            return (JButton) value;
        } else {
            return new JLabel((String) value);
        }
    }

}
