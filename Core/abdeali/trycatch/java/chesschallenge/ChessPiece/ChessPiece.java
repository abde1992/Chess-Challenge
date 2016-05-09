package abdeali.trycatch.java.chesschallenge.ChessPiece;


public abstract class ChessPiece implements Comparable<ChessPiece> {
	
	protected int row=-1,col=-1;
	
	public abstract char getType();
	public abstract boolean doesAttackLocation(int col,int row);

	
	public void setColumn(int col) {
		this.col = col;
	}
	
	public int getColumn() {
		return col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
	
	@Override
	public int hashCode() {
		return getType();
	}	
	
	@Override
	public boolean equals(Object other){
		if(this == other) return true;

		if(other == null || (this.getClass() != other.getClass()))
			return false;

		ChessPiece p = (ChessPiece) other;
		return getType()==p.getType();
	}

	@Override
	public int compareTo(ChessPiece p) {
		if(getType() < p.getType()) return -1;
		if(getType() == p.getType()) return 0;
		return 1;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getType());
	}

}
