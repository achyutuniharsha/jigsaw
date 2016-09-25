package com.example.jigsaw;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

class ViewPort extends View {
    List<Layer> layers = new LinkedList<Layer>();
    int[] ids ;
	public static Bitmap bm,bg,frame,gameBm;
	Paint paint;
	Rect srcRect=new Rect();
	Rect dstRect=new Rect();
	public static jigsawPuzzle xyz;
	public static int vWidth,vHeight;
	public static int pad,pieces;
	public static int pWidth,pHeight;
	public static int GAMEBM_WIDTH,GAMEBMHEIGHT;
	public static int x,y;
	int count;
	int right_display=0;
	boolean right_first=true,left_first=true;
	public static int  countPlaced=0;

 public ViewPort(Context context,AttributeSet attrs) {
        super(context,attrs);
        puzzleActivity.ISCOMPLETED=false;
        ViewPort.pieces=puzzleActivity.pieces;
		pad=(int)(Math.floor(vWidth/4)/10)*10;
		//Log.i("********pad"+pad,"actual width"+vWidth+"actual Height"+vHeight);
		pWidth=(int)Math.floor((vWidth-2*pad)/10)*10;
		pHeight=pWidth;
		
		
		
		//Log.i("********pad"+pad,"play width"+pWidth+"play Height"+pHeight);

			
		xyz=new jigsawPuzzle(pieces);
        paint=new Paint();
		
		bm=BitmapFactory.decodeResource(getResources(), R.drawable.genius);
		bg=BitmapFactory.decodeResource(getResources(),R.drawable.bg);
		frame=BitmapFactory.decodeResource(getResources(),R.drawable.frame);
		gameBm=MainActivity.selectedBm;
		gameBm=scaleIt(gameBm);
		GAMEBM_WIDTH=gameBm.getWidth();GAMEBMHEIGHT=gameBm.getHeight();
		
		xyz.splitIntoPieces(gameBm,pad);
		right_display=pad+pWidth;
		count=0;countPlaced=0;
        for (int i = 0; i < pieces*pieces; i++) {
        	getCoordinates(xyz.bmChunks[i]);
            Layer l = new Layer(context, this, xyz.bmChunks[i],x,y,xyz.xdims[i],xyz.ydims[i],xyz.dimsx[i],xyz.dimsy[i]);
            layers.add(l);
        }
    }
 @Override
 protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
     super.onSizeChanged(xNew, yNew, xOld, yOld);

     vWidth = xNew;
     vHeight = yNew;
}
    private void getCoordinates(Bitmap bitmap) {
		// TODO Auto-generated method stub

		//for displaying left side
    	Log.i("Count:"+count,"Total Bitmaps:"+xyz.totalBitmaps);
		if(count<(int)xyz.totalBitmaps/2){
			if(left_first){x=0;y=0;left_first=false;return;}

			if(count==1){x=bitmap.getWidth();y=0;		count++;	return;}
			
		if(count%2==0){
			x=0;y+=bitmap.getHeight();
			
		}	else{x=bitmap.getWidth();}
			
		}
		// for displaying right side
		else{
			if(right_first){x=right_display;y=0;right_first=false;return;}
			if(count>20){count=0;left_first=true;right_first=true;}
			
			if(count%2!=0){
    			x=right_display;y+=bitmap.getHeight();
    			
    		}	else{x=right_display+bitmap.getWidth();}
			
			
			
		}
		count++;

	}
	private Bitmap scaleIt(Bitmap par) {
		// TODO Auto-generated method stub
		par=Bitmap.createScaledBitmap(par, pWidth,pHeight, false);
		return par;
	}
    public void drawScaledImage(Canvas canvas,Bitmap bm, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
    	
    	
   	    srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        
        
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;
        
        
        canvas.drawBitmap(bm, srcRect, dstRect,null);
      
    }
    @Override
    protected void onDraw(Canvas canvas) {
		drawScaledImage(canvas,frame, pad,0,pWidth,pHeight,0,0, frame.getWidth(), frame.getHeight());


        for (Layer l : layers) {
        	
        	l.draw(canvas);
        }
        if(countPlaced==pieces*pieces){
        	//case for game completed
        	puzzleActivity.ISCOMPLETED=true;
        	((puzzleActivity)getContext()).gameCompleted();
        }
    }

    private Layer target;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            target = null;
            for (int i = layers.size() - 1; i >= 0; i--) {
                Layer l = layers.get(i);
                if (l.contains(event)&&!l.placed) {
                    target = l;
                    layers.remove(l);
                    layers.add(l);
                    invalidate();
                    break;
                }
            }
        }
      /*  if (event.getAction() == MotionEvent.ACTION_UP) {
            target = null;int count=layers.size()-1;
            for (int i = layers.size() - 1; i >= 0; i--) {
                Layer l = layers.get(i);
                if(l.placed){count--;continue;}
                if(count==i)ViewPort.completed=true;
            }
        }*/
        if (target == null) {
            return false;
        }
        return target.onTouchEvent(event);
    }
}

