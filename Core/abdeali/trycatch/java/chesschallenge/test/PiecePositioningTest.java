package abdeali.trycatch.java.chesschallenge.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceFactory;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;


@RunWith(Parameterized.class)
public class PiecePositioningTest {

	@Parameterized.Parameters
	public static Iterable<Object[]> allCases() throws FileNotFoundException, ChessChallengeException {
		ArrayList<Object[]> cases=new ArrayList<>();
		cases.add(new Object[] {new TestCaseFromFile("knight.txt")});
		cases.add(new Object[] {new TestCaseFromFile("bishop.txt")});
		cases.add(new Object[] {new TestCaseFromFile("king.txt")});
		cases.add(new Object[] {new TestCaseFromFile("queen.txt")});
		cases.add(new Object[] {new TestCaseFromFile("rook.txt")});
		return cases;
	}

	TestCaseFromFile data;
	public PiecePositioningTest(TestCaseFromFile data) {
		this.data=data;
	}

	@Test
	public void test() throws ChessChallengeException {
		for(int i=0;i<data.config.size();i++) {
			
			HashMap<ChessBoard.Position,ChessPiece> map=data.config.get(i);
			
			ChessBoard board=new ChessBoard(data.m,data.n);

			// Can't place on threatened positions
			for (Map.Entry<ChessBoard.Position, ChessPiece> entry : map.entrySet()) {
				ChessPiece piece=entry.getValue();
				piece.setPosition(entry.getKey());
				assertTrue(board.safeToPlacePiece(piece));
				board.PlacePiece(piece);
			}
			
			for (ChessBoard.Position pos : data.blockedPositions.get(i)) {
                assertFalse(board.safeToPlacePiece(PieceFactory.createPiece(PieceType.KING), pos));
                assertFalse(board.safeToPlacePiece(PieceFactory.createPiece(PieceType.QUEEN), pos));
                assertFalse(board.safeToPlacePiece(PieceFactory.createPiece(PieceType.BISHOP), pos));
                assertFalse(board.safeToPlacePiece(PieceFactory.createPiece(PieceType.ROOK), pos));
                assertFalse(board.safeToPlacePiece(PieceFactory.createPiece(PieceType.KNIGHT), pos));
            }

		}
	}

}
