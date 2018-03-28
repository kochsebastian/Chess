/**
 * 
 */
package pieces;

import java.util.ArrayList;



/**
 * @author SebastianKoch
 * Class implements the Queen, there are two possible Queen: a white one / a black one 
 */
public class Queen extends MajorMinorPiece {

	/**
	 * Constructor for Queen: 
	 * Queen Attributes: 
	 * Colour of the Queen
	 * String which is displayed on the Console (Unicode Queen Char)
	 * @param isWhite white = true / black = false
	 */
	public Queen(Boolean isWhite) {
		super(isWhite);
		if(isWhite)
			this.boardString =  "\u2655";
		else
			this.boardString = "\u265B";
	}

	/* (non-Javadoc)
	 * @see Pieces.Piece#threatenedSquares(int, int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> threatenedSquares(int y, int x) {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		int index = -1;
		Rook Rcopy = new Rook(this.isWhite());
		
		for(int i=0; i<Rcopy.threatenedSquares(y, x).size();i++) { 
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(Rcopy.threatenedSquares(y, x).get(i).get(0));
			moves.get(index).add(Rcopy.threatenedSquares(y, x).get(i).get(1));
			
		}
		Rcopy = null;
		Bishop Bcopy = new Bishop(this.isWhite());
		for(int i=0; i<Bcopy.threatenedSquares(y, x).size();i++) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(Bcopy.threatenedSquares(y, x).get(i).get(0));
			moves.get(index).add(Bcopy.threatenedSquares(y, x).get(i).get(1));
			
		}
		Bcopy = null;
		return moves;
	}

	/**
	 * @return Queen
	 */
	@Override
	public Piece getPiece() {
		return this;
	}

}
