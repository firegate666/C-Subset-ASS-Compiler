package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;

import java.awt.event.ActionEvent;

/**
 * @author Administrator
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SwitchOutputVisibleAction extends AbstractAction {

    /**
     * Constructor for SwitchOutputVisibleAction.
     *
     * @param app
     */
    public SwitchOutputVisibleAction(CIPWindow app) {
        super(app);
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        getApp().switchOutputVisible();
    }

}
