package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BishopTest.class,
				ChessboardTest.class, 
				EmptySquareTest.class, 
				KingTest.class, 
				KnightTest.class,
				MajorMinorPieceTest.class, 
				PawnTest.class, PieceTest.class, 
				PlayerTest.class, 
				QueenTest.class, 
				RookTest.class,
				GameEndTest.class})

public class AllTests {
	// nothing happens here
}
