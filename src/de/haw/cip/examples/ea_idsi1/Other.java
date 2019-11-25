package de.haw.cip.examples.ea_idsi1;

import de.haw.cip.util.Prt;

// Implementierung der Zustände
// nach Gammas Singelton-Muster

public final class Other extends State {

    private static Other handleVar = null;

    // Priv. Konstruktor gegen externe Objekt-Erzeugung
    private Other() {
    }

    public static Other handle() {
        // Erzeugung der einzigen Instanz
        if (handleVar == null) {
            handleVar = new Other();
            Prt.ln("\nOther-Zustand erzeugt !!!\n");
        }
        // Liefert globalen Zugriffspunkt
        return handleVar;
    }

    @Override
    protected void acceptDigit(Automaton a, char ch) {
        store(a, ch);
        changeState(a, Digit.handle());
    }

    @Override
    protected void acceptLetter(Automaton a, char ch) {
        store(a, ch);
        //changeState(a, Letter.handle());
    }

    @Override
    protected void acceptOther(Automaton a, char ch) {
        // Nothing to do
        // No changeState
    }

    @Override
    protected void acceptSign(Automaton a, char ch) {
        store(a, ch);
        changeState(a, Sign.handle());
    }

}
