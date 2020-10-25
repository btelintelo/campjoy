package org.campjoy.identitree.starter2020.activities;

import org.campjoy.identitree.starter2020.FragmentActivityBase;
import org.campjoy.identitree.starter2020.R;
import org.campjoy.identitree.starter2020.model.GlossaryAdapter;
import org.campjoy.identitree.starter2020.model.GlossaryModel;

import android.os.Bundle;
import android.widget.ListView;

public class GlossaryActivity extends FragmentActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glossary);
		
		GlossaryModel glossary = GlossaryModel.loadInstance(getApplicationContext());
		GlossaryAdapter adapter = new GlossaryAdapter(getApplicationContext(), glossary);
		
		ListView termListView = (ListView) findViewById(R.id.glossary_list);
		termListView.setAdapter(adapter);
	}
}
