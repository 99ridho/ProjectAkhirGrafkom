package com.imamsantosa.projectakhir;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class GamePlay {
	private int height, width;
	private FloatBuffer vertexBuffer;	// buffer holding the vertices
	private float vertices[] = {
			 0.0f, 0.0f,  0.0f,		// V1 - bottom left
			 0.0f,  1.0f,  0.0f,		// V2 - top left
			 1.0f, 0.0f,  0.0f,		// V3 - bottom right
			 1.0f,  1.0f,  0.0f			// V4 - top right
	};

	private FloatBuffer textureBuffer;	// buffer holding the texture coordinates
	private float texture[] = {    		
			// Mapping cordinates for the vertices
			0.0f, 1.0f,		// top left		(V2)
			0.0f, 0.0f,		// bottom left	(V1)
			1.0f, 1.0f,	// top right	(V4)
			1.0f, 0.0f,		// bottom right	(V3)
	};
	
	private int[] jawaban_c = {
			R.drawable.blue_0,
			R.drawable.blue_1,
			R.drawable.blue_2,
			R.drawable.blue_3,
			R.drawable.blue_4,
			R.drawable.blue_5,
			R.drawable.blue_6,
			R.drawable.blue_7,
			R.drawable.blue_8,
			R.drawable.blue_9,
			R.drawable.blue_10,
			R.drawable.blue_11,
			R.drawable.blue_12,
			R.drawable.blue_13,
			R.drawable.blue_14,
			R.drawable.blue_15,
			R.drawable.blue_16,
			R.drawable.blue_17,
			R.drawable.blue_18,
			R.drawable.blue_19,
			R.drawable.blue_20,
			R.drawable.blue_21,
			R.drawable.blue_22,
			R.drawable.blue_23,
			R.drawable.blue_24,
			R.drawable.blue_25,
			R.drawable.blue_26,
			R.drawable.blue_27,
			R.drawable.blue_28,
			R.drawable.blue_29,
			R.drawable.blue_30,
			R.drawable.blue_31,
			R.drawable.blue_32,
			R.drawable.blue_33,
			R.drawable.blue_34,
			R.drawable.blue_35,
			R.drawable.blue_36,
			R.drawable.blue_37,
			R.drawable.blue_38,
			R.drawable.blue_39,
			R.drawable.blue_40,
			R.drawable.blue_41,
			R.drawable.blue_42,
			R.drawable.blue_43,
			R.drawable.blue_44,
			R.drawable.blue_45,
			R.drawable.blue_46,
			R.drawable.blue_47,
			R.drawable.blue_48,
			R.drawable.blue_49,
			R.drawable.blue_50,
			R.drawable.blue_51,
			R.drawable.blue_52,
			R.drawable.blue_53,
			R.drawable.blue_54,
			R.drawable.blue_55,
			R.drawable.blue_56,
			R.drawable.blue_57,
			R.drawable.blue_58,
			R.drawable.blue_59,
			R.drawable.blue_60,
			R.drawable.blue_61,
			R.drawable.blue_62,
			R.drawable.blue_63,
			R.drawable.blue_64,
			R.drawable.blue_65,
			R.drawable.blue_66,
			R.drawable.blue_67,
			R.drawable.blue_68,
			R.drawable.blue_69,
			R.drawable.blue_70,
			R.drawable.blue_71,
			R.drawable.blue_72,
			R.drawable.blue_73,
			R.drawable.blue_74,
			R.drawable.blue_75,
			R.drawable.blue_76,
			R.drawable.blue_77,
			R.drawable.blue_78,
			R.drawable.blue_79,
			R.drawable.blue_80,
			R.drawable.blue_81,
			R.drawable.blue_82,
			R.drawable.blue_83,
			R.drawable.blue_84,
			R.drawable.blue_85,
			R.drawable.blue_86,
			R.drawable.blue_87,
			R.drawable.blue_88,
			R.drawable.blue_89,
			R.drawable.blue_90,
			R.drawable.blue_91,
			R.drawable.blue_92,
			R.drawable.blue_93,
			R.drawable.blue_94,
			R.drawable.blue_95,
			R.drawable.blue_96,
			R.drawable.blue_97,
			R.drawable.blue_98,
			R.drawable.blue_99,
			R.drawable.blue_100
	};
	
	private int[] jawaban_a = {
			R.drawable.red_0,
			R.drawable.red_1,
			R.drawable.red_2,
			R.drawable.red_3,
			R.drawable.red_4,
			R.drawable.red_5,
			R.drawable.red_6,
			R.drawable.red_7,
			R.drawable.red_8,
			R.drawable.red_9,
			R.drawable.red_10,
			R.drawable.red_11,
			R.drawable.red_12,
			R.drawable.red_13,
			R.drawable.red_14,
			R.drawable.red_15,
			R.drawable.red_16,
			R.drawable.red_17,
			R.drawable.red_18,
			R.drawable.red_19,
			R.drawable.red_20,
			R.drawable.red_21,
			R.drawable.red_22,
			R.drawable.red_23,
			R.drawable.red_24,
			R.drawable.red_25,
			R.drawable.red_26,
			R.drawable.red_27,
			R.drawable.red_28,
			R.drawable.red_29,
			R.drawable.red_30,
			R.drawable.red_31,
			R.drawable.red_32,
			R.drawable.red_33,
			R.drawable.red_34,
			R.drawable.red_35,
			R.drawable.red_36,
			R.drawable.red_37,
			R.drawable.red_38,
			R.drawable.red_39,
			R.drawable.red_40,
			R.drawable.red_41,
			R.drawable.red_42,
			R.drawable.red_43,
			R.drawable.red_44,
			R.drawable.red_45,
			R.drawable.red_46,
			R.drawable.red_47,
			R.drawable.red_48,
			R.drawable.red_49,
			R.drawable.red_50,
			R.drawable.red_51,
			R.drawable.red_52,
			R.drawable.red_53,
			R.drawable.red_54,
			R.drawable.red_55,
			R.drawable.red_56,
			R.drawable.red_57,
			R.drawable.red_58,
			R.drawable.red_59,
			R.drawable.red_60,
			R.drawable.red_61,
			R.drawable.red_62,
			R.drawable.red_63,
			R.drawable.red_64,
			R.drawable.red_65,
			R.drawable.red_66,
			R.drawable.red_67,
			R.drawable.red_68,
			R.drawable.red_69,
			R.drawable.red_70,
			R.drawable.red_71,
			R.drawable.red_72,
			R.drawable.red_73,
			R.drawable.red_74,
			R.drawable.red_75,
			R.drawable.red_76,
			R.drawable.red_77,
			R.drawable.red_78,
			R.drawable.red_79,
			R.drawable.red_80,
			R.drawable.red_81,
			R.drawable.red_82,
			R.drawable.red_83,
			R.drawable.red_84,
			R.drawable.red_85,
			R.drawable.red_86,
			R.drawable.red_87,
			R.drawable.red_88,
			R.drawable.red_89,
			R.drawable.red_90,
			R.drawable.red_91,
			R.drawable.red_92,
			R.drawable.red_93,
			R.drawable.red_94,
			R.drawable.red_95,
			R.drawable.red_96,
			R.drawable.red_97,
			R.drawable.red_98,
			R.drawable.red_99,
			R.drawable.red_100
	};
	
	private int[] jawaban_b = {
			R.drawable.yellow_0,
			R.drawable.yellow_1,
			R.drawable.yellow_2,
			R.drawable.yellow_3,
			R.drawable.yellow_4,
			R.drawable.yellow_5,
			R.drawable.yellow_6,
			R.drawable.yellow_7,
			R.drawable.yellow_8,
			R.drawable.yellow_9,
			R.drawable.yellow_10,
			R.drawable.yellow_11,
			R.drawable.yellow_12,
			R.drawable.yellow_13,
			R.drawable.yellow_14,
			R.drawable.yellow_15,
			R.drawable.yellow_16,
			R.drawable.yellow_17,
			R.drawable.yellow_18,
			R.drawable.yellow_19,
			R.drawable.yellow_20,
			R.drawable.yellow_21,
			R.drawable.yellow_22,
			R.drawable.yellow_23,
			R.drawable.yellow_24,
			R.drawable.yellow_25,
			R.drawable.yellow_26,
			R.drawable.yellow_27,
			R.drawable.yellow_28,
			R.drawable.yellow_29,
			R.drawable.yellow_30,
			R.drawable.yellow_31,
			R.drawable.yellow_32,
			R.drawable.yellow_33,
			R.drawable.yellow_34,
			R.drawable.yellow_35,
			R.drawable.yellow_36,
			R.drawable.yellow_37,
			R.drawable.yellow_38,
			R.drawable.yellow_39,
			R.drawable.yellow_40,
			R.drawable.yellow_41,
			R.drawable.yellow_42,
			R.drawable.yellow_43,
			R.drawable.yellow_44,
			R.drawable.yellow_45,
			R.drawable.yellow_46,
			R.drawable.yellow_47,
			R.drawable.yellow_48,
			R.drawable.yellow_49,
			R.drawable.yellow_50,
			R.drawable.yellow_51,
			R.drawable.yellow_52,
			R.drawable.yellow_53,
			R.drawable.yellow_54,
			R.drawable.yellow_55,
			R.drawable.yellow_56,
			R.drawable.yellow_57,
			R.drawable.yellow_58,
			R.drawable.yellow_59,
			R.drawable.yellow_60,
			R.drawable.yellow_61,
			R.drawable.yellow_62,
			R.drawable.yellow_63,
			R.drawable.yellow_64,
			R.drawable.yellow_65,
			R.drawable.yellow_66,
			R.drawable.yellow_67,
			R.drawable.yellow_68,
			R.drawable.yellow_69,
			R.drawable.yellow_70,
			R.drawable.yellow_71,
			R.drawable.yellow_72,
			R.drawable.yellow_73,
			R.drawable.yellow_74,
			R.drawable.yellow_75,
			R.drawable.yellow_76,
			R.drawable.yellow_77,
			R.drawable.yellow_78,
			R.drawable.yellow_79,
			R.drawable.yellow_80,
			R.drawable.yellow_81,
			R.drawable.yellow_82,
			R.drawable.yellow_83,
			R.drawable.yellow_84,
			R.drawable.yellow_85,
			R.drawable.yellow_86,
			R.drawable.yellow_87,
			R.drawable.yellow_88,
			R.drawable.yellow_89,
			R.drawable.yellow_90,
			R.drawable.yellow_91,
			R.drawable.yellow_92,
			R.drawable.yellow_93,
			R.drawable.yellow_94,
			R.drawable.yellow_95,
			R.drawable.yellow_96,
			R.drawable.yellow_97,
			R.drawable.yellow_98,
			R.drawable.yellow_99,
			R.drawable.yellow_100
	};
	
	private int[] background = { 
			R.drawable.background_play
	};
	
	private int[] timing ={
			R.drawable.timing,
			R.drawable.timing_danger
	};
	private int[] texture_jawaban_a = new int[1];
	private int[] texture_jawaban_b = new int[1];
	private int[] texture_jawaban_c = new int[1];
	private int[] texture_background = new int[1];
	private int[] texture_timing = new int[1];
	private int[] texture_timing_danger = new int[1];
	
	public GamePlay() {
		// a float has 4 bytes so we allocate for each coordinate 4 bytes
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		
		// allocates the memory from the byte buffer
		vertexBuffer = byteBuffer.asFloatBuffer();
		
		// fill the vertexBuffer with the vertices
		vertexBuffer.put(vertices);
		
		// set the cursor position to the beginning of the buffer
		vertexBuffer.position(0);
		
		byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuffer.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
	}
	
	public void draw(GL10 gl, int height, int width){
		this.height = height;
		this.width = width;
		
		gl.glPushMatrix(); 
			gl.glScalef(this.width, this.height, 1.0f);
//			Log.d("z", width+" "+height);
			this.drawBackground(gl);
	    gl.glPopMatrix();
	    
	    gl.glPushMatrix(); 
	    	gl.glTranslatef(24.0f, 21.0f, 0.0f);
			gl.glScalef(380.0f, 105.0f, 1.0f);
			
	//		Log.d("z", width+" "+height);
			this.drawJawabanA(gl);
	    gl.glPopMatrix();
	    
	    gl.glPushMatrix(); 
	    	gl.glTranslatef(451.0f, 21.0f, 0.0f);
			gl.glScalef(380.0f, 105.0f, 1.0f);
			
	//		Log.d("z", width+" "+height);
			this.drawJawabanB(gl);
	    gl.glPopMatrix();
    
	    gl.glPushMatrix(); 
			gl.glTranslatef(871.0f, 21.0f, 0.0f);
			gl.glScalef(380.0f, 105.0f, 1.0f);
			
		//		Log.d("z", width+" "+height);
			this.drawJawabanC(gl);
		gl.glPopMatrix();
	}
	
	public void load(GL10 gl, Context context){
		this.loadBackground(gl, context);
		this.loadTiming(gl, context);
		this.loadTimingDanger(gl, context);
	}
	
	public void loadJawaban(GL10 gl, Context context, int a, int b, int c){
		this.loadJawabanA(gl, context, a);
		this.loadJawabanB(gl, context, b);
		this.loadJawabanC(gl, context, c);
	}
	
	private void drawJawabanA(GL10 gl) {
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_jawaban_a[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	private void drawJawabanB(GL10 gl) {
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_jawaban_b[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	private void drawJawabanC(GL10 gl) {
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_jawaban_c[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	private void drawBackground(GL10 gl){
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_background[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	public void drawTiming(GL10 gl){
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_timing[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	public void drawTimingDanger(GL10 gl){
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_timing_danger[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	private void loadJawabanA(GL10 gl, Context context, int angka) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				jawaban_a[angka]);

		// generate one texture pointer
		gl.glGenTextures(1, texture_jawaban_a, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_jawaban_a[0]);
		
		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Clean up
		bitmap.recycle();
	}
	
	private void loadJawabanB(GL10 gl, Context context, int angka) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				jawaban_b[angka]);

		// generate one texture pointer
		gl.glGenTextures(1, texture_jawaban_b, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_jawaban_b[0]);
		
		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Clean up
		bitmap.recycle();
	}
	
	private void loadJawabanC(GL10 gl, Context context, int angka) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				jawaban_c[angka]);

		// generate one texture pointer
		gl.glGenTextures(1, texture_jawaban_c, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_jawaban_c[0]);
		
		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Clean up
		bitmap.recycle();
	}
	
	private void loadBackground(GL10 gl, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				background[0]);

		// generate one texture pointer
		gl.glGenTextures(1, texture_background, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_background[0]);
		
		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Clean up
		bitmap.recycle();
	}

	private void loadTiming(GL10 gl, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				timing[0]);

		// generate one texture pointer
		gl.glGenTextures(1, texture_timing, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_timing[0]);
		
		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Clean up
		bitmap.recycle();
	}
	
	private void loadTimingDanger(GL10 gl, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				timing[1]);

		// generate one texture pointer
		gl.glGenTextures(1, texture_timing_danger, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_timing_danger[0]);
		
		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Clean up
		bitmap.recycle();
	}
}
