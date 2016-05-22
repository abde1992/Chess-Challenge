package abdeali.trycatch.java.chesschallenge.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;

public class Result {
	
	protected ChessBoard board;
	protected boolean storeConfig;

	public Result(ChessBoard board,boolean storeConfig) {
		this.board=board;
		this.storeConfig=storeConfig;
		numConfig=0;
	}
	
	protected List<HashMap<ChessBoard.Position,ChessPiece>> config=new ArrayList<HashMap<ChessBoard.Position,ChessPiece>>();
	protected int numConfig;
	
	
	// storeConfig - should this configuration be stored.
	public void AddConfig(HashMap<ChessBoard.Position,ChessPiece> pieceLocation) {
		if(storeConfig)
			config.add(pieceLocation);
		numConfig++;
	}
	
	public boolean containsConfig(HashMap<ChessBoard.Position,ChessPiece> pieceLocation) {
		return config.contains(pieceLocation);
	}
	
	public int getNumConfig() {
		return numConfig;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<ChessBoard.Position,ChessPiece> getConfig(int i) {
		return (HashMap<ChessBoard.Position,ChessPiece>) config.get(i).clone();
	}
	
	// Return configuration i as a string formatted in a space-separated-value format
	public String getConfigAsSSV(int i) {
		HashMap<ChessBoard.Position,ChessPiece> pieceLocation=config.get(i);
		StringBuilder sb = new StringBuilder();

		int offset = 0;
		for(int r = 0; r < board.getHeight() ; r++) {
			
			for(int c = 0; c < board.getWidth(); c++) {
				ChessPiece piece = pieceLocation.get(board.new Position(offset++));
				sb.append(piece!=null?piece.getChar():" ");
				sb.append(" ");
			}
			sb.append("\n");
			
		}

		return sb.toString();
	}
	
	// Return configuration i as a string formatted for output
	public String getConfigAsText(int i) {
		
		HashMap<ChessBoard.Position,ChessPiece> pieceLocation=config.get(i);
		
		StringBuilder sb = new StringBuilder();

		int offset = 0;
		for(int r = 0; r < board.getHeight() ; r++) {
			
			for(int c = 0; c < board.getWidth(); c++) {
				sb.append("|");
				sb.append("---");
			}
			sb.append("|\n");
			
			for(int c = 0; c < board.getWidth(); c++) {
				sb.append("| ");
				ChessPiece piece = pieceLocation.get(Integer.valueOf(offset++));
				sb.append(piece!=null?piece.getChar():" ");
				sb.append(" ");
			}
			sb.append("|\n");
		}
		for(int c = 0; c < board.getWidth(); c++) {
			sb.append("|");
			sb.append("---");
		}
		sb.append("|\n");

		return sb.toString();
	}
}