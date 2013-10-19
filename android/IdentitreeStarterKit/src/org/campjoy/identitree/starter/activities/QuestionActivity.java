package org.campjoy.identitree.starter.activities;

import org.campjoy.identitree.starter.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class QuestionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.question, menu);
		return true;
	}

}
