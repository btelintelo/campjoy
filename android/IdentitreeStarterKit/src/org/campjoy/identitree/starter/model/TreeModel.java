package org.campjoy.identitree.starter.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class TreeModel extends BaseModel {
	private static final String LOG_TAG = TreeModel.class.getSimpleName();

	private static TreeModel instance;
	private HashMap<String, Tree> trees = new HashMap<String, Tree>();

	public TreeModel(Context applicationContext)
	{
		super(applicationContext, "trees.json");
	}
	
	public static TreeModel getInstance(Context applicationContext) {
		if(instance ==null)
		{
			instance = new TreeModel(applicationContext);
		}
		return instance;
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
	
	public Tree getTreeById(int i)
	{
		Tree result = null;
		
		Iterator<Entry<String, Tree>> it = trees.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	Map.Entry<String, Tree> treePair = (Map.Entry<String, Tree>)it.next();
	    	if(treePair.getKey().equalsIgnoreCase(String.valueOf(i)))
	    	{
	    		return treePair.getValue();
	    	}
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
		return result;
		
	}
}
