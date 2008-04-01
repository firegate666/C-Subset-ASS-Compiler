package de.haw.cip.logic;

import java.util.Vector;

/**
 * @author koose_m@haw.informatik.hamburg.de
 *
 */
public class Symbol {
	/**
	 * Name of symbol
	 */
	private String _identifier;
	/**
	 * Type of symbol
	 */
	private String _type;
	/**
	 * Symbol is assigned in program
	 */
	private boolean _assigned;
	/**
	 * Value after declaration
	 */
	private String _initialValue;
	/**
	 * Value is used in program
	 */
	private boolean _used;
	
	public Vector rowData() {
		Vector result=new Vector();
		result.add(this.get_identifier());
		result.add(this.get_type());
		result.add(new Boolean(this.is_assigned() ));
		result.add(this.get_initialValue()  );
		result.add(new Boolean(this.is_used() ));
		return result;
	}
		
	public Symbol(String identifier, String type) {
		this.set_identifier(identifier );
		this.set_type(type);
	}

	/**
	 * Returns the _assigned.
	 * @return boolean
	 */
	public boolean is_assigned() {
		return _assigned;
	}

	/**
	 * Returns the _identifier.
	 * @return String
	 */
	public String get_identifier() {
		return _identifier;
	}

	/**
	 * Returns the _type.
	 * @return String
	 */
	public String get_type() {
		return _type;
	}

	/**
	 * Sets the _assigned.
	 * @param _assigned The _assigned to set
	 */
	public void set_assigned(boolean _assigned) {
		this._assigned = _assigned;
	}

	/**
	 * Sets the _identifier.
	 * @param _identifier The _identifier to set
	 */
	private void set_identifier(String _identifier) {
		this._identifier = _identifier;
	}

	/**
	 * Sets the _type.
	 * @param _type The _type to set
	 */
	private void set_type(String _type) {
		this._type = _type;
	}

    /**
     * Returns the _initialValue.
     * @return int
     */
    public String get_initialValue() {
        return _initialValue;
    }

    /**
     * Sets the _initialValue.
     * @param _initialValue The _initialValue to set
     */
    public void set_initialValue(String _initialValue) {
        this._initialValue = _initialValue;
    }
    
    public String toString() {
    	String result="";
		result+=this.get_identifier() +"\t";
		result+=this.get_type() +"\t";
		result+=this.is_assigned() +"\t";
		result+=this.get_initialValue()+"\t";
		result+=this.is_used();
		return result;
    }

	/**
	 * Returns the _isUsed.
	 * @return boolean
	 */
	public boolean is_used() {
		return _used;
	}

	/**
	 * Sets the _isUsed.
	 * @param _isUsed The _isUsed to set
	 */
	public void set_used(boolean _isUsed) {
		this._used = _isUsed;
	}

}
