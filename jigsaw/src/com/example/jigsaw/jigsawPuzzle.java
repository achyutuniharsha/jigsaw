package com.example.jigsaw;

import android.graphics.Bitmap;
import android.util.Log;

public class jigsawPuzzle {
	 public int pieces,size,n,counter=0;
	 Bitmap[] bmChunks;
	 int[] dimsx,dimsy,xdims,ydims;
	 public boolean[] placed;
	 int totalBitmaps=0;
	 public jigsawPuzzle(int n){
		 this.n=n;
		 bmChunks=new Bitmap[n*n];
		 placed=new boolean[n*n];
		 xdims=new int[n*n];
		 ydims=new int[n*n];
		 dimsx=new int[n*n];
		 dimsy=new int[n*n];
		 totalBitmaps=n*n;
		 
	 }
	public void  splitIntoPieces(Bitmap bm,int pad){
		size=bm.getWidth()/n;
		int x=0,y=0;
		try{
		for( x=0;x<n;x++){
			for( y=0;y<n;y++){
				bmChunks[counter]=Bitmap.createBitmap(bm, x*size, y*size,
		                size, size);
				xdims[counter]=(x)* size+pad;
			    ydims[counter]=(y)* size;
				dimsx[counter]=(x+1)* size+pad;
			    dimsy[counter]=(y+1)* size;
				
				Log.i("splitIntoPieces"+counter,"Xdims"+xdims[counter]+"Ydims"+ydims[counter]);
				counter++;

			}
			
		}}catch(Exception e){Log.i("error", "exception occured"+e);}
		
		
		
		
		
	}
	
	
}
