package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * Equal State of the automaton used for the scanner
 * 
 *  =    /-\   =    /-\
 * ---> |ASS| ---> | E | ---> EQUAL
 *       \-/        \-/
 */
public final class E extends State {
	public void destroy() {
		handleVar = null;
	}
	private E() {
		initialize();
	}
	
	private void initialize() {
	}

	
	private static E handleVar = null;
	private String token = "COMPARE_OPERATOR";
	
	public static E handle() {
		if(handleVar==null) {
			handleVar = new E();
			//Prt.ln("\nE \"==\" -Zustand erzeugt !!!");
		}
		return handleVar;
	}
	
	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,SC.handle());
	};

	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException
	{
		//restore(a,ch, token);
		//changeState(a,Start.handle());
	};

	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,Word.handle());
	};

	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,IC.handle());
	};

	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n PrintableASCII found"));
	};

	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,SPC.handle());
	};

	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,SPC.handle());
	};

	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n * after == not allowed"));
	};

	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,C.handle());
	};

	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n = after == not allowed"));
	};

	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n > after == not allowed"));
	};

	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n < after == not allowed"));
	};

	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,SPC.handle());
	};

	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,SPC.handle());
	};

	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n { after == not allowed"));
	};

	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n } after == not allowed"));
	};

	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n ; after == not allowed"));
	};

	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException
	{
	};

	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n End of text found"));
	};

	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n Line feed found"));
	};

	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n ! after == not allowed"));
	};
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n , after == not allowed"));
	};


	public static void main(String[] args) {
	}
}
