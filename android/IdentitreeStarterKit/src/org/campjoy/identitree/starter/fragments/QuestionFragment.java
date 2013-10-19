package org.campjoy.identitree.starter.fragments;

import java.util.ArrayList;

import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.Question;
import org.campjoy.identitree.starter.model.QuestionModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

	private TextView firstTextView;
	private TextView secondTextView;
	
	private ImageView firstImageView;
	private ImageView secondImageView;
	
	private ArrayList<Question> questions;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_question, container, false);
		firstTextView = (TextView) getView().findViewById(R.id.text_first_question);
		secondTextView = (TextView) getView().findViewById(R.id.text_second_question);
		
		firstImageView = (ImageView) getView().findViewById(R.id.image_first_question);
		secondImageView = (ImageView) getView().findViewById(R.id.image_second_question);
		
		QuestionModel.getInstance().initInstance(getActivity());
		questions = QuestionModel.getInstance().getQuestions();
		
		firstTextView.setText(questions.get(0).getText());
		secondTextView.setText(questions.get(1).getText());
		return view;
	}
	
	
	
	
}
