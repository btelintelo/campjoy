package org.campjoy.identitree.starter;

import org.campjoy.identitree.starter.activities.GlossaryActivity;
import org.campjoy.identitree.starter.activities.QuestionActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("IdentiTree");
		setContentView(R.layout.activity_main_tab);
		initTabs();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main_tab, menu);
//		return true;
//	}
	
	private void initTabs() {
		TabHost host = getTabHost();
		// following setDividerDrawable call must occur before addTab call in order to be effective.
		// host.getTabWidget().setDividerDrawable(android.R.drawable.divider_horizontal_dark);

    	host.addTab(host.newTabSpec("Home").setIndicator(createCustomTabView("Home", R.drawable.home))
    			.setContent(new Intent(this, MainActivity.class))
    			);

    	host.addTab(host.newTabSpec("Identify").setIndicator(createCustomTabView("Identify", R.drawable.search))
    			.setContent(new Intent(this, QuestionActivity.class))
    			);

	    	host.addTab(host.newTabSpec("Glossary").setIndicator(createCustomTabView("Glossary", R.drawable.book))
    			.setContent(new Intent(this, GlossaryActivity.class))
    			);

    	host.addTab(host.newTabSpec("About").setIndicator(createCustomTabView("About", R.drawable.info))
    			.setContent(new Intent(this, AboutActivity.class))
    			);

    
    }
	
private int firstIconHeight;
	
	private View createCustomTabView(String tabName, int icon) {

		View tabView = getLayoutInflater().inflate(R.layout.tabs, null);
		TextView label = (TextView) tabView.findViewById(R.id.tab_name);
		label.setText(tabName);
		Drawable d = getResources().getDrawable(icon);
		
		// If the icon heights are not uniform, then the text labels on the tabs
		// will not be lined up with each other.  So we just take the height of the
		// first image and apply it to the rest.  Can't hard code a number, like we
		// had been doing, since that doesn't work across pixel densities.
		if (firstIconHeight == 0) {
			firstIconHeight = d.getIntrinsicHeight();
		}
		d.setBounds(0, 0, d.getIntrinsicWidth(), firstIconHeight);
		label.setCompoundDrawables(null, d, null, null);

		return tabView;
	}

}
