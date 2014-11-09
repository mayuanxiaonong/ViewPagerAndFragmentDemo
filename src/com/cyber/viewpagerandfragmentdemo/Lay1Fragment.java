package com.cyber.viewpagerandfragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Lay1Fragment extends Fragment {
	
	private Button button;
	
	public static Lay1Fragment instance() {
		Lay1Fragment fragment = new Lay1Fragment();
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
		View view = inflater.inflate(R.layout.activity_lay1, container, false);
		button = (Button) view.findViewById(R.id.lay1_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "sjlkdfajlksafj", Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

}
