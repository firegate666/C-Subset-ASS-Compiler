package de.mk.exception;

import de.mk.swing.ScrollText;

import javax.swing.*;
import java.awt.*;

/**
 * @author koose_m
 */


public final class ExceptionHandler {

    /**
     * A handle to the unique Singleton instance.
     */
    static private ExceptionHandler _instance = null;
    Exception ex;
    private ScrollText infoTF;
    private JTabbedPane tp;
    private JFrame frame = null;
    /**
     * The constructor could be made private
     * to prevent others from instantiating this class.
     * But this would also make it impossible to
     * create instances of Singleton subclasses.
     */
    private ExceptionHandler() {
    } // no other Instances please

    /**
     * @return The unique instance of this class.
     */
    static public ExceptionHandler instance(Exception e) {
        if (null == _instance) {
            _instance = new ExceptionHandler();
        }
        _instance.showException(e);
        return _instance;
    }


    private JPanel getTextPanel(String eMsg) {

        ScrollText sourceText = new ScrollText(new SystemInfo().getInfosAsString());
        ScrollText destText = new ScrollText(eMsg);
        JPanel split = new JPanel();
        split.setLayout(new GridLayout(2, 1));
        split.add(sourceText.getPane());
        split.add(destText.getPane());
        return split;
    }

    private String getMsg(Exception e) {
        String result = "";

        result += e.getMessage() + "\n";
        result += "Cause: " + e.getCause() + "\n";
        // Ist das Gleiche wie e.getMessage() siehe oben
        //result += e.toString()+"\n";
        result += "-------------------------------------\n";
//	StackTraceElement[] a = e.getStackTrace();
//	
//	e.printStackTrace(new PrintStream()) 
//	for(int i=0; i < a.length;i++){
//	result += a.toString()+"\n";
//	}

        return result;
    }

    private void showException(Exception e) {
        ex = e;
        e.printStackTrace();
        if (frame == null) {
            frame = new JFrame();
            frame.setTitle("EXCEPTION!!!!");
            frame.getContentPane().add(this.getTextPanel(this.getMsg(e)));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setVisible(true);
            // Damit das Ursprungsfenster offen bleibt
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else { // wo sollen weitere Exceptions aufgenommen werden ?
        }
    }
}
