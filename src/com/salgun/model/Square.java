package com.salgun.model;

public class Square {
	
	private int row;
	private int col;
	
	private Piece mPiece;
	
	public Piece getmPiece() {
		return mPiece;
	}
	public void setmPiece(Piece mPiece) {
		this.mPiece = mPiece;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public Square(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	



}
