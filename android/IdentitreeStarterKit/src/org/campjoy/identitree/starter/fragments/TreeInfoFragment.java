package org.campjoy.identitree.starter.fragments;

import org.campjoy.identitree.starter.FragmentBase;
import org.campjoy.identitree.starter.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TreeInfoFragment extends FragmentBase{

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tree_info, container, false);
	}
}
