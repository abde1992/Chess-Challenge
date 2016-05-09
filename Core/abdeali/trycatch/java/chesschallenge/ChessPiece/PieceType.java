package abdeali.trycatch.java.chesschallenge.ChessPiece;

public enum PieceType {
	KING("King", King.class),
	QUEEN("Queen", Queen.class),
	BISHOP("Bishop", Bishop.class),
	ROOK("Rook", Rook.class),
	KNIGHT("Knight", Knight.class);
	
	private final String name;
	private final Class<? extends ChessPiece> type;

	// -- Constructors
	//--------------------------------------------------------------------------------------------------

	// name -- Name of the piece, Example; Queen
	// type -- ChessPiece Class which represents the piece type 
	private PieceType(String name, Class<? extends ChessPiece> type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Class<? extends ChessPiece> getType() {
		return type;
	}
}