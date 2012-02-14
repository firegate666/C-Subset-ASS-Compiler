package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;

import java.awt.event.ActionEvent;

/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SwitchStatusVisibleAction extends AbstractAction {

	/**
	 * Constructor for SwitchStatusVisibleAction.
	 * @param app
	 */
	public SwitchStatusVisibleAction(CIPWindow app) {
		super(app);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		getApp().switchStatusVisible();
	}

}
