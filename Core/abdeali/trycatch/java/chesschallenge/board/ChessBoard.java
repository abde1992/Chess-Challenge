package abdeali.trycatch.java.chesschallenge.board;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;
import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece.ThreatsMask;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceFactory;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;
import abdeali.trycatch.java.chesschallenge.exception.InvalidPositionException;

public class ChessBoard {

	// Hashmap used to store the configurations as they are processed 
	private HashMap<ChessBoard.Position,ChessPiece> pieceLocation;
	
	// Returns a copy of the piece location map
	@SuppressWarnings("unchecked")
	public HashMap<ChessBoard.Position,ChessPiece> getPieceLocationMap() {
		return (HashMap<Position,ChessPiece>) pieceLocation.clone();
	}
	
	// Mask to store each piece position (as a set bit)
	private BitSet occupiedSquaresMask;
	
	public boolean isPositionOccupied(Position pos) {
		return occupiedSquaresMask.get(pos.getLinearIndex());
	}
	
	// No. of piece threatening a board position offset
	private int[] numThreats;
	
	// Map containing threatened position information for each piece type and its linearly ordered position on the board
	// Eg. threatMaskCache.get(PieceType.King)[0].mask will give a bitset with bits set at those positions which can be threatened by a king placed at (0,0)
	private Map<PieceType,ThreatsMask[]> threatMaskCache;

	private int width;
	public int getWidth() {
		return width;
	}
	private int height;
	public int getHeight() {
		return height;
	}
	

	public ChessBoard(int width, int height) throws ChessChallengeException {

		this.width = width;
		this.height = height;

		// Initialize cached data structures
		threatMaskCache=new HashMap<PieceType,ThreatsMask[]> ();
		for(PieceType type : PieceType.values()) {
			ChessPiece piece=PieceFactory.createPiece(type);
			ThreatsMask[] maskAtPos=new ThreatsMask[width*height];
			for(int i=0;i<width;i++) {
				for(int j=0;j<height;j++) {
					ChessBoard.Position pos=new Position(i,j);
					
					// Get the mask and offsets for piece type 'type' placed at position 'pos'
					ThreatsMask tmask=piece.threatsMask(this, pos);
					maskAtPos[pos.getLinearIndex()]=tmask;
				}
			}
			threatMaskCache.put(type, maskAtPos);
		}
		
		// initialize a hash map to contain the placed pieces
		// Keys are the locations of the pieces 
		// Values are the ChessPiece objects
		pieceLocation=new HashMap<ChessBoard.Position,ChessPiece>();
		
		// Initialize mask and numThreats array
		occupiedSquaresMask=new BitSet(width*height);
		numThreats=new int[width*height];
		
	}


	// Represents a board position and conversion methods
	public class Position {
		public int x,y;
		
		public Position(int x, int y) {
			this.x=x;this.y=y;
		}
		
		// Make position from linear offset
		public Position(int offset) {
			x = offset % width;
			y = offset / width;
		}
		
		// Get the linearly ordered index of this position
		public int getLinearIndex() {
			return y*width+x;
		}
		
		// Return true if this position is within bounds of the board
		public boolean isInBounds() {
			return x>=0&&x<width&&y>=0&&y<height;
		}
		
		@Override
		public boolean equals(Object other){
			if(this == other) return true;

			if(other == null || (this.getClass() != other.getClass()))
				return false;

			Position p = (Position) other;
			return x==p.x && y==p.y;
		}
		
		@Override
		public int hashCode() {
			return getLinearIndex();
		}

	}

	

	// Returns true if piece can be placed at the position returned by piece.getPosition()
	public boolean safeToPlacePiece(ChessPiece piece) {
		return safeToPlacePiece(piece,piece.getPosition());
	}
	// Returns true if piece can be placed at the position pos.
	public boolean safeToPlacePiece(ChessPiece piece, Position pos) {

		int offset=pos.getLinearIndex();
		// Is this position already occupied?
		if(occupiedSquaresMask.get(offset)) return false;

		// Get a mask containing all squares threatened by the new piece, when placed at position pos
		BitSet threats=threatMaskCache.get(piece.getType())[offset].mask;
		
		// Return true if no other piece threatens this position
		// and the new piece doesn't threaten already placed pieces
		return numThreats[offset]==0 && !occupiedSquaresMask.intersects(threats);

	}

	// Update the numThreats array accordingly
	// If add is true, increase numThreats at all positions that the piece can threaten
	public void updateThreats(ChessPiece piece, boolean add) {
		
		// Get the position the piece is to be placed
		Position pos=piece.getPosition();
		// Get all positions that the piece can threaten
		List<Integer> threats = threatMaskCache.get(piece.getType())[pos.getLinearIndex()].offsets;
		
		for (int i = 0; i<threats.size(); i++) {
			if (add) {
				numThreats[threats.get(i)]++;
			} else {
				numThreats[threats.get(i)]--;
			}
		}
	}

	
	public void PlacePiece(ChessPiece piece) throws InvalidPositionException {
		Position pos=piece.getPosition();
		int offset=piece.getPosition().getLinearIndex();
		if(occupiedSquaresMask.get(offset))
			throw new InvalidPositionException("Position already occupied");
		
		pieceLocation.put(pos, piece);
		occupiedSquaresMask.set(offset);
		updateThreats(piece,true);
	}
	
	public void RemovePiece(ChessPiece piece) throws InvalidPositionException {
		Position pos=piece.getPosition();
		int offset=pos.getLinearIndex();
		if(!occupiedSquaresMask.get(offset))
			throw new InvalidPositionException("No piece placed at this position");
		
		pieceLocation.remove(pos);
		occupiedSquaresMask.set(offset,false);
		updateThreats(piece,false);
	}
	

}
