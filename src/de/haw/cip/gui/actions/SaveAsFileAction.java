package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.gui.FileDialog;

import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author behnke_m
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SaveAsFileAction extends AbstractAction {

    public SaveAsFileAction(CIPWindow app) {
        super(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File f = new FileDialog(Ressource_Bundle.getString("dialog_choosedestinationfile")).getFile(); //$NON-NLS-1$
        if (f == null) return;
        getApp().setSourceFile(f);
        new SaveFileAction(getApp()).actionPerformed(e);
        //getApp().addLastOpenedFile(f);
        new OpenFileAction(getApp()).actionPerformedAfterSave(e);
    }


}
