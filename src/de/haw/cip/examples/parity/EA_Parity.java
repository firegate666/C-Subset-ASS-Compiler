package de.haw.cip.examples.parity;

import de.haw.cip.util.Prt;

public class EA_Parity {

	public static void main(String[] args) {

		// ! Odd e = new Odd(); // Privater Konstruktor
		Prt.ln();

		Automaton a1 = new Automaton("a1"); // Erzeugung zweier Automaten
		Automaton a2 = new Automaton("a2");

		a1.acceptEtx();

		a1.acceptZero();
		a1.acceptZero();
		a1.acceptEtx();

		a1.acceptZero();
		a2.acceptZero();
		a1.acceptOne();
		a2.acceptOne();

		a1.acceptZero();
		a2.acceptOne();
		a1.acceptEtx();
		a2.acceptZero();

		a1.acceptOne();
		a2.acceptOne();
		a1.acceptOne();
		a2.acceptZero();
		a1.acceptEtx();
		a2.acceptEtx();

		Prt.ln();

	}
}
