/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import chess.Chessboard;
import chess.EmptySquare;
import chess.Square;
import exceptions.ProgrammEndException;
import pieces.Pawn;
import pieces.Queen;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author SebastianKoch
 * Tests the Pawn class
 */
public class PawnTest {
	
	/**
	 * @throws java.lang.Exception standard Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		Chessboard.board[1][3] = null;
		Chessboard.board[1][3] = new Pawn(false);
		Chessboard.board[2][4] = null;
		Chessboard.board[2][4] = new Pawn(true);
		Chessboard.board[1][4] = null;
		Chessboard.board[1][4] = new Pawn(true);
	}

	/**
	 * Test method for {@link pieces.Pawn#getPiece()}.
	 */ 
	@Test
	public final void testGetPiece() {
		Square board[][] = new Square[8][8];
		board[0][0] = new Pawn(true);
		assertTrue(board[0][0].getPiece().equals(new Pawn(true)));
	}

	/**
	 * Test method for {@link pieces.Pawn#validMove(int, int, int, int)}.
	 * tests if a valid move is detected as such
	 */
	@Test
	public final void testValidMove() {
		
		assertTrue(Chessboard.board[1][3].getPiece().validMove(1, 3,2, 3));
		assertTrue(Chessboard.board[1][3].getPiece().validMove(1, 3,3, 3));
		assertTrue(Chessboard.board[1][3].getPiece().validMove(1, 3,2, 4));
	}
	
	/**
	 * Test method for {@link pieces.Pawn#checkForQueening(int, int, int, int)}.
	 * tests if a queen will succed
	 */
	@Test
	public final void testQueening() {
		Chessboard.board[1][4].getPiece().validMove(1, 4,0, 4);
		try {
			Chessboard.update(1, 4, 0, 4);
		} catch (ProgrammEndException e) {
			
		}
		assertTrue(Chessboard.board[0][4].getPiece().equals(new Queen(true)));
	}

	/**
	 * Test method for {@link pieces.Pawn#threatenedSquares(int, int)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testThreatenedSquares() {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		Chessboard.board[3][3] = null;
		Chessboard.board[3][3] = new Pawn(false);
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(4); moves.get(0).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(4); moves.get(1).add(2);
		assertEquals(Chessboard.board[3][3].getPiece().threatenedSquares(3, 3),moves);
		
		Chessboard.board[3][3] = null;
		Chessboard.board[3][3] = new Pawn(true);
		moves.clear();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(2); moves.get(0).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(2); moves.get(1).add(2);
		assertEquals(Chessboard.board[3][3].getPiece().threatenedSquares(3, 3),moves);
		
	}

	/**
	 * Test method for {@link pieces.Pawn#possibleSquares(java.util.ArrayList)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testPossibleSquaresArrayListOfArrayListOfInteger() {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			} 
		}
		Chessboard.board[1][1] = null;
		Chessboard.board[1][1] = new Pawn(false);
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(3); moves.get(0).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(2); moves.get(1).add(1);
		assertEquals(Chessboard.board[1][1].getPiece().possibleSquares(1, 1),moves);
		
		Chessboard.board[6][1] = null;
		Chessboard.board[6][1] = new Pawn(true);
		moves.clear();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(4); moves.get(0).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(5); moves.get(1).add(1);
		assertEquals(Chessboard.board[6][1].getPiece().possibleSquares(6, 1),moves);
		
	}
	/**
	 * Test method for {@link pieces.Pawn#Pawn(java.lang.Boolean)}.
	 * tests a pawn
	 */
	@Test
	public final void testPawn() {
		Pawn p = new Pawn(true); 
		assertTrue(p instanceof Pawn);
	}

}
