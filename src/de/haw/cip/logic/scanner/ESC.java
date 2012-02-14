package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * State for the escape characters of the automaton used for the scanner
 */

public final class ESC extends State {
	
	public void destroy() {
		handleVar = null;
	}
	private static ESC handleVar = null;
	private ESC() {
		initialize();
	}
	
	private void initialize() {
	}

	
	public static ESC handle() {
		if(handleVar==null) {
			handleVar = new ESC();
			//Prt.ln("\nESC -Zustand erzeugt !!!");
		}
		return handleVar;
	}
	
	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException{
		store(a,ch);
		changeState(a,SC.handle());
	}
	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException{
		if(ch == 'n') {
			store(a,ch);
			changeState(a,SC.handle());
		}
		else if(ch == 't') {
			store(a,ch);
			changeState(a,SC.handle());
		}
		else {
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
		}
	};

	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException{
		changeState(a,Ill.handle());
	}
	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException{
		if(ch == 92) { // Bist Du Backslash? \
			store(a,ch);
			changeState(a,SC.handle());
		}
		else {
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
		}
	};
	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n End of text found"));
	}
	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Line feed found"));
	}
	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	}
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Illegal ESC-Character"));
	};

	public static void main(String[] args) {
	}
}
