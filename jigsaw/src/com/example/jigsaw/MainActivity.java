package com.example.jigsaw;




import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	 public static ViewPager viewpager;public static Context c;
	public static Bitmap selectedBm;
	 GridView gridview;
	 private final int GALLERY_ACTIVITY_CODE=200;
	 private final int RESULT_CROP = 400;
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
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		ViewPort.vHeight = size.y;
		ViewPort.vWidth = size.x;
		viewpager=(ViewPager)findViewById(R.id.pager);
		  
		final PagerAdapter padapter= new PagerAdapter(getSupportFragmentManager());
		viewpager.setAdapter(padapter);
		MainActivity.c=getApplicationContext();
		if(puzzleActivity.ISCOMPLETED){
			puzzleActivity.ISCOMPLETED=false;
		String str = getIntent().getStringExtra("padapter");
		viewpager.setCurrentItem(2);
		
		}
		
		
		


	}
	
	

	public void playClick(View v){
		viewpager.setCurrentItem(1);
		
	}
	

	public void noobClick(View v){
		
		puzzleActivity.type=puzzleActivity.Type.noob;
		viewpager.setCurrentItem(2);
		//changeActivity();
	}

	public void interClick(View v){
		
		puzzleActivity.type=puzzleActivity.Type.inter;//changeActivity();
		viewpager.setCurrentItem(2);
	}
	public void geniusClick(View v){
		
		puzzleActivity.type=puzzleActivity.Type.genius;//changeActivity();
		viewpager.setCurrentItem(2);
	}
	public void changeActivity(Bitmap b){
		MainActivity.selectedBm=b;
		Intent intent = new Intent();
		intent.setClass(this,puzzleActivity.class);
		
		startActivity(intent);
	}
	public void openGallery(View v){
		
		Intent gallery_Intent = new Intent(getApplicationContext(), Gallery.class);
        startActivityForResult(gallery_Intent, GALLERY_ACTIVITY_CODE);
        
	}
	 	@Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if (requestCode == GALLERY_ACTIVITY_CODE) {
	             if(resultCode == Activity.RESULT_OK){  
	                String  picturePath = data.getStringExtra("picturePath");  
	                 //perform Crop on the Image Selected from Gallery
	                 performCrop(picturePath);
	             }
	        }

	        if (requestCode == RESULT_CROP ) {
	             if(resultCode == Activity.RESULT_OK){  
	                 Bundle extras = data.getExtras();
	                 Bitmap selectedBitmap = extras.getParcelable("data");
	                 // Set The Bitmap Data To ImageView
	                 changeActivity(selectedBitmap);
	             }
	        }
	    }

	    private void performCrop(String picUri) {
	        try {
	            //Start Crop Activity

	            Intent cropIntent = new Intent("com.android.camera.action.CROP");
	            // indicate image type and Uri
	            File f = new File(picUri);
	            Uri contentUri = Uri.fromFile(f);

	            cropIntent.setDataAndType(contentUri, "image/*");
	            // set crop properties
	            cropIntent.putExtra("crop", "true");
	            // indicate aspect of desired crop
	            cropIntent.putExtra("aspectX", 1);
	            cropIntent.putExtra("aspectY", 1);
	            // indicate output X and Y
	            cropIntent.putExtra("outputX", 280);
	            cropIntent.putExtra("outputY", 280);

	            // retrieve data on return
	            cropIntent.putExtra("return-data", true);
	            // start the activity - we handle returning in onActivityResult
	            startActivityForResult(cropIntent, RESULT_CROP);
	        }
	        catch (ActivityNotFoundException anfe) {
	            // display an error message
	            String errorMessage = "your device doesn't support the crop action!";
	            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
	            toast.show();
	        }
	    }   
	
	
	
	

	
}
