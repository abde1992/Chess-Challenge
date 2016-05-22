package abdeali.trycatch.java.utils;

import java.util.HashMap;
import java.util.Map;

import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;

public class Utils {

	public static Map<PieceType,Integer> getNumPieceMap(int nking,int nqueen,int nbishop,int nknight,int nrook) {
		Map<PieceType,Integer> numPieces=new HashMap<>();
		numPieces.put(PieceType.KING, nking);
		numPieces.put(PieceType.QUEEN, nqueen);
		numPieces.put(PieceType.BISHOP, nbishop);
		numPieces.put(PieceType.KNIGHT, nknight);
		numPieces.put(PieceType.ROOK, nrook);
		return numPieces;
	}
}
