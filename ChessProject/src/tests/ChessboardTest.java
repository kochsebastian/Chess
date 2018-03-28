/**
 * 
 */
package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import chess.Chessboard;
import chess.EmptySquare;
import exceptions.ProgrammEndException;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 * @author SebastianKoch
 *
 */
public class ChessboardTest {

	/**
	 * @throws java.lang.Exception standard Exception
	 * sets up a board where many different checks and checkmates can be performed
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare(); 
				Chessboard.board[i][j].setThreatenedBy("nothing");
			}
		}
		Chessboard.board[6][1] = new Rook(false);
		Chessboard.board[0][4] = new Queen(false);
		Chessboard.board[7][4] = new King(true);
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				
				Chessboard.board[i][j] = new EmptySquare();
			}
		}
		
		Chessboard.board[0][6]=null;
		Chessboard.board[0][6] = new King(false);
	
		
		Chessboard.board[1][7] = null;
		Chessboard.board[1][7] = new Pawn(false);
		Chessboard.board[1][6] = null;
		Chessboard.board[1][6] = new Pawn(false);
		Chessboard.board[3][5] = null;
		Chessboard.board[3][5] = new Knight(true);
		Chessboard.board[1][5] = null;
		Chessboard.board[1][5] = new Pawn(false);

		
		Chessboard.board[6][6] = null;
		Chessboard.board[6][6] = new Rook(true);
		Chessboard.board[0][5] = null;
		Chessboard.board[0][5] = new Rook(false);
		Chessboard.board[0][7] = null;
		Chessboard.board[0][7] = new Rook(false);
		
		
	}




	/**
	 * Test method for {@link chess.Chessboard#update(int, int, int, int)}.
	 * tests if the chessboard is correctly updated
	 */
	@Test
	public final void testUpdate() {
		try {
			Chessboard.update(1,6,2,6);
		} catch (ProgrammEndException e) {
			
		}
		assertTrue(Chessboard.board[2][6].getPiece().equals(new Pawn(false))); 
	}

	/**
	 * Test method for {@link chess.Chessboard#generateThreatsOnChessboard()}.
	 * tests if the threats on the chessboard are correctly generated
	 */
	@Test
	public final void testGenerateThreatsOnChessboard() {
		Chessboard.generateThreatsOnChessboard();
		assertEquals(Chessboard.board[2][5].getThreatenedBy(),"black");
		assertEquals(Chessboard.board[1][6].getThreatenedBy(),"blackANDwhite"); 
	}

