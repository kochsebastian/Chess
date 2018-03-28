/**
 * 
 */
package pieces;

import java.util.ArrayList;

import chess.Chessboard;
import chess.Square;


/**
 * @author SebastianKoch
 * Every special piece will extend the piece class. The piece class extends the square class because every piece is connected to the 
 * square its standing on
 *
 */
public abstract class Piece extends Square {
	
	private Boolean isWhite;
	
	
	/**
	 * 
	 * @param isWhite white = true / black = false
	 */
	public Piece(Boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	/**
	 * 
	 * @return isWhite white = true / black = false ( getter for isWhihte)
	 */
	public Boolean isWhite() {
		return this.isWhite;
	}
	
	/**
	 * 
	 * @return boardString Unicode char for every piece
	 */
	public String draw() { // getter for boardString
		return boardString;
	}
	
	/**
	 * 
	 * @param startY y coordinate of piece
	 * @param startX x coordinate of piece
	 * @param endY y coordinate of target square
	 * @param endX x coordinate of target square
	 * @return true for a valid move / false for a invalid move
	 */
	public abstract Boolean validMove(int startX, int startY, int endX, int endY);
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return ArrayList of all Squares that are threatened by a piece
	 */
	public abstract ArrayList<ArrayList<Integer>> threatenedSquares(int y, int x);
	
	
	/**
	 * 
	 * @param threatenedSquares all Squares that are threatened by this piece
	 * @return ArrayList of all possible Squares the piece can move to
	 */
	public abstract ArrayList<ArrayList<Integer>> possibleSquares(ArrayList<ArrayList<Integer>> threatenedSquares);
		
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return ArrayList of all possible Squares the piece can move to
	 */
	public ArrayList<ArrayList<Integer>> possibleSquares(int y, int x){
		return null;
	}
		
	
	
	/**
	 * 
	 * @param pieceColour isWhite Boolean white annother name
	 * @param x x coordinate of piece
	 * @param y y coordinate of piece
	 * @return true for takeable / false for not takeable
	 */
	public static Boolean isPieceTakeable(Boolean pieceColour, int x, int y) {
		if(!(Chessboard.board[x][y].getPiece().isWhite == pieceColour)) 
			return true;
		else
			return false;
	}

	
	 


	


	
}
