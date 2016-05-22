package abdeali.trycatch.java.chesschallenge;

import abdeali.trycatch.java.chesschallenge.board.Result;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;
import abdeali.trycatch.java.chesschallenge.solver.CachedSolver;
import abdeali.trycatch.java.chesschallenge.solver.Solver;
import abdeali.trycatch.java.utils.Utils;


public class ChessChallengeTest {

	public static void main(String[] args) {
		
		// Set to false so that the configurations are not stored (to reduce memory footprint)
		// Set to true to store and list all the configurations
		boolean storeConfig=false;
		
		long begin = System.currentTimeMillis();

		Result res=null;
		try {
			
			Solver solver = new CachedSolver(7, 7, Utils.getNumPieceMap(2, 2, 2, 1, 0), storeConfig);
			
			res = solver.solve();
			
		} catch (ChessChallengeException e) {
			e.printStackTrace();
			return;
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(res.getNumConfig() + " number of unique configurations found");
		System.out.println("Time taken: " + (end - begin) + "ms");
		
		// Print the configurations 
		if(storeConfig) {
			for(int i=0;i<res.getNumConfig();i++) {
				System.out.println("Config #"+i);
				System.out.println(res.getConfigAsText(i));
			}
		}
	}
	
}
