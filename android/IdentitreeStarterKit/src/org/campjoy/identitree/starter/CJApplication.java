package org.campjoy.identitree.starter;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class CJApplication extends Application{

		private static final String TAG = CJApplication.class.getSimpleName();

		private PackageInfo packageInfo;

	
		private static boolean activityVisible = true;
		private FragmentActivity currentActivity;

			
		@Override
		public void onCreate() {

			super.onCreate();

			getMyPackageInfo();
		}

		public static boolean isActivityVisible() {
			return activityVisible;
		}

		public static void activityResumed() {
			activityVisible = true;
		}

		public static void activityPaused() {
			activityVisible = false;
		}

		public FragmentActivity getCurrentActivity() {
			return currentActivity;
		}

		public void setCurrentActivity(FragmentActivity currentActivity) {
			this.currentActivity = currentActivity;
		}

	

		public boolean isNetworkAvailable() {
			ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = conMan.getActiveNetworkInfo();

			if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()
					|| !activeNetworkInfo.isConnectedOrConnecting()) {
				return false;
			}
			return true;
		}

	

		private PackageInfo getMyPackageInfo() {

			if (packageInfo == null) {

				try {
					packageInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);

				} catch (NameNotFoundException e) {
					Log.e(TAG, "can't get my package info: " + e.getLocalizedMessage(), e);
				}
			}
			return packageInfo;
		}

		public String getVersionName() {

			PackageInfo p = getMyPackageInfo();

			return p.versionName;
		}
}
