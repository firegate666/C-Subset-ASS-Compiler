package de.haw.cip.logic.semanticanalysis;

import de.haw.cip.gui.CIPWindow;
import de.haw.cip.logic.Automaton;
import de.mb.exception.InstanceNotInitializedException;
import de.mk.exception.CompilerErrorException;
import de.mk.exception.ExceptionHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author Martin Koose
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CodeBuilder extends Automaton {

    private CIPWindow app;

    private ArrayList sourceLines;
    private String ptf;
    private String asm = "*\tProgrammstart\n\tORG     $1000\n";
    private Iterator it;
    private State actState = Start.handle();
    private Stack opStack;
    private Stack runtimeStack;

    private String EAC = "D0";
    private String HAC = "D1";
    private String opnd1 = "";
    private String opnd2 = "";
    private String dummy = "";
    private String lastcmp = "";

    private int varcount;

    private CodeBuilder() {
    }

    public CodeBuilder(String ptf, CIPWindow app) throws InstanceNotInitializedException {
        this.app = app;
        this.initialize(ptf);
        this.accept(ptf);
    }

    // Aktionen Operandenstack beschreiben
    public void push(String item) {
        this.opStack.push(item);
    }

    public void pushKzT() {
        if (actState.equals(O1EAC.handle())) {
            push(EAC);
        } else if (actState.equals(O2EAC.handle())) {
            push((String) runtimeStack.pop());
        } else {

            // ERROR
        }
    }

    // Aktionen Operandenstack lesen
    public String pop() {
        String temp = (String) this.opStack.pop();
        return temp;
    }

    public void pullOpnd1() {
        opnd1 = pop();
    }

    public void pullOpnd2() {
        opnd2 = pop();
    }

    public void pushDummy() {
        this.opStack.push("(SP)+");
    }

    public void popDummy() {
        String temp = pop();
    }

    public void Opnd1ToEac() {
        this.addMOV('W', pop(), EAC);
    }

    public void Opnd2ToEac() {
        this.addMOV('W', pop(), EAC);
    }

    public void OpndToEac() {
        this.Opnd1ToEac();
    }

    public void eacCMPOpnd2(String s) {
        lastcmp = s;
        asm += "\tCMP.W " + directOpnd(opnd2) + "," + directOpnd(EAC) + "\n";
    }

    public void eacCMPhac(String s) {
        lastcmp = s;
        asm += "\tCMP.W " + directOpnd(HAC) + "," + directOpnd(EAC) + "\n";
    }

    // Aktionen Ergebisaccu leeren
    public void EacToRunTimeStack() {
        this.addMOV('W', directOpnd(EAC), "-(SP)");
    }

    public void EacToOpnd1() {
        this.addMOV('W', directOpnd(EAC), directOpnd(opnd1));
    }

    // Aktionen Operatoren umsetzen
    public void eacOPopnd2(String OP) {
        asm += "\t" + OP + " " + directOpnd(opnd2) + "," + directOpnd(EAC) + "\n";
    }

    public void addAssignment() {
        asm += "\tMOVE.W " + directOpnd(pop()) + "," + directOpnd(pop()) + "\n";
    }

    public void eacOPhac(String OP) {
        asm += "\t" + OP + " " + directOpnd(HAC) + "," + directOpnd(EAC) + "\n";
    }

    public void negEac() {
        asm += "\tNEG " + directOpnd(EAC) + "\n";
    }

    // Sonstige Aktionen
    public void eacToHac() {
        System.out.println("Stack size: " + opStack.size());
        String temp = EAC;
        EAC = HAC;
        HAC = temp;
    }

    private String directOpnd(String opnd) {
        if (opnd == null) return opnd;
        if (Character.isDigit(opnd.charAt(0)))
            return "#" + opnd;
        return opnd;
    }

    private void initialize(String ptf) {
        this.opStack = new Stack();
        varcount = 0;
        this.runtimeStack = new Stack();
        StringTokenizer st;
        st = new StringTokenizer(ptf, "\n");
        while (st.hasMoreTokens()) {
            this.addSourceLine(st.nextToken());
        }
    }

    private void setIterator() throws InstanceNotInitializedException {
        if (sourceLines == null)
            throw new InstanceNotInitializedException("Can't initialize iterator. No source lines added.");
        else
            it = sourceLines.iterator();
    }

    private String nextLine() throws InstanceNotInitializedException {
        if (it == null) {
            this.setIterator();
        }
        String result;
        result = (String) it.next();
        return result;
    }

    private boolean hasNextLine() throws InstanceNotInitializedException {
        if (it == null) {
            this.setIterator();
        }
        return it.hasNext();
    }

    private void addSourceLine(String line) {
        if (sourceLines == null) {
            sourceLines = new ArrayList();
        }

        line = line.trim();
        if (!line.equals("")) {
            sourceLines.add(line);
        }
    }

    public int codeLines() {
        if (sourceLines == null)
            return 0;
        else
            return sourceLines.size();
    }

    public String getAsm() {
        return this.asm;
    }

    public void addMOV(char size, String a, String b) {
        asm += "\tMOVE." + size + " " + directOpnd(a) + "," + directOpnd(b) + "\n";
    }

    public void addMOVAdr(String a, String b) {
        asm += "\tMOVEA.L " + directOpnd(a) + "," + directOpnd(b) + "\n";
    }

    public void addMUL() {
        String b = directOpnd(pop());
        String a = directOpnd(pop());
        asm += "\tMULT " + a + "," + b + "\n";
    }

    public void addADD() {
        String b = directOpnd(pop());
        String a = directOpnd(pop());
        if (Character.isDigit(a.charAt(0)))
            a = "#" + a;
        asm += "\tADD.W " + a + "," + b + "\n";
    }

    public void addDIV() {
        String b = directOpnd(pop());
        String a = directOpnd(pop());
        asm += "\tDIVS " + a + "," + b + "\n";
    }

    public void addSUB() {
        String b = directOpnd(pop());
        String a = directOpnd(pop());
        asm += "\tSUB.W " + a + "," + b + "\n";
    }

    public void addBRF(String marke) throws CompilerErrorException {
        String branch = "";
        if (lastcmp.equalsIgnoreCase("==")) branch = "BNE";
        else if (lastcmp.equalsIgnoreCase("<")) branch = "BGE";
        else if (lastcmp.equalsIgnoreCase("<=")) branch = "BGT";
        else if (lastcmp.equalsIgnoreCase(">")) branch = "BLE";
        else if (lastcmp.equalsIgnoreCase(">=")) branch = "BLT";
        else if (lastcmp.equalsIgnoreCase("!=")) branch = "BEQ";
        else if (lastcmp.equalsIgnoreCase("<>")) branch = "BEQ";
        else throw new CompilerErrorException("Ung�ltiger Vergleich: " + lastcmp);

        asm += "\t" + branch + " " + marke.substring(1) + "\n";
    }

    public void addJMP(String marke) {
        asm += "\tJMP " + marke.substring(1) + "\n";
    }

    public void addPrintString() throws CompilerErrorException {
        //Annahme der auszugebende String
        //existiert an einer definierten Stelle im Speicher.

        ArrayList list = new ArrayList();

        this.asm += "*\tPRINTANWEISUNG\n";
        int opc = new Integer(pop()).intValue();

        for (int i = 0; i < opc; i++) {
            String operand = pop();


            if ((Character.isDigit(operand.charAt(0)))
                    || (app.getScanner().getSymbols().getSymbol(operand).get_type().equalsIgnoreCase("int"))) {
                // PRINT INTEGER
                this.addMOV('W', operand, "D1");
                this.addMOV('B', "#NUMOUT", "D0");
                this.addTrap(15);
            } else {
                // PRINTSTRING
                boolean linebreak = crlfAtEnd(operand);

                this.addMOVAdr("#" + operand, "A1");
                this.addMOV('W', "#(STRVAR" + (varcount + 1) + "-STRVAR" + varcount + ")", "D1");

                if (linebreak)
                    this.addMOV('B', "#PRTSTRCLRLF", "D0");
                else
                    this.addMOV('B', "#PRTSTR", "D0");

                //entweder wir f�gen jedem PRG PRTSTR zu oder es muss eine neuen Methode her.
                this.addTrap(15);
                varcount++;
            }
        }
    }

    private boolean crlfAtEnd(String operand) throws CompilerErrorException {
        boolean result = false;
        String value = app.getScanner().getSymbols().getSymbol(operand).get_initialValue();
        if (value.endsWith("" + '\\' + 'n' + '\'')) {
            value = value.substring(0, value.length() - 3);
            if (value.length() == 1) value += " '";
            else value += "'";
            app.getScanner().getSymbols().getSymbol(operand).set_initialValue(value);
            result = true;
        }

        return result;
    }

    public void addScanf() {
        String varName = pop();
        this.asm += "*\tSCANANWEISUNG\n";
        this.addMOV('B', "#NUMIN", "D0");
        this.addTrap(15);
        this.addMOV('W', "D1", varName);
    }

    public void addTrap(int trapNumber) {
        asm += "\tTRAP #" + trapNumber + "\n";
    }

    public void addNEG(/*String a*/
    ) {
        String a = directOpnd(pop());
        asm += "\tNEG " + a + "\n";
    }

    public void addCMP(String s) {
        String b = directOpnd(pop());
        String a = directOpnd(pop());
        asm += "\tCMP " + b + "," + a + "\n";
    }

    public void addMARK(String mark) {
        asm += mark.substring(1) + "\n";
    }

    //
    //
    //
    //
    // ------> Automaton ab HIER
    //
    //
    //

    @Override
    public void accept(String s) {
        String result = "";
        try {
            while (this.hasNextLine()) {
                String actLine = this.nextLine();

                StringTokenizer tokens = new StringTokenizer(actLine, " ");
                while (tokens.hasMoreTokens()) {

                    String actToken = tokens.nextToken();
                    //Prt.ln(actToken);
                    if (actToken.equals("+"))
                        actState.acceptPlus(this, actToken);
                    else if (actToken.equals("-"))
                        actState.acceptMinus(this, actToken);
                    else if (actToken.equals("*"))
                        actState.acceptMul(this, actToken);
                    else if (actToken.equals("/"))
                        actState.acceptDiv(this, actToken);
                    else if (actToken.equals("="))
                        actState.acceptAssign(this, actToken);
                    else if (actToken.equals(">"))
                        actState.acceptCMP(this, actToken);
                    else if (actToken.equals("<"))
                        actState.acceptCMP(this, actToken);
                    else if (actToken.equals("!="))
                        actState.acceptCMP(this, actToken);
                    else if (actToken.equals("=="))
                        actState.acceptCMP(this, actToken);
                    else if (actToken.equals("<="))
                        actState.acceptCMP(this, actToken);
                    else if (actToken.equals(">="))
                        actState.acceptCMP(this, actToken);
                    else if (actToken.equals("<>"))
                        actState.acceptCMP(this, actToken);
                    else if (actToken.equals("JMP"))
                        actState.acceptJMP(this, actToken);
                    else if (actToken.equals("BRF"))
                        actState.acceptBRF(this, actToken);
                    else if (actToken.equals("scanf"))
                        actState.acceptScan(this, actToken);
                    else if (actToken.equals("printf"))
                        actState.acceptPrint(this, actToken);
                    else if (actToken.equals("@"))
                        actState.acceptPreMinus(this, actToken);
                    else if (actToken.startsWith(":"))
                        actState.acceptMark(this, actToken);
                    else
                        actState.acceptOperand(this, actToken);

                }

            }
            asm += "\tSTOP\t#$2700\n";
        } catch (Exception e) {
            ExceptionHandler.instance(e);
        }
    }

    public void restore(String s, String foundToken) {
    }

    public void restoreAndStore(String s, String foundToken) {
        restore(s, foundToken);
        store(s);
    }

    public void store(String s) {
    }

    public void resetAndStore(String s) {
    }

    public void reset(String s) {
    }

    protected void changeState(State newState) {
        actState = newState;
    }

}
