package abdeali.trycatch.java.chesschallenge.ChessPiece;

public class Queen extends ChessPiece {

	@Override
	public char getType() {
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
	
}
