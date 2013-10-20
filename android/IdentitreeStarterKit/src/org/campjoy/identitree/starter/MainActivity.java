package org.campjoy.identitree.starter;

import org.campjoy.identitree.starter.activities.GlossaryActivity;
import org.campjoy.identitree.starter.activities.QuestionActivity;
import org.campjoy.identitree.starter.activities.TreeInfoActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

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
				startActivity(intent);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
