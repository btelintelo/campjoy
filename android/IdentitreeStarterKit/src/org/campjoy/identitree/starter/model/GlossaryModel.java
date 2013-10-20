package org.campjoy.identitree.starter.model;

import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class GlossaryModel extends BaseModel {

	private HashMap<String, Term> termMap;
	private String[] terms;
	
	private static GlossaryModel instance = null;

	public static GlossaryModel loadInstance(Context applicationContext)
	{
		if (instance == null)
		{
			instance = new GlossaryModel(applicationContext);
		}

		return instance;
	}
	
	public static GlossaryModel getInstance()
	{
		if (instance == null)
		{
			throw new IllegalStateException("You must load the model before you call getInstance");
		}
		return instance;
	}

	private GlossaryModel(final Context applicationContext) {
		super(applicationContext, "glossary.json");
	}

	protected void parseJson(String json) {
		try {
			JSONObject readableJson = new JSONObject(json);
			JSONArray terms = readableJson.getJSONArray("terms");

			termMap = new HashMap<String, Term>();

			for (int i = 0; i < terms.length(); i++) {
				JSONObject oneTerm = terms.getJSONObject(i);
				Term t = new Term(oneTerm);
				this.termMap.put(t.getName().toLowerCase(), t);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int count() {
		return termMap.size();
	}

	public String[] getTerms() {
		if(terms == null)
		{
			terms = new String[termMap.size()];
			termMap.keySet().toArray(terms);
			Arrays.sort(terms);
		}
		return terms;
	}

	public Term getTermById(String id) {
		return termMap.get(id);
	}

	public boolean hasTermThatStartsWith(String term) {
		term = term.toLowerCase();
		for (String key : termMap.keySet()) {
			if (key.toLowerCase().startsWith(term)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasTerm(String term) {
		term = term.toLowerCase();
		for (String key : termMap.keySet()) {
			if (key.toLowerCase().equalsIgnoreCase(term)) {
				return true;
			}
		}
		return false;
	}
}
