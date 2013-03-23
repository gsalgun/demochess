package com.salgun.chessdemo;

import java.util.ArrayList;

import com.salgun.chessdemo.R;
import com.salgun.model.Board;
import com.salgun.model.Piece;
import com.salgun.model.Square;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements Callback {

	private GameThread mThread;
	private Point mSize; //Ekran boyutu
	private Bitmap mBoardBitMap; //Satranç tahtasý bmp
	private Board mBoard; //Satranç tahtasý sýnýfý
	private int mSquareX; //Tek karenin kenar uzunluðu
	private boolean mFaulty; //Hatalý hareket var
	private Piece mCurrentPiece; //Seçili taþ
	private boolean mPicked; //Taþ seçildi
	private String mWarningText; //Uyarý yazýsý
	private boolean mWon; //Oyun kazanýldý

	public GamePanel(Context context) {
		super(context);			
		getHolder().addCallback(this);
		
		//Ekran boyutunu alalým
		Display display =  ((Activity)context).getWindowManager().getDefaultDisplay();
		mSize = new Point();
		display.getSize(mSize);

		//Tahtanýn kenar uzunluðu ekranýn geniþliði olacak
		int length = mSize.x;

		//Tahta bmp'ini alýp boyutunu ayarlýyoruz
		mBoardBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.board);
		mBoardBitMap = Bitmap.createScaledBitmap(mBoardBitMap, length, length, false);

		mThread = new GameThread(getHolder(), this);

		//Satranç tahtasý oluþtu, içinde kareler ve taþlar oluþacak
		mBoard = new Board();

		//Karenin kenarý bu kadar
		mSquareX = length/8;

		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		//Ekran yaratýldýðýnda thread çalýþmaya baþlasýn
		mThread.setRunning(true);
		mThread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.DKGRAY); //Arkaplan çizildi
		canvas.drawBitmap(mBoardBitMap, 0, ((mSize.y-mSize.x)/2), null); //Tahta ekrana ortalanarak çizildi

		//Taþlarý çizdirelim
		ArrayList<Piece> pieces = mBoard.getmPieces();
		for (int i = 0; i < pieces.size(); i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), pieces.get(i).getmBitmap());
			Double d = mSquareX*0.8;
			bitmap = Bitmap.createScaledBitmap(bitmap, d.intValue(), d.intValue(), false);
			Square square = (pieces.get(i)).getCurrentSquare();
			Double d2 = mSquareX*0.05;
			canvas.drawBitmap(bitmap, mSquareX*square.getCol()+d2.intValue(), (mSquareX*square.getRow())+((mSize.y-mSize.x)/2)+d2.intValue(), null);
		}

		if (mFaulty||mWon) {
			//Hatalý hareket ya da oyun bitti, uyarý gösterelim
			Paint paint = new Paint();
			paint.setColor(Color.WHITE);
			paint.setStyle(Style.FILL);
			paint.setTextAlign(Align.CENTER);
			paint.setTextSize((float) (mSquareX*0.5));

			canvas.drawText(mWarningText, mSize.x/2, ((mSize.y-mSize.x)/4), paint);
		}

		if (mPicked&&!mWon) {
			
			//Taþ seçildi, kareyi highlight edelim
			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth((float) (mSquareX*0.1));
			paint.setStyle(Style.STROKE);
			canvas.drawRect((mCurrentPiece.getCurrentSquare().getCol()+1)*mSquareX, mCurrentPiece.getCurrentSquare().getRow()*mSquareX+((mSize.y-mSize.x)/2), mCurrentPiece.getCurrentSquare().getCol()*mSquareX, (mCurrentPiece.getCurrentSquare().getRow()+1)*mSquareX+((mSize.y-mSize.x)/2), paint);
		}
	}

	public Piece whichPiece(int x, int y) //Dokunulan yerde hangi taþ var
	{				
		return whichSquare(x,y).getmPiece();		
	}

	public Square whichSquare(int x, int y) //Dokunulan yerde hangi kare var
	{
		int newY = y-((mSize.y-mSize.x)/2);

		int col = newY/mSquareX;
		int row = x/mSquareX;

		return mBoard.getmSquares()[col][row];		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {


		if (event.getAction() == MotionEvent.ACTION_DOWN && !mWon) {


			//Dokunulan yer satranç tahtasý üzerinde mi
			int emptyArea = ((mSize.y-mSize.x)/2);

			if ((int)event.getY()>emptyArea&&(int)event.getY()<(mSize.y-emptyArea)) {

				//Seçili taþ var mý
				if (mPicked) {
					Square nextSquare = whichSquare((int)event.getX(), (int)event.getY());

					if (mCurrentPiece.getmDestinations().contains(nextSquare)) {
						//Seçilen kare taþýn gidebileceði bir kare
						mFaulty = false;
						mCurrentPiece.setCurrentSquare(nextSquare);
						//Oyun kazanýldý
						mWon = true;
						mWarningText = getResources().getString(R.string.warning_youwon);
					} 
					else { //Seçilen karede baþka taþ var
						if (nextSquare.getmPiece()!=null && nextSquare.getmPiece().isMovable()) {
							if (mCurrentPiece == nextSquare.getmPiece())
							{
								mPicked = false;
								mFaulty = false;
								//Ayný taþ seçildi, seçim kaldýrýldý
							}
							else{
								mCurrentPiece = nextSquare.getmPiece();
								mFaulty = false;
								mPicked = true;
								//Seçili taþ deðiþti

							}
						}

						else { //Taþý yanlýþ yere oynatmaya çalýþýyor
							mFaulty = true;
							mWarningText = getResources().getString(R.string.warning_wrongsquare);
						}		
					}
				}
				else {
					//Yeni taþ seçilecek
					try { //Hangi taþ
						mCurrentPiece = whichPiece((int)event.getX(), (int)event.getY());
						if (!mCurrentPiece.isMovable()) { //Oynatýlabilir taþ mý
							mFaulty = true;
							if (mCurrentPiece.isBlack()) { 
								mWarningText = getResources().getString(R.string.warning_wrongset);
							}
							else {
								mWarningText = getResources().getString(R.string.warning_wrongpiece);
							}
						}
						else { //Taþ seçildi
							mFaulty = false;
							mPicked = true;
						}
					} catch (Exception e) {

					}
				}


			}



		}





		return true;
	}





}
