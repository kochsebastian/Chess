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
import pieces.Bishop;
import pieces.MajorMinorPiece;
import pieces.Pawn;
import pieces.Rook;

/**
 * @author SebastianKoch 
 * sets up
 *
 */
public class MajorMinorPieceTest {

	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a chessboard with a Majorpiece and a few other pieces
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
		Chessboard.board[0][4] = new Rook(false);
		Chessboard.board[2][4] = null;
		Chessboard.board[2][4] = new Pawn(true);
		Chessboard.board[0][3] = null;
		Chessboard.board[0][3] = new Pawn(true);
		Chessboard.generateThreatsOnChessboard();
	}

	/**
	 * Test method for {@link pieces.MajorMinorPiece#validMove(int, int, int, int)}. 
	 * tests if a valid move is detected as such
	 */
	@Test
	public final void testValidMove() {
	
		assertTrue(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 3));
		assertTrue(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 5));
		assertFalse(Chessboard.board[0][4].getPiece().validMove(0, 4,0, 2)); 

	}

	/**
	 * Test method for {@link pieces.MajorMinorPiece#possibleSquares(java.util.ArrayList)}.
	 * Tests if the correct squares are returned
	 */
	@Test
	public final void testPossibleSquares() {
		ArrayList<ArrayList<Integer>> moves= new ArrayList<ArrayList<Integer>>();
		moves.add(new ArrayList<Integer>()); moves.get(0).add(0); moves.get(0).add(5);
		moves.add(new ArrayList<Integer>()); moves.get(1).add(0); moves.get(1).add(6);
		moves.add(new ArrayList<Integer>()); moves.get(2).add(0); moves.get(2).add(7);
		moves.add(new ArrayList<Integer>()); moves.get(3).add(0); moves.get(3).add(3);
		moves.add(new ArrayList<Integer>()); moves.get(4).add(1); moves.get(4).add(4);
		moves.add(new ArrayList<Integer>()); moves.get(5).add(2); moves.get(5).add(4);
		assertTrue(Chessboard.board[0][4].getPiece().possibleSquares(Chessboard.board[0][4].getPiece().threatenedSquares(0, 4)).containsAll(moves));
		assertTrue(moves.containsAll(Chessboard.board[0][4].getPiece().possibleSquares(Chessboard.board[0][4].getPiece().threatenedSquares(0, 4))));
	} 

	/**
	 * Test method for {@link pieces.MajorMinorPiece#MajorMinorPiece(java.lang.Boolean)}.
	 * tests a major or minor piece
	 */
	@Test
	public final void testMajorMinorPiece() {
		MajorMinorPiece m = new Bishop(true); 
		assertTrue(m instanceof MajorMinorPiece);
		assertTrue(m instanceof Bishop);
		assertFalse(m instanceof Rook);
	}

}
