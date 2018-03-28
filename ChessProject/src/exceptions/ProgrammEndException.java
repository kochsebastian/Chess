/**
 * 
 */
package exceptions;

/**
 * @author SebastianKoch
 * class for the ProgrammEndException exception
 */
public class ProgrammEndException extends Exception {

	/**
	 * default id
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @param message the only massage can be : "Checkmate ends the game"
	 */
	public ProgrammEndException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
