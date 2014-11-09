package com.cyber.viewpagerandfragmentdemo;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
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

public class MainActivity extends FragmentActivity implements OnClickListener {
	
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

		tv1 = (TextView) findViewById(R.id.tab1);
		tv1.setOnClickListener(this);
		textViews.add(tv1);
		tv2 = (TextView) findViewById(R.id.tab2);
		tv2.setOnClickListener(this);
		textViews.add(tv2);
		tv3 = (TextView) findViewById(R.id.tab3);
		tv3.setOnClickListener(this);
		textViews.add(tv3);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels / AppUtil.TAB_COUNT;
		int bw = BitmapUtil.getBitmap(getResources(), R.drawable.cursor).getWidth();
		Matrix matrix = new Matrix();
		matrix.postTranslate((width - bw) / 2, 0);
		cursor = (ImageView) findViewById(R.id.cursor);
		cursor.setImageMatrix(matrix);
		
		List<Fragment> list = new ArrayList<Fragment>();
		list.add(Lay1Fragment.instance());
		list.add(Lay2Fragment.instance());
		list.add(Lay3Fragment.instance());
		pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), list));
		pager.setCurrentItem(0);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab1:
			pager.setCurrentItem(0);
			break;
		case R.id.tab2:
			pager.setCurrentItem(1);
			break;
		case R.id.tab3:
			pager.setCurrentItem(2);
			break;
		}
	}

	public class MyPagerAdapter extends PagerAdapter {
		
		private List<View> list;
		
		public MyPagerAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View container, Object object) {
			return container == object;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(list.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(list.get(position), 0);
			return list.get(position);
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
