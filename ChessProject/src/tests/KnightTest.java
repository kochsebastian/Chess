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
 * Test for the Knight class
 */
public class KnightTest {

	
	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a chessboard with a Knight
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		Chessboard.board[2][2] = null;
		Chessboard.board[2][2] = new Knight(false);
	}
	
	/**
	 * Test method for {@link pieces.Knight#getPiece()}.
	 */
	@Test
	public final void testGetPiece() {
		Square board[][] = new Square[8][8];
		board[0][0] = new Knight(true); 
		assertTrue(board[0][0].getPiece().equals(new Knight(true)));
	} 

	/**
	 * Test method for {@link pieces.Knight#threatenedSquares(int, int)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testThreatenedSquares() {

		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(4); moves.get(0).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(4); moves.get(1).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(0); moves.get(2).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(3).add(0); moves.get(3).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(4).add(3); moves.get(4).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(5).add(3); moves.get(5).add(0);
		moves.add(new ArrayList<Integer>()); moves.get(6).add(1); moves.get(6).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(7).add(1); moves.get(7).add(0);
		
		assertTrue(Chessboard.board[2][2].getPiece().threatenedSquares(2, 2).containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[2][2].getPiece().threatenedSquares(2, 2)));
		
	}

	/**
	 * Test method for {@link pieces.Knight#Knight(java.lang.Boolean)}.
	 * tests a knight
	 */
	@Test
	public final void testKnight() {
		Knight k = new Knight(true); 
		assertTrue(k instanceof Knight);
	}

}
