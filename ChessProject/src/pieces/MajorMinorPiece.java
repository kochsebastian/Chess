/**
 * 
 */
package pieces;

import java.util.ArrayList;

import chess.Chessboard;

/**
 * @author SebastianKoch
 * This class extends the piece class, the classes Knight, Rook, Bishop, Queen exdent this class
 * Its an abstract class so no Object will be created through this class
 */
public abstract class MajorMinorPiece extends Piece { 
	// includes Rook, Knight, Bishop, Queen

	/**
	 * @param isWhite white = true / black = false
	 */
	public MajorMinorPiece(Boolean isWhite) {
		super(isWhite);
	}

	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#validMove(int, int, int, int)
	 */
	@Override
	public Boolean validMove(int startX, int startY, int endX, int endY) {
		ArrayList<ArrayList<Integer>> threatened = this.threatenedSquares(startX, startY); // coordinates that are threatened by this piece
		ArrayList<ArrayList<Integer>> moves = this.possibleSquares(threatened); // coordinates the piece can move to
		ArrayList<ArrayList<Integer>> move =new ArrayList<ArrayList<Integer>>();
		move.add(new ArrayList<Integer>()); move.get(0).add(endX); move.get(0).add(endY); // move coordinates
		if(moves.containsAll(move)) {
			if(isSquareOccupied(endX, endY)) { 
				if((isPieceTakeable(this.isWhite(), endX, endY))) 
					return true; //  square is occupied but takeable
				else  
					return false;  //  square is occupied and not takeable
			}
			else
				return true; //  square is not occupied
			
		}
		else
			return false; //  destionationsquare is not a possible square
	}

	

	/* (non-Javadoc)
	 * @see Pieces.Piece#possibleSquares(java.util.ArrayList)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> possibleSquares(ArrayList<ArrayList<Integer>> threatenedSquares) {
		ArrayList<ArrayList<Integer>> possibleSquares = new ArrayList<ArrayList<Integer>>();
		int index=-1;
		for(int i=0; i < threatenedSquares.size();i++) {
			if((Chessboard.board[threatenedSquares.get(i).get(0)][threatenedSquares.get(i).get(1)].getPiece() == null) 
					|| !(Chessboard.board[threatenedSquares.get(i).get(0)][threatenedSquares.get(i).get(1)].getPiece()
																										 .isWhite() == this.isWhite())) {
				// square is empty or takeable
				possibleSquares.add(new ArrayList<Integer>()); index ++;
				possibleSquares.get(index).add(threatenedSquares.get(i).get(0));
				possibleSquares.get(index).add(threatenedSquares.get(i).get(1));
			}
		}
		return possibleSquares;
	}


}
