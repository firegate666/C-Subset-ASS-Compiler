package de.haw.cip.logic.scanner;

import de.haw.cip.logic.*;
import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * End State of the automaton used for the scanner
 */
public final class End extends State {
	
	public void destroy() {
		handleVar = null;
	}
	private static End handleVar = null;
	private End() {
		initialize();
	}
	
	private void initialize() {
	}

	
	public static End handle() {
		if(handleVar==null) {
			handleVar = new End();
		}
		//Prt.ln("\nEnd-Zustand erzeugt !!! Der Scanner ist ferig und alle Tokens gelesen.");
		return handleVar;
	}
	
	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptColon(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptTick(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptDot(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException{};
	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException{};

	public static void main(String[] args) {
	}
}
