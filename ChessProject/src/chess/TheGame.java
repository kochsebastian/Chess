/**
 * 
 */
package chess;
import java.io.IOException;

import exceptions.ProgrammEndException;


/**
 * @author SebastianKoch
 * This class contains the main method
 */
public final class TheGame {
	
	static Player s = new Player();
	
	/**
	 * Discribs to the user how to input a move then setups the game, draws it and then starts the game
	 * @param args arguments
	 */
	public static void main(String[] args) { 
		System.out.println("Wie werden Zuege eingegeben? => Um einen Zug einzugeben muss zuerst das Startfeld"
				+ " eingegeben werden, \ndazu wird zuerst der Linienbuchstabe und dann die Reihennummer ohne "
				+ "Leerzeichen eingegeben und dann bestaetigt (Bsp. e2 bzw. E2");
		System.out.println("Danach kann ein Zielfeld im gleichen Format eingegen und bestaetigt werden "
				+ "(Bsp. e2 bzw. E2");
		System.out.println("Schachs und Schachmatts werden automatisch ausgegeben");
		System.out.println("Weiss beginnt:");
		Chessboard.setup();
		Chessboard.draw();
		
		while(true) { 
			try {
				playGame();
			}catch(ProgrammEndException e) {
				break;
			}
		}
		System.exit(0);

	}
	
	/**
	 * the game: black and white are playing in turns 
	 * @throws ProgrammEndException if a checkmate occurs the exception ends the game
	 */
	private static void playGame() throws ProgrammEndException {
		turn("Weiss");
		turn("Schwarz");
	}
	
	
	/**
	 * in a turn the player is asked for the source square and then for the target square
	 *  if the input is valid the board will be updated
	 * @param whichTurn "Weiss" for the white player / "Schwarz" for the black player
	 * @throws ProgrammEndException if a checkmate occurs the exception ends the game
	 */
	private static void turn(String whichTurn) throws ProgrammEndException {

		Boolean richtig = false;
		Boolean whiteBlack = (whichTurn.equals("Weiss") ? true : false);
		int [] targetSquare = new int[2];
		int [] sourceSquare = new int[2]; 
		
		
		do {
			Boolean repeat;
			
			do {
				repeat=false;
				System.out.println(whichTurn + "er Spieler Ausgangsfeld? ");
				try {
				sourceSquare = s.getSquare(System.in);
				}catch(NullPointerException e) {
					repeat = true;
				}
				catch(NumberFormatException e) {
					repeat = true;
				} catch (IOException e) {
					repeat = true;
				}
			}while(repeat);
			
			do {
				repeat = false;
				System.out.println(whichTurn + "er Spieler Zielfeld? ");
				try {
					targetSquare = s.getMove(System.in);
				}
				catch(NumberFormatException e) {
					repeat = true;
				} catch (IOException e) {
					repeat = true;
				}
			}while(repeat);
			
			
			if(Chessboard.board[sourceSquare[0]][sourceSquare[1]].getPiece().isWhite() == whiteBlack) {
				if(Chessboard.board[sourceSquare[0]][sourceSquare[1]].getPiece().validMove(sourceSquare[0], 
																						sourceSquare[1], 
																						targetSquare[0], 
																						targetSquare[1])){
					
					Chessboard.update(sourceSquare[0], sourceSquare[1], targetSquare[0], targetSquare[1]);
					if(Chessboard.checkBlack == true && whichTurn.equals("Schwarz") 
							|| Chessboard.checkWhite == true && whichTurn.equals("Weiss")) {
						System.out.println("Fehler Eingabe bitte wiederholen!");
						Chessboard.reverse(sourceSquare[0], sourceSquare[1], targetSquare[0], targetSquare[1]);
						richtig = false;
					}
					else {
						richtig = true;
						Chessboard.draw();
					}
				}else {
					System.out.println("Fehler Eingabe bitte wiederholen!");
					richtig = false;
				}
			}else {
				System.out.println("Fehler Eingabe bitte wiederholen!");
				richtig = false;
			}
			
		}while(!richtig);
	}	

}
