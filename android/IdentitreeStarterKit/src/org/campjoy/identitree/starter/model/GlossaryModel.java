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
	
	public boolean hasTermThatStartsWith(String term)
	{
		term = term.toLowerCase();
		for(String key : terms.keySet())
		{
			if(key.toLowerCase().startsWith(term))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean hasTerm(String term)
	{
		term = term.toLowerCase();
		for(String key : terms.keySet())
		{
			if(key.toLowerCase().equalsIgnoreCase(term))
			{
				return true;
			}
		}
		return false;
	}
}
