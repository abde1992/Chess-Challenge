package abdeali.trycatch.java.chesschallenge.Algorithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;

public class ChessBoard {

	private HashMap<Integer,ChessPiece> pieceLocation;

	private int width;
	private int height;
	private ChessPiece[] pieces;

	public ChessBoard(int width, int height, ChessPiece[] pieces) {

		this.width = width;
		this.height = height;
		this.pieces = pieces;
	}


	public Result findUniqueConfig() {
		Result res=new Result(width,height);
		PiecePermutations pp=new PiecePermutations(pieces);
		pieceLocation=new HashMap<Integer,ChessPiece>();

		for(ChessPiece[] perm=pp.nextPermutation();perm!=null;perm=pp.nextPermutation()) {
			findNextPieceLocation(perm,0,0,res);
		}
		
		return res;
	}

	private boolean safeToPlacePiece(int offset,ChessPiece piece) {
		int col = offset % width;
		int row = offset / width;
		for(int placedOffset : pieceLocation.keySet()) {
			ChessPiece placedPiece=pieceLocation.get(placedOffset);
			// Check if placed piece attacks this location
			if(placedPiece.doesAttackLocation(col, row))
				return false;
			int placedCol = placedOffset % width;
			int placedRow = placedOffset / width;
			// Check if this piece placed at this position attacks already placed pieces
			if(piece.doesAttackLocation(placedCol, placedRow))
				return false;
		}
		return true;
	}
	
	private void findNextPieceLocation(ChessPiece[] pieces,int pieceIndex,int boardOffset,Result res) {
		
		if(pieceIndex == pieces.length) {
			// all chess pieces placed; save the config
			res.AddConfig((HashMap<Integer,ChessPiece>)pieceLocation.clone());
		}
		else {

			ChessPiece piece = pieces[pieceIndex];
			int offset = boardOffset;
			while(offset < width*height) {

				boolean placed=false;
				for(; offset < width*height; offset++) {
					if(pieceLocation.get(Integer.valueOf(offset)) == null) {
						
						int col = offset % width;
						int row = offset / width;
						piece.setColumn(col);
						piece.setRow(row);
						if(safeToPlacePiece(offset, piece)) {
							// Place piece on board
							try {
							pieceLocation.put(offset, piece);
							}
							catch(Exception e) {
								System.out.println();
							}
							placed=true;
							break;
						}
					}
				}
				
				if(!placed) 
					break;
				else {

					// continue to place remaining pieces on the board after this offset
					findNextPieceLocation(pieces, pieceIndex + 1, offset + 1, res);

					// backtracking
					pieceLocation.remove(offset);
					offset++;
				}
				
			}
		}
	}

}
