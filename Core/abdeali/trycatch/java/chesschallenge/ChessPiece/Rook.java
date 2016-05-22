package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.board.ChessBoard;

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
	public ThreatsMask threatsMask(ChessBoard chessBoard, ChessBoard.Position placePos) {
		
		BitSet mask = new BitSet(chessBoard.getWidth() * chessBoard.getHeight());
		List<Integer> off=new ArrayList<Integer>(); 

		// Threats in the row
		for (int x = 0; x < chessBoard.getWidth(); x++) {
			if(x==placePos.x) continue;
			ChessBoard.Position p=chessBoard.new Position(x, placePos.y);
			off.add(p.getLinearIndex());
			mask.set(p.getLinearIndex());
		}
		// Threats in the column
		for (int y = 0; y < chessBoard.getHeight(); y++) {
			if(y==placePos.y) continue;
			ChessBoard.Position p=chessBoard.new Position(placePos.x, y);
			off.add(p.getLinearIndex());
			mask.set(p.getLinearIndex());
		}

		return new ThreatsMask(mask, off);
	}

	@Override
	public PieceType getType() {
		return PieceType.ROOK;
	}
}
