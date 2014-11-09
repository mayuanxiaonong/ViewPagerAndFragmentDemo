package com.cyber.viewpagerandfragmentdemo.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
	
	public static Bitmap getBitmap(Resources res, int id) {
		return BitmapFactory.decodeResource(res, id);
	}

}
