package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard.Position;

public class Knight extends ChessPiece {

	@Override
	public char getChar() {
		return 'H';
	}

	@Override
	public boolean doesAttackLocation(int col, int row) {
		int dy = Math.abs(getRow() - row);
		int dx = Math.abs(getColumn() - col);
		if((dy == 2 && dx == 1) || (dy == 1 && dx == 2))
			return true;
		return false;
	}

	@Override
	public ThreatsMask threatsMask(ChessBoard chessBoard, Position placePos) {
		BitSet mask = new BitSet(chessBoard.getHeight() * chessBoard.getWidth());
		List<Integer> off=new ArrayList<Integer>(); 
		
		int[] dx = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] dy = new int[] {1,  2, 2, 1, -1, -2, -2, -1};
		
		for(int i=0;i<dx.length;i++) {
			ChessBoard.Position position = chessBoard.new Position(placePos.x+dx[i],placePos.y+dy[i]);
			if (position.isInBounds()) {
				off.add(position.getLinearIndex());
				mask.set(position.getLinearIndex());
			}
		}

		return new ThreatsMask(mask, off);
	}

	@Override
	public PieceType getType() {
		return PieceType.KNIGHT;
	}
}
