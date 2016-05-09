package abdeali.trycatch.java.chesschallenge.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;

public class Result {
	
	private int width,height;
	private boolean storeConfig;

	public Result(int width,int height,boolean storeConfig) {
		this.width=width;
		this.height=height;
		this.storeConfig=storeConfig;
		numConfig=0;
	}
	
	private List<HashMap<Integer,ChessPiece>> config=new ArrayList<HashMap<Integer,ChessPiece>>();
	private int numConfig;
	
	
	// storeConfig - should this configuration be stored.
	public void AddConfig(HashMap<Integer,ChessPiece> pieceLocation) {
		if(storeConfig)
			config.add(pieceLocation);
		numConfig++;
	}
	
	public int getNumConfig() {
		return numConfig;
	}
	
	// Return configuration i as a string formatted in a space-separated-value format
	public String getConfigAsSSV(int i) {
		HashMap<Integer,ChessPiece> pieceLocation=config.get(i);
		StringBuilder sb = new StringBuilder();

		int offset = 0;
		for(int r = 0; r < height ; r++) {
			
			for(int c = 0; c < width; c++) {
				ChessPiece piece = pieceLocation.get(Integer.valueOf(offset++));
				sb.append(piece!=null?piece.getType():" ");
				sb.append(" ");
			}
			sb.append("\n");
			
		}

		return sb.toString();
	}
	
	// Return configuration i as a string formatted for output
	public String getConfigAsText(int i) {
		
		HashMap<Integer,ChessPiece> pieceLocation=config.get(i);
		
		StringBuilder sb = new StringBuilder();

		int offset = 0;
		for(int r = 0; r < height ; r++) {
			
			for(int c = 0; c < width; c++) {
				sb.append("|");
				sb.append("---");
			}
			sb.append("|\n");
			
			for(int c = 0; c < width; c++) {
				sb.append("| ");
				ChessPiece piece = pieceLocation.get(Integer.valueOf(offset++));
				sb.append(piece!=null?piece.getType():" ");
				sb.append(" ");
			}
			sb.append("|\n");
		}
		for(int c = 0; c < width; c++) {
			sb.append("|");
			sb.append("---");
		}
		sb.append("|\n");

		return sb.toString();
	}
}