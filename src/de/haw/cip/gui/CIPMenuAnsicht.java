package de.haw.cip.gui;

import de.haw.cip.gui.actions.SwitchOutputVisibleAction;
import de.haw.cip.gui.actions.SwitchSourceVisibleAction;
import de.haw.cip.gui.actions.SwitchStatusVisibleAction;

import javax.swing.*;

/**
 * @author Administrator
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CIPMenuAnsicht extends JMenu {

    private CIPMenu menu;

    private JMenuItem view_source;
    private JMenuItem view_output;
    private JMenuItem view_status;

    public CIPMenuAnsicht(CIPMenu source, String title) {
        super(title);
        this.menu = source;
        this.initialize();
    }

    public void selectViewSource(boolean selected) {
        this.view_source.setSelected(selected);
    }

    public void selectViewOutput(boolean selected) {
        this.view_output.setSelected(selected);
    }

    public void selectViewStatus(boolean selected) {
        this.view_status.setSelected(selected);
    }

    private void initialize() {
        this.view_source = new JMenuItem("Quelltext");
        this.view_source.addActionListener(new SwitchSourceVisibleAction(menu.getApp()));

        this.view_output = new JMenuItem("Output");
        this.view_output.addActionListener(new SwitchOutputVisibleAction(menu.getApp()));

        this.view_status = new JMenuItem("Statuszeile");
        this.view_status.addActionListener(new SwitchStatusVisibleAction(menu.getApp()));

        this.add(this.view_source);
        this.add(this.view_output);
        //this.add(this.view_status );
    }


}
