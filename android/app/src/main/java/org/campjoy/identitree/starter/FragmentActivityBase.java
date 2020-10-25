package org.campjoy.identitree.starter;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public abstract class FragmentActivityBase extends FragmentActivity {

	public CJApplication getCJApplication() {

		return (CJApplication) getApplicationContext();
	}

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);

		getCJApplication().setCurrentActivity(this);

		setContentView(R.layout.fragment_container);
	}
	
	@Override
	protected void onResume() {

		super.onResume();

		CJApplication.activityResumed();
	}

	@Override
	protected void onPause() {

		super.onPause();

		CJApplication.activityPaused();
	}

	public void startFragment(Fragment frag, String backStackTag) {

		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

		tx.replace(R.id.layout_fragment_container, frag);
		tx.addToBackStack(backStackTag);

		tx.commit();
	}
	
	public void loadFragment(Fragment frag)
	{
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

		tx.replace(R.id.layout_fragment_container, frag);
		tx.commit();	
	}

}
