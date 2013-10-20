package org.campjoy.identitree.starter.fragments;

import java.io.IOException;
import java.io.InputStream;

import org.campjoy.identitree.starter.FragmentActivityBase;
import org.campjoy.identitree.starter.FragmentBase;
import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.QuestionModel;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionFragment extends FragmentBase {

	private TextView firstTextView;
	private TextView secondTextView;
	private TextView pathTextView;
	
	private ImageView firstImageView;
	private ImageView secondImageView;
	
	private String firstText = "";
	private String secondText = "";
	
	private String firstImagePath = "";
	private String secondImagePath = "";
	
	private String pathsTraversed = "";
	
	private int id;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_question, container, false);
		
		firstTextView = (TextView) view.findViewById(R.id.text_first_question);
		secondTextView = (TextView) view.findViewById(R.id.text_second_question);
		pathTextView = (TextView) view.findViewById(R.id.text_path);
		
		firstImageView = (ImageView) view.findViewById(R.id.image_first_question);
		secondImageView = (ImageView) view.findViewById(R.id.image_second_question);
		
		RelativeLayout firstLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout1);
		RelativeLayout secondLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout2);
		
		Bundle bundle = this.getArguments(); 
		id = bundle.getInt("ID");
		pathsTraversed = bundle.getString("Path");
		pathsTraversed += String.valueOf((id + 1)) + ", ";
		
		final QuestionModel model = new QuestionModel(getActivity().getApplicationContext());
		
		firstText = model.getQuestions().get(id).getChoice1().getText();
		secondText = model.getQuestions().get(id).getChoice2().getText();
		
		firstTextView.setText(firstText);
		secondTextView.setText(secondText);
		pathTextView.setText(pathsTraversed);
		
		firstImagePath = String.valueOf((id + 1)) + "a.png";
		secondImagePath = String.valueOf((id + 1)) + "b.png";
		
		InputStream ims = null;
		try {
			ims = getActivity().getAssets().open(firstImagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    Drawable d = Drawable.createFromStream(ims, null);
	    firstImageView.setImageDrawable(d);
	    
	    InputStream ims1 = null;
		try {
			ims1 = getActivity().getAssets().open(secondImagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    Drawable d1 = Drawable.createFromStream(ims1, null);
	    secondImageView.setImageDrawable(d1);
		
		firstLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String stringNextId = model.getQuestions().get(id).getChoice1().getNextId();
				int nextId = -1;
				
				if(!stringNextId.equals("")) {
					// Need to add Paths from ids
					nextId = Integer.parseInt(stringNextId);
					//Accounts for array start index
					nextId=nextId-1;
					
					QuestionFragment questionFragment = new QuestionFragment();
					Bundle bundle = new Bundle();
					bundle.putInt("ID", nextId);
					bundle.putString("Path", pathsTraversed);
					questionFragment.setArguments(bundle);
					
					((FragmentActivityBase) getActivity()).startFragment(questionFragment, String.valueOf(nextId));
				} else {
					// Executes when treeid = ""
					// Method to start Treeinfo Fragment
					// TODO INSERT TREEINFO SCREEN CALL HERE
					TreeInfoFragment treeInfoFragment = new TreeInfoFragment();
					String treeId= model.getQuestions().get(id).getChoice1().getTreeId();
					Bundle bundle = new Bundle();
					bundle.putString("TreeId", treeId);
					treeInfoFragment.setArguments(bundle);
					((FragmentActivityBase) getActivity()).startFragment(treeInfoFragment,treeId);
				}
			}
		});
		
		secondLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String stringNextId = model.getQuestions().get(id).getChoice2().getNextId();
				if(!stringNextId.equals(""))
				{
					int nextId = Integer.parseInt(stringNextId);
					//				nextId--;
					nextId= nextId-1;

					QuestionFragment questionFragment = new QuestionFragment();
					Bundle bundle = new Bundle();
					bundle.putInt("ID", nextId);
					bundle.putString("Path", pathsTraversed);
					questionFragment.setArguments(bundle);

					((FragmentActivityBase) getActivity()).startFragment(questionFragment, String.valueOf(nextId));
				}
				else
				{
					TreeInfoFragment treeInfoFragment = new TreeInfoFragment();
					String treeId= model.getQuestions().get(id).getChoice2().getTreeId();
					Bundle bundle = new Bundle();
					bundle.putString("TreeId", treeId);
					treeInfoFragment.setArguments(bundle);
					((FragmentActivityBase) getActivity()).startFragment(treeInfoFragment,treeId);
				}
			}
		});

		return view;
	}
}
