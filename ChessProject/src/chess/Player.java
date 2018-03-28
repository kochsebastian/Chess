/**
 * 
 */
package chess;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author SebastianKoch
 *
 */
public class Player {

	private String move;
	
	/** 
	 * constructor
	 */
	public Player() {
		
	}
	
	
	/**
	 * 
	 * @param in InputStream for normal use System.in for testing ByteArrayInputStream
	 * @return int[] x and y coordinates of the square to move to (int[0] is correctly defined form 0 to 7, 
	 * int[1] is correctly defined form 0 to 7)
	 * @throws NumberFormatException NumberFormatException (if string only consists out of numbers or letters)
	 * @throws IOException IOException (if square is not on the board)
	 */
	public int[] getMove(InputStream in) throws NumberFormatException, IOException{
		//System.setIn(System.in);
		int z[] = new int[2];
		@SuppressWarnings("resource")
		Scanner input = new Scanner(in);
		move = input.nextLine();
		//input.close();
		char ch = move.toLowerCase().charAt(0);
		z[1]= (int)ch - (int)'a' + 1;
		z[0] = Integer.parseInt(move.substring(1, 2)); // could throw NumberFormatException
		z[0] = 8-z[0];
		z[1]-=1;
		if(z[0]< 0 || z[0]>7 || z[1]< 0 || z[1]>7) {
			throw new IOException();
		}
		return z;
		
	}

	
	/**
	 * 
	 * @param in InputStream for normal use System.in for testing ByteArrayInputStream
	 * @return int[] x and y coordinates of the square to move from (int[0] is correctly defined form 0 to 7, 
	 * int[1] is correctly defined form 0 to 7)
	 * @throws NumberFormatException (if string only consists out of numbers or letters)
	 * @throws NullPointerException if no piece is on that square
	 * @throws IOException (if square is not on the board) 
	 */
	public int[] getSquare(InputStream in) throws NumberFormatException, NullPointerException, IOException{
		//System.setIn(System.in);
		int z[] = new int[2];
		@SuppressWarnings("resource")
		Scanner input = new Scanner(in);
		move = input.nextLine();
		//input.close();
		char ch = move.toLowerCase().charAt(0);
		  
		z[1]= (int)ch - (int)'a' + 1;
		z[1]-=1;
		z[0] = Integer.parseInt(move.substring(1, 2)); // could throw NumberFormatException
		z[0] = 8-z[0];
		
		if(z[0]< 0 || z[0]>7 || z[1]< 0 || z[1]>7) {
			throw new IOException();
		}
		if(Chessboard.board[z[0]][z[1]].getPiece() == null) {
			throw new NullPointerException();
		}
		return z;
		
	}

}
