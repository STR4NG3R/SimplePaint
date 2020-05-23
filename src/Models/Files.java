/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Models.ListForms;
import java.io.FileInputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author don_roquiri
 */
public class Files {

    public static String path;
   /* public static void leer() {
        try (java.io.FileInputStream archivo = new FileInputStream("Archivo.spm"); java.io.ObjectInputStream obj_archivo = new java.io.ObjectInputStream(archivo);) {
            Models.GlobalListForms.listForms = (ListForms) obj_archivo.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public static void guardar() {
        try (
                java.io.FileOutputStream archivo = new java.io.FileOutputStream("Archivo.spm"); java.io.ObjectOutputStream obj_archivo = new java.io.ObjectOutputStream(archivo);) {
            obj_archivo.writeObject(Models.GlobalListForms.listForms);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un error en la escritura del archivo.\nError: " + e);
        }
    }

    public static void guardar(String name) {
        try (
                java.io.FileOutputStream archivo = new java.io.FileOutputStream(path +"\\"+ name + ".spm");
                java.io.ObjectOutputStream obj_archivo = new java.io.ObjectOutputStream(archivo);) {
            obj_archivo.writeObject(Models.GlobalListForms.listForms);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un error en la escritura del archivo.\nError: " + e);
        }
    }

    public static void leer() {
        //if(!path.endsWith(".spm")){
        System.out.println(path);
        try (java.io.FileInputStream archivo = new FileInputStream(path);
                java.io.ObjectInputStream obj_archivo = new java.io.ObjectInputStream(archivo);) {
            Models.GlobalListForms.listForms = (ListForms) obj_archivo.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*}else{
            JOptionPane.showMessageDialog(null, "Error, Bad file selected");
        }*/
    }

}
