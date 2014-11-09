package com.cyber.viewpagerandfragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Lay2Fragment extends Fragment {
	public static Lay2Fragment instance() {
		Lay2Fragment fragment = new Lay2Fragment();
		fragment.setArguments(new Bundle());
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_lay2, container, false);
		return view;
	}	
	

}
