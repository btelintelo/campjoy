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
	private HashMap<String, Tree> trees;

	private TreeModel(Context applicationContext) {
		super(applicationContext, "trees.json");
	}

	public static TreeModel loadInstance(Context applicationContext) {
		if (instance == null) {
			instance = new TreeModel(applicationContext);
		}
		return instance;
	}

	public static TreeModel getInstance() {
		if (instance == null) {
			throw new IllegalStateException(
					"You must call loadInstance before you can call getInstance");
		}
		return instance;
	}

	@Override
	protected void parseJson(String json) {
		if (trees == null) {
			trees = new HashMap<String, Tree>();
		}

		try {
			JSONObject readableJson = new JSONObject(json);
			JSONArray trees = readableJson.getJSONArray("trees");

			for (int i = 0; i < trees.length(); i++) {
				JSONObject oneTree = trees.getJSONObject(i);
				Tree t = new Tree(oneTree);
				this.trees.put(t.getId(), t);
			}
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Failed to load trees from json");
		}
	}

	public Tree getTreeById(String id) {
		return trees.get(id);
	}
}
