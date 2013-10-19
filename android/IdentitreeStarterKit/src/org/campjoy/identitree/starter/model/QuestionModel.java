package org.campjoy.identitree.starter.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class QuestionModel {

	private ArrayList<Question> questions = new ArrayList<Question>();
	
	public QuestionModel(final Context applicationContext) {
		Thread loader = new Thread() {
			@Override
			public void run() {
				loadQuestions(applicationContext);
			}
		};

		loader.start();
	}

	private void loadQuestions(Context applicationContext) {
		String questionJson = readJsonFromFile(applicationContext);
		parseJson(questionJson);
	}

	private String readJsonFromFile(Context applicationContext) {
		InputStream is = null;
		String info = null;

		try {
			is = applicationContext.getAssets().open("questions.json");
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder builder = new StringBuilder();

			String line = reader.readLine();
			while (line != null) {
				builder.append(line);
				line = reader.readLine();
			}

			info = builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("QuestionModel", "readJsonFromFile(Context applicationContext)");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				Log.d("QuestionModel", "readJsonFromFile(Context applicationContext)");
			}
		}
		return info;
	}

	private void parseJson(String json) {
		try {
			JSONObject readableJson = new JSONObject(json);
			JSONArray questions = readableJson.getJSONArray("questions");

			for (int i = 0; i < questions.length(); i++) {
				JSONObject oneQuestion = questions.getJSONObject(i);
				Question q = new Question(oneQuestion);
				this.questions.add(q);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			Log.d("QuestionModel", "parseJson(String json)");
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Question q : questions) {
			sb.append(q.toString());
		}

		return sb.toString();
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}
}
