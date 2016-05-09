package abdeali.trycatch.java.chesschallenge.ChessPiece;

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
	public char getType() {
		return 'K';
	}
	
	
}
