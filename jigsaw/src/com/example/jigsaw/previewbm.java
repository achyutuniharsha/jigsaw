package com.example.jigsaw;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class previewbm extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.previewbm);
		ImageView v=(ImageView)findViewById(R.id.preview);
		v.setImageBitmap(ViewPort.gameBm);

	}
}
