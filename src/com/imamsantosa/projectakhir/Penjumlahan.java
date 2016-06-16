package com.imamsantosa.projectakhir;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;


public class Penjumlahan{
	private boolean is_first = true;
	private Context context;
	private GamePlay gameplay;
	private int score = 0;
	private int a, b, jawaban, index_jawaban;
	private int[] pilihan = new int[3];
	
	public Penjumlahan(Context context, GamePlay gameplay) {
		this.context = context;
		this.gameplay = gameplay;
	}
	
	public void draw(GL10 gl){
		if(is_first)
			first();
		this.loadJawaban(gl);
	}
	
	public void first(){
		this.generateSoal();
		this.generateJawaban();
		is_first = false;
	}
	
	public void next(){
		score++;
		this.generateSoal();
		this.generateJawaban();
	}
	
	public void gameOver(){
		score = 0;
		is_first = true;
	}
	
	public boolean pilihJawaban(int pilih){
		if(pilihan[pilih] == jawaban){
			return true;
		}
		
		return false;
	}
	
	private void generateSoal(){
		a = (int) (Math.random()*50);
		b = (int) (Math.random()*50);
		jawaban = a+b;
	}
	
	private void generateJawaban(){
		index_jawaban = (int) (Math.random()*3);
		pilihan[index_jawaban] = jawaban;
		for(int i=0; i<=2; i++){
			if(i != index_jawaban){
				pilihan[i] = generatePilihan();
			}
		}
	}
	
	private int generatePilihan(){
		int pilihan = (int) (Math.random()*100);
		if(pilihan != jawaban){
			return pilihan;
		}
		
		return generatePilihan();
	}
	
	private void loadJawaban(GL10 gl){
		gameplay.loadJawaban(gl, context, pilihan[0], pilihan[1], pilihan[2]);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}
}
