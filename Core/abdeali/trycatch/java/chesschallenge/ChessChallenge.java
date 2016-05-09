package abdeali.trycatch.java.chesschallenge;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;
import abdeali.trycatch.java.chesschallenge.Algorithm.Result;
import abdeali.trycatch.java.chesschallenge.ChessPiece.*;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;
import abdeali.trycatch.java.chesschallenge.exception.InvalidPieceException;


public class ChessChallenge {

	// Arguments to be passed in order:
	// m n nking nqueen nbishop nrook nknight storeConfig
	// nking, nqueen, etc. is the number of pieces of each type of piece
	// storeConfig - is a boolean value to indicate if the configurations should be listed on the console (Pass true only for very small sized boards) 
	public static void main(String[] args) {

		int m=0,n=0,nking=0,nqueen=0,nbishop=0,nrook=0,nknight=0;
		boolean storeConfig=false;
		try {
			m=Integer.parseInt(args[0]);
			n=Integer.parseInt(args[1]);
			nking=Integer.parseInt(args[2]);
			nqueen=Integer.parseInt(args[3]);
			nbishop=Integer.parseInt(args[4]);
			nrook=Integer.parseInt(args[5]);
			nknight=Integer.parseInt(args[6]);
			
			// Pass false so that the configurations are not stored (to reduce memory footprint)
			// Pass true to store and list all the configurations
			storeConfig=Boolean.parseBoolean(args[7]);

		}
		catch (Exception e) {
			System.out.println("Arguments not properly formatted");
			return;
		}

		ChessPiece[] pieces=new ChessPiece[nking+nqueen+nbishop+nrook+nknight];
		int p=0;

		try {
			for(int i=0;i<nking;i++)
				pieces[p++]=PieceFactory.createPiece(PieceType.KING);
			for(int i=0;i<nqueen;i++)
				pieces[p++]=PieceFactory.createPiece(PieceType.QUEEN);
			for(int i=0;i<nbishop;i++)
				pieces[p++]=PieceFactory.createPiece(PieceType.BISHOP);
			for(int i=0;i<nrook;i++)
				pieces[p++]=PieceFactory.createPiece(PieceType.ROOK);
			for(int i=0;i<nknight;i++)
				pieces[p++]=PieceFactory.createPiece(PieceType.KNIGHT);

		} catch (InvalidPieceException e) {
			e.printStackTrace();
			return;
		}

		ChessBoard chessBoard = new ChessBoard(m, n, pieces);

		long begin = System.currentTimeMillis();

		Result res=null;
		try {
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
