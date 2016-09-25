package com.example.jigsaw;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PagerAdapter extends FragmentPagerAdapter {

	public PagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0){
		
		
		case 0 : Log.i("case","0");return new logoFrag();
		case 1: Log.i("case","1");return new menuFrag();
		
		case 2: Log.i("case","2");return new SelectFrag();
		
		
		}
		return null;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
