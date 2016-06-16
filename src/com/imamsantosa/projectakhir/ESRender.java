package com.imamsantosa.projectakhir;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class ESRender implements Renderer {

	private ESText glText;
	Context context;
	int Run_Mode=0;
	int height = 0;
	int width = 0;
	
	private int x = 0;
	private int y = 0;
	
	private int position = 0;
	private int back_position = 0;
	private MainMenu mainmenu;
	private AboutUs about;
	private ChangeLevel chlevel;
	private GamePlay gameplay;
	private Penjumlahan penjumlahan;
	private Pause pause;
	
	
	private float decrement_timing = 2.5f;
	private float game_timing = 0.0f;
	private boolean paused = false;
	
	public ESRender(Context context) {
		// super();
		this.context = context;
		this.mainmenu = new MainMenu();	
		this.about = new AboutUs();
		this.chlevel = new ChangeLevel();
		this.gameplay = new GamePlay();
		this.penjumlahan = new Penjumlahan(context, gameplay);
		
		this.pause = new Pause();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_NORMALIZE);
//		penjumlahan.loadImage(gl);
		Log.v("position", " "+position);
		
		switch(position){
			case 0: // main menu
				mainMenu(gl);
				break;
			case 1: // about us
				about(gl);
				break;
			case 2: // change level to play
				changeLevel(gl);
				break;
			case 3: // penjumlahan
				gamePlay(gl);
				penjumlahan(gl);
				break;
			case 4: // pengurangan
				break;
			case 5: // perkalian
				break;
			case 6: 
				paused(gl);
				break;
		}
		
		teksPointer(gl);
		
	}
	
	private void teksPointer(GL10 gl){
		gl.glPushMatrix();
		gl.glEnable(GL10.GL_BLEND); // Enable Alpha Blend
		gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA); // Set
																		// Alpha
																		// Blend
																		// Function

		glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
												// // WHITE)
		gl.glTranslatef(2.0f, 150.0f, 1.0f);
		gl.glScalef(2.0f, 2.0f, 2.0f);
		glText.draw("Moch Wahyu Imam Santosa :-) x="+x+" y= "+y, 0, 0); // Draw
		glText.end(); // End Text Rendering
