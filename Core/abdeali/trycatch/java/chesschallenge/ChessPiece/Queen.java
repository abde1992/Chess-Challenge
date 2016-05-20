package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.BitSet;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;
import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard.Position;

public class Queen extends ChessPiece {

	@Override
	public char getChar() {
		return 'Q';
	}

	@Override
	public boolean doesAttackLocation(int col, int row) {
		// Vertical or horizontal squares
		if(getRow() == row || getColumn() == col)
			return true;
		// Diagonal squares
		if(Math.abs(getRow() - row) == Math.abs(getColumn() - col))
			return true;
		return false;
	}

	@Override
	public BitSet threatsMask(ChessBoard chessBoard, Position placePos) {
		BitSet rookMask = new Rook().threatsMask(chessBoard, placePos);
		BitSet bishopMask = new Bishop().threatsMask(chessBoard, placePos);

		BitSet mask=rookMask;
		mask.or(bishopMask);
		return mask;
	}

	@Override
	public PieceType getType() {
		return PieceType.QUEEN;
	}

}
