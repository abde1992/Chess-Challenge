package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard.Position;

public class Bishop extends ChessPiece {

	@Override
	public char getChar() {
		return 'B';
	}

	@Override
	public boolean doesAttackLocation(int col, int row) {
		if(Math.abs(getRow() - row) == Math.abs(getColumn() - col))
			return true;
		return false;
	}

	@Override
	public ThreatsMask threatsMask(ChessBoard chessBoard, Position placePos) {
		BitSet mask = new BitSet(chessBoard.getWidth() * chessBoard.getHeight());

		int[] dx = new int[] { -1, -1,  1, 1 };
		int[] dy = new int[] {-1,  1, -1, 1 };

		List<Integer> off=new ArrayList<Integer>(); 
		
		for(int i=0;i<dx.length;i++) {
			ChessBoard.Position p;
			for(int d=1; (p=chessBoard.new Position(placePos.x + dx[i]*d, placePos.y + dy[i]*d)).isInBounds(); d++) {
				off.add(p.getLinearIndex());
				mask.set(p.getLinearIndex());
			}
		}
		
		return new ThreatsMask(mask,off);
	}

	@Override
	public PieceType getType() {
		return PieceType.BISHOP;
	}

}
