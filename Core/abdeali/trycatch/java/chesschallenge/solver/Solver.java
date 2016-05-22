package abdeali.trycatch.java.chesschallenge.solver;

import java.util.Map;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceFactory;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.Result;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;

public abstract class Solver {

	protected int m,n;

	protected ChessBoard board;
	protected ChessPiece[] pieces;
	protected Result result;

	public Solver(int m, int n, Map<PieceType, Integer> numPieces,boolean storeConfig) throws ChessChallengeException {
		this.m = m;
		this.n = n;

		int p=0;
		for (PieceType pieceType : numPieces.keySet()) {
			p+=numPieces.get(pieceType);
		}
		pieces=new ChessPiece[p];
		p=0;
		for (PieceType pieceType : numPieces.keySet()) {
			for(int i=0;i<numPieces.get(pieceType);i++) {
				pieces[p++]=PieceFactory.createPiece(pieceType);
			}
		}

		board=new ChessBoard(m,n);
		result=new Result(board,storeConfig);
	}

	// Finds all unique configurations with the pieces, arranged so that none of them threaten each other
	public abstract Result solve() throws ChessChallengeException;

}
