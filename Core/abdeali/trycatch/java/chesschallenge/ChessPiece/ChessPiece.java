package abdeali.trycatch.java.chesschallenge.ChessPiece;

import java.util.BitSet;

import abdeali.trycatch.java.chesschallenge.Algorithm.ChessBoard;


public abstract class ChessPiece implements Comparable<ChessPiece> {

	protected ChessBoard.Position pos;

	public abstract PieceType getType();
	public abstract char getChar();
	// Does the piece attack the location represented by col and row
	public abstract boolean doesAttackLocation(int col,int row);
	// Return a mask representing positions that this piece can attack when placed at placePos
	public abstract BitSet threatsMask(ChessBoard chessBoard, ChessBoard.Position placePos);


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
