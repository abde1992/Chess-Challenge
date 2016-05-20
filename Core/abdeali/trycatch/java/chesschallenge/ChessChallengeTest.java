package abdeali.trycatch.java.chesschallenge;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;
import abdeali.trycatch.java.chesschallenge.Algorithm.Result;
import abdeali.trycatch.java.chesschallenge.ChessPiece.*;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;


public class ChessChallengeTest {

	public static void main(String[] args) {
		
		// Set to false so that the configurations are not stored (to reduce memory footprint)
		// Set to true to store and list all the configurations
		boolean storeConfig=false;
		
		long begin = System.currentTimeMillis();

		Result res=null;
		try {
			
			ChessBoard chessBoard = new ChessBoard(7, 7, new ChessPiece[] {new King(), new King(), 
					new Queen(), new Queen(), new Bishop(), new Bishop(), new Knight()});
			
			res = chessBoard.findUniqueConfig(storeConfig);
			
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
