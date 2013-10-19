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

public class GlossaryModel {

	private ArrayList<Term> terms = new ArrayList<Term>();
	public GlossaryModel(final Context applicationContext)
	{
		Thread loader = new Thread(){
			@Override
			public void run()
			{
				loadModel(applicationContext);
			}
		};
		
		loader.start();
	}
	
	private void loadModel(Context applicationContext)
	{
		String termJson = readJsonFromFile(applicationContext);
		parseJson(termJson);
	}

	private String readJsonFromFile(Context applicationContext)
	{
		InputStream is = null;
		String info = null;
		try {
			is = applicationContext.getAssets().open("glossary.json");
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
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return info;
	}
	
	private void parseJson(String json)
	{
		try {
			JSONObject readableJson = new JSONObject(json);
			JSONArray terms = readableJson.getJSONArray("terms");
			
			for(int i = 0; i < terms.length(); i++)
			{
				JSONObject oneTerm = terms.getJSONObject(i);
				Term t = new Term(oneTerm);
				this.terms.add(t);
				System.out.println(t);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
