package abdeali.trycatch.java.chesschallenge.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;

public class Result {
	
	private int width,height;

	public Result(int width,int height) {
		this.width=width;
		this.height=height;
		numConfig=0;
	}
	
	private List<HashMap<Integer,ChessPiece>> config=new ArrayList<HashMap<Integer,ChessPiece>>();
	private int numConfig;
	
	
	public void AddConfig(HashMap<Integer,ChessPiece> pieceLocation) {
		//config.add(pieceLocation);
		numConfig++;
	}
	
	public int getNumConfig() {
		return numConfig;
	}
	
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