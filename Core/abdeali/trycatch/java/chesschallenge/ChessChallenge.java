package abdeali.trycatch.java.chesschallenge;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;
import abdeali.trycatch.java.chesschallenge.Algorithm.Result;
import abdeali.trycatch.java.chesschallenge.ChessPiece.*;


public class ChessChallenge {

	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(7, 7, new ChessPiece[] {new King(), new King(), new Queen(), new Queen(), new Bishop(), new Bishop(), new Knight()});
		
		long begin = System.currentTimeMillis();

		// Pass false so that the configurations are not stored (to reduce memory footprint)
		// Need to pass true to show all the configurations
		Result res = chessBoard.findUniqueConfig(false);
		
		long end = System.currentTimeMillis();
		
		System.out.println(res.getNumConfig() + " number of unique configurations found");
		System.out.println("Time taken: " + (end - begin) + "ms");
		
		// Print the configurations (true should be passed to the findUniqueConfig method above)
		for(int i=0;i<res.getNumConfig();i++) {
			System.out.println("Config #"+i);
			System.out.println(res.getConfigAsText(i));
		}
	}
	
}
