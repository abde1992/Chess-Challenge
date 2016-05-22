package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.BitSet;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard.Position;

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
	public ThreatsMask threatsMask(ChessBoard chessBoard, Position placePos) {
		ThreatsMask rookMask = new Rook().threatsMask(chessBoard, placePos);
		ThreatsMask bishopMask = new Bishop().threatsMask(chessBoard, placePos);

		List<Integer> off=rookMask.offsets;
		off.addAll(bishopMask.offsets);
		BitSet mask=rookMask.mask;
		mask.or(bishopMask.mask);
		return new ThreatsMask(mask, off);
	}

	@Override
	public PieceType getType() {
		return PieceType.QUEEN;
	}

}
