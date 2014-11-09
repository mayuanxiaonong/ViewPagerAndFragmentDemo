package com.cyber.viewpagerandfragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Lay3Fragment extends Fragment {
	public static Lay3Fragment instance() {
		Lay3Fragment fragment = new Lay3Fragment();
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
		View view = inflater.inflate(R.layout.activity_lay3, container, false);
		return view;
	}	
	

}
