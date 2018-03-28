/**
 * 
 */
package chess;

import pieces.*;


/**
 * @author SebastianKoch
 *
 */
public abstract class Square {
	protected String boardString;
	private String threatenedBy; // "nothing", "black", "white", "blackANDwhite" 
	
	/** 
	 * abstract
	 */
	public Square() {
		
	} 
	
	/**
	 * 
	 * @return Piece the chess piece or empty square
	 */
	public abstract Piece getPiece();
	
	/**
	 * 
	 * @return threatendBy ("black" or "white" or "blackANDwhite")
	 */
	public String getThreatenedBy() {
		return threatenedBy;
	}
	
	/**
	 * 
	 * @return boardString similar to a getter
	 */
	public abstract String draw();
	
	
	/**
	 * @param threatenedBy the threatenedBy to set
	 */
	public void setThreatenedBy(String threatenedBy) {
		this.threatenedBy = threatenedBy;
	}
	
	/**
	 * 
	 * @param x x coordinate of square
	 * @param y y coordinate of square
	 * @return true for occupied / false for unoccupied
	 */
	public Boolean isSquareOccupied(int x, int y) {
		if(Chessboard.board[x][y].getPiece() == null){
			return false;
		} 
		else{
			return true;
		}
	}


	/**
	 * Compared Attributes: isWhite, Class
	 * @param piece the piece to compare to
	 * @return true for equal / false for different
	 */
	public boolean equals(Piece piece) { 
		if(this.getClass().equals(piece.getClass()) && this.getPiece().isWhite() == piece.isWhite())
			return true;
		else 
			return false;
	}
	

}
