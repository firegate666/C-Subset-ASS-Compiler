package de.haw.cip.gui;

import de.haw.cip.logic.Symboltable;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;

/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class JSymboltable extends JTable {

	private Symboltable symboltable;

	public JSymboltable() {
		super(40,5);
	}
	
	public JSymboltable(Symboltable symbols) {
		super(symbols.rowData(),symbols.columnNames() );
		
		this.symboltable = symbols;
	}
	
	public String getText() {
		return symboltable.toString();
	}
		
}
