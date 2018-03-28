/**
 * 
 */
package pieces;

import java.util.ArrayList;

import chess.Chessboard;
import chess.EmptySquare;



/**
 * @author SebastianKoch
 * Class implements the Pawn, there are 16 possible Pawns: 8 white ones / 8 black ones
 */ 
public class Pawn extends Piece { 

	/**
	 * Constructor for Pawn: 
	 * Pawn Attributes: 
	 * Colour of the Bishop
	 * String which is displayed on the Console (Unicode Bishop Char)
	 * @param isWhite white = true / black = false
	 */
	public Pawn(Boolean isWhite) {
		super(isWhite);
		if(isWhite)
			this.boardString =  "\u2659";
		else
			this.boardString = "\u265F";
	}

	/* (non-Javadoc)
	 * @see Pieces.Piece#validMove(int, int, int, int)
	 */
	@Override
	public Boolean validMove(int startX, int startY, int endX, int endY) {
		ArrayList<ArrayList<Integer>> threatened= this.threatenedSquares(startX, startY);
		ArrayList<ArrayList<Integer>> moves= this.possibleSquares(startX,startY);
		int index = moves.size();
		for(int i = 0;i<threatened.size();i++) {
			// add to the possible moves the threat moves 
			if(Chessboard.board[threatened.get(i).get(0)][threatened.get(i).get(1)].getPiece() == null) {	
				continue;
			}
			if(Chessboard.board[threatened.get(i).get(0)][threatened.get(i).get(1)].getPiece().isWhite() == this.isWhite()) {
				continue;
			}
			moves.add(new ArrayList<Integer>()); 
			moves.get(index).add(threatened.get(i).get(0));
			moves.get(index).add(threatened.get(i).get(1));
			index++;
			
		}
		ArrayList<ArrayList<Integer>> move =new ArrayList<ArrayList<Integer>>();
		move.add(new ArrayList<Integer>()); 
		move.get(0).add(endX);
		move.get(0).add(endY);
		if(moves.containsAll(move)) { 
			if(isSquareOccupied(endX, endY)) {  
				// target square is occupied
				if((isPieceTakeable(this.isWhite(), endX, endY))){ 
					// target square is takeable
					Chessboard.board[endX][endY] = new EmptySquare();
					checkForQueening(startX, startY, endX, endY);
					return true; 
				}
				else {
					// target square isnt takeable
					return false; 
				}
			}
			else{
				// target square isnt occupied
				checkForQueening(startX, startY, endX, endY);
				return true;
			}
		}else{
			// no valid move
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see Pieces.Piece#threatenedSquares(int, int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> threatenedSquares(int y, int x) {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if(this.getPiece().isWhite() == false){
			if((y+1 <8 && x+1 <8)) {
				moves.add(new ArrayList<Integer>());
				index++;
				moves.get(index).add(y+1);
				moves.get(index).add(x+1);
			}
			if((y+1 <8 && x-1 >=0)) {
				moves.add(new ArrayList<Integer>());
				index++;
				moves.get(index).add(y+1);
				moves.get(index).add(x-1);
			}
		}
		if(this.getPiece().isWhite() == true){
			if((y-1 >=0 && x+1 <8)) {
				moves.add(new ArrayList<Integer>());
				index++;
				moves.get(index).add(y-1);
				moves.get(index).add(x+1);
			}
			if((y-1 >=0 && x-1 >=0)) {
				moves.add(new ArrayList<Integer>());
				index++;
				moves.get(index).add(y-1);
				moves.get(index).add(x-1);
			}
		}
		return moves;
	}
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return possible squares for the pawn
	 */
	@Override
	public ArrayList<ArrayList<Integer>> possibleSquares(int y, int x) {
		Boolean Up = (this.isWhite()==true ? true : false);
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if(Up){
			if(y==6){ 
				if(Chessboard.board[y-1][x].getPiece() == null && Chessboard.board[y-2][x].getPiece() == null ){
					moves.add(new ArrayList<Integer>());
					index++;
					moves.get(index).add(y-2);
					moves.get(index).add(x);
				}
			}
			if(y-1 >=0 && Chessboard.board[y-1][x].getPiece() == null){
				moves.add(new ArrayList<Integer>());
				index++;
				moves.get(index).add(y-1);
				moves.get(index).add(x);
			} 

		}else{
			if(y==1){
				if( Chessboard.board[y+1][x].getPiece() == null && Chessboard.board[y+2][x].getPiece() == null ){
					moves.add(new ArrayList<Integer>());
					index++;
					moves.get(index).add(y+2);
					moves.get(index).add(x);
				}
			}
			if(y+1 <8 && Chessboard.board[y+1][x].getPiece() == null){
				moves.add(new ArrayList<Integer>());
				index++;
				moves.get(index).add(y+1);
				moves.get(index).add(x);
			}
		}
		return moves;
	}
	
	
	/**
	 * 
	 * @param threatenedSquares all Squares that are threatened by this piece
	 * @return null for Pawn
	 * @see pieces.Piece#possibleSquares(java.util.ArrayList) for other pieces
	 */
	@Override
	public ArrayList<ArrayList<Integer>> possibleSquares(ArrayList<ArrayList<Integer>> threatenedSquares) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void checkForQueening(int startY, int startX, int endY,int endX) {
		Boolean pawnColour = Chessboard.board[startY][startX].getPiece().isWhite();
		if(pawnColour == true) {
			if(endY ==0) {
				Chessboard.board[startY][startX] = null;
				Chessboard.board[startY][startX] = new Queen(pawnColour);
			} 
		}
		else {
			if(endY == 7) {
				Chessboard.board[startY][startX] = null;
				Chessboard.board[startY][startX] = new Queen(pawnColour);
			}
		}
	}
	

	/**
	 * @return Pawn
	 */
	@Override
	public Piece getPiece() {
		return this;
	}

	

}
