package org.campjoy.identitree.starter.activities;

import org.campjoy.identitree.starter.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class QuestionActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_question);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

}
