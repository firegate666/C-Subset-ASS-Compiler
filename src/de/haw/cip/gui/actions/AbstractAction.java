package de.haw.cip.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.haw.cip.gui.CIPWindow;

public abstract class AbstractAction implements ActionListener {

	private CIPWindow app; 	

	@Override
	public abstract void actionPerformed(ActionEvent e) ;

	public AbstractAction(CIPWindow app) {
		setApp(app);
	}

	private void setApp(CIPWindow app) {
		this.app = app;
	}
	
	public CIPWindow getApp() {
		return app;
	}		

}
