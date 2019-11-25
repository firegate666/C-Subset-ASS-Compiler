package de.haw.cip.logic;

import de.haw.cip.logic.scanner.State;
import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Automaton {

    private char actChar = ' ';
    protected State actState = null;
    private String name = "noName";
    protected ArrayList buffer = new ArrayList();
    private ArrayList states = new ArrayList();

    protected Automaton(String n) {
        name = n;
    } // Init auf Anfangszustand

    protected Automaton() {
        this("no name given");
    }

    public void resetScanner() {
        for (Object state : states) ((State) state).destroy();
        buffer.clear();
        actChar = ' ';
        actState = null;
    }

    public String getBufferCopy() {
        String result = "";
        for (Object o : buffer) result += o.toString();
        return result;
    }

    public abstract void accept(String actString) throws CompilerErrorException;


    public void changeState(State newState) {
        // Aktualisierung des Zustands
        actState = newState;
        if (!states.contains(newState))
            states.add(newState);
    }

    public void store(char ch) {
        buffer.add(ch);
    }

    private void restore(char ch) {
        Iterator it = buffer.iterator();
        Prt.st("TOKEN Found: ");
        while (it.hasNext())
            Prt.st(it.next().toString());
        Prt.ln();
        reset(ch);
    }

    public void restoreAndStore(char ch) {
        restore(ch);
        store(ch);
    }

    public void reset(char ch) {
        buffer.clear();
    }

    public void resetAndStore(char ch) {
        reset(ch);
        store(ch);
    }

}
