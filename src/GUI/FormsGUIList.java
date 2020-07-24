/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.GlobalListForms;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author pablo
 */
public class FormsGUIList extends JPanel {

    JList<String> list;
    DefaultListModel<String> listMdl;

    public FormsGUIList() {
        listMdl = new DefaultListModel<>();
        setLayout(new BorderLayout());
        list = new JList<>(listMdl);
        list.setMinimumSize(new Dimension(228, 513));
        add(list, BorderLayout.CENTER);

    }

    public void add(String nanmeForm) {
        listMdl.addElement(nanmeForm);
    }

    public void remove(int index[]) {
        for (int i : index) {
            list.remove(i);
        }
    }

    public void copy(int index[]) {
        for (int i : index) {
            listMdl.addElement(GlobalListForms.listForms.get(i).type + "");
            GlobalListForms.listForms.add(
                    GlobalListForms.listForms.get(i)
            );
        }
    }

    public int[] getSelectedIndices() {
        return list.getSelectedIndices();
    }
}
