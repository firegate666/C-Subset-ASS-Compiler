package de.haw.cip;

import de.haw.cip.gui.CIPWindow;
import de.mk.exception.ExceptionHandler;

/**
 * <p>Überschrift: Compiler & Interpreter Praktikum</p>
 * <p>Beschreibung: Compiler für Subset von C</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Organisation: </p>
 *
 * @author Martin Koose & Marco Behnke
 * <p>
 * This Program can compile source files written in a subset of the
 * c programming language. To understand what commands can be used, look at <i>C Subset Grammar.doc</i>.
 * <p>
 * Compiled programs can be run on a 68K machine.
 */
public class CSubsetCompiler {

    /**
     * Main executable method
     */
    public static void main(String[] args) {
        CIPWindow cip;
        try {
            cip = new CIPWindow();
            cip.setVisible(false);
            cip.setBounds(25, 25, 800, 600);
            cip.setVisible(true);
        } catch (Exception e) {
            System.out.println("STARTER_ERROR");
            e.printStackTrace();
            ExceptionHandler.instance(e);
        }
    }
}
