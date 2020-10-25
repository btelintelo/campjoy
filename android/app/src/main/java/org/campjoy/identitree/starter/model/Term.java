package org.campjoy.identitree.starter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Term {

	String name, description, imageLocation;

	public Term(JSONObject term) {
		try {
			name = term.getString("name");
			description = term.getString("description");
			imageLocation = term.getString("image");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	@Override
	public String toString() {
		return name + ", " + description + ", " + imageLocation;
	}
}
