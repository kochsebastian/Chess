/**
 *
 * 
 */
package chess;

import java.util.ArrayList;

import exceptions.ProgrammEndException;
import pieces.*;



/**
 * @author SebastianKoch
 * This Class implements the Chessboard and all functions where Pieces interact with each other 
 * Functions: setup Chessboard, draw Chessboard, update Chessboard, reset Chessboard, mark Squares as threaded if they are,
 * check for possible checks on Chessboard, ccheck for checkmate
 */
public abstract class Chessboard { 


	static final char COLUMNS[] = {'A','B','C','D','E','F','G','H'};
	static final char ROWS[] = {1,2,3,4,5,6,7,8};
	
	public static Square board[][] = new Square[8][8];
	public static Square backup;
	public static Boolean checkWhite = false;
	public static Boolean checkBlack = false;
	
	/**
	 * Setting up the Chessboard with all the pieces standing on it
	 * The Chessboard is 8x8
	 */
	public static void setup() {
		// Blacks Startingposition
			// Minor- and Majorpieces plus King and Queen
			board[0][0] = new Rook(false); // H1
			board[0][1] = new Knight(false); // H2
			board[0][2] = new Bishop(false); // H3
			board[0][3] = new Queen(false); // H4
			board[0][4] = new King(false); // H5	
			board[0][5] = new Bishop(false); // H6
			board[0][6] = new Knight(false); // H7
			board[0][7] = new Rook(false); // H8
			
			// Pawns
			for(int i=0;i<8;i++) {
				board[1][i] = new Pawn(false); // Pawns on the 7th Row
			}
			
			
			// emty Squares
			for(int i = 2;i<6;i++) { 
				for(int j = 0;j<8;j++) {
					board[i][j] = new EmptySquare();
				}
			}
			
			
		// Whites Startingposition
			// Pawns
			for(int i=0;i<8;i++) {
				board[6][i] = new Pawn(true); // Pawns on the 2nd Row
			}
			// Minor and Majorpieces plus King und Queen
			board[7][0] = new Rook(true); // A1
			board[7][1] = new Knight(true); // A2
			board[7][2] = new Bishop(true); // A3
			board[7][3] = new Queen(true); // A4
			board[7][4] = new King(true); // A5
		
			board[7][5] = new Bishop(true); // A6
			board[7][6] = new Knight(true); // A7
			board[7][7] = new Rook(true); // A8
	
	}
	
	/**
	 * Drawing the Chessboard, each pieces has an individual char (from unicode) which is displayed 
	 * on the console, the empty squares have their own char as well
	 */
	public static void draw() {
		System.out.print("\n");
		
		for(char i: COLUMNS){ 
			System.out.format("  "+i + " "); 	
		}
		System.out.println();
		for(int i = 0; i < 8; i++) { 
			System.out.print((8-i) + " "); 
			
			for(Square s: board[i]) { 
				System.out.format( " %s ",s.draw() ); 
			}
			
			System.out.print((8-i) + " "); 
			
			System.out.print("\n");
		}
		for(char i: COLUMNS){ 			
			System.out.print("  "+i + " "); 	 
		}
		System.out.println();
	
	}
	
	
	/**
	 * Updating the chessboard. It changes pieces position and markes squares as threatened if they are.
	 * In the end the function chechs for cpossible checks
	 * @param startY y coordinate of piece
	 * @param startX x coordinate of piece
	 * @param endY y coordinate of target square
	 * @param endX x coordinate of target square
	 * @throws ProgrammEndException if a checkmate occurs the exception ends the game
	 */
	public static void update(int startX,int startY, int endX, int endY) throws ProgrammEndException {
		
		checkWhite=false;
		checkBlack=false;
		backup = board[endX][endY];
		
		board[endX][endY] = board[startX][startY];
	
		board[startX][startY] = null;
		board[startX][startY] = new EmptySquare();
		
		generateThreatsOnChessboard();
		checkForChecks();
		
		 
	}
	
	/**
	 * Marking squares as threatened if they are. A square can be threatened by the black pieces 
	 * or the white ones or by both ones
	 */
	public static void generateThreatsOnChessboard() {
		resetBoard();
		for(int i=0; i< 8; i++){
			for(int j = 0; j<8; j++){
				if(board[i][j].getPiece() == null) {
					continue; 
				}
				ArrayList<ArrayList<Integer>> moves = board[i][j].getPiece().threatenedSquares(i, j);	
				String possibleNewThreat = (board[i][j].getPiece().isWhite() == true) ? "white" : "black";
				String possibleOldThreat = (board[i][j].getPiece().isWhite() == true) ? "black" : "white";
				
				for(int k=0; k < moves.size();k++) {
					if(board[moves.get(k).get(0)][moves.get(k).get(1)].getThreatenedBy().equals(possibleOldThreat) ||
							board[moves.get(k).get(0)][moves.get(k).get(1)].getThreatenedBy().equals("blackANDwhite") ){
						board[moves.get(k).get(0)][moves.get(k).get(1)].setThreatenedBy("blackANDwhite");
					}else {
						board[moves.get(k).get(0)][moves.get(k).get(1)].setThreatenedBy(possibleNewThreat);
					}
					
					if(board[moves.get(k).get(0)][moves.get(k).get(1)].getThreatenedBy().equals(possibleOldThreat)
							||board[moves.get(k).get(0)][moves.get(k).get(1)].getThreatenedBy().equals("blackANDwhite")){ 
						board[moves.get(k).get(0)][moves.get(k).get(1)].setThreatenedBy("blackANDwhite");
					}else {
						board[moves.get(k).get(0)][moves.get(k).get(1)].setThreatenedBy(possibleNewThreat);
					}
				}
				
	
			}
		}						
	}
	
