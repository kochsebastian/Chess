/**
 * 
 */
package pieces;

import java.util.ArrayList;


import chess.Chessboard;



/**
 * @author SebastianKoch
 *  Class implements the Rook, there are two possible Rook: a white one / a black one 
 */
public class Rook extends MajorMinorPiece {

	/**
	 * Constructor for Rook: 
	 * Rook Attributes: 
	 * Colour of the Rook
	 * String which is displayed on the Console (Unicode Bishop Char)
	 * @param isWhite white = true / black = false
	 */
	public Rook(Boolean isWhite) {
		super(isWhite);
		if(isWhite)
			this.boardString =  "\u2656";
		else
			this.boardString = "\u265C";
	}


	


	/* (non-Javadoc)
	 * @see Pieces.Piece#threatenedSquares(int, int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> threatenedSquares(int y, int x) {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.addAll(left(y, x));
		moves.addAll(right(y, x));
		moves.addAll(up(y, x));
		moves.addAll(down(y, x));
		
		return moves;
	}

	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the left direction
	 */
	private ArrayList<ArrayList<Integer>> left(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		int xcopy = x; // reset
		xcopy --;
		while(xcopy>=0) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(y);
			moves.get(index).add(xcopy);
			if(Chessboard.board[y][xcopy].getPiece() != null) {
				break;
			}
			xcopy --;
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the bottom direction
	 */
	private ArrayList<ArrayList<Integer>> down(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		int ycopy = y; // reset
		ycopy ++;
		while(ycopy<8) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(ycopy);
			moves.get(index).add(x);
			if(Chessboard.board[ycopy][x].getPiece() != null) {
				break;
			}
			ycopy ++;
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the right direction
	 */
	private ArrayList<ArrayList<Integer>> right(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		int xcopy = x; // reset
		xcopy ++;
		while(xcopy<8) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(y);
			moves.get(index).add(xcopy);
			if(Chessboard.board[y][xcopy].getPiece() != null) {
				break;
			}
			xcopy++;
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the up direction
	 */
	private ArrayList<ArrayList<Integer>> up(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		int ycopy = y; // reset
		ycopy --;
		while(ycopy>=0 ) {
			moves.add(new ArrayList<Integer>()); index ++;
			moves.get(index).add(ycopy);
			moves.get(index).add(x);
			if(Chessboard.board[ycopy][x].getPiece() != null) {
				break;
			}
			ycopy --;
		}
		return moves;
	}


	/**
	 * @return Rook
	 */
	@Override
	public Piece getPiece() {
		return this;
	}


}
