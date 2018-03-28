/**
 * 
 */
package pieces;

import java.util.ArrayList;


import chess.Chessboard;
import chess.EmptySquare;


/**
 * @author SebastianKoch
 * Class implements the King, there are two possible King: a white one / a black one 
 */
public class King extends Piece {

	/**
	 * Constructor for King: 
	 * King Attributes: 
	 * Colour of the King
	 * String which is displayed on the Console (Unicode Bishop Char)
	 * @param isWhite white = true / black = false
	 */
	public King(Boolean isWhite) {
		super(isWhite);
		if(isWhite)
			this.boardString =  "\u2654";
		else
			this.boardString = "\u265A";
	}
	 
	
	/* (non-Javadoc)
	 * @see Pieces.Piece#validMove(int, int, int, int)
	 */
	@Override
	public Boolean validMove(int startX, int startY, int endX, int endY) {
		// check for castling
		if((startY == 4 && Math.abs(startY-endY)==2) 
				&& (startX == 7 && endX ==7) || (startX == 0 && endX ==0))  {
			if(startY-endY == 2) { // to the left 
				if(castleLeft(startX, startY, endY, endX)== true) {
					return true;
				}
			}
			else if(startY-endY == -2) { // to the right
				if(castleRight(startX, startY, endY, endX)== true) {
					return true;
				}
			}
		}
		
		ArrayList<ArrayList<Integer>> threatened= this.threatenedSquares(startX, startY);
		ArrayList<ArrayList<Integer>> moves= this.possibleSquares(threatened);
		ArrayList<ArrayList<Integer>> move =new ArrayList<ArrayList<Integer>>();
		move.add(new ArrayList<Integer>()); 
		move.get(0).add(endX);
		move.get(0).add(endY);
		if(moves.containsAll(move)) {
			if(isSquareOccupied(endX, endY)) { 
				// target square is occupied
				if((isPieceTakeable(this.isWhite(), endX, endY))){ 
					// piece on target sqaure is takeable
					return true; 
				}
				else {
					// piece on target sqaure is not takeable
					return false;
				}
			}
			else{
				// target square is empty
				return true;
			}
		}else{
			// move is invalid
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see Pieces.Piece#threatenedSquares(int, int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> threatenedSquares(int y, int x) {
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		moves.addAll(up(y, x));
		moves.addAll(down(y, x));
		moves.addAll(upRight(y, x));
		moves.addAll(downRight(y, x));
		moves.addAll(upLeft(y, x));
		moves.addAll(downLeft(y, x));
		moves.addAll(left(y, x));
		moves.addAll(right(y, x));
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the top direction
	 */
	private ArrayList<ArrayList<Integer>> up(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if(y-1 >= 0){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y-1);
			moves.get(index).add(x);
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
		if(y+1 < 8){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y+1);
			moves.get(index).add(x);
		}
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
		if(x-1 >= 0){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y);
			moves.get(index).add(x-1);
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
		if(x+1 < 8){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y);
			moves.get(index).add(x+1);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the top-left direction
	 */
	private ArrayList<ArrayList<Integer>> upLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if(y-1 >=0 && x-1 >= 0){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y-1);
			moves.get(index).add(x-1);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the top-right direction
	 */
	private ArrayList<ArrayList<Integer>> upRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if(y-1 >=0 && x+1 < 8){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y-1);
			moves.get(index).add(x+1);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the bottom-right direction
	 */
	private ArrayList<ArrayList<Integer>> downRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if(y+1 < 8 && x+1 < 8){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y+1);
			moves.get(index).add(x+1);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the bottom-left direction
	 */
	private ArrayList<ArrayList<Integer>> downLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if(y+1 < 8 && x-1 >= 0){
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y+1);
			moves.get(index).add(x-1);
		}
		return moves;
	}

