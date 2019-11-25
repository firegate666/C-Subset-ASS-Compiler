package de.haw.cip.logic.semanticanalysis;

import de.mk.exception.CompilerErrorException;

/**
 * @author koose_m@haw.informatik.hamburg.de
 */
public class OP1 extends State {

    private static State handleVar = null;

    private OP1() {
    }

    public static State handle() {
        if (handleVar == null) {
            handleVar = new OP1();
        }
        //Prt.ln("OP1");
        return handleVar;
    }

    public static void main(String[] args) {
    }

    protected void acceptOperand(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.push(s);
        changeState(a, OP2.handle());
    }

    protected void acceptScan(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.addScanf();
        changeState(a, Start.handle());
    }

    protected void acceptPreMinus(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.OpndToEac();
        a.negEac();
        changeState(a, O2EAC.handle());
    }

    public void destroy() {
    }
}
