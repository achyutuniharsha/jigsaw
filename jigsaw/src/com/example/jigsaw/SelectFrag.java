package com.example.jigsaw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SelectFrag extends Fragment {
	
	View v;
	GridView gridview;
	Context c;
	Activity a;Bitmap b;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		  v = inflater.inflate(R.layout.select_frag, container, false); 
	    gridview = (GridView) v.findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(a));
	  

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v,
	                int position, long id) {
	        	if(position==0){
	        			 b=BitmapFactory.decodeResource(getResources(),  R.drawable.sample_1)     ; 		
	        	}else if(position==1){
       			 b=BitmapFactory.decodeResource(getResources(),  R.drawable.sample_2)     ; 		

	        	}else if(position==2){
       			 b=BitmapFactory.decodeResource(getResources(),  R.drawable.sample_3)     ; 		

	        	}/*else if(position==3){
	        		
       			 b=BitmapFactory.decodeResource(getResources(),  R.drawable.sample_4)     ; 		

	        	}else if(position==4){
       			 b=BitmapFactory.decodeResource(getResources(),  R.drawable.sample_5)     ; 		

	        	}else if(position==5){
	        		
       			 b=BitmapFactory.decodeResource(getResources(),  R.drawable.sample_6)     ; 		

	        	}*/
	        	((MainActivity)getActivity()).changeActivity(b);
	           
	        }
	    });
		return v;
		
	}
	@Override
	public void onAttach(Context context) {
	    super.onAttach(context);

	    if (context instanceof Activity){
	        a=(Activity) context;
	    }

	}
}
