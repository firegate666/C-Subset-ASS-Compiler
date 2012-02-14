package de.haw.cip.logic.scanner;

import de.mk.exception.CompilerErrorException;

/**
 * @author behnke_m / koose_m
 *
 * Comment State of the automaton used for the scanner
 *
 *        |----------------------------------> DIVISION 
 *        |
 *        |               *
 *  /    /-\   *    /-\  --->  /-\   /    /-\
 * ---> | C | ---> |CA |  o   |CE2| ---> |Sta|
 *       \-/        \-/  <---  \-/        \-/
 *        |         | ^
 *       /|         | |                    ^
 *        |          -                     |
 *        \/         o                     |
 *       /-\                               |
 *      |CE1|------------------------------|
 *       \-/
 *       | ^
 *       | |
 *        -
 *        o
 */
public final class CA extends State {
	@Override
	public void destroy() {
		handleVar = null;
	}
	private CA() {
		initialize();
	}
	
	private void initialize() {
	}

	
	private static CA handleVar = null;
	
	public static CA handle() {
		if(handleVar==null) {
			handleVar = new CA();
			//Prt.ln("\nCA \"/*\" -Zustand erzeugt !!!");
		}
		return handleVar;
	}
	

	@Override
	protected void acceptGoosefeet(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptOther(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptLetter(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptDigit(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptPrintable(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptPlus(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptMinus(Scanner a, char ch) throws CompilerErrorException{};

	@Override
	protected void acceptMul(Scanner a, char ch) throws CompilerErrorException{
		store(a,ch);
		changeState(a,CE2.handle());
	};

	@Override
	protected void acceptDiv(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptEqual(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptGreater(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptLess(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptPar_On(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptPar_Off(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptK_On(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptK_Off(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptSemicolon(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptSpace(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptETX(Scanner a, char ch) throws CompilerErrorException
	{
		changeState(a, Ill.handle("Bufferdump: "+a.getBufferCopy() +"\n End of text found"));
	};

	@Override
	protected void acceptLF(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptNOT(Scanner a, char ch) throws CompilerErrorException{};
	@Override
	protected void acceptKomma(Scanner a, char ch) throws CompilerErrorException{};

	public static void main(String[] args) {
	}
}
