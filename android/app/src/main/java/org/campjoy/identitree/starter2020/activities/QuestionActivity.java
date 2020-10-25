package org.campjoy.identitree.starter2020.activities;

import org.campjoy.identitree.starter2020.CJApplication;
import org.campjoy.identitree.starter2020.FragmentActivityBase;
import org.campjoy.identitree.starter2020.R;
import org.campjoy.identitree.starter2020.fragments.QuestionFragment;

import android.os.Bundle;
import android.view.Menu;

public class QuestionActivity extends FragmentActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CJApplication cjApplication = getCJApplication();
		int id = cjApplication.getStartingIndex();
		String path = "Path: ";
		QuestionFragment questionFragment = new QuestionFragment();
		
		Bundle bundle = new Bundle();
		bundle.putInt("ID", id);
		bundle.putString("Path", path);
		
		questionFragment.setArguments(bundle);
		loadFragment(questionFragment);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

}
