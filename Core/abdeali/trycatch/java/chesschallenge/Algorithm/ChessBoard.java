package abdeali.trycatch.java.chesschallenge.Algorithm;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceFactory;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;

public class ChessBoard {

	// Hashmap used to store the configurations as they are processed 
	private HashMap<Integer,ChessPiece> pieceLocation;
	private BitSet occupiedSquaresMask;
	private int[] numThreats;
	private Map<PieceType,BitSet[]> threatMaskCache;

	private int width;
	public int getWidth() {
		return width;
	}
	private int height;
	public int getHeight() {
		return height;
	}
	private ChessPiece[] pieces;

	public ChessBoard(int width, int height, ChessPiece[] pieces) throws ChessChallengeException {

		this.width = width;
		this.height = height;
		this.pieces = pieces;

		threatMaskCache=new HashMap<PieceType,BitSet[]> ();
		for(PieceType type : PieceType.values()) {
			ChessPiece piece=PieceFactory.createPiece(type);
			BitSet[] maskAtPos=new BitSet[width*height];
			for(int i=0;i<width;i++) {
				for(int j=0;j<height;j++) {
					ChessBoard.Position pos=new Position(i,j);
					maskAtPos[pos.getLinearIndex()]=piece.threatsMask(this, pos);
				}
			}
			threatMaskCache.put(type, maskAtPos);
		}
	}


	public class Position {
		public int x,y;
		public Position(int x, int y) {
			this.x=x;this.y=y;
		}
		public Position(int offset) {
			x = offset % width;
			y = offset / width;
		}
		public int getLinearIndex() {
			return y*width+x;
		}
		public boolean isInBounds() {
			return x>=0&&x<width&&y>=0&&y<height;
		}
	}


	// Finds all unique configurations with the pieces, arranged so that none of them threaten each other
	// storeConfig -- indicates if the configurations should be stored (as a HashMap)
	public Result findUniqueConfig(boolean storeConfig) throws ChessChallengeException {
		Result res=new Result(width,height,storeConfig);

		// Initialize PiecePermutations helper object, which generates permutations of the chess pieces,
		// taking into account duplicate types (eg. 2 kings, 3 rooks, etc.)
		PiecePermutations pp=new PiecePermutations(pieces);

		// initialize a hash map to contain the placed pieces
		// Keys are the locations of the pieces represented by linear offsets into the board
		// Values are the ChessPiece objects
		pieceLocation=new HashMap<Integer,ChessPiece>();
		occupiedSquaresMask=new BitSet(width*height);
		numThreats=new int[width*height];

		for(ChessPiece[] perm=pp.nextPermutation();perm!=null;perm=pp.nextPermutation()) {
			findNextPieceLocation(perm,0,0,res);
		}

		return res;
	}

	// Returns true if piece can be placed at the position pos.
	private boolean safeToPlacePiece(Position pos,ChessPiece piece) {

		int offset=pos.getLinearIndex();
		if(occupiedSquaresMask.get(offset)) return false;

		BitSet threats=threatMaskCache.get(piece.getType())[offset];
		return numThreats[offset]==0 && !occupiedSquaresMask.intersects(threats);

	}

	private void updateThreats(Position pos, ChessPiece piece, boolean add) {
		BitSet threats = threatMaskCache.get(piece.getType())[pos.getLinearIndex()];
		// TODO: optimize
		for (int i = threats.nextSetBit(0); i>=0; i=threats.nextSetBit(i+1)) {
			if (add) {
				numThreats[i]++;
			} else {
				numThreats[i]--;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void findNextPieceLocation(ChessPiece[] pieces,int pieceIndex,int boardOffset,Result res) throws ChessChallengeException {

		if(pieceIndex>pieces.length) throw new ChessChallengeException("Invalid Piece Index");

		if(pieceIndex == pieces.length) {
			// all chess pieces placed; save this configuration
			res.AddConfig((HashMap<Integer,ChessPiece>) pieceLocation.clone());
		}
		else {


			// Try to place the piece on the board after boardOffset
			ChessPiece piece = pieces[pieceIndex];
			int offset = boardOffset;
			while(offset < width*height) {

				boolean placed=false;
				for(; offset < width*height; offset++) {
					if(pieceLocation.get(Integer.valueOf(offset)) == null) {

						Position pos=new Position(offset);
						piece.setPosition(pos);

						if(safeToPlacePiece(pos, piece)) {
							// Place piece on board
							pieceLocation.put(offset, piece);
							occupiedSquaresMask.set(pos.getLinearIndex());
							updateThreats(pos,piece,true);

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
					Position pos=new Position(offset);
					occupiedSquaresMask.set(pos.getLinearIndex(),false);
					updateThreats(pos,piece,false);
					offset++;
				}

			}
		}
	}

}
