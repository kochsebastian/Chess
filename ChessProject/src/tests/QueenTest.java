/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import chess.Chessboard;
import chess.EmptySquare;
import chess.Square;
import pieces.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author SebastianKoch
 * Tests the Queen class
 */
public class QueenTest {

	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a chessboard with a queen and some other pieces
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		Chessboard.board[1][1] = null;
		Chessboard.board[1][1] = new Queen(false);
		Chessboard.board[3][3] = null;
		Chessboard.board[3][3] = new Pawn(false);
		Chessboard.board[1][3] = null;
		Chessboard.board[1][3] = new Pawn(false);
		Chessboard.board[3][1] = null;
		Chessboard.board[3][1] = new Pawn(false);
	}

	/**
	 * Test method for {@link pieces.Queen#getPiece()}.
	 */
	@Test
	public final void testGetPiece() {
		Square board[][] = new Square[8][8];
		board[0][0] = new Queen(true);
		assertTrue(board[0][0].getPiece().equals(new Queen(true)));
	}

	/**
	 * Test method for {@link pieces.Queen#threatenedSquares(int, int)}.
	 * tests if the correct squares are returned
	 */
	@Test
	public final void testThreatenedSquares() {
		
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(1); moves.get(0).add(0);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(1); moves.get(1).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(1); moves.get(2).add(3);
		
		moves.add(new ArrayList<Integer>()); moves.get(3).add(0); moves.get(3).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(4).add(2); moves.get(4).add(1);
		
		moves.add(new ArrayList<Integer>()); moves.get(5).add(3); moves.get(5).add(1);
		
		moves.add(new ArrayList<Integer>()); moves.get(6).add(2); moves.get(6).add(0);
		moves.add(new ArrayList<Integer>()); moves.get(7).add(2); moves.get(7).add(2);
		
		moves.add(new ArrayList<Integer>()); moves.get(8).add(3); moves.get(8).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(9).add(0); moves.get(9).add(0);	
		moves.add(new ArrayList<Integer>()); moves.get(10).add(0); moves.get(10).add(2);	
		
		assertTrue(Chessboard.board[1][1].getPiece().threatenedSquares(1, 1).containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[1][1].getPiece().threatenedSquares(1, 1)));

	}

	/**
	 * Test method for {@link pieces.Queen#Queen(java.lang.Boolean)}.
	 * tests a queen
	 */
	@Test
	public final void testQueen() {
		Queen q = new Queen(true);
		assertTrue(q instanceof Queen);
	}

}
