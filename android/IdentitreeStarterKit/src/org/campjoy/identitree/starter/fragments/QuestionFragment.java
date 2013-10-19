package org.campjoy.identitree.starter.fragments;

import java.io.IOException;
import java.io.InputStream;

import org.campjoy.identitree.starter.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuestionFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_question, container, false);
		return view;
	}
	
	public String getJSON() {
		String json = null;
        
		try {
            InputStream is = getActivity().getAssets().open("file_name.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
		
        return json;
	}
	
	public void parseJSON() {
		String json = getJSON();
	}
}
