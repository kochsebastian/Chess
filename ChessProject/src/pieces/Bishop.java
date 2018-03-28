/**
 * 
 */
package pieces;

import java.util.ArrayList;

import chess.Chessboard;



/**
 * @author SebastianKoch
 * Class implements the Bishop, there are two possible Bishops: a white one / a black one 
 */
public class Bishop extends MajorMinorPiece {

	/**
	 * Constructor for Bishop: 
	 * Bishop Attributes: 
	 * Colour of the Bishop
	 * String which is displayed on the Console (Unicode Bishop Char)
	 * @param isWhite white = true / black = false
	 */
	public Bishop(Boolean isWhite) {
		super(isWhite);
		if(isWhite)
			this.boardString =  "\u2657";
		else
			this.boardString = "\u265D";
	}

	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#threatenedSquares(int, int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> threatenedSquares(int y, int x) {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.addAll(moveDownLeft(y, x));
		moves.addAll(moveDownRight(y, x));
		moves.addAll(moveUpLeft(y, x));
		moves.addAll(moveUpRight(y, x)); 
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the down-left direction
	 */
	private ArrayList<ArrayList<Integer>> moveDownLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		int index = -1;
		int ycopy = y; 
		int xcopy = x; 
		xcopy --; 
		ycopy ++;
		while(xcopy>=0 && ycopy <8) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(ycopy);
			moves.get(index).add(xcopy);
			if(Chessboard.board[ycopy][xcopy].getPiece() != null) {
				break;
			}
			xcopy --;
			ycopy ++;
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the down-right direction
	 */
	private ArrayList<ArrayList<Integer>> moveDownRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		int index = -1;
		int ycopy = y; // reset
		int xcopy = x; // reset
		ycopy ++;
		xcopy ++;
		while(ycopy<8 && xcopy <8) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(ycopy);
			moves.get(index).add(xcopy);
			if(Chessboard.board[ycopy][xcopy].getPiece() != null) {
				break;
			}
			ycopy ++;
			xcopy ++;
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the up-right direction
	 */
	private ArrayList<ArrayList<Integer>> moveUpRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		int index =-1;
		int ycopy = y; // reset
		int xcopy = x; // reset
		xcopy ++;
		ycopy --;

		while(xcopy<8 && ycopy >=0) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(ycopy);
			moves.get(index).add(xcopy);
			if(Chessboard.board[ycopy][xcopy].getPiece() != null) {
				break;
			}
			xcopy ++;
			ycopy --;
		}
		return moves;
	}

	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the up-left direction
	 */
	private ArrayList<ArrayList<Integer>> moveUpLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		int index =-1;
		int ycopy = y; // reset
		int xcopy = x; // reset
		ycopy --;
		xcopy --;
		while(ycopy>=0 && xcopy>=0 ) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(ycopy);
			moves.get(index).add(xcopy);
			if(Chessboard.board[ycopy][xcopy].getPiece() != null) {
				break;
			}
			ycopy --;
			xcopy --;
		}
		return moves;
	}


	/**
	 * @return Bishop
	 */
	@Override
	public Piece getPiece() {
		return this;
	}

	

}
