package abdeali.trycatch.java.chesschallenge.ChessPiece;

public class Knight extends ChessPiece {

	@Override
	public char getType() {
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
}
