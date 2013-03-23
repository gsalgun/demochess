package com.salgun.model;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Piece {
	
	private int mBitmap; //Ta��n bitmap resource u
	private boolean isMovable; //Ta� oynat�labilir mi
	private boolean isBlack; //Ta� siyah m�
	private Square mCurrentSquare; //Ta��n �st�nde oldu�u kare
	private List<Square> mDestinations; //Ta��n gidebilece�i yerler
	
	public boolean isBlack() {
		return isBlack;
	}
	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	public Square getCurrentSquare() {
		return mCurrentSquare;
	}
	public void setCurrentSquare(Square mCurrentSquare) {
		mCurrentSquare.setmPiece(this);
		this.mCurrentSquare = mCurrentSquare;
	}
	public List<Square> getmDestinations() {
		return mDestinations;
	}
	public void setDestinations(List<Square> mDestinations) {
		this.mDestinations = mDestinations;
	}
	public int getmBitmap() {
		return mBitmap;
	}
	public void setmBitmap(int mBitmap) {
		this.mBitmap = mBitmap;
	}
	public boolean isMovable() {
		return isMovable;
	}
	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}
	
	public void findDestination(Square[][] mSquares) { //Ta�lar�n gidebilece�i yerler hesaplanacak, her ta� i�in farkl�
	}
	

}
