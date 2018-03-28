/**
 * 
 */
package pieces;

import java.util.ArrayList;



/**
 * @author SebastianKoch
 * Class implements the Knight, there are to possible Knight: a white one / a black one 
 */
public class Knight extends MajorMinorPiece {

	/**
	 * Constructor for Knight: 
	 * Bishop Attributes: 
	 * Colour of the Knight
	 * String which is displayed on the Console (Unicode Knight Char)
	 * @param isWhite white = true / black = false
	 */
	public Knight(Boolean isWhite) {
		super(isWhite);
		if(isWhite)
			this.boardString =  "\u2658";
		else
			this.boardString = "\u265E";
	}

	

	/* (non-Javadoc)
	 * @see Pieces.Piece#threatenedSquares(int, int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> threatenedSquares(int y, int x) {
		// max 8 possible knight moves
		ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
		moves.addAll(upUpRight(y, x));
		moves.addAll(downDownRight(y, x));
		moves.addAll(upUpLeft(y, x));
		moves.addAll(downDownLeft(y, x));
		moves.addAll(upRightRight(y, x));
		moves.addAll(downRightRight(y, x));
		moves.addAll(upLeftLeft(y, x));
		moves.addAll(downLeftLeft(y, x));
		
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the upUpRight direction
	 */
	private ArrayList<ArrayList<Integer>> upUpRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y-2 >=0 && x+1 <8)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y-2);
			moves.get(index).add(x+1);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the upRightRight direction
	 */
	private ArrayList<ArrayList<Integer>> upRightRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y-1 >=0 && x+2 <8)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y-1);
			moves.get(index).add(x+2);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the downRightRight direction
	 */
	private ArrayList<ArrayList<Integer>> downRightRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y+1 <8 && x+2 <8)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y+1);
			moves.get(index).add(x+2);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the downDownRight direction
	 */
	private ArrayList<ArrayList<Integer>> downDownRight(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y+2 <8 && x+1 <8)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y+2);
			moves.get(index).add(x+1);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the downDownLeft direction
	 */
	private ArrayList<ArrayList<Integer>> downDownLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y+2<8 && x-1 >=0)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y+2);
			moves.get(index).add(x-1);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the downLeftLeft direction
	 */
	private ArrayList<ArrayList<Integer>> downLeftLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y+1<8 && x-2 >=0)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y+1);
			moves.get(index).add(x-2);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the upLeftLeft direction
	 */
	private ArrayList<ArrayList<Integer>> upLeftLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y-1>=0&&x-2 >=0)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y-1);
			moves.get(index).add(x-2);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param y y coordinate of piece
	 * @param x x coordinate of piece
	 * @return all threatenedSquare in the bottom-left direction
	 */
	private ArrayList<ArrayList<Integer>> upUpLeft(int y, int x){
		ArrayList<ArrayList<Integer>> moves =new ArrayList<ArrayList<Integer>>();
		int index=-1;
		if((y-2>=0&&x-1 >=0)) {
			moves.add(new ArrayList<Integer>());
			index++;
			moves.get(index).add(y-2);
			moves.get(index).add(x-1);
		}
		return moves;
	}


	/**
	 * @return Knight
	 */
	@Override
	public Piece getPiece() {
		return this;
	}


}
