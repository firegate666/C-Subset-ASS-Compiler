package de.haw.cip.examples.ea_idsi1;

import de.haw.cip.util.Prt;

public final class Digit extends State {

    private static Digit handleVar = null;

    // Priv. Konstruktor gegen externe Objekt-Erzeugung
    private Digit() {
    }

    public static Digit handle() {
        // Erzeugung der einzigen Instanz
        if (handleVar == null) {
            handleVar = new Digit();
            Prt.ln("\nDigit-Zustand erzeugt !!!\n");
        }
        // Liefert globalen Zugriffspunkt
        return handleVar;
    }

    @Override
    protected void acceptDigit(Automaton a, char ch) {
        store(a, ch);
        // No changeState
    }

    @Override
    protected void acceptLetter(Automaton a, char ch) {
        restoreAndStore(a, ch);
        changeState(a, Identifier.handle());
    }

    @Override
    protected void acceptOther(Automaton a, char ch) {
        restore(a, ch);
        changeState(a, Other.handle());
    }

    @Override
    protected void acceptSign(Automaton a, char ch) {
        restoreAndStore(a, ch);
        changeState(a, Sign.handle());
    }

}
