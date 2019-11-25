package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;

import java.awt.event.ActionEvent;

/**
 * @author behnke_m
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AllAnalysisAction extends AbstractAction {

    public AllAnalysisAction(CIPWindow app) {
        super(app);
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new LexicalAnalysisAction(getApp()).actionPerformed(e);
        new SyntaxAnalysisAction(getApp()).actionPerformed(e);
        new SemanticAnalysisAction(getApp()).actionPerformed(e);
        new CodeBuilderAction(getApp()).actionPerformed(e);
    }

}
