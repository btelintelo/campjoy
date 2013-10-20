package org.campjoy.identitree.starter.activities;

import org.campjoy.identitree.starter.FragmentActivityBase;
import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.fragments.QuestionFragment;

import android.os.Bundle;
import android.view.Menu;

public class QuestionActivity extends FragmentActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int id = 0;		
		QuestionFragment questionFragment = new QuestionFragment();
		
		Bundle bundle = new Bundle();
		bundle.putInt("ID", id);
		
		questionFragment.setArguments(bundle);
		startFragment(questionFragment, String.valueOf(id));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

}
