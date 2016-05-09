package abdeali.trycatch.java.chesschallenge.test;

import static org.junit.Assert.*;

import org.junit.Test;

import abdeali.trycatch.java.chesschallenge.Algorithm.*;
import abdeali.trycatch.java.chesschallenge.ChessPiece.*;

public class PiecePermutationsTest {

	@Test
	public void test() {
		PiecePermutations p=new PiecePermutations(new ChessPiece[] {new King(),new Queen(),new Knight(),new Queen(),new King()});
		int i=0;
		while(p.nextPermutation()!=null) {
			//System.out.println(i+":"+Arrays.toString(lp));
			i++;
		}
		assertEquals(30,i);
	}

}
