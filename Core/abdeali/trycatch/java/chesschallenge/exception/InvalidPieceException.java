package abdeali.trycatch.java.chesschallenge.exception;

@SuppressWarnings("serial")
public class InvalidPieceException extends ChessChallengeException {

	public InvalidPieceException() {
		super("Invalid piece");
	}
	
	public InvalidPieceException(String msg) {
		super(msg);
	}


}
