package de.haw.cip.examples.ea_idsi1;

import de.haw.cip.util.Prt;

public class EA_IdSI1 {

	public static void main(String[] args) {

		// ! Odd e = new Odd(); // Privater Konstruktor
		Prt.ln();

		Automaton a1 = new Automaton("a1");

		a1.accept("Einer 2Geht _Noch");
		a1.accept("?,:*#eins2DREI");
		a1.accept("123  456Sieben+X-8+9!!!");
		a1.accept("++-90+ 135-Ende");

		Prt.ln();
		System.out.println("Fertig!!");
	}
}
