package abdeali.trycatch.java.chesschallenge.exception;

@SuppressWarnings("serial")
public class InvalidPositionException extends ChessChallengeException {

	public InvalidPositionException() {
		super("Invalid position");
	}
	
	public InvalidPositionException(String msg) {
		super(msg);
	}
	
}
