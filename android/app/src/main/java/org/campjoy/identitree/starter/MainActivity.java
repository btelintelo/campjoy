package org.campjoy.identitree.starter;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import org.campjoy.identitree.starter.activities.QuestionActivity;

public class MainActivity extends FragmentActivityBase {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Intent intent = new Intent(this, QuestionActivity.class);
		ImageView questionButton = findViewById(R.id.button_question);
		questionButton.setOnClickListener(v -> {
			getCJApplication().setStartingIndex(0);
			LocalActivityManager manager = ((MainTabActivity) getParent()).getLocalActivityManager();
			manager.destroyActivity("Identify", true);
			manager.startActivity("Identify", new Intent(getParent(), QuestionActivity.class));
			((MainTabActivity) getParent()).getTabHost().setCurrentTab(1);
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
