/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import chess.Chessboard;
import chess.EmptySquare;
import chess.Square;
import pieces.*;
/**
 * @author SebastianKoch 
 * Test for the King class
 */
public class KingTest {
	
	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a chessboard with a king and some extra pieces
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
				Chessboard.board[i][j].setThreatenedBy("nothing");
			}
		}
		
		Chessboard.board[0][4] = null;
		Chessboard.board[0][4] = new King(false);
		Chessboard.board[0][0] = null;
		Chessboard.board[0][0] = new Rook(false);
		Chessboard.board[0][7] = null;
		Chessboard.board[0][7] = new Rook(false);
		Chessboard.generateThreatsOnChessboard();
		
	}

	/**
	 * Test method for {@link pieces.King#getPiece()}.
	 */
	@Test
	public final void testGetPiece() {
		Square board[][] = new Square[8][8]; 
		board[0][0] = new King(true);
		assertTrue(board[0][0].getPiece().equals(new King(true)));
	
	}

	/**
	 * Test method for {@link pieces.King#validMove(int, int, int, int)}.
	 * tests if a valid move is detected as such
	 */
	@Test
	public final void testValidMove() {
	
		assertTrue(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 5));
		assertTrue(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 2));
		assertTrue(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 6));
		Chessboard.board[1][4] = null;
		Chessboard.board[1][4] = new Pawn(true);
		Chessboard.generateThreatsOnChessboard();
		assertTrue(Chessboard.board[0][4].getPiece().validMove(0, 4,1, 4));
		assertFalse(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 2));
		assertFalse(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 6));
		
	}

	/**
	 * Test method for {@link pieces.King#threatenedSquares(int, int)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testThreatenedSquares() {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		Chessboard.board[2][2] = null;
		Chessboard.board[2][2] = new King(false);
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(3); moves.get(0).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(1); moves.get(1).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(2); moves.get(2).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(3).add(2); moves.get(3).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(4).add(3); moves.get(4).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(5).add(3); moves.get(5).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(6).add(1); moves.get(6).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(7).add(1); moves.get(7).add(1);
		assertTrue(Chessboard.board[2][2].getPiece().threatenedSquares(2, 2).containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[2][2].getPiece().threatenedSquares(2, 2)));
	}

	/**
	 * Test method for {@link pieces.King#possibleSquares(java.util.ArrayList)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testPossibleSquaresArrayListOfArrayListOfInteger() {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
				Chessboard.board[i][j].setThreatenedBy("nothing");
			} 
		}
		Chessboard.board[2][2] = null;
		Chessboard.board[2][2] = new King(false);
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(3); moves.get(0).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(1); moves.get(1).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(2); moves.get(2).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(3).add(2); moves.get(3).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(4).add(3); moves.get(4).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(5).add(3); moves.get(5).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(6).add(1); moves.get(6).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(7).add(1); moves.get(7).add(1);
		assertTrue(Chessboard.board[2][2].getPiece().possibleSquares(Chessboard.board[2][2].getPiece()
																						  .threatenedSquares(2, 2))
																						  .containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[2][2].getPiece().possibleSquares(Chessboard.board[2][2].getPiece()
																										   .threatenedSquares(2, 2))));
		
		Chessboard.board[3][3] = null;
		Chessboard.board[3][3] = new Rook(true);
		Chessboard.generateThreatsOnChessboard();
		moves.clear();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(1); moves.get(0).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(2); moves.get(1).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(3); moves.get(2).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(3).add(1); moves.get(3).add(1);
	
		assertTrue(Chessboard.board[2][2].getPiece().possibleSquares(Chessboard.board[2][2].getPiece()
																						 .threatenedSquares(2, 2))
																						 .containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[2][2].getPiece().possibleSquares(Chessboard.board[2][2].getPiece()
																										   .threatenedSquares(2, 2))));

		
	}

	/**
	 * Test method for {@link pieces.King#King(java.lang.Boolean)}.
	 * tests a king
	 */
	@Test
	public final void testKing() {
		King k = new King(true);
		assertTrue(k instanceof King);
	}

}
