package org.campjoy.identitree.starter.activities;

import org.campjoy.identitree.starter.FragmentActivityBase;
import org.campjoy.identitree.starter.fragments.TreeInfoFragment;

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
