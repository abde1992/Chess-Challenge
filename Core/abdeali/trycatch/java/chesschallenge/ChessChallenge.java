package abdeali.trycatch.java.chesschallenge;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;
import abdeali.trycatch.java.chesschallenge.Algorithm.Result;
import abdeali.trycatch.java.chesschallenge.ChessPiece.*;


public class ChessChallenge {

	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(7, 7, new ChessPiece[] {new King(), new King(), new Queen(), new Queen(), new Bishop(), new Bishop(), new Knight()});
		//ChessBoard chessBoard = new ChessBoard(3, 3, new ChessPiece[] {new King(), new King(), new Rook()});
		//ChessBoard chessBoard = new ChessBoard(4, 4, new ChessPiece[] {new Rook(), new Rook(), new Knight(), new Knight(), new Knight(), new Knight()});
		
		long begin = System.currentTimeMillis();

		Result res = chessBoard.findUniqueConfig();
		
		long end = System.currentTimeMillis();
		
		System.out.println(res.getNumConfig() + " number of unique configurations found");
		System.out.println("Time taken: " + (end - begin) + "ms");
		
		/*for(int i=0;i<res.getNumConfig();i++) {
			System.out.println("Config #"+i);
			System.out.println(res.getConfigAsText(i));
		}*/
	}
	
}
