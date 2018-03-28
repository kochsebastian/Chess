package tests;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import chess.Chessboard;
import chess.EmptySquare;
import chess.Player;
import pieces.Rook;


/**
 * 
 */

/**
 * @author SebastianKoch
 * Tests the Player class
 */
public class PlayerTest {

	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a empty chessboard
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
	} 

	/**
	 * Test method for {@link chess.Player#Player()}.
	 * tests a player
	 */
	@Test
	public final void testPlayer() {
		Player p = new Player();
		assertEquals(p.getClass(), new Player().getClass());
	}

	/**
	 * Test method for {@link chess.Player#getMove(java.io.InputStream)}.
	 * tests the getMove() method
	 */
	@Test
	public final void testGetMove()  {
		Player p1 = new Player();
		int[] b = {0,0};
		ByteArrayInputStream in = new ByteArrayInputStream("a8".getBytes());
		System.setIn(in);
		try {
			assertArrayEquals(p1.getMove(in),b);
		}catch(NumberFormatException | IOException e) { 
			fail("unexpected Exception");
		}
	}

	
	/**
	 * Test method for {@link chess.Player#getMove(java.io.InputStream)}.
	 * @throws NumberFormatException will be thrown
	 * tests the getMove() method but a NumberFormatException is expected 
	 */
	@Test(expected = NumberFormatException.class)
	public final void testGetMoveNumberFormatException() throws NumberFormatException{
		Player p1 = new Player();
		ByteArrayInputStream in = new ByteArrayInputStream("aa".getBytes());
		System.setIn(in);
		
		try {
			p1.getMove(in);
		} catch (IOException e) {
			fail("unexpected Exception");
		}
		
	}
	
	/**
	 * Test method for {@link chess.Player#getMove(java.io.InputStream)}.
	 * @throws IOException will be thrown
	 * tests the getMove() method but a IOException is expected 
	 */
	@Test(expected = IOException.class)
	public final void testGetMoveIOException() throws IOException{
		Player p1 = new Player();
		ByteArrayInputStream in = new ByteArrayInputStream("j2".getBytes());
		System.setIn(in);
		
		try {
			p1.getMove(in);
		} catch (NumberFormatException e) {
			fail("unexpected Exception");
		}
		
	}
	
	/**
	 * Test method for {@link chess.Player#getSquare(java.io.InputStream)}.
	 * tests the getSquare() method
	 */
	@Test
	public final void testGetSquare() {
		Chessboard.board[0][0] = null;
		Chessboard.board[0][0] = new Rook(true);
		
		Player p1 = new Player();
		int[] b = {0,0};
		ByteArrayInputStream in = new ByteArrayInputStream("a8".getBytes());
		System.setIn(in);
		try {
			assertArrayEquals(p1.getSquare(in),b); 
		} catch (NumberFormatException | NullPointerException | IOException e) {
			fail("unexpected Exception");
		}
		
	}
	
	/**
	 * Test method for {@link chess.Player#getSquare(java.io.InputStream)}.
	 * @throws NullPointerException will be thrown
	 * tests the getSquare() method but a NullPointerException is expected 
	 */
	@Test(expected = NullPointerException.class)
	public final void testGetSquareNullPointerException() throws NullPointerException{
		Player p1 = new Player();

		ByteArrayInputStream in = new ByteArrayInputStream("a8".getBytes());
		System.setIn(in);
		try {
			p1.getSquare(in);
		} catch (NumberFormatException  | IOException e) {
			fail("unexpected Exception");
		}
	}
	
	/**
	 * Test method for {@link chess.Player#getSquare(java.io.InputStream)}.
	 * @throws IOException will be thrown
	 * * tests the getSquare() method but a IOException is expected 
	 */
	@Test(expected = IOException.class)
	public final void testGetSquareIOException() throws IOException{
		Player p1 = new Player();
		ByteArrayInputStream in = new ByteArrayInputStream("a9".getBytes());
		System.setIn(in);
		try {
			p1.getSquare(in);
		} catch (NullPointerException  | NumberFormatException e) {
			fail("unexpected Exception");
		}
	}
	
	
	
	
	

}
