package de.haw.cip.logic.semanticanalysis;

import de.mk.exception.CompilerErrorException;

/**
 * @author koose_m@haw.informatik.hamburg.de
 */
public class O1EAC extends State {

    private static State handleVar = null;

    private O1EAC() {
    }

    public static State handle() {
        if (handleVar == null) {
            handleVar = new O1EAC();
        }
        //Prt.ln("O1EAC");
        return handleVar;
    }

    public static void main(String[] args) {
    }

    @Override
    protected void acceptOperand(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.EacToRunTimeStack();
        a.push(s);
        changeState(a, OP2.handle());
    }

    @Override
    protected void acceptPlus(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.pullOpnd2();
        a.popDummy();
        a.eacOPopnd2("ADD.W");
        changeState(a, O2EAC.handle());
    }

    @Override
    protected void acceptMinus(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.pullOpnd2();
        a.popDummy();
        a.eacOPopnd2("SUB.W");
        changeState(a, O2EAC.handle());
    }

    @Override
    protected void acceptMul(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.pullOpnd2();
        a.popDummy();
        a.eacOPopnd2("MULS");
        changeState(a, O2EAC.handle());
    }

    @Override
    protected void acceptDiv(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.pullOpnd2();
        a.popDummy();
        a.eacOPopnd2("DIVS");
        changeState(a, O2EAC.handle());
    }

    @Override
    protected void acceptPrint(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.addPrintString();
        changeState(a, Start.handle());
    }

    @Override
    protected void acceptCMP(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.pullOpnd2();
        a.popDummy();
        a.eacCMPOpnd2(s);
        changeState(a, Start.handle());
    }

    @Override
    protected void acceptPreMinus(CodeBuilder a, String s)
            throws CompilerErrorException {
        a.EacToRunTimeStack();
        a.OpndToEac();
        a.negEac();
        changeState(a, O2EAC.handle());
    }

    @Override
    public void destroy() {
    }

}
