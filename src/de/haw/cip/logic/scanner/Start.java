package de.haw.cip.logic.scanner;

import de.haw.cip.util.Prt;
import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * Start State of the automaton used for the scanner
 */
public final class Start extends State {
	public void destroy() {
		handleVar = null;
	}
	private Start() {
		initialize();
	}
	
	private void initialize() {
	}

	
	private static Start handleVar = null;
	
	public static Start handle() {
		if(handleVar==null) {
			handleVar = new Start();
		}
		return handleVar;
	}
	

	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException
	{
		store(a,ch);
		changeState(a,SC.handle());
	};

	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException
	{
	};

	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, Word.handle());
	};

	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, IC.handle());
	};

	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException
	{
		Prt.ln("PRINTABLE");
		store(a,ch);
		changeState(a, Ill.handle());
	};

	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, C.handle());
	};

	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, ASS.handle());
	};

	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, G.handle());
	};

	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, L.handle());
	};

	protected void acceptColon(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException
	{
	};

	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, End.handle());
	};

	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException
	{
	};


	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, N.handle());
	};
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException
	{
		store(a, ch);
		changeState(a, SPC.handle());
	};

	public static void main(String[] args) {
	}
}