	/**
	 * Checking if the black or white king is in check, if one of them is, the function returns true
	 * If one king is in check the checkForCheckmate() function will be invoked
	 *
	 * @return true for check / false for no check
	 * @throws ProgrammEndException if a checkmate occurs the exception ends the game
	 */
	public static Boolean checkForChecks() throws ProgrammEndException { 
		for(int i=0; i< 8; i++){
			for(int j = 0; j<8; j++){
				if(board[i][j].getPiece() == null)
					continue;
				
				ArrayList<ArrayList<Integer>> moves= board[i][j].getPiece().threatenedSquares(i, j); 
				Boolean kingColour = (board[i][j].getPiece().isWhite() == true)? false:true;
				for(int k=0; k < moves.size();k++) {
					if(board[moves.get(k).get(0)][moves.get(k).get(1)].equals(new King(kingColour))){
						System.out.println("CHECK!!");
						if(kingColour == true) {
							checkWhite=true;
						}else {
							checkBlack=true; 
						}
						if(checkForCheckmate(i,j,moves.get(k).get(0),moves.get(k).get(1))){
							System.out.println("CHECKMATE");
							throw new ProgrammEndException("Checkmate ends the game");
						}
						return true;
						
					}			
				}
			}
		}
		return false;
	}
	/**
	 * Resetting the chessboard so no square is threatened by anything
	 */
	static void resetBoard() {
		for(int i=0; i< 8; i++){
			for(int j = 0; j<8; j++){
				board[i][j].setThreatenedBy("nothing");
			}
		}
	}
	
	/**
	 * Checking for checkmate 
	 * 
	 * @param yP y coordinate of threat Piece
	 * @param xP x cooridinate of threat Piece
	 * @param yK y coordinate of threatened King
	 * @param xK x coordinate of threatened King
	 * @return true for checkmate / false for no checkmate
	 */
	public static Boolean checkForCheckmate(int yP, int xP, int yK, int xK) { 
		// there are 3 ways to prevent chackmate
		// 1. king can dodge the threat 
		// 2. the threatning piece can be taken
		// 3. some piece can be placed between king and threatning piece (doesnt work with a knight)
		
		Boolean kingColourBackup =board[yK][xK].getPiece().isWhite();
		board[yK][xK] = new EmptySquare();
		generateThreatsOnChessboard();
		board[yK][xK] = new King(kingColourBackup);
		
		// try first option
		if(firstOption(yK, xK) == false) {
			return false;
		}
		
		// try second option
		if(secondOption(yP, xP, yK, xK) == false) {
			return false;
		}
		// piece isnt takeable
		// try third option
		if(thirdOption(yP, xP, yK, xK) == true) {
			return true;	
		}else {
			return false;
		}
		
		
	}
	
	/**
	 * checking if king is able to dodge 
	 * 
	 * @param yK y coordinate of threatened King
	 * @param xK x coordinate of threatened King
	 * @return true if king can doge / false if king cant doge
	 */
	private static Boolean firstOption(int yK, int xK){ 
		ArrayList<ArrayList<Integer>> movesKing = board[yK][xK].getPiece().possibleSquares(board[yK][xK].getPiece()
				  																					  .threatenedSquares(yK,xK));
		if(movesKing.size()!=0) {
			return false;
			// dodging is possible
		}
		return true;
	}
	
