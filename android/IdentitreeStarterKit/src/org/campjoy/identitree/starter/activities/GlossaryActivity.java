package org.campjoy.identitree.starter.activities;

import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.GlossaryAdapter;
import org.campjoy.identitree.starter.model.GlossaryModel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class GlossaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glossary);
		
		GlossaryModel glossary = new GlossaryModel(getApplicationContext());
		GlossaryAdapter adapter = new GlossaryAdapter(getApplicationContext(), glossary);
		
		ListView termListView = (ListView) findViewById(R.id.glossary_list);
		termListView.setAdapter(adapter);
	}
}
