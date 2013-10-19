package org.campjoy.identitree.starter.model;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class TreeModel extends BaseModel {
	private static final String LOG_TAG = TreeModel.class.getSimpleName();

	private HashMap<String, Tree> trees = new HashMap<String, Tree>();

	public TreeModel(Context applicationContext)
	{
		super(applicationContext, "trees.json");
	}
	
	@Override
	protected void parseJson(String json)
	{
		try {
			JSONObject readableJson = new JSONObject(json);
			JSONArray trees = readableJson.getJSONArray("trees");
			
			for(int i = 0; i < trees.length(); i++)
			{
				JSONObject oneTree = trees.getJSONObject(i);
				Tree t = new Tree(oneTree);
				this.trees.put(t.getId(), t);
			}
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Failed to load trees from json");
		}
	}
}