	/*
	 * Test method for {@link Chess.Chessboard#checkForChecks()}.
	 * tests if a check is correctly detected
	 */
	@Test
	public final void testCheckForChecks() {

		Chessboard.board[1][6] = Chessboard.board[6][6];
	
		Chessboard.board[6][6] = null;
		Chessboard.board[6][6] = new EmptySquare();
		
		Chessboard.generateThreatsOnChessboard();
		try {
			assertTrue(Chessboard.checkForChecks());
		} catch (ProgrammEndException e) {
			
		}
		
		
	}

	
	/**
	 * Test method for {@link chess.Chessboard#resetBoard()}. 
	 * tests if the chessboard is correctly resetted
	 */
	@Test
	public final void testResetBoard() {
		for(int i=0; i< 8; i++){
			for(int j = 0; j<8; j++){
				Chessboard.board[i][j].setThreatenedBy("nothing");
			}
		}
		for(int i=0; i< 8; i++){
			for(int j = 0; j<8; j++){
				assertEquals(Chessboard.board[i][j].getThreatenedBy(),"nothing");
			}
		}
	}

	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate is correctly detected
	 */
	@Test
	public final void testCheckForCheckmate() {
		
		
		Chessboard.board[1][6] = Chessboard.board[6][6];
	
		Chessboard.board[6][6] = null; 
		Chessboard.board[6][6] = new EmptySquare();
	
		Chessboard.generateThreatsOnChessboard();
		assertTrue(Chessboard.checkForCheckmate(1, 6, 0, 6));
		
	}
	
	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate attack is correctly detected as failed because the 
	 * king can doge the attack
	 */
	@Test
	public final void testCheckForCheckmateKingCanDodge() {
		
		Chessboard.board[0][7] = null;
		Chessboard.board[0][7] = new EmptySquare();
		
		Chessboard.board[1][6] = Chessboard.board[6][6];
		
		Chessboard.board[6][6] = null;
		Chessboard.board[6][6] = new EmptySquare();
		
		Chessboard.generateThreatsOnChessboard();
		assertFalse(Chessboard.checkForCheckmate(1, 6, 0, 6));
		
	}
	
	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate is correctly detected because the king cant dodge
	 */
	@Test
	public final void testCheckForCheckmateKingCantDodge() {
		
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare(); 
				Chessboard.board[i][j].setThreatenedBy("nothing");
			}
		}
		Chessboard.board[7][6] = new EmptySquare(); 
		Chessboard.board[7][6] = new King(true);
		Chessboard.board[6][2] = new EmptySquare(); 
		Chessboard.board[6][2] = new Rook(false);
		Chessboard.board[7][3] = new EmptySquare(); 
		Chessboard.board[7][3] = new Queen(false);
		
		
		assertTrue(Chessboard.checkForCheckmate(7, 3, 7, 6));
		
		
	}
	
	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate is correctly detected as failed because a piece can take the
	 * threatning piece
	 */
	@Test
	public final void testCheckForCheckmatePieceCanTake() {
		
		Chessboard.board[1][6] = null;
		Chessboard.board[1][6] = new Rook(true);
		Chessboard.board[3][7] = null;
		Chessboard.board[3][7] = new Knight(false);
		 
		 
		Chessboard.generateThreatsOnChessboard();
		assertFalse(Chessboard.checkForCheckmate(1, 6, 0, 6)); 
		
	}

	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate is correctly detected as failed because a piece can be placed 
	 * between the king and the threatning piece
	 */
	@Test
	public final void testCheckForCheckmatePieceCanGoBetween() {
		
		Chessboard.board[1][6] = null;
		Chessboard.board[1][6] = new EmptySquare();
		Chessboard.board[2][7] = null;
		Chessboard.board[2][7] = new Rook(false);
		 
		Chessboard.generateThreatsOnChessboard();
		assertFalse(Chessboard.checkForCheckmate(6, 6, 0, 6));
		
	}
	
	
	
	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate is correctly detected by using a knight as the check giving piece
	 */
	@Test
	public final void testCheckForCheckmatePieceWithKnight() {
		Chessboard.board[1][6] = null;
		Chessboard.board[1][6] = new Knight(false);
		Chessboard.board[2][5] = null;
		Chessboard.board[2][5] = new Knight(true);
		 
		
		Chessboard.generateThreatsOnChessboard();
		assertTrue(Chessboard.checkForCheckmate(2, 5, 0, 6));
		
	}
	
	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate  is correctly detected by using a pawn as the check giving piece
	 */
	@Test
	public final void testCheckForCheckmatePieceWithPawn() {
		
		Chessboard.board[1][7] = null;
		Chessboard.board[1][7] = new Pawn(true);
		Chessboard.board[0][7] = null;
		Chessboard.board[0][7] = new Knight(false);
		Chessboard.board[6][7] = null;
		Chessboard.board[6][7] = new Rook(true); 
		Chessboard.board[3][5] = null;
		Chessboard.board[3][5] = new EmptySquare();
		
		Chessboard.generateThreatsOnChessboard();
		assertTrue(Chessboard.checkForCheckmate(1, 7, 0, 6));
		
	}
	
	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate is correctly detected when a piece cant be placed between 
	 * king and threatning piece
	 */
	@Test
	public final void testCheckForCheckmatePieceCantGoBetween() {
		for(int i = 0;i<8;i++) { 
			for(int j = 0;j<8;j++) {
				Chessboard.board[i][j] = new EmptySquare(); 
				Chessboard.board[i][j].setThreatenedBy("nothing");
			}
		}
		Chessboard.board[3][7] = null;
		Chessboard.board[3][7] = new King(false);
		Chessboard.board[7][6] = null;
		Chessboard.board[7][6] = new Rook(true);
		Chessboard.board[1][5] = null;
		Chessboard.board[1][5] = new Queen(true); 
		Chessboard.board[2][7] = null;
		Chessboard.board[2][7] = new Pawn(false);
		Chessboard.board[4][7] = null;
		Chessboard.board[4][7] = new Bishop(false);
		Chessboard.board[6][2] = null;
		Chessboard.board[6][2] = new Bishop(true);
		Chessboard.board[3][4] = null;
		Chessboard.board[3][4] = new Queen(false);
		 
		 
		Chessboard.generateThreatsOnChessboard(); 
		assertTrue(Chessboard.checkForCheckmate(1, 5, 3, 7)); 
		
	}
	/**
	 * Test method for {@link chess.Chessboard#checkForCheckmate(int, int, int, int)}.
	 * tests if a checkmate is correctly detected when a piece cant be placed between 
	 * king and threatning piece
	 */
	@Test
	public final void testCheckForCheckmateFoolsMate() {
		Chessboard.setup();
		Chessboard.board[6][5] = null;
		Chessboard.board[6][5] = new EmptySquare();
		Chessboard.board[6][6] = null;
		Chessboard.board[6][6] = new EmptySquare();
		Chessboard.board[4][5] = null;
		Chessboard.board[4][5] = new Pawn(true);
		Chessboard.board[4][6] = null;
		Chessboard.board[4][6] = new Pawn(true);
		Chessboard.board[1][4] = null;
		Chessboard.board[1][4] = new EmptySquare();
		Chessboard.board[2][4] = null;
		Chessboard.board[2][4] = new Pawn(false);
		Chessboard.board[0][3] = null;
		Chessboard.board[0][3] = new EmptySquare();
		Chessboard.board[4][7] = null;
		Chessboard.board[4][7] = new Queen(false);
		 
		Chessboard.generateThreatsOnChessboard(); 
		assertTrue(Chessboard.checkForCheckmate(4, 7, 7, 4)); 
		
	}
	
	/**
	 * Test method for {@link chess.Chessboard#reverse(int, int, int, int)}.
	 * tests if the chessboard is correctly reversed after a invalid move
	 */
	@Test
	public final void testReverse() {
		Chessboard.checkWhite=false;
		Chessboard.checkBlack=false;
		Chessboard.backup = Chessboard.board[6][7];
		
		Chessboard.board[6][7] = Chessboard.board[6][6];
	
		Chessboard.board[6][6] = null;
		Chessboard.board[6][6] = new EmptySquare();
		 
		Chessboard.reverse(6, 6, 6, 7); 
		assertTrue(Chessboard.board[6][6].getPiece().equals(new Rook(true))); 
	}

}
