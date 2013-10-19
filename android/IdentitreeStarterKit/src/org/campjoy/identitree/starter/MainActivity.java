package org.campjoy.identitree.starter;

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

	private Button questionButton;
	private ImageView treeBrowserImgView;
	private ImageView aboutImgView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Intent intent = new Intent(this, QuestionActivity.class);
		questionButton = (Button) findViewById(R.id.button_question);
		questionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent);
			}
		});
		
		
//		final Intent about = new Intent(this, AboutActivity.class);
//		aboutImgView = (ImageView) findViewById(R.id.imageAbout);
//		aboutImgView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(about);
//			}
//		});
//		
//		final Intent treeBrowser = new Intent(this, TreeInfoActivity.class);
//		treeBrowserImgView = (ImageView) findViewById(R.id.imageTreeBrowser);
//		treeBrowserImgView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(treeBrowser);
//			}
//		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
