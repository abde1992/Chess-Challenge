package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.BitSet;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.board.ChessBoard;


public abstract class ChessPiece implements Comparable<ChessPiece> {

	protected ChessBoard.Position pos;

	public abstract PieceType getType();
	public abstract char getChar();
	// Does the piece attack the location represented by col and row
	public abstract boolean doesAttackLocation(int col,int row);
	public boolean doesAttackLocation(ChessBoard.Position otherPos) {
		return doesAttackLocation(otherPos.x,otherPos.y);
	}
	// Return a mask representing positions that this piece can attack when placed at placePos
	public abstract ThreatsMask threatsMask(ChessBoard chessBoard, ChessBoard.Position placePos);

	// Cached object to store threatened positions in both mask and list form
	public class ThreatsMask {
		public BitSet mask;
		public List<Integer> offsets;
		public ThreatsMask(BitSet mask,List<Integer> offsets) {
			this.mask=mask;
			this.offsets=offsets;
		}
	}

	public void setPosition(ChessBoard.Position pos) {
		this.pos=pos;
	}

	public ChessBoard.Position getPosition() {
		return pos;
	}
	
	public int getColumn() {
		return pos.x;
	}
	public int getRow() {
		return pos.y;
	}

	@Override
	public int hashCode() {
		return getChar();
	}	

	@Override
	public boolean equals(Object other){
		if(this == other) return true;

		if(other == null || (this.getClass() != other.getClass()))
			return false;

		ChessPiece p = (ChessPiece) other;
		return getChar()==p.getChar();
	}

	@Override
	public int compareTo(ChessPiece p) {
		if(getChar() < p.getChar()) return -1;
		if(getChar() == p.getChar()) return 0;
		return 1;
	}

	@Override
	public String toString() {
		return getType().name();
	}

}
