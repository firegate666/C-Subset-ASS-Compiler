package de.haw.cip.examples.ea_idsi1;

import de.haw.cip.util.Prt;

public final class Sign extends State {

	private static Sign handleVar = null;
	// Priv. Konstruktor gegen externe Objekt-Erzeugung
	private Sign() {
	}

	public static Sign handle() {
		// Erzeugung der einzigen Instanz
		if (handleVar == null) {
			handleVar = new Sign();
			Prt.ln("\nSign-Zustand erzeugt !!!\n");
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
		resetAndStore(a, ch);
		changeState(a, Identifier.handle());
	}

	@Override
	protected void acceptOther(Automaton a, char ch) {
		reset(a, ch);
		changeState(a, Other.handle());
	}

	@Override
	protected void acceptSign(Automaton a, char ch) {
		resetAndStore(a, ch);
		changeState(a, Sign.handle());
	}

}
