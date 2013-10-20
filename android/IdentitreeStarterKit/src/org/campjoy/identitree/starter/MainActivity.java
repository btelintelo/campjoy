package org.campjoy.identitree.starter;

import org.campjoy.identitree.starter.activities.QuestionActivity;
import android.os.Bundle;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends FragmentActivityBase {

	private ImageView questionButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Intent intent = new Intent(this, QuestionActivity.class);
		questionButton = (ImageView) findViewById(R.id.button_question);
		questionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				startActivity(intent);
				getCJApplication().setStartingIndex(0);
				LocalActivityManager manager = ((MainTabActivity) getParent()).getLocalActivityManager();
				manager.destroyActivity("Identify", true);
				manager.startActivity("Identify", new Intent(((MainTabActivity) getParent()), QuestionActivity.class));
				((MainTabActivity) getParent()).getTabHost().setCurrentTab(1);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
