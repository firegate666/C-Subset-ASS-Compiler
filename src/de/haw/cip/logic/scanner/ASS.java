package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m	
 *
 * Assign State of the automaton used for the scanner
 * 
 *  =    /-\
 * ---> |ASS| ---> ASSIGN
 *       \-/
 * 
 */

public final class ASS extends State {
	
	private String token = "ASSIGN";
	private static ASS handleVar = null;
	
	@Override
	public void destroy() {
		handleVar = null;
	}
	
	private ASS() {
		initialize();
	}
	
	private void initialize() {
	}

	public static ASS handle() {
		if(handleVar==null) {
			handleVar = new ASS();
			//Prt.ln("\nAssign \"=\" -Zustand erzeugt !!!");
		}
		return handleVar;
	}
	
	@Override
	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a, ch, token);
		changeState(a, SC.handle());
	};
	@Override
	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException
	{
		//restore(a, ch, token);
		//changeState(a, Start.handle());
	};

	@Override
	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a, ch, token);
		changeState(a, Word.handle());
	};

	@Override
	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a, ch, token);
		changeState(a, IC.handle());
	};

	@Override
	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n PrintableASCII found"));
	};

	@Override
	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a, ch, token);
		changeState(a, SPC.handle());
	};

	@Override
	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a, ch, token);
		changeState(a, SPC.handle());
	};

	@Override
	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n * after = not allowed"));
	};

	@Override
	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a,ch, token);
		changeState(a,C.handle());
	};

	@Override
	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, E.handle());
	};

	@Override
	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n > after = not allowed"));
	};

	@Override
	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n < after = not allowed"));
	};

	@Override
	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a, ch, token);
		changeState(a, SPC.handle());
	};

	@Override
	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException
	{
		restoreAndStore(a, ch, token);
		changeState(a, SPC.handle());
	};

	@Override
	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n { after = not allowed"));
	};

	@Override
	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n } after = not allowed"));
	};

	@Override
	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n ; after = not allowed"));
	};

	@Override
	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException
	{
		//restore(a, ch, token);
		//changeState(a, Start.handle());
	};

	@Override
	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n illegal ETX found"));
	};

	@Override
	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n illegal Line Feed found"));
	};

	@Override
	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n ! after = not allowed"));
	};
	@Override
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n , after = not allowed"));
	};

	public static void main(String[] args) {
	}
}
