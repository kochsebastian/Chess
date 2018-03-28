/**
 * 
 */
package tests;

import static org.junit.Assert.*;


import org.junit.Test;

import chess.EmptySquare;
import chess.Square;

/**
 * @author SebastianKoch
 * Tests the EmptySquare class
 */
public class EmptySquareTest {

	/**
	 * Test method for {@link chess.EmptySquare#getPiece()}.
	 */
	@Test
	public final void testGetPiece() {
		Square board[][] = new Square[8][8]; 
		board[0][0] = new EmptySquare();
		assertEquals(board[0][0].getPiece(), null);
	}

	/**
	 * Test method for {@link chess.EmptySquare#draw()}.
	 * tests if the correct string is drawn
	 */
	@Test
	public final void testDraw() {
		Square board[][] = new Square[8][8]; 
		board[0][0] = new EmptySquare();
		assertEquals(board[0][0].draw(), "\u25A2");
	}

	/**
	 * Test method for {@link chess.EmptySquare#EmptySquare()}.
	 * tests a empty square
	 */
	@Test
	public final void testEmptySquare() {
		EmptySquare e = new EmptySquare();
		assertTrue(e instanceof EmptySquare);
	}

}
