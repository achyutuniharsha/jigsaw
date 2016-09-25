package com.example.jigsaw;



import com.example.jigsaw.MoveGestureDetector.SimpleOnMoveGestureListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

class Layer {
    Matrix matrix = new Matrix();
    Matrix inverse = new Matrix();
    RectF bounds;
    View parent;
    Bitmap bitmap;
    MoveGestureDetector mgd;
    ScaleGestureDetector sgd;
  
    int xdims,ydims,dimsx,dimsy;
    boolean placed=false,locked;

    public Layer(Context ctx, View p, Bitmap b,float x,float y, int xd, int yd, int dx, int dy) {
        parent = p;
        bitmap = b;
        bounds = new RectF(0, 0, b.getWidth(), b.getHeight());
        mgd = new MoveGestureDetector(ctx, mgl);
        
        matrix.postTranslate( x,y);
        xdims=xd;
        ydims=yd;
        dimsx=dx;
        dimsy=dy;
        placed=false;locked=false;
    
    }

    

	public boolean contains(MotionEvent event) {
        matrix.invert(inverse);
        float[] pts = {event.getX(), event.getY()};
        inverse.mapPoints(pts);
        if (!bounds.contains(pts[0], pts[1])) {
            return false;
        }
        return Color.alpha(bitmap.getPixel((int) pts[0], (int) pts[1])) != 0;
    }

    public boolean onTouchEvent(MotionEvent event) {
       
    	   mgd.onTouchEvent(event);
    	  
    	   
       if(event.getAction()==MotionEvent.ACTION_UP){
    	  
        int x=(int) event.getX(),y=(int) event.getY();
        if(x<=dimsx&&x>=xdims&&y<=dimsy&&y>=ydims&&!placed){
     	   

        	locked=true;
        	
        	parent.invalidate();
        	

        }
        Log.i("x"+x,"y"+y);
    	Log.i("xdim"+xdims,"ydims"+ydims);}
       
        
       
       return true;
    }

    public void draw(Canvas canvas) {
    	if(locked){
    		matrix.reset();
    		matrix.postTranslate(xdims, ydims);
    		locked=false;placed=true;ViewPort.countPlaced++;
    	}
    	canvas.drawBitmap(bitmap, matrix, null);
    }
   

    SimpleOnMoveGestureListener mgl = new SimpleOnMoveGestureListener() {
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            PointF delta = detector.getFocusDelta();
            matrix.postTranslate(delta.x, delta.y);          
            parent.invalidate();
            return true;
        }
    };

   
    
    
}