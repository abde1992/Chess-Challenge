package abdeali.trycatch.java.chesschallenge.ChessPiece;

public class Bishop extends ChessPiece {

	@Override
	public char getType() {
		return 'B';
	}

	@Override
	public boolean doesAttackLocation(int col, int row) {
		if(Math.abs(getRow() - row) == Math.abs(getColumn() - col))
			return true;
		return false;
	}

}
