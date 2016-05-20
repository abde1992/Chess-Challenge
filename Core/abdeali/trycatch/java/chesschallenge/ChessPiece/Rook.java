package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.BitSet;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;

public class Rook extends ChessPiece {

	@Override
	public char getChar() {
		return 'R';
	}

	@Override
	public boolean doesAttackLocation(int col, int row) {
		if(getRow() == row || getColumn() == col)
			return true;
		return false;
	}

	@Override
	public BitSet threatsMask(ChessBoard chessBoard, ChessBoard.Position placePos) {
		
		BitSet mask = new BitSet(chessBoard.getWidth() * chessBoard.getHeight());

		// Threats in the row
		for (int x = 0; x < chessBoard.getWidth(); x++) {
			mask.set(chessBoard.new Position(x, placePos.y).getLinearIndex());
		}
		// Threats in the column
		for (int y = 0; y < chessBoard.getHeight(); y++) {
			mask.set(chessBoard.new Position(placePos.x, y).getLinearIndex());
		}

		// Remove placePos
		mask.clear(placePos.getLinearIndex());
		return mask;
	}

	@Override
	public PieceType getType() {
		return PieceType.ROOK;
	}
}
