package com.imamsantosa.projectakhir;

import android.content.Context;
//import android.graphics.PixelFormat;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * A view container where OpenGL ES graphics can be drawn on screen. This view
 * can also be used to capture touch events, such as a user interacting with
 * drawn objects.
 */
public class ESSurfaceView extends GLSurfaceView {

	private final ESRender esRender;

	public float initx_stick = 1013;
	public float inity_stick = 500;
	public Point _touchingPoint = new Point(1013, 500);
	public Point _pointerPosition = new Point(220, 150);
	private Boolean _dragging = false;

	public ESSurfaceView(Context context) {
		super(context);

		// Set the Renderer for drawing on the GLSurfaceView
		esRender = new ESRender(context);
		setRenderer(esRender);

		// To enable keypad
		this.setFocusable(true);
		this.requestFocus();

		// To enable touch mode
		this.setFocusableInTouchMode(true);

	}

	@Override
	public boolean onTouchEvent(MotionEvent v) {
		kontrol(v);
		return true; 
	}

	public void kontrol(MotionEvent event){
		Point _touchingPoint = new Point(1013, 500);
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_dragging = true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			_dragging = false;
		}
		
		if(_dragging){
			_touchingPoint.x = (int) event.getX();
			_touchingPoint.y = esRender.height - (int) event.getY();
			
			esRender.setX(_touchingPoint.x);
			esRender.setY(_touchingPoint.y);
			
			switch(esRender.getPosition()){
				case 0: //main menu
					listenMainMenu(_touchingPoint);
					break;
				case 1: //about us
					listenAboutUs(_touchingPoint);
					break;
				case 2: //ch level
					listenChangeLevel(_touchingPoint);
					break;
				case 3: case 4: case 5:
					listenGamePlay(_touchingPoint);
					break;
				case 6:
					listenPause(_touchingPoint);
					break;
			}
			
		} 
	}
	
	private void listenMainMenu(Point Touching){
		int x = Touching.x;
		int y = Touching.y;
		
		if(x >= 128 && x<= 394 && y >= 74 && y <= 149){
			// action play
			esRender.setPosition(2);
		}
		
		if(x >= 510 && x<= 774 && y >= 74 && y <= 149){
			// action about
			esRender.setPosition(1);
		}
		
		if(x >= 883 && x<= 1144 && y >= 74 && y <= 149){
			// action quit
			System.exit(0);
		}
		
	}
	
	private void listenAboutUs(Point Touching){
		int x = Touching.x;
		int y = Touching.y;
		
		if(x >= 563 && x<= 679 && y >= 59 && y <= 149){
			// action back
			esRender.setPosition(0);
		}
	}
	
	private void listenChangeLevel(Point Touching){
		int x = Touching.x;
		int y = Touching.y;
		
		if(x >= 547 && x<= 709 && y >= 39 && y <= 169){
			// action back
			esRender.setPosition(0);
		}
		
		if(x >= 126 && x<= 395 && y >= 294 && y <= 452){
			// penjumlahan
			esRender.setPosition(3);
		}
		
		if(x >= 488 && x<= 759 && y >= 294 && y <= 452){
			// pengurangan
		}
		
		if(x >= 849 && x<= 1125 && y >= 294 && y <= 452){
			// perkalian
		}
		
		
	}
	
	private void listenGamePlay(Point Touching){
		int x = Touching.x;
		int y = Touching.y;
		
		if(x >= 24 && x<= 405 && y >= 20 && y <= 136){
			// pilih a
			esRender.pilihJawaban(0);
		}
		
		if(x >= 449 && x<= 831 && y >= 20 && y <= 136){
			// pilih b
			esRender.pilihJawaban(1);
		}
		
		if(x >= 871 && x<= 1253 && y >= 20 && y <= 136){
			// pilih c
			esRender.pilihJawaban(2);
		}
		
		if(x >= 1148 && x<= 1280 && y >= 551 && y <= 639){
			// pause
			esRender.paused();
		}
	}
	
	private void listenPause(Point Touching){
		int x = Touching.x;
		int y = Touching.y;
		
		if(x >= 757 && x<= 903 && y >= 73 && y <= 174){
			// pilih resume
			esRender.resume();
		}
		
		if(x >= 374 && x<= 523 && y >= 73 && y <= 174){
			// pilih home
			
		}
		
	}
	// Key-up event handler
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return true; // Event handled
	}
	
	
	
	
}
