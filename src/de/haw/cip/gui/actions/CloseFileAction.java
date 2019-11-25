package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;
import de.mb.swing.actions.CloseAction;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Martin Koose
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CloseFileAction extends AbstractAction {


    public CloseFileAction(CIPWindow app) {
        super(app);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        new CloseAction(e);
    }

}
