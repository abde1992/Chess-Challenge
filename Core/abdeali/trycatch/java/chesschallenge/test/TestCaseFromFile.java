package abdeali.trycatch.java.chesschallenge.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

import abdeali.trycatch.java.chesschallenge.ChessPiece.ChessPiece;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceFactory;
import abdeali.trycatch.java.chesschallenge.ChessPiece.PieceType;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard;
import abdeali.trycatch.java.chesschallenge.board.ChessBoard.Position;
import abdeali.trycatch.java.chesschallenge.exception.ChessChallengeException;
import abdeali.trycatch.java.chesschallenge.exception.InvalidPieceException;
import abdeali.trycatch.java.utils.Utils;

public class TestCaseFromFile {
	
    public int m,n;
    public int nking;
    public int nqueen;
    public int nbishop;
    public int nrook;
    public int nknight;
    public Map<PieceType,Integer> numPieces;
    public List<HashMap<ChessBoard.Position,ChessPiece>> config;
    public List<List<Position>> blockedPositions;

    public TestCaseFromFile(String file) throws FileNotFoundException, ChessChallengeException {

        Scanner scanner = new Scanner(new FileReader(file));
        
        m = scanner.nextInt();
        n = scanner.nextInt();

        nking = scanner.nextInt();
        nqueen = scanner.nextInt();
        nbishop = scanner.nextInt();
        nrook = scanner.nextInt();
        nknight = scanner.nextInt();
        
        int numResults = scanner.nextInt();

        numPieces=Utils.getNumPieceMap(nking, nqueen, nbishop, nknight, nrook);

        ChessBoard board=new ChessBoard(m,n);
        config = new ArrayList<HashMap<ChessBoard.Position,ChessPiece>>(numResults);
        blockedPositions=new ArrayList<List<Position>>(numResults);

        for (int i = 0; i < numResults && scanner.hasNextLine(); i++) {
        	
        	HashMap<ChessBoard.Position,ChessPiece> pieceLocationMap=new HashMap<ChessBoard.Position,ChessPiece>();
        	List<Position> blockedPositionList=new ArrayList<Position>();
        	
            scanner.nextLine();
            for (int y = 0; y < n; y++) {
                String line = scanner.nextLine();
                String[] tokens = line.split("\\s+");
                for (int x = 0; x < tokens.length; x++) {
                    String token = tokens[x];
                    
                    switch (token) {
                    	case "x":
                    		blockedPositionList.add(board.new Position(x,y));
                    		break;
	                    case ".":
	                        break;
	                    default: {
	                    	ChessPiece piece = null;
	                    	switch(token.charAt(0)) {
	                    	case 'K':
	                    		piece=PieceFactory.createPiece(PieceType.KING);
	                    		break;
	                    	case 'Q':
	                    		piece=PieceFactory.createPiece(PieceType.QUEEN);
	                    		break;
	                    	case 'H':
	                    		piece=PieceFactory.createPiece(PieceType.KNIGHT);
	                    		break;
	                    	case 'B':
	                    		piece=PieceFactory.createPiece(PieceType.BISHOP);
	                    		break;
	                    	case 'R':
	                    		piece=PieceFactory.createPiece(PieceType.ROOK);
	                    		break;
	                    	}
	                        if (piece == null) {
	                            throw new InvalidPieceException("Unknown piece type '" + token + "'");
	                        }
	                        
	                        pieceLocationMap.put(board.new Position(x,y), piece);
	                        
	                    }
                    }
                }
            }

            config.add(pieceLocationMap);
            blockedPositions.add(blockedPositionList);
        }
        
        scanner.close();
    }
}



