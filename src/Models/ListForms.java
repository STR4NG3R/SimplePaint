/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class ListForms extends ArrayList<Forms> {

    public boolean add(Forms e, boolean i) {
        if (i) {
            System.out.println("ADDED");
            return super.add(e);
        } else {
            return false;
        }
    }

}
