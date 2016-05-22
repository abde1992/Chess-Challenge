package abdeali.trycatch.java.chesschallenge.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.lang.reflect.Constructor;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.Result;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;
import abdeali.trycatch.java.chesschallenge.solver.CachedSolver;
import abdeali.trycatch.java.chesschallenge.solver.Solver;
import static org.junit.Assert.*;


@RunWith(Parameterized.class)
@SuppressWarnings({"unchecked"})
public class SolverValidityTest {

	@Parameterized.Parameters
	public static Iterable<Object[]> cases() {
		LinkedList<Object[]> result = new LinkedList<>();
		result.add(new Object[] {new TestData(4, 4, 2, 1, 1, 2, 1)});
		result.add(new Object[] {new TestData(5, 5, 2, 1, 1, 2, 1)});
		result.add(new Object[] {new TestData(4, 5, 2, 1, 1, 2, 1)});
		result.add(new Object[] {new TestData(3, 4, 2, 0, 1, 2, 1)});
		return result;
	}

	
	private static Object[] solvers = { CachedSolver.class };
	private TestData data;

	public SolverValidityTest(TestData data) {
		this.data = data;
	}

	@Test
	public void testValidity() throws ChessChallengeException {
		HashMap<PieceType, Integer> numPieces = new HashMap<>();
		numPieces.put(PieceType.KING,   data.kings);
		numPieces.put(PieceType.QUEEN,  data.queens);
		numPieces.put(PieceType.BISHOP, data.bishops);
		numPieces.put(PieceType.ROOK,   data.rooks);
		numPieces.put(PieceType.KNIGHT, data.knights);

		for (Object solverClass : solvers) {
			
			Solver solver = null;
			Constructor<?>[] ctors = ((Class<? extends Solver>)solverClass).getDeclaredConstructors();
			for (Constructor<?> ctor : ctors) {
				if (ctor.getGenericParameterTypes().length == 4) {
					try {
						solver = (Solver)ctor.newInstance(data.m, data.n, numPieces, true);
					} catch (Exception e) {
					}
					break;
				}
			}

			assertNotNull(solver);

			Result res=solver.solve();

			// Check if all results are unique
			HashSet<String> map = new HashSet<>();
			for (int i=0;i<res.getNumConfig();i++) {
				assertFalse(map.contains(res.getConfigAsSSV(i)));
				map.add(res.getConfigAsSSV(i));
			}

			// Check all configurations are valid (i.e. have the required pieces and none is threatening any other) 
			for (int i=0;i<res.getNumConfig();i++) {
				
				HashMap<ChessBoard.Position,ChessPiece> pieceLocation=res.getConfig(i);
				HashMap<PieceType,Integer> numPiecesTest=new HashMap<PieceType,Integer>();
				for(ChessBoard.Position p:pieceLocation.keySet()) {
					PieceType t=pieceLocation.get(p).getType();
					int num=numPiecesTest.getOrDefault(t, 0);
					numPiecesTest.put(t,num+1);
				}
				// Check if the required pieces are on the configuration
				assertEquals(numPiecesTest,numPieces);
				
				for(ChessBoard.Position p:pieceLocation.keySet()) {
					for(ChessBoard.Position p1:pieceLocation.keySet()) {
						if(p.equals(p1)) continue;
						ChessPiece piece=pieceLocation.get(p);
						piece.setPosition(p);
						assertFalse(piece.doesAttackLocation(p1));
					}
				}
			}

		}

	}

	private static class TestData {

		private int m,n;
		private int kings;
		private int queens;
		private int bishops;
		private int rooks;
		private int knights;

		public TestData(int m, int n, int kings, int queens, int bishops, int rooks, int knights) {
			this.m = m;
			this.n = n;
			this.kings = kings;
			this.queens = queens;
			this.bishops = bishops;
			this.rooks = rooks;
			this.knights = knights;
		}

	}
}

