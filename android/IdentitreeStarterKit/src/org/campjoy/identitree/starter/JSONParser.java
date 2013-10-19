package org.campjoy.identitree.starter;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class JSONParser {

	private Context context;
	private String path;
	
	private static final String TAG_CHOICES = "choices";
	
	public JSONParser(Context context, String path) {
		this.context = context;
		this.path = path;
	}
	
	public String getJSON() {
		String json = null;
        
		try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
		
        return json;
	}
	
	public void parseJSON() {
		String json = getJSON();
		
		try {
			JSONObject jObject = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
