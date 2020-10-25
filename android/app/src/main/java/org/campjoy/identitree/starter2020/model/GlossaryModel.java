package org.campjoy.identitree.starter2020.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class GlossaryModel extends BaseModel {

	private HashMap<String, Term> termMap;
	private String[] terms;
	private HashMap<Pattern, String> termRegex; 
	
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

	public Term getTermByLink(String link)
	{
		Term result = null;
		
		for(Pattern re : termRegex.keySet())
		{
			if(re.matcher(link).matches())
			{
				result = getTermById(termRegex.get(re));
				break;
			}
		}
		
		return result;
	}
	
	public ArrayList<Matcher> findMatches(String sentence)
	{
		initializeRegex();
		
		ArrayList<Matcher> matches = new ArrayList<Matcher>();
		for(Pattern re : termRegex.keySet())
		{
			Matcher matcher = re.matcher(sentence);
			if (matcher.find())
			{
				matches.add(matcher);
			}
		}
		
		ArrayList<Matcher> validMatches = processMatches(matches);
		
		return validMatches;
	}
	
	private void initializeRegex()
	{
		if(termRegex != null)
		{
			return;
		}

		if (terms == null)
		{
			getTerms();
		}
		
		termRegex = new HashMap<Pattern, String>();
		for(int i = 0; i < terms.length; i++)
		{
			String curTerm = terms[i].toLowerCase();
			Pattern p = Pattern.compile(curTerm + "\\w*", Pattern.CASE_INSENSITIVE);
			termRegex.put(p, curTerm);
		}
	}
	
	private ArrayList<Matcher> processMatches(ArrayList<Matcher> matches)
	{
		ArrayList<Matcher> validMatches = new ArrayList<Matcher>();
		
		for(int i = 0; i < matches.size(); i++)
		{
			for(int j = 0; j < matches.size(); j++)
			{
				if (i == j)
				{
					Matcher m = matches.get(i);
					if(!validMatches.contains(m))
					{
						validMatches.add(m);
					}
					continue;
				}
				
				Matcher x = matches.get(i);
				Matcher y = matches.get(j);
				
				int x1 = x.start();
				int x2 = x.end();
				
				int y1 = y.start();
				int y2 = y.end();
				
				if(doRangesOverlap(x1, x2, y1, y2))
				{
					if((x1-x2)>(y1-y2))
					{
						if(!validMatches.contains(x))
						{
							validMatches.add(x);
						}
					}
					else
					{
						if(!validMatches.contains(y))
						{
							validMatches.add(y);
						}
					}
				}
			}
		}
		
		return validMatches;
	}
	
	private boolean doRangesOverlap(int x1, int x2, int y1, int y2)
	{
		return x1 <= y2 && y1 <= x2;
	}
}
