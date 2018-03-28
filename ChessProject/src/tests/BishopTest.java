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
import pieces.Bishop;

import pieces.Pawn;


/**
 * @author SebastianKoch 
 * Test for the Bishop class
 */
public class BishopTest {

	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a chessboard with a Bishop and a few other pieces
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		Chessboard.board[3][3] = null; 
		Chessboard.board[3][3] = new Bishop(false);
		
		Chessboard.board[1][1] = null;
		Chessboard.board[1][1] = new Pawn(false);
		
		Chessboard.board[5][5] = null;
		Chessboard.board[5][5] = new Pawn(false);
		
		Chessboard.board[1][5] = null;
		Chessboard.board[1][5] = new Pawn(false);
		
		Chessboard.board[5][1] = null;
		Chessboard.board[5][1] = new Pawn(false);
	}
	/**
	 * Test method for {@link pieces.Bishop#getPiece()}.
	 */
	@Test
	public final void testGetPiece() {
		Square board[][] = new Square[8][8];
		board[0][0] = new Bishop(true);
		assertTrue(board[0][0].getPiece().equals(new Bishop(true)));
	} 

	/**
	 * Test method for {@link pieces.Bishop#threatenedSquares(int, int)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testThreatenedSquares() {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
	
		moves.add(new ArrayList<Integer>()); moves.get(0).add(2); moves.get(0).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(4); moves.get(1).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(4); moves.get(2).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(3).add(2); moves.get(3).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(4).add(1); moves.get(4).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(5).add(5); moves.get(5).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(6).add(1); moves.get(6).add(5);
		moves.add(new ArrayList<Integer>()); moves.get(7).add(5); moves.get(7).add(5); 
		
		assertTrue(Chessboard.board[3][3].getPiece().threatenedSquares(3, 3).containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[3][3].getPiece().threatenedSquares(3, 3)));
		
		
	}

	/**
	 * Test method for {@link pieces.Bishop#Bishop(java.lang.Boolean)}.
	 * tests a bishop
	 */
	@Test
	public final void testBishop() {
		Bishop b = new Bishop(true);
		assertTrue(b instanceof Bishop);
	}

	
}
