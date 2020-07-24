/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import GUI.Window;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme;

/**
 * <h1>Simple Paint</h1>
 * Simple paint it's a software for educational purpose
 * <p>
 * @author pablo
 * @version 1.0
 * @since 2019
 */
public class Paint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            FlatLaf.install(new FlatAtomOneLightIJTheme());
            new Window();
        });
    }

}
