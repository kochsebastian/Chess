package tests;


import org.junit.Before;
import org.junit.Test;

import chess.Chessboard;
import chess.EmptySquare;
import exceptions.ProgrammEndException;
import pieces.Pawn;

public class GameEndTest {

	@Before
	public void setUp() throws Exception {
		Chessboard.setup();
	}

	@Test(expected = ProgrammEndException.class)
	public final void testGameEnd() throws ProgrammEndException {
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
		
		Chessboard.update(0,3,4,7);
		 
		
	}

}
