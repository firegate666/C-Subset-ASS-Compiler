package de.mb.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

/**
 * @author Marco Behnke
 */

public class JDefaultFrame extends JFrame {

    /**
     * Public constructor
     */
    public JDefaultFrame() {
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new DefaultListener());
    }

    class DefaultListener extends WindowAdapter {
        DefaultListener() {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            new CloseAction(e);
        }
    }

    // Internal classes
    class CloseAction {
        private String _msg =
                ResourceBundle.getBundle("de.mb.swing.jdefaultframe").getString(
                        "closingdialog_message");
        private String _title =
                ResourceBundle.getBundle("de.mb.swing.jdefaultframe").getString(
                        "closingdialog_title");

        CloseAction(AWTEvent e) {
            action(e);
        }

        private void action(AWTEvent e) {
            int test =
                    JOptionPane.showConfirmDialog(
                            (Component) e.getSource(),
                            _msg,
                            _title,
                            JOptionPane.YES_NO_OPTION);
            if (test == 0)
                System.exit(0);
        }
    }
}
