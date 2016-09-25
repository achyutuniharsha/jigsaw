package com.example.jigsaw;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnDrawListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class puzzleActivity extends Activity   {
	public static enum Type{
		noob,inter,genius
	}
	public static Type type=Type.noob;
	public static final int noob=3;
	public static final int inter=4;
	public static final int genius=5;
	ImageView v;
	ViewPort view;
	jigsawPuzzle ob;
	RelativeLayout rl;
	public static int pieces;
	public static boolean ISCOMPLETED;
	@Override
	 protected void onResume(){
		super.onResume();
		 setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		 
	 }
	@Override
	protected void onPause(){
		super.onPause();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		if(type==Type.noob)pieces=noob;
		else if(type==Type.inter)pieces=inter;
		else pieces=genius;
		
		
		setContentView(R.layout.puzzle);
		/*
		rl=(RelativeLayout) findViewById(R.layout.game_completed);
		rl.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				changeActivity();
				return false;
			}
		});*/
		
	
	}
	protected void changeActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this,MainActivity.class);
		//intent.putExtra("padapter", MainActivity.selectedLevel);
		
		startActivity(intent);
	}
	public void gameCompleted(){
		
		setContentView(R.layout.game_completed);
		v=(ImageView)findViewById(R.id.gamebm);
		v.setImageBitmap(ViewPort.gameBm);
		rl=(RelativeLayout) findViewById(R.layout.game_completed);
		
	}
	public void previewClick(View v){
		Intent intent=new Intent(puzzleActivity.this,previewbm.class);
		startActivity(intent);
		
	}
	public void taptoplay(View v){
		changeActivity();

	}
	

	
	
	

}
