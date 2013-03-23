package com.salgun.model;

import java.util.ArrayList;

import com.salgun.chessdemo.R;
import com.salgun.chessdemo.R.drawable;

import android.graphics.BitmapFactory;

public class Board {
	
	private Square[][] mSquares;
	private ArrayList<Piece> mPieces;

	public Board() {		
		super();
		createSquares(); //Kareleri yarat
		createPieces(); //Taþlarý yarat
		findDestinations(); //Taþlar için gidilebilecek kareleri belirle
	}

	public Square[][] getmSquares() {
		return mSquares;
	}

	public void setmSquares(Square[][] mSquares) {
		this.mSquares = mSquares;
	}

	public void createSquares()
	{
		mSquares = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				mSquares[i][j]= new Square(i,j);
			}
		}
	}
	
	public void findDestinations()
	{
		for (Piece p : mPieces) {
			p.findDestination(mSquares);
		}
	}
	
	public void createPieces()
	{
		mPieces = new ArrayList<Piece>();
		
		//Ýlk siyah kale
		Piece bRook1 = new Piece();
		bRook1.setBlack(true);
		bRook1.setCurrentSquare(mSquares[0][0]);
		bRook1.setmBitmap(R.drawable.br);
		mPieces.add(bRook1);
		
		//Ýlk siyah at
		Knight bKnight1 = new Knight();
		bKnight1.setBlack(true);
		bKnight1.setCurrentSquare(mSquares[0][1]);
		bKnight1.setmBitmap(R.drawable.bn);
		mPieces.add(bKnight1);
		
		//Ýlk siyah fil
		Piece bBishop1 = new Piece();
		bBishop1.setBlack(true);
		bBishop1.setCurrentSquare(mSquares[0][2]);
		bBishop1.setmBitmap(R.drawable.bb);
		mPieces.add(bBishop1);
		
		//Siyah þah
		Piece bKing = new Piece();
		bKing.setBlack(true);
		bKing.setCurrentSquare(mSquares[0][3]);
		bKing.setmBitmap(R.drawable.bk);
		mPieces.add(bKing);
				
		//Siyah vezir
		Piece bQueen = new Piece();
		bQueen.setBlack(true);
		bQueen.setCurrentSquare(mSquares[0][4]);
		bQueen.setmBitmap(R.drawable.bq);
		mPieces.add(bQueen);
				
		//Ýkinci siyah fil
		Piece bBishop2 = new Piece();
		bBishop2.setBlack(true);
		bBishop2.setCurrentSquare(mSquares[0][5]);
		bBishop2.setmBitmap(R.drawable.bb);
		mPieces.add(bBishop2);
				
		//Ýkinci siyah at
		Knight bKnight2 = new Knight();
		bKnight2.setBlack(true);
		bKnight2.setCurrentSquare(mSquares[0][6]);
		bKnight2.setmBitmap(R.drawable.bn);
		mPieces.add(bKnight2);
				
		//Ýkinci siyah kale
		Piece bRook2 = new Piece();
		bRook2.setBlack(true);
		bRook2.setCurrentSquare(mSquares[0][7]);
		bRook2.setmBitmap(R.drawable.br);
		mPieces.add(bRook2);
		
		//Ýlk beyaz kale
		Piece wRook1 = new Piece();
		wRook1.setBlack(false);
		wRook1.setMovable(false);
		wRook1.setCurrentSquare(mSquares[7][0]);
		wRook1.setmBitmap(R.drawable.wr);
		mPieces.add(wRook1);
				
		//Ýlk beyaz at
		Knight wKnight1 = new Knight();
		wKnight1.setBlack(false);
		wKnight1.setMovable(true);
		wKnight1.setCurrentSquare(mSquares[7][1]);
		wKnight1.setmBitmap(R.drawable.wn);
		mPieces.add(wKnight1);
				
		//Ýlk beyaz fil
		Piece wBishop1 = new Piece();
		wBishop1.setBlack(false);
		wBishop1.setMovable(false);
		wBishop1.setCurrentSquare(mSquares[7][2]);
		wBishop1.setmBitmap(R.drawable.wb);
		mPieces.add(wBishop1);
				
		//beyaz þah
		Piece wKing = new Piece();
		wKing.setBlack(false);
		wKing.setMovable(false);
		wKing.setCurrentSquare(mSquares[7][3]);
		wKing.setmBitmap(R.drawable.wk);
		mPieces.add(wKing);
						
		//beyaz vezir
		Piece wQueen = new Piece();
		wQueen.setBlack(false);
		wQueen.setMovable(false);
		wQueen.setCurrentSquare(mSquares[7][4]);
		wQueen.setmBitmap(R.drawable.wq);
		mPieces.add(wQueen);
						
		//Ýkinci beyaz fil
		Piece wBishop2 = new Piece();
		wBishop2.setBlack(false);
		wBishop2.setMovable(false);
		wBishop2.setCurrentSquare(mSquares[7][5]);
		wBishop2.setmBitmap(R.drawable.wb);
		mPieces.add(wBishop2);
						
		//Ýkinci beyaz at
		Knight wKnight2 = new Knight();
		wKnight2.setBlack(false);
		wKnight2.setMovable(true);
		wKnight2.setCurrentSquare(mSquares[7][6]);
		wKnight2.setmBitmap(R.drawable.wn);
		mPieces.add(wKnight2);
						
		//Ýkinci beyaz kale
		Piece wRook2 = new Piece();
		wRook2.setBlack(false);
		wRook2.setMovable(false);
		wRook2.setCurrentSquare(mSquares[7][7]);
		wRook2.setmBitmap(R.drawable.wr);
		mPieces.add(wRook2);
		
		
		//Siyah piyonlar
		for (int i = 0; i < 8; i++) {
			Pawn pawn = new Pawn();
			pawn.setBlack(true);
			pawn.setMovable(false);
			pawn.setCurrentSquare(mSquares[1][i]);
			pawn.setmBitmap(R.drawable.bp);
			mPieces.add(pawn);
		}
		
		//Beyaz piyonlar
		for (int i = 0; i < 8; i++) {
			Pawn pawn = new Pawn();
			pawn.setBlack(false);
			pawn.setMovable(true);
			pawn.setCurrentSquare(mSquares[6][i]);
			pawn.setmBitmap(R.drawable.wp);
			mPieces.add(pawn);
		}
	}

	public ArrayList<Piece> getmPieces() {
		return mPieces;
	}

	public void setmPieces(ArrayList<Piece> mPieces) {
		this.mPieces = mPieces;
	}

}
