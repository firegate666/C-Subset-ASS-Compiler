package de.haw.cip.gui.actions;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.logic.syntaxanalysis.SyntaxAnalysis;
import de.mk.exception.ExceptionHandler;

import java.awt.event.ActionEvent;

/**
 * @author Administrator
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SemanticAnalysisAction extends AbstractAction {

    /**
     * Constructor for SemanticAnalysisAction.
     *
     * @param app
     */
    public SemanticAnalysisAction(CIPWindow app) {
        super(app);
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            SyntaxAnalysis s = SyntaxAnalysis.getInstance();
            getApp().setPtfOutput(s.getPtf());
            getApp().printStatus("PTF-Erzeugung beendet.");
            getApp().getMenu().deactivateAllAnalysis();
            getApp().getMenu().deactivateLexicalAnalysis();
            getApp().getMenu().deactivateSyntaxAnalysis();
            getApp().getMenu().deactivateSemanticAnalysis();
            getApp().getMenu().activateCodeBuilder();
        } catch (Exception ex) {
            ExceptionHandler.instance(ex);
        }

    }

}
