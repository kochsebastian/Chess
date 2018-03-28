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
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * @author SebastianKoch 
 * Tests the Piece class
 *
 */
public class PieceTest {

	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a chessboard with some pieces
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare(); 
				Chessboard.board[i][j].setThreatenedBy("nothing");
			}
		}
		Chessboard.board[1][1] = null;
		Chessboard.board[1][1] = new Rook(true);
		Chessboard.board[1][3] = null; 
		Chessboard.board[1][3] = new Pawn(false);
		Chessboard.board[1][0] = null;
		Chessboard.board[1][0] = new Knight(true);
		Chessboard.board[0][1] = null;
		Chessboard.board[0][1] = new Knight(true);
		Chessboard.board[2][1] = null;
		Chessboard.board[2][1] = new Queen(true);	
	}

	/**
	 * Test method for {@link pieces.Piece#draw()}.
	 * tests if a piece is correctly drawn
	 */
	@Test
	public final void testDraw() {
		assertEquals(Chessboard.board[1][1].getPiece().draw(),"\u2656");
	}

	/**
	 * Test method for {@link pieces.Piece#Piece(java.lang.Boolean)}.
	 * tests a piece
	 */
	@Test
	public final void testPiece() {
		Piece k = new King(true);
		assertNotEquals(k.getClass(),new Queen(false).getClass());
	}

	/**
	 * Test method for {@link pieces.Piece#isWhite()}.
	 * tests if the correct piece colour is returned
	 */
	@Test
	public final void testIsWhite() {
		assertEquals(Chessboard.board[1][1].getPiece().isWhite(), true);
	}

	/**
	 * Test method for {@link pieces.Piece#validMove(int, int, int, int)}.
	 * tests if a valid move is detected as such
	 */
	@Test
	public final void testValidMove() {
		assertTrue(Chessboard.board[1][1].getPiece().validMove(1, 1, 1, 3));
	}

	/**
	 * Test method for {@link pieces.Piece#threatenedSquares(int, int)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testThreatenedSquares() {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>());   moves.get(0).add(0);   moves.get(0).add(1);
		moves.add(new ArrayList<Integer>());   moves.get(1).add(2);   moves.get(1).add(1);
		moves.add(new ArrayList<Integer>());   moves.get(2).add(1);   moves.get(2).add(2);
		moves.add(new ArrayList<Integer>());   moves.get(3).add(1);   moves.get(3).add(3);
		moves.add(new ArrayList<Integer>());   moves.get(4).add(1);   moves.get(4).add(0); 
		
		assertTrue(Chessboard.board[1][1].getPiece().threatenedSquares(1, 1).containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[1][1].getPiece().threatenedSquares(1, 1)));
	}

	/**
	 * Test method for {@link pieces.Piece#possibleSquares(java.util.ArrayList)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testPossibleSquaresArrayListOfArrayListOfInteger() {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(1); moves.get(0).add(2);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(1); moves.get(1).add(3);
		assertEquals(Chessboard.board[1][1].getPiece().possibleSquares(Chessboard.board[1][1].getPiece().threatenedSquares(1, 1)),moves);
	}

	/**
	 * Test method for {@link pieces.Piece#possibleSquares(int, int)}.
	 * Tests if null is returned for every piece except a pawn
	 */
	@Test
	public final void testPossibleSquaresIntInt() {
		assertEquals(Chessboard.board[1][1].getPiece().possibleSquares(1,1),null);
		assertNotEquals(Chessboard.board[1][3].getPiece().possibleSquares(1,1),null);
	}

	/**
	 * Test method for {@link pieces.Piece#isPieceTakeable(java.lang.Boolean, int, int)}.
	 * tests if a piece is correctly detected as takeable 
	 */
	@Test
	public final void testIsPieceTakeable() {
		assertTrue(Piece.isPieceTakeable(Chessboard.board[1][1].getPiece().isWhite(), 1, 3));
		assertFalse(Piece.isPieceTakeable(Chessboard.board[1][1].getPiece().isWhite(), 1, 0));
	}

}