	/* (non-Javadoc)
	 * @see Pieces.Piece#possibleSquares(java.util.ArrayList)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> possibleSquares(ArrayList<ArrayList<Integer>> threatenedSquares) {
		ArrayList<ArrayList<Integer>> possibleSquares =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		String contraryColour = (this.isWhite() ? "black" :"white");
		for(int i=0; i < threatenedSquares.size();i++) { 
			if(((Chessboard.board[threatenedSquares.get(i).get(0)][threatenedSquares.get(i).get(1)].getThreatenedBy()
																								 .equals(contraryColour)) 
				|| (Chessboard.board[threatenedSquares.get(i).get(0)][threatenedSquares.get(i).get(1)].getThreatenedBy()
																									.equals("blackANDwhite")))){} // XXX
			else {
				if(Chessboard.board[threatenedSquares.get(i).get(0)][threatenedSquares.get(i).get(1)].getPiece()== null 
					|| (Chessboard.board[threatenedSquares.get(i).get(0)][threatenedSquares.get(i).get(1)].getPiece()
																										.isWhite()
																										.equals(!this.isWhite()))) {
					// target square is emty or targetsquare is takeable
					possibleSquares.add(new ArrayList<Integer>()); index ++;
					possibleSquares.get(index).add(threatenedSquares.get(i).get(0));
					possibleSquares.get(index).add(threatenedSquares.get(i).get(1));
				 }
			}
			
		}
		return possibleSquares;
	}
	/**
	 * @return King
	 */
	@Override
	public Piece getPiece() {
		return this;
	}
	
	
	
	/**
	 * 
	 * @param startY y coordinate of piece
	 * @param startX x coordinate of piece
	 * @param endY y coordinate of target square
	 * @param endX x coordinate of target square
	 * @return true for a valid move / false for a invalid move
	 */
	private Boolean castleLeft(int startX, int startY, int endX, int endY) {
		String threatcolor = (startX == 7 && this.isWhite() == true) ? "black" : "white";
		if(startX != 7 && startX != 0) {
			// king isnt on the correct row
			return false;
		}
		
		if(Chessboard.board[startX][0].equals(new Rook(this.isWhite()))) {
			// rook is on the correct square
			if(!(Chessboard.board[startX][startY].getThreatenedBy().equals(threatcolor))
					&& !(Chessboard.board[startX][startY].getThreatenedBy().equals("blackANDwhite"))) {
				// king isnt threatened
				if((!(Chessboard.board[startX][startY-1].getThreatenedBy().equals(threatcolor)) 
						&& !(Chessboard.board[startX][startY-1].getThreatenedBy().equals("blackANDwhite")))
						&& Chessboard.board[startX][startY-1].getPiece() == null) {
					// square1 is emtpty and isnt threatened
					if((!(Chessboard.board[startX][startY-2].getThreatenedBy().equals(threatcolor))
							&& !(Chessboard.board[startX][startY-2].getThreatenedBy().equals("blackANDwhite")))
						&& Chessboard.board[startX][startY-2].getPiece() == null){
						// square2 is emtpty and isnt threatened
						Chessboard.board[startX][startY-1] = new Rook(this.isWhite());
						Chessboard.board[startX][0] =null;
						Chessboard.board[startX][0] =new EmptySquare();
						
						return true;
					}
				}	
			}
		}
		// rook isnt on the correct sqaure
		return false;
	
	}

	/**
	 * 
	 * @param startY y coordinate of piece
	 * @param startX x coordinate of piece
	 * @param endY y coordinate of target square
	 * @param endX x coordinate of target square
	 * @return true for a valid move / false for a invalid move
	 */
	private Boolean castleRight(int startX, int startY, int endX, int endY) {	
		String threatcolor = (startX == 7 && this.isWhite() == true) ? "black" : "white";
		if(startX != 7 && startX != 0) {
			// king isnt on the correct row
			return false;
		}
			
		if(Chessboard.board[startX][7].equals(new Rook(this.isWhite()))) {
			// rook is on the correct square
			if(!(Chessboard.board[startX][startY].getThreatenedBy().equals(threatcolor))
					&& !(Chessboard.board[startX][startY].getThreatenedBy().equals("blackANDwhite"))) {
				// king isnt threatened
				if((!(Chessboard.board[startX][startY+1].getThreatenedBy().equals(threatcolor))
						&& !(Chessboard.board[startX][startY+1].getThreatenedBy().equals("blackANDwhite")))
						&& Chessboard.board[startX][startY+1].getPiece() == null) {
					// square1 is emtpty and isnt threatened
					if((!(Chessboard.board[startX][startY+2].getThreatenedBy().equals(threatcolor))
							&& !(Chessboard.board[startX][startY+2].getThreatenedBy().equals("blackANDwhite")))
						&& Chessboard.board[startX][startY+2].getPiece() == null){
						// square2 is emtpty and isnt threatened
						Chessboard.board[startX][startY+1] = new Rook(this.isWhite());
						Chessboard.board[startX][7] =null;
						Chessboard.board[startX][7] =new EmptySquare();
						
						return true;
					}
				}	
			}
		}
		// rook isnt on the correct sqaure
		return false;
		 
	}
		
	


}