//		gl.glEnable(GL10.GL_LIGHTING);
//
//		// disable texture + alpha
		gl.glDisable(GL10.GL_BLEND); // Disable Alpha Blend
		gl.glPopMatrix();
	}
	
	private void penjumlahan(GL10 gl){
		gl.glPushMatrix();
			penjumlahan.draw(gl);
		gl.glPopMatrix();
		
		// render soal
		gl.glPushMatrix();
			gl.glEnable(GL10.GL_BLEND); // Enable Alpha Blend
			gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			glText.begin(0.0f, 1.0f, 0.0f, 1.0f);
			gl.glTranslatef(341.0f, 350.0f, 1.0f);
			gl.glScalef(5.0f, 5.0f, 1.0f);
			glText.draw(penjumlahan.getA()+" + "+penjumlahan.getB()+" = ...", 0, 0); // Draw
			glText.end(); // End Text Rendering
			gl.glDisable(GL10.GL_BLEND); // Disable Alpha Blend
		gl.glPopMatrix();
	}
	
	private void gamePlay(GL10 gl){
		gl.glPushMatrix();
			gameplay.draw(gl, height, width);
		gl.glPopMatrix();
		
		// render score
		gl.glPushMatrix();
			gl.glEnable(GL10.GL_BLEND); // Enable Alpha Blend
			gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			glText.begin(0.0f, 1.0f, 0.0f, 1.0f);
			gl.glTranslatef(341.0f, 580.0f, 1.0f);
			gl.glScalef(5.0f, 5.0f, 1.0f);
			if(position == 3){
				glText.draw(" "+penjumlahan.getScore(), 0, 0); // Draw
			} else if(position == 4){
				// score pengurangan
			} else if(position == 5){
				// score perkalian
			}
			
			glText.end(); // End Text Rendering
			gl.glDisable(GL10.GL_BLEND); // Disable Alpha Blend
		gl.glPopMatrix();
		
		//render timing
		gl.glPushMatrix();
			gl.glTranslatef(-game_timing, 160.0f, 1.0f);
			gl.glScalef(width, 20.0f, 0.0f);
			if(game_timing <= 1000){
				gameplay.drawTiming(gl);
			} else {
				gameplay.drawTimingDanger(gl);
			}
		gl.glPopMatrix();
		if(!paused){
			game_timing += decrement_timing;
		}
		if(game_timing >= 1280){
			gameOver();
		}
	}
	
	private void gameOver(){
		this.setPosition(0);
		game_timing = 0;
		penjumlahan.gameOver();
	}
	
	private void paused(GL10 gl){
		gl.glPushMatrix();
			pause.draw(gl, height, width);
		gl.glPopMatrix();
	}
	

	private void mainMenu(GL10 gl){
		gl.glPushMatrix(); 
			mainmenu.draw(gl, height, width);
	    gl.glPopMatrix();
	}
	
	private void about(GL10 gl){
		gl.glPushMatrix(); 
			about.draw(gl, height, width);
	    gl.glPopMatrix();
	}
	
	private void changeLevel(GL10 gl){
		gl.glPushMatrix();
			chlevel.draw(gl, height, width);
		gl.glPopMatrix();
	}
	
	private void loadImage(GL10 gl){
		mainmenu.load(gl, context);
	 	about.load(gl, context);
	 	chlevel.load(gl, context);
	 	gameplay.load(gl, context);
	 	pause.load(gl, context);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f); // Set color's clear-value to
		//gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Set color's clear-value to
													// black
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Set color's clear-value to
		
		gl.glClearDepthf(1.0f); // Set depth's clear-value to farthest
		gl.glEnable(GL10.GL_DEPTH_TEST); // Enables depth-buffer for hidden
		// surface removal
		gl.glDepthFunc(GL10.GL_LEQUAL); // The type of depth testing to do
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); // nice
		// perspective
		// view
		gl.glShadeModel(GL10.GL_SMOOTH); // Enable smooth shading of color
		gl.glDisable(GL10.GL_DITHER); // Disable dithering for better
		// performance
		gl.glEnable(GL10.GL_LIGHTING);	
    	gl.glEnable(GL10.GL_LIGHT0);
    	//gl.glEnable(GL10.GL_LIGHT1);
    	

		// Create the GLText
		glText = new ESText(gl, context.getAssets());

		// Load the font from file (set size + padding), creates the texture
		// NOTE: after a successful call to this the font is ready for
		// rendering!
		glText.load("Roboto-Regular.ttf", 14, 2, 4); // Create Font (Height: 14
														// Pixels / X+Y Padding
														// 2 Pixels)

		// gl.glDisable(GL10.GL_DITHER); // Disable dithering for better
		// performance
		
		 // Setup Blending (NEW)
	 	gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f); // Full brightness, 50% alpha (NEW)
	 	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE); // Select blending function (NEW)

		// Setup Texture, each time the surface is created (NEW)
	 	
	 	loadImage(gl);
		
		gl.glEnable(GL10.GL_TEXTURE_2D); // Enable texture (NEW)

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.width = width;
		this.height = height;

		gl.glViewport(0, 0, width, height);
		// Setup orthographic projection
		gl.glMatrixMode(GL10.GL_PROJECTION); // Activate Projection Matrix
		gl.glLoadIdentity(); // Load Identity Matrix
		gl.glOrthof(0, width, 0, height, 500.0f, -500.0f);

		// Save width and height
		// this.width = width; // Save Current Width
		// this.height = height; // Save Current Height

		gl.glMatrixMode(GL10.GL_MODELVIEW); // Select model-view matrix
		gl.glLoadIdentity(); // Reset
	
	}

	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public void paused(){
		back_position = position;
		this.setPosition(6);
	}
	
	public void resume(){
		this.setPosition(back_position);
	}
	
	public void pilihJawaban(int pilihan){
		if(position == 3){
			if(penjumlahan.pilihJawaban(pilihan)){
				penjumlahan.next();
			} else {
				this.gameOver();
			}
		} else if(position == 4){
			
		} else if(position == 5){
			
		}
	}
}