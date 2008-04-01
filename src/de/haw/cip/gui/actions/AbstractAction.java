package de.haw.cip.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.haw.cip.gui.CIPWindow;
import de.mk.exception.CompilerErrorException;

public abstract class AbstractAction implements ActionListener {

	private CIPWindow app; 	

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
