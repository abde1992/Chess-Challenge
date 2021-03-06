package abdeali.trycatch.java.chesschallenge.ChessPiece;

import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;


// Factory of chess pieces 

public class PieceFactory {


	// Private constructor to force static access to the factory 
	private PieceFactory() {
	}


	// Create a ChessPiece from the PieceType passed.
	public static ChessPiece createPiece(PieceType pieceType) throws ChessChallengeException {
		try {
			return pieceType.getType().newInstance();
		} catch (Exception ex) {
			throw new ChessChallengeException(ex.getMessage());
		}
	}
}