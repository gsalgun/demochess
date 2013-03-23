package com.salgun.chessdemo;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	
	private SurfaceHolder mSurfaceHolder;
	private GamePanel mGamePanel;

	
	public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
		super();
		this.mSurfaceHolder = surfaceHolder;
		this.mGamePanel = gamePanel;
	}
	
	private boolean running;
	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		Canvas canvas;
		while (running) {
			canvas = null;
			try {
				canvas = this.mSurfaceHolder.lockCanvas();
				synchronized (mSurfaceHolder) {
					//ekrana çizim gerçekleþiyor
					this.mGamePanel.render(canvas);				
				}
			}
				catch(Exception e){
					
				}
			finally {
				// hata olursa surfaceholder inconsistent kalmasýn
				
					if (canvas != null) {
						mSurfaceHolder.unlockCanvasAndPost(canvas);
					}
				
				
			}
			
			
		}
	}
}

