package abdeali.trycatch.java.chesschallenge.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import abdeali.trycatch.java.chesschallenge.ChessPiece.*;
import abdeali.trycatch.java.chesschallenge.board.*;

public class PiecePermutationsTest {

	@Test
	public void test() {
		
		String[] testPerm = {
				"KKQB", "KKBQ", "KQKB", "KQBK", "KBKQ", "KBQK",
				"QKKB", "QKBK", "QBKK", "BKKQ", "BKQK", "BQKK"
		};
		Arrays.sort(testPerm);
		PiecePermutations p=new PiecePermutations(new ChessPiece[] {new King(),new King(),new Queen(),new Bishop()});
		ChessPiece[] pieces;
		// Check if testPerm matches the permutations returned from PiecePermutations
		for(int i=0;(pieces=p.nextPermutation())!=null;i++) {
			int j=0;
			for(ChessPiece piece:pieces)
				assertTrue(piece.getChar()==testPerm[i].charAt(j++));
		}

	}

}
