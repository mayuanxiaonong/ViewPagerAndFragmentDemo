package com.cyber.viewpagerandfragmentdemo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

public class UpdateThread extends Thread {

	private Context context;

	public UpdateThread(Context context) {
		this.context = context;
	}

	String mSavePath;

	@Override
	public void run() {
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			File path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			File file = new File(path, "ViewPagerAndFragmentDemo.apk");
			if (file.exists()) {
				file.delete();
			}
			URL url = new URL(
					"http://192.168.1.88:8080/update/ViewPagerAndFragmentDemo.apk");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.connect();
			is = conn.getInputStream();

			fos = new FileOutputStream(file);
			byte buf[] = new byte[4096];
			int n;
			while ((n = is.read(buf)) != -1) {
				fos.write(buf, 0, n);
			}
			fos.close();
			is.close();

			Intent i = new Intent(Intent.ACTION_VIEW);
			Uri uri = Uri.fromFile(file);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setDataAndType(uri, "application/vnd.android.package-archive");
			context.startActivity(i);
		} catch (IOException e) {
			Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}

	}
}
