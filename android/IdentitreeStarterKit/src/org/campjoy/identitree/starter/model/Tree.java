package org.campjoy.identitree.starter.model;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tree {

	private String id, name, description, scientificName, family;
	private HashMap<String, String> table = new HashMap<String, String>();

	public Tree(JSONObject tree) {
		parseTree(tree);
	}

	private void parseTree(JSONObject tree) {
		try {
			id = tree.getString("id");
			name = tree.getString("name");
			description = tree.getString("description");
			scientificName = tree.getString("sciname");
			family = tree.getString("family");

			JSONArray tableData = tree.getJSONArray("tableData");
			parseTableData(tableData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parseTableData(JSONArray tableData) {
		for (int i = 0; i < tableData.length(); i++) {
			JSONObject row;
			try {
				row = tableData.getJSONObject(i);
				JSONArray keys = row.names();
				for (int j = 0; j < keys.length(); j++) {
					String key = keys.getString(j);
					String value = row.getString(key);

					table.put(key, value);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getScientificName() {
		return scientificName;
	}

	public String getFamily() {
		return family;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(id);
		result.append(", ");
		result.append(name);
		result.append(", ");
		result.append(description);
		result.append(", ");
		result.append(scientificName);
		result.append(", ");
		result.append(family);
		result.append('\n');

		for (String key : table.keySet()) {
			result.append(key).append(": ").append(table.get(key)).append('\n');
		}
		return result.toString();
	}
}
