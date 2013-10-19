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

public class TreeModel {

	private ArrayList<Tree> trees = new ArrayList<Tree>();
	public TreeModel(final Context applicationContext) {
		Thread loader = new Thread(){
			@Override
			public void run()
			{
				loadTrees(applicationContext);
			}
		};
		
		loader.start();
	}
	
	private void loadTrees(Context applicationContext)
	{
		String treeJson = readJsonFromFile(applicationContext);
		parseJson(treeJson);
	}
	
	private String readJsonFromFile(Context applicationContext)
	{
		InputStream is = null;
		String info = null;
		try {
			is = applicationContext.getAssets().open("trees.json");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return info;
	}
	
	private void parseJson(String json)
	{
		try {
			JSONObject readableJson = new JSONObject(json);
			JSONArray trees = readableJson.getJSONArray("trees");
			
			for(int i = 0; i < trees.length(); i++)
			{
				JSONObject oneTree = trees.getJSONObject(i);
				Tree t = new Tree(oneTree);
				this.trees.add(t);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Tree t : trees)
		{
			sb.append(t.toString());
		}
		return sb.toString();
	}
}
