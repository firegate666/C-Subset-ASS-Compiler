package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;
import de.mb.swing.JInfoDialog;

import java.awt.event.ActionEvent;

/**
 * @author behnke_m
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DisplayInfoDialogAction extends AbstractAction {

	public DisplayInfoDialogAction(CIPWindow app) {
		super(app);
	}
    /**
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
		JInfoDialog d = new JInfoDialog(getApp(),getApp().appTitle(),getApp().appVersion(),getApp().appAuthors() );
		d.show();

    }

}
