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
 * Tests the Rook class
 */
public class RookTest {

	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a chessboard with a rook 
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		Chessboard.board[1][1] = null;
		Chessboard.board[1][1] = new Rook(false);
	}

	/**
	 * Test method for {@link pieces.Rook#getPiece()}.
	 */
	@Test
	public final void testGetPiece() {
		Square board[][] = new Square[8][8];
		board[0][0] = new Rook(true);
		assertTrue(board[0][0].getPiece().equals(new Rook(true)));
	}

	/**
	 * Test method for {@link pieces.Rook#threatenedSquares(int, int)}.
	 * tests if the correct squares are returned
	 */
	@Test
	public final void testThreatenedSquares() {
		
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(0); moves.get(0).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(2); moves.get(1).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(3); moves.get(2).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(3).add(4); moves.get(3).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(4).add(5); moves.get(4).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(5).add(6); moves.get(5).add(1);
		moves.add(new ArrayList<Integer>()); moves.get(6).add(7); moves.get(6).add(1);
		
		moves.add(new ArrayList<Integer>()); moves.get(7).add(1); moves.get(7).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(8).add(1); moves.get(8).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(9).add(1); moves.get(9).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(10).add(1); moves.get(10).add(5);
		moves.add(new ArrayList<Integer>()); moves.get(11).add(1); moves.get(11).add(6);
		moves.add(new ArrayList<Integer>()); moves.get(12).add(1); moves.get(12).add(7);
		moves.add(new ArrayList<Integer>()); moves.get(13).add(1); moves.get(13).add(0);
		
		assertTrue(Chessboard.board[1][1].getPiece().threatenedSquares(1, 1).containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[1][1].getPiece().threatenedSquares(1, 1)));
		
	}

	/**
	 * Test method for {@link pieces.Rook#Rook(java.lang.Boolean)}.
	 * tests a rook
	 */
	@Test
	public final void testRook() {
		Rook r = new Rook(true);
		assertTrue(r instanceof Rook); 
		
	}

}
