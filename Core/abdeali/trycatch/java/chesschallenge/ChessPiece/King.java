package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard.Position;

public class King extends ChessPiece {

	@Override
	public boolean doesAttackLocation(int col, int row) {
		// Horizontal or vertical squares
		if( (getRow() == row && Math.abs(getColumn() - col) == 1) || (getColumn() == col && Math.abs(getRow() - row) == 1) )
			return true;
		// Diagonal squares
		if(Math.abs(getColumn() - col) == 1 && Math.abs(getRow() - row) == 1)
			return true;
		return false;
	}

	@Override
	public char getChar() {
		return 'K';
	}

	@Override
	public ThreatsMask threatsMask(ChessBoard chessBoard, Position placePos) {
		BitSet mask = new BitSet(chessBoard.getWidth() * chessBoard.getHeight());
		List<Integer> off=new ArrayList<Integer>(); 
		
		int[] dx = new int[] {0, 1, 1, 1, 0, -1, -1,-1};
		int[] dy = new int[] {1, 1, 0, -1,-1,-1,  0, 1};
		
		for(int i=0;i<dx.length;i++) {
			ChessBoard.Position position = chessBoard.new Position(placePos.x+dx[i],placePos.y+dy[i]);
			if (position.isInBounds()) {
				off.add(position.getLinearIndex());
				mask.set(position.getLinearIndex());
			}
		}
		
		return new ThreatsMask(mask,off);
	}

	@Override
	public PieceType getType() {
		return PieceType.KING;
	}


}
