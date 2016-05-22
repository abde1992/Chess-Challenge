package abdeali.trycatch.java.chesschallenge.solver;

import java.util.Map;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;
import abdeali.trycatch.java.chesschallenge.board.PiecePermutations;
import abdeali.trycatch.java.chesschallenge.board.Result;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard.Position;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;

// Extends Solver
// Solves the problem using backtracking and ordered permutations of the pieces (taking into account duplicates)
// Uses the threat caching done in ChessBoard class
// See also ChessBoard.java

public class CachedSolver extends Solver {

	public CachedSolver(int m, int n, Map<PieceType, Integer> numPieces,
			boolean storeConfig) throws ChessChallengeException {
		super(m, n, numPieces, storeConfig);
	}

	@Override
	public Result solve() throws ChessChallengeException {

		// Initialize PiecePermutations helper object, which generates permutations of the chess pieces,
		// taking into account duplicate types (eg. 2 kings, 3 rooks, etc.)
		PiecePermutations pp=new PiecePermutations(pieces);

		for(ChessPiece[] perm=pp.nextPermutation();perm!=null;perm=pp.nextPermutation()) {
			findNextPieceLocation(perm,0,0);
		}

		return result;
	}

	// Finds next safe location for the piece at pieceIndex and places it there. Then recurses for the next piece in the pieces array.
	// Also does backtracking after all solutions in a branch have been found.
	protected void findNextPieceLocation(ChessPiece[] pieces,int pieceIndex,int boardOffset) throws ChessChallengeException {

		if(pieceIndex>pieces.length) throw new ChessChallengeException("Invalid Piece Index");

		if(pieceIndex == pieces.length) {
			// all chess pieces placed; save this configuration
			result.AddConfig(board.getPieceLocationMap());
		}
		else {


			// Try to place the piece on the board after boardOffset
			ChessPiece piece = pieces[pieceIndex];
			int offset = boardOffset;
			while(offset < board.getWidth()*board.getHeight()) {

				boolean placed=false;
				for(; offset < board.getWidth()*board.getHeight(); offset++) {
					Position pos=board.new Position(offset);
					piece.setPosition(pos);

					if(board.safeToPlacePiece(piece)) {
						
						// Place piece on board
						board.PlacePiece(piece);

						placed=true;
						break;
					}
					
				}

				if(!placed) 
					return;
				else {

					// continue to place remaining pieces on the board after this offset
					findNextPieceLocation(pieces, pieceIndex + 1, offset + 1);

					// ***     Backtracking    *** 
					board.RemovePiece(piece);
					
					offset++;
				}

			}
		}
	}
}
