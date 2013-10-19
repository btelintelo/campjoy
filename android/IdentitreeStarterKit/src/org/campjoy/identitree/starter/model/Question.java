package org.campjoy.identitree.starter.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Question {

	private String id, text, nextid, treeid;
	private ArrayList<String[]> choice = new ArrayList<String[]>();
	
	public Question(JSONObject question) {
		parseQuestion(question);
	}

	private void parseQuestion(JSONObject question) {
		try {
			id = question.getString("id");
			JSONArray choices = question.getJSONArray("choices");
			parseTableData(choices);
		} catch (JSONException e) {
			e.printStackTrace();
			Log.d("Question", "parseQuestion(JSONObject question)");
		}

	}

	private void parseTableData(JSONArray choices) {
		try {
			JSONObject menuObject = choices.getJSONObject(0);
			text = menuObject.getString("text");
			nextid = menuObject.getString("nextid");
			treeid = menuObject.getString("treeid");
			choice.add(new String[] {text, nextid, treeid});
			
			menuObject = choices.getJSONObject(1);
			text = menuObject.getString("text");
			nextid = menuObject.getString("nextid");
			treeid = menuObject.getString("treeid");
			choice.add(new String[] {text, nextid, treeid});
		} catch (JSONException e1) {
			e1.printStackTrace();
			Log.d("Question", "Failed");
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(id);
		result.append(", ");
		result.append(text);
		result.append(", ");
		result.append(nextid);
		result.append(", ");
		result.append(treeid);
		result.append("\n");
		
		return result.toString();
	}

	public ArrayList<String[]> getChoice() {
		return choice;
	}
}
