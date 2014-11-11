package com.cyber.viewpagerandfragmentdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyber.viewpagerandfragmentdemo.util.AppUtil;
import com.cyber.viewpagerandfragmentdemo.util.BitmapUtil;
import com.cyber.viewpagerandfragmentdemo.util.UpdateThread;

public class MainActivity extends FragmentActivity implements OnClickListener {
	
	private TextView tmp;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private List<TextView> textViews = new ArrayList<TextView>();
	private ViewPager pager;
	private ImageView cursor; // ¶¯»­Í¼Æ¬
	private int index = 0; // µ±Ç°Ò³¿¨±àºÅ
	private int width = 0; // ¿¨Æ¬¿í¶È

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tmp = (TextView) findViewById(R.id.tmp);
		tmp.setOnClickListener(this);
		tv1 = (TextView) findViewById(R.id.tab_label_access_list);
		tv1.setOnClickListener(this);
		textViews.add(tv1);
		tv2 = (TextView) findViewById(R.id.tab_label_2);
		tv2.setOnClickListener(this);
		textViews.add(tv2);
		tv3 = (TextView) findViewById(R.id.tab_label_3);
		tv3.setOnClickListener(this);
		textViews.add(tv3);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels / AppUtil.TAB_COUNT;
		int bw = BitmapUtil.getBitmap(getResources(), R.drawable.cursor).getWidth();
		Matrix matrix = new Matrix();
		matrix.postTranslate((width - bw) / 2, 0);
		cursor = (ImageView) findViewById(R.id.image_cursor);
		cursor.setImageMatrix(matrix);
		
		List<Fragment> list = new ArrayList<Fragment>();
		list.add(new AccessListFragment());
		list.add(new Lay2Fragment());
		list.add(new Lay3Fragment());
		pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), list));
		pager.setCurrentItem(0);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_label_access_list:
			pager.setCurrentItem(0);
			break;
		case R.id.tab_label_2:
			pager.setCurrentItem(1);
			break;
		case R.id.tab_label_3:
			pager.setCurrentItem(2);
			break;
		case R.id.tmp:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(null).setMessage("Update ?");
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					new UpdateThread(MainActivity.this).start();
				}
			});
			builder.setNegativeButton("No", null);
			builder.create().show();
			break;
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int page) {
			Animation animation = new TranslateAnimation(width * index, width * page, 0, 0);
			animation.setFillAfter(true);
			animation.setDuration(200);
			cursor.startAnimation(animation);
			index = page;
			for(TextView v : textViews) {
				v.setTextColor(getResources().getColor(android.R.color.black));
			}
			textViews.get(index).setTextColor(getResources().getColor(android.R.color.holo_blue_light));
		}
		
	}
	
}
