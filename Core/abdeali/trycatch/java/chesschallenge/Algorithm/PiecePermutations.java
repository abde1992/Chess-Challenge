package abdeali.trycatch.java.chesschallenge.Algorithm;

import java.util.Arrays;
import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;


// Generates permutations of the chess pieces, taking into account duplicate piece types
// Call nextPermutation method to get next permutation
// Ensures no permutation is repeated.
// All permutations are returned once during the lifecycle of the object. After all permutations have been returned, the nextPermutation method returns null.
// This is O(n^2) (worst case) implementation.
// TODO: Replace sort by the quick reverse method
public class PiecePermutations {

	private ChessPiece[] pieces;
	private boolean done = false;		// true represents all permutations have been returned

	// Constructor
	// Initializes the permutation object
	public PiecePermutations(ChessPiece[] pieces) {

		this.pieces=pieces;

		// Sort the string in increasing order
		Arrays.sort(pieces);

	}

	// Helper method to reverse an array of ChessPieces 
	private void reverse(ChessPiece[] pieces, int l, int h) {
		while (l < h) {
			ChessPiece temp=pieces[l];
			pieces[l]=pieces[h];
			pieces[h]=temp;
			l++;
			h--;
		}
	}

	public ChessPiece[] nextPermutation() {

		ChessPiece[] nextPerm=null;

		if ( ! done ) {

			// store this permutation for returning later
			nextPerm=pieces.clone();

			// Find the rightmost piece which is smaller than the right piece adjacent to it.
			int i;
			for ( i = pieces.length - 2; i >= 0; --i )
				if (pieces[i].compareTo(pieces[i+1])<0)
					break;

			// If there is no such character, whole array is sorted in non-increasing order,
			// i.e. all permutations have been returned; set done to true
			if ( i == -1 )
				done = true;
			else {

				// Find the smallest piece greater than i
				int smallIndex=i+1;
				for (int j = smallIndex + 1; j <= pieces.length-1; j++)
					if (pieces[j].compareTo(pieces[i]) > 0 && pieces[j].compareTo(pieces[smallIndex]) < 0)
						smallIndex = j;

				// Swap pieces at i and smallIndex
				ChessPiece temp=pieces[i];
				pieces[i]=pieces[smallIndex];
				pieces[smallIndex]=temp;

				Arrays.sort(pieces,i+1,pieces.length);
			}
		}
		
		return nextPerm;
	}

}
