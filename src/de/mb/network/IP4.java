package de.mb.network;/** *  Title: Description: Copyright: Copyright (c) 2001 Company: * * @author     mb * @created    December 11, 2001 * @version    1.0 */public class IP4 {	/**  Description of the Field */	private int tuple1;	/**  Description of the Field */	private int tuple2;	/**  Description of the Field */	private int tuple3;	/**  Description of the Field */	private int tuple4;	/**	 *  Constructor for the IP4 object	 *	 * @param  tuple1  Description of the Parameter	 * @param  tuple2  Description of the Parameter	 * @param  tuple3  Description of the Parameter	 * @param  tuple4  Description of the Parameter	 */	public IP4(int t1, int t2, int t3, int t4) throws MalformedIPException {		StringBuilder errorMessage = new StringBuilder("");		if ((t1 < 1) || (t1 > 254))			errorMessage.append(				"tuple1 is malformed. Must be between 2 and 254.\n");		if ((t2 < 1) || (t2 > 254))			errorMessage.append(				"tuple2 is malformed. Must be between 2 and 254.\n");		if ((t3 < 1) || (t3 > 254))			errorMessage.append(				"tuple3 is malformed. Must be between 2 and 254.\n");		if ((t4 < 1) || (t4 > 254))			errorMessage.append(				"tuple4 is malformed. Must be between 2 and 254.\n");		if(!errorMessage.equals(""))			throw new MalformedIPException(errorMessage.toString() );		setTuple1(t1);		setTuple2(t2);		setTuple3(t3);		setTuple4(t4);	}	protected void setTuple1(int number) {		tuple1 = number;	}	protected void setTuple2(int number) {		tuple2 = number;	}	protected void setTuple3(int number) {		tuple3 = number;	}	protected void setTuple4(int number) {		tuple4 = number;	}	/**	 *  Description of the Method	 *	 * @return    Description of the Return Value	 */	@Override	public String toString() {		StringBuilder result = new StringBuilder();		result.append(tuple1).append(".");		result.append(tuple2).append(".");		result.append(tuple3).append(".");		result.append(tuple4);		return result.toString();	}}