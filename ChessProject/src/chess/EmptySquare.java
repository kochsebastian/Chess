/**
 * 
 */
package chess;


import pieces.Piece;

/**
 * @author SebastianKoch
 *Class implements the empty squares, there are min. 24 empty squares, max. 62 empty squares
 */
public class EmptySquare extends Square {

	/**
	 * Constructor for EmptySquare: 
	 * EmptySquare Attributes: 
	 * String which is displayed on the Console (Unicode white square Char)
	 */
	public EmptySquare() {
		this.boardString = "\u25A2";
	} 

	/* (non-Javadoc)
	 * @see Chess.Square#getObject()
	 */
	@Override
	public Piece getPiece() {
		return null;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see Chess.Square#draw()
	 */
	@Override
	public String draw() {
		return boardString;
	}



}
