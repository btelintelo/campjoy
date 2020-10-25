package org.campjoy.identitree.starter2020.activities;

import org.campjoy.identitree.starter2020.FragmentActivityBase;
import org.campjoy.identitree.starter2020.fragments.TreeInfoFragment;

import android.os.Bundle;



public class TreeInfoActivity extends FragmentActivityBase {
	private TreeInfoFragment treeInfoFragment;
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		
		
		treeInfoFragment = new TreeInfoFragment();
		
		Bundle bundle = new Bundle();
		bundle.putString("TreeId", "american_basswood");
		treeInfoFragment.setArguments(bundle);
		loadFragment(treeInfoFragment);
	}

}
