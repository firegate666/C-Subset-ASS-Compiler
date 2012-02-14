package de.mk.exception;

/**
 * @author koose_m@haw.informatik.hamburg.de
 *
 */
public class CompilerErrorException extends Exception{

 private String additionalMsg;
 
  //constructor
  public CompilerErrorException(String xtraMsg) {
   
   this.additionalMsg = xtraMsg;
  }//end constructor
 
  
  public CompilerErrorException(){}
 
  //-----------------------------------------------------//

  //Overrides Throwable's getMessage() method
	@Override
  public String getMessage(){
    return ("Der Compilationsvorgang ist mit folgendem Fehler abgebrochen :\n"+ additionalMsg);
  }//end getMessage method
}

