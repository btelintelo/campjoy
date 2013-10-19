package org.campjoy.identitree.starter.model;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class GlossaryModel extends BaseModel {

	private HashMap<String, Term> terms = new HashMap<String, Term>();

	public GlossaryModel(final Context applicationContext)
	{
		super(applicationContext, "glossary.json");
	}
	
	protected void parseJson(String json)
	{
		try {
			JSONObject readableJson = new JSONObject(json);
			JSONArray terms = readableJson.getJSONArray("terms");
			
			for(int i = 0; i < terms.length(); i++)
			{
				JSONObject oneTerm = terms.getJSONObject(i);
				Term t = new Term(oneTerm);
				this.terms.put(t.getName(), t);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
