package de.haw.cip.logic;



/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class Token {
	private String nSem;
	private String nSym;
	
	public Token(String newNSym, String newNSem) {
		setNSym(newNSym);
		setNSem(newNSem);
	}
	
	public String getNSym() {
		return nSym;
	}
	
	public String getNSem() {
		return nSem;
	}
	
	private void setNSym(String newNSym) {
		nSym = newNSym;
	}
	
	private void setNSem(String newNSem){
		nSem = newNSem;
	}
	

}