	/**
	 * checking if threatning piece is takeable
	 * 
	 * @param yP y coordinate of threat Piece
	 * @param xP x coordinate of threat Piece
	 * @param yK y coordinate of threatened King
	 * @param xK x coordinate of threatened King
	 * @return true if cant be taken / false can be taken
	 */
	private static Boolean secondOption(int yP, int xP, int yK, int xK){
		String threatColour = board[yP][xP].getThreatenedBy();
		String kingColour = (board[yK][xK].getPiece().isWhite()==true ? "white": "black");
		ArrayList<ArrayList<Integer>> cacheSquares = new ArrayList<ArrayList<Integer>>(); 
		
		if((threatColour.equals(kingColour))
				|| (board[yP][xP].getThreatenedBy().equals("blackANDwhite"))){	
			// threatning piece is threatened by a piece of the kings colour 
			for(int i=0;i<8;i++) {	
				for(int j=0;j<8;j++) {
					if(board[i][j].getPiece() == null) {
						continue;
					}
					
					if(board[i][j].getPiece().isWhite() != board[yK][xK].getPiece().isWhite()) {
						continue;
					}
					
					cacheSquares = board[i][j].getPiece().possibleSquares(board[i][j].getPiece()
																				    .threatenedSquares(i,j));
					if(cacheSquares != null){
						for(int k=0;k<cacheSquares.size();k++) {
							if(cacheSquares.get(k).get(0) == yP && cacheSquares.get(k).get(1) == xP 
									&& (!(board[i][j].equals(new King(false))) 
											|| !(board[i][j].equals(new King(true))))) {
								return false; // threatning piece is takeable 
							}
						}		
					}
				}
			}	
		}
		return true;
	}
	/**
	 * checking if some piece can be placed between king and threatning piece 
	 * 
	 * @param yP y coordinate of threat Piece
	 * @param xP x coordinate of threat Piece
	 * @param yK y coordinate of threatened King
	 * @param xK x coordinate of threatened King
	 * @return true for checkmate / false for no chackmate
	 */
	private static Boolean thirdOption(int yP, int xP, int yK, int xK){
		
		
		if(board[yP][xP].equals(new Pawn(true)) || board[yP][xP].equals(new Pawn(false)) 
				|| board[yP][xP].equals(new Knight(true))|| board[yP][xP].equals(new Knight(false))){ 
			// third option doesnt work for knight or pawn
			return true;
		} 
		
		// get all squares that lay between king and threatning piece
		ArrayList<ArrayList<Integer>> checkSquares = getSquaresBetween(yP,xP,yK,xK);
		
		
		for(int i =0; i<8; i++){
			for(int j = 0;j<8;j++){
				
				if(board[i][j].getPiece() == null
						|| board[i][j].getPiece().isWhite() != board[yK][xK].getPiece().isWhite()) {
					continue;
				}
				
				for(int k =0; k< checkSquares.size(); k++){
					if(board[i][j].getPiece().validMove(i, j, checkSquares.get(k).get(0), 
															 checkSquares.get(k).get(1))) {
						return false;
					} 
				}
					
			}	
		}
		return true;
	}
	/**
	 * 
	 * @param yP y coordinate of threat Piece
	 * @param xP x coordinate of threat Piece
	 * @param yK y coordinate of threatened King
	 * @param xK x coordinate of threatened King
	 * @return ArrayList<ArrayList<Integer>> ofSquares between the King and the threatning piece
	 */
	private static ArrayList<ArrayList<Integer>> getSquaresBetween(int yP, int xP, int yK, int xK){
		
		Boolean pieceColour = board[yP][xP].getPiece().isWhite();
		Square pieceCopy = board[yP][xP];
		board[yP][xP] = null;
		board[yP][xP] = new Rook(pieceColour);
		Boolean rookCheck = false; 
		
		ArrayList<ArrayList<Integer>> betweenRook = board[yP][xP].getPiece().threatenedSquares(yP, xP);
		
		for(int i = 0; i<betweenRook.size(); i++) {
			if(betweenRook.get(i).get(0) == yK && betweenRook.get(i).get(1) == xK) {
				rookCheck = true;
			}
		}
		
		Boolean kingColour = board[yK][xK].getPiece().isWhite();
		
		if(rookCheck == true) {
			board[yK][xK] = null;
			board[yK][xK] = new Rook(kingColour);
		}else {
			board[yP][xP] = null;
			board[yP][xP] = new Bishop(pieceColour);
			board[yK][xK] = null;
			board[yK][xK] = new Bishop(kingColour);
		}
		
		ArrayList<ArrayList<Integer>> pieceMoves = board[yP][xP].getPiece().threatenedSquares(yP, xP); 
		ArrayList<ArrayList<Integer>> kingMoves = board[yK][xK].getPiece().threatenedSquares(yK, xK);
		ArrayList<ArrayList<Integer>> checkSquares = new ArrayList<ArrayList<Integer>>(); // squares between piece and king
		
		int index = -1;
		for(int i = 0; i< pieceMoves.size(); i++) {
			for(int j = 0; j< kingMoves.size(); j++) {
				if(pieceMoves.get(i).get(0) == kingMoves.get(j).get(0) 
						&& pieceMoves.get(i).get(1) == kingMoves.get(j).get(1)) {
					checkSquares.add(new ArrayList<Integer>()); index ++;
					checkSquares.get(index).add(pieceMoves.get(i).get(0));
					checkSquares.get(index).add(pieceMoves.get(i).get(1));
				}
			}
		}
		
		board[yP][xP] = null;
		board[yP][xP] = pieceCopy;
		board[yK][xK] = null;
		board[yK][xK] = new King(kingColour);
		
		return checkSquares;
		
	}
	
	/**
	 * Resetting the chessboard to its last move
	 * @param startY y coordinate of piece
	 * @param startX x coordinate of piece
	 * @param endY y coordinate of target square
	 * @param endX x coordinate of target square
	 */
	public static void reverse(int startX,int startY, int endX, int endY) {
		checkWhite=false;
		checkBlack=false;
		board[startX][startY] = board[endX][endY] ;
		board[endX][endY] = backup;
		System.out.println("NO CHECK");
		
	}
	
	
}
