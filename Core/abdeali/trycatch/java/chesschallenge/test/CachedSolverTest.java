package abdeali.trycatch.java.chesschallenge.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import abdeali.trycatch.java.chesschallenge.ChessPiece.*;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.Result;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;
import abdeali.trycatch.java.chesschallenge.solver.CachedSolver;
import abdeali.trycatch.java.chesschallenge.solver.Solver;


@RunWith(Parameterized.class)
public class CachedSolverTest {

	@Parameterized.Parameters
	public static Iterable<Object[]> allCases() throws FileNotFoundException, ChessChallengeException {
		ArrayList<Object[]> cases=new ArrayList<>();
		cases.add(new Object[] {new TestCaseFromFile("knight.txt")});
		cases.add(new Object[] {new TestCaseFromFile("bishop.txt")});
		cases.add(new Object[] {new TestCaseFromFile("king.txt")});
		cases.add(new Object[] {new TestCaseFromFile("queen.txt")});
		cases.add(new Object[] {new TestCaseFromFile("rook.txt")});
		cases.add(new Object[] {new TestCaseFromFile("8queens.txt")});
		cases.add(new Object[] {new TestCaseFromFile("case1.txt")});
		cases.add(new Object[] {new TestCaseFromFile("case2.txt")});
		return cases;
	}

	TestCaseFromFile data;
	public CachedSolverTest(TestCaseFromFile data) {
		this.data=data;
	}

	@Test
	public void test() throws ChessChallengeException {

		Solver solver=new CachedSolver(data.m,data.n,data.numPieces,true);

		Result res=solver.solve();

		for(HashMap<ChessBoard.Position,ChessPiece> pieceLocation:data.config) {
			assertTrue(res.containsConfig(pieceLocation));
		}

	}

}
