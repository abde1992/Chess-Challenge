package abdeali.trycatch.java.chesschallenge.ChessPiece;

public class Rook extends ChessPiece {

	@Override
	public char getType() {
		return 'R';
	}

	@Override
	public boolean doesAttackLocation(int col, int row) {
		if(getRow() == row || getColumn() == col)
			return true;
		return false;
	}

}
