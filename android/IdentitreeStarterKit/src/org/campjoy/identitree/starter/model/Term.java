package org.campjoy.identitree.starter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Term {

	String name, description, image;

	public Term(JSONObject term) {
		try {
			name = term.getString("name");
			description = term.getString("description");
			image = term.getString("image");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return name + ", " + description + ", " + image;
	}
}
