package abdeali.trycatch.java.chesschallenge.test;

import static org.junit.Assert.*;

import org.junit.Test;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;
import abdeali.trycatch.java.chesschallenge.Algorithm.Result;
import abdeali.trycatch.java.chesschallenge.ChessPiece.*;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;

public class ChessBoardTest {

	@Test
	public void test() throws ChessChallengeException {
		
		ChessBoard chessBoard = new ChessBoard(3, 3, new ChessPiece[] {new King(), new King(), new Rook()});
		Result res = chessBoard.findUniqueConfig(true);
		assertEquals(4,res.getNumConfig());
		assertEquals("K   K \n      \n  R   \n",res.getConfigAsSSV(0));
		
	}

}
