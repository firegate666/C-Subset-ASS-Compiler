package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractAction implements ActionListener {

    private CIPWindow app;

    public AbstractAction(CIPWindow app) {
        setApp(app);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);

    public CIPWindow getApp() {
        return app;
    }

    private void setApp(CIPWindow app) {
        this.app = app;
    }

}
